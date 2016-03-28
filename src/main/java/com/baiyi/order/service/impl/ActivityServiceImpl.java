package com.baiyi.order.service.impl;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baiyi.order.dao.ActivityDao;
import com.baiyi.order.dao.FoodDao;
import com.baiyi.order.dao.TerminalDao;
import com.baiyi.order.model.Activity;
import com.baiyi.order.service.ActivityService;
import com.baiyi.order.util.EnumList.ActivityTypeEnum;
import com.baiyi.order.vo.ActivityVO;

@Service
public class ActivityServiceImpl implements ActivityService {

	@Resource
	private ActivityDao activityDao;
	@Resource
	private TerminalDao terminalDao;
	@Resource
	private FoodDao foodDao;

	@Override
	public void update(Activity activity) {
		activityDao.update(activity);
	}

	@Override
	public void update(Collection<Activity> activities) {
		for (Activity activity : activities) {
			activityDao.update(activity);
		}
	}

	@Override
	public Activity find(Integer id) {
		return activityDao.find(id);
	}

	@Override
	public Activity find(Integer kitchenId, Integer foodId) {
		return activityDao.find(kitchenId, foodId);
	}

	@Override
	public List<Activity> findList() {
		return activityDao.findList();
	}

	@Override
	public List<Activity> findList(Integer kitchenId, Integer foodId, ActivityTypeEnum type, Boolean used) {
		return activityDao.findList(kitchenId, foodId, type, used);
	}

	@Override
	public List<Activity> findList(String kitchen, String food, ActivityTypeEnum type, Boolean used) {
		return activityDao.findList(kitchen, food, type, used);
	}

	@Override
	public List<Activity> findList(String kitchen, String food, ActivityTypeEnum type, Boolean used, String sort, String order, int pageNo, int pageSize) {
		return activityDao.findList(kitchen, food, type, used, sort, order, pageNo, pageSize);
	}

	@Override
	public List<ActivityVO> findVOList(String kitchen, String food, ActivityTypeEnum type, Boolean used, String sort, String order, int pageNo, int pageSize) {
		return activityDao.findVOList(kitchen, food, type, used, sort, order, pageNo, pageSize);
	}

	@Override
	public int count(String kitchen, String food, ActivityTypeEnum type, Boolean used) {
		return activityDao.count(kitchen, food, type, used);
	}

	@Override
	public int countVO(String kitchen, String food, ActivityTypeEnum type, Boolean used) {
		return activityDao.countVO(kitchen, food, type, used);
	}

}
