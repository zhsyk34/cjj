package com.baiyi.order.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.baiyi.order.dao.ActivityDao;
import com.baiyi.order.dao.FoodDao;
import com.baiyi.order.dao.FoodTasteDao;
import com.baiyi.order.dao.TemplateFoodDao;
import com.baiyi.order.dao.TerminalDao;
import com.baiyi.order.model.Activity;
import com.baiyi.order.model.Food;
import com.baiyi.order.model.FoodTaste;
import com.baiyi.order.model.TemplateFood;
import com.baiyi.order.model.Terminal;
import com.baiyi.order.service.FoodService;
import com.baiyi.order.util.EnumList.ActivityTypeEnum;
import com.baiyi.order.util.EnumList.TerminalTypeEnum;
import com.baiyi.order.util.ValidateUtil;
import com.baiyi.order.vo.FoodVO;

@Service
public class FoodServiceImpl implements FoodService {

	@Resource
	private FoodDao foodDao;
	@Resource
	private FoodTasteDao foodTasteDao;
	@Resource
	private TemplateFoodDao templateFoodDao;
	@Resource
	private ActivityDao activityDao;
	@Resource
	private TerminalDao terminalDao;

	@Override
	public void save(Food food, Integer[] tasteIds) {
		foodDao.save(food);
		Integer foodId = food.getId();

		if (ValidateUtil.isNotEmpty(tasteIds)) {
			for (Integer tasteId : tasteIds) {
				FoodTaste foodTaste = new FoodTaste();
				foodTaste.setFoodId(foodId);
				foodTaste.setTasteId(tasteId);
				foodTasteDao.save(foodTaste);
			}
		}

		List<Terminal> terminals = terminalDao.findList(null, TerminalTypeEnum.KITCHEN, null);
		if (CollectionUtils.isNotEmpty(terminals)) {
			for (Terminal terminal : terminals) {
				Activity activity = new Activity();
				activity.setKitchenId(terminal.getId());
				activity.setFoodId(foodId);
				activity.setType(ActivityTypeEnum.NORMAL);
				activityDao.save(activity);
			}
		}
	}

	@Override
	public void delete(Integer id) {
		foodDao.delete(id);

		List<FoodTaste> foodTastes = foodTasteDao.findList(id, null);
		foodTasteDao.delete(foodTastes);

		List<Activity> activities = activityDao.findList(null, id, null, null);
		activityDao.delete(activities);
	}

	@Override
	public void delete(Food food) {
		this.delete(food.getId());
	}

	@Override
	public void delete(Integer[] ids) {
		if (ValidateUtil.isNotEmpty(ids)) {
			for (Integer id : ids) {
				this.delete(id);
			}
		}
	}

	@Override
	public void delete(List<Food> foods) {
		if (CollectionUtils.isNotEmpty(foods)) {
			for (Food food : foods) {
				this.delete(food);
			}
		}
	}

	@Override
	public void update(Food food, Integer[] tasteIds) {
		foodDao.update(food);

		Integer foodId = food.getId();
		List<FoodTaste> foodTastes = foodTasteDao.findList(foodId, null);
		foodTasteDao.delete(foodTastes);

		if (ValidateUtil.isNotEmpty(tasteIds)) {
			for (Integer tasteId : tasteIds) {
				FoodTaste foodTaste = new FoodTaste();
				foodTaste.setFoodId(foodId);
				foodTaste.setTasteId(tasteId);
				foodTasteDao.save(foodTaste);
			}
		}
	}

	@Override
	public void merge(Food food, Integer[] tasteIds) {
		Integer foodId = food.getId();
		if (ValidateUtil.isPK(foodId)) {
			this.update(food, tasteIds);
		} else {
			this.save(food, tasteIds);
		}
	}

	@Override
	public Food find(Integer id) {
		return foodDao.find(id);
	}

	@Override
	public Food find(String name) {
		return foodDao.find(name);
	}

	@Override
	public List<Food> findList() {
		return foodDao.findList();
	}

	@Override
	public List<Food> findList(String name, Integer typeId, Integer userId) {
		return foodDao.findList(name, typeId, userId);
	}

	@Override
	public List<Food> findList(String name, Integer typeId, Integer userId, String sort, String order, int pageNo, int pageSize) {
		return foodDao.findList(name, typeId, userId, sort, order, pageNo, pageSize);
	}

	@Override
	public int count(String name, Integer typeId, Integer userId) {
		return foodDao.count(name, typeId, userId);
	}

	@Override
	public FoodVO findVO(Integer id) {
		return foodDao.findVO(id);
	}

	@Override
	public List<FoodVO> findVOList() {
		return foodDao.findVOList();
	}

	@Override
	public List<FoodVO> findVOList(String name, Integer typeId, Integer userId) {
		return foodDao.findVOList(name, typeId, userId);
	}

	@Override
	public List<FoodVO> findVOList(String name, Integer typeId, Integer userId, String sort, String order, int pageNo, int pageSize) {
		return foodDao.findVOList(name, typeId, userId, sort, order, pageNo, pageSize);
	}

	@Override
	public boolean exist(Integer id, String name) {
		Food food = this.find(name);
		if (food == null) {
			return false;
		}
		if (!ValidateUtil.isPK(id)) {
			return true;
		}
		return !food.getId().equals(id);
	}

	@Override
	public boolean relate(Integer id) {
		List<TemplateFood> templateFoodList = templateFoodDao.findList(null, id);
		return CollectionUtils.isNotEmpty(templateFoodList);
	}

	@Override
	public boolean relate(Integer[] ids) {
		for (Integer id : ids) {
			if (this.relate(id)) {
				return true;
			}
		}
		return false;
	}

}
