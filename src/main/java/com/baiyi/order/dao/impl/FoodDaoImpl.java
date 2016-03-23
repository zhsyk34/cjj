package com.baiyi.order.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.baiyi.order.dao.FoodDao;
import com.baiyi.order.model.Food;
import com.baiyi.order.model.Material;
import com.baiyi.order.model.Taste;
import com.baiyi.order.model.Type;
import com.baiyi.order.util.ValidateUtil;
import com.baiyi.order.vo.FoodVO;

@Repository
public class FoodDaoImpl extends CommonsDaoImpl<Food> implements FoodDao {

	@Override
	public void delete(List<Food> foods) {
		super.delete(foods);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Food find(String name) {
		List<Food> foodList = (List<Food>) hibernateTemplate.find("from Food as food where food.name = ?", name);
		if (CollectionUtils.isEmpty(foodList)) {
			return null;
		}
		return foodList.get(0);
	}

	@Override
	public List<Food> findList(String name, Integer typeId, Integer userId) {
		return findList(name, typeId, userId, null, null, -1, -1);
	}

	@Override
	public List<Food> findList(String name, Integer typeId, Integer userId, String sort, String order, int pageNo, int pageSize) {
		StringBuffer queryString = new StringBuffer("from Food as food where 1 = 1");
		Map<String, Object> map = new HashMap<>();

		if (StringUtils.isNotBlank(name)) {
			queryString.append(" and food.name like :name");
			map.put("name", "%" + name + "%");
		}
		if (ValidateUtil.isPK(typeId)) {
			queryString.append(" and food.typeId = :typeId");
			map.put("typeId", typeId);
		}
		if (ValidateUtil.isPK(userId)) {
			queryString.append(" and food.userId = :userId");
			map.put("userId", userId);
		}
		if (StringUtils.isNotBlank(sort) && StringUtils.isNotBlank(order)) {
			queryString.append(" order by food." + sort + " " + order);
		}
		return super.findList(queryString.toString(), (pageNo - 1) * pageSize, pageSize, map);
	}

	@Override
	public int count(String name, Integer typeId, Integer userId) {
		StringBuffer queryString = new StringBuffer("select count(*) from Food as food where 1 = 1");
		Map<String, Object> map = new HashMap<>();

		if (StringUtils.isNotBlank(name)) {
			queryString.append(" and food.name like :name");
			map.put("name", "%" + name + "%");
		}
		if (ValidateUtil.isPK(typeId)) {
			queryString.append(" and food.typeId = :typeId");
			map.put("typeId", typeId);
		}
		if (ValidateUtil.isPK(userId)) {
			queryString.append(" and food.userId = :userId");
			map.put("userId", userId);
		}
		return super.count(queryString.toString(), map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public FoodVO findVO(Integer id) {
		StringBuffer queryString = new StringBuffer("select food.*, type.name as typeName, material.path");
		queryString.append(" from Food as food left join Type as type on food.typeId = type.id");
		queryString.append(" left join Material as material on food.materialId = material.id");
		queryString.append(" where food.id = :id");

		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query = session.createSQLQuery(queryString.toString());
		query.setResultTransformer(Transformers.aliasToBean(FoodVO.class));
		query.setParameter("id", id);

		List<FoodVO> foodVOList = (List<FoodVO>) query.list();
		if (CollectionUtils.isNotEmpty(foodVOList)) {
			return foodVOList.get(0);
		}
		return null;
	}

	@Override
	public List<FoodVO> findVOList() {
		return this.findVOList(null, null, null);
	}

	@Override
	public List<FoodVO> findVOList(String name, Integer typeId, Integer userId) {
		return this.findVOList(name, typeId, userId, null, null, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<FoodVO> findVOList(String name, Integer typeId, Integer userId, String sort, String order, int pageNo, int pageSize) {
		StringBuffer queryString = new StringBuffer("select food.*, type.name as typeName, material.path");
		queryString.append(" from (Food as food left join Type as type on food.typeId = type.id)");
		queryString.append(" left join Material as material on food.materialId = material.id");
		queryString.append(" where 1 = 1");

		Map<String, Object> map = new HashMap<>();

		if (StringUtils.isNotBlank(name)) {
			queryString.append(" and food.name like :name");
			map.put("name", "%" + name + "%");
		}

		if (ValidateUtil.isPK(typeId)) {
			queryString.append(" and food.typeId = :typeId");
			map.put("typeId", typeId);
		}

		if (ValidateUtil.isPK(userId)) {
			queryString.append(" and food.userId = :userId");
			map.put("userId", userId);
		}

		if (StringUtils.isNotBlank(sort) && StringUtils.isNotBlank(order)) {
			queryString.append(" order by food." + sort + " " + order);
		}

		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query = session.createSQLQuery(queryString.toString());
		query.setResultTransformer(Transformers.aliasToBean(FoodVO.class));
		query.setProperties(map);

		if (pageNo >= 0 && pageSize > 0) {
			query.setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize);
		}

		List<FoodVO> foodVOList = (List<FoodVO>) query.list();
		if (CollectionUtils.isNotEmpty(foodVOList)) {
			for (FoodVO foodVO : foodVOList) {
				Integer foodId = foodVO.getId();
				List<Taste> tasteList = this.findTasteList(foodId);
				foodVO.setTasteList(tasteList);
			}
		}
		return foodVOList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Material findMaterial(Integer id) {
		StringBuffer queryString = new StringBuffer("select material from Food as food, Material as material");
		queryString.append(" where food.materialId = material.id and food.id = ?");
		List<Material> materialList = (List<Material>) hibernateTemplate.find(queryString.toString(), id);
		if (CollectionUtils.isEmpty(materialList)) {
			return null;
		}
		return materialList.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Type findType(Integer id) {
		StringBuffer queryString = new StringBuffer("select type from Food as food, Type as type");
		queryString.append(" where food.typeId = type.id and food.id = ?");
		List<Type> typeList = (List<Type>) hibernateTemplate.find(queryString.toString(), id);
		if (CollectionUtils.isEmpty(typeList)) {
			return null;
		}
		return typeList.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Taste> findTasteList(Integer id) {
		StringBuffer queryString = new StringBuffer("select taste from Food as food, FoodTaste as foodTaste, Taste as taste");
		queryString.append(" where food.id = foodTaste.foodId and foodTaste.tasteId = taste.id and food.id = ?");
		return (List<Taste>) hibernateTemplate.find(queryString.toString(), id);
	}

}
