package com.baiyi.order.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baiyi.order.dao.MaterialDao;
import com.baiyi.order.model.Food;
import com.baiyi.order.model.Material;
import com.baiyi.order.service.MaterialService;
import com.baiyi.order.util.EnumList.MaterialTypeEnum;
import com.baiyi.order.util.ValidateUtil;

@Service
public class MaterialServiceImpl implements MaterialService {

	@Resource
	private MaterialDao materialDao;

	@Override
	public void save(Material material) {
		materialDao.save(material);
	}

	@Override
	public void delete(Integer id) {
		materialDao.delete(id);
	}

	@Override
	public void delete(Material material) {
		materialDao.delete(material);
	}

	@Override
	public void delete(Integer[] ids) {
		materialDao.delete(ids);
	}

	@Override
	public void delete(List<Material> materials) {
		materialDao.delete(materials);
	}

	@Override
	public void update(Material material) {
		materialDao.update(material);
	}

	@Override
	public void merge(Material material) {
		materialDao.merge(material);
	}

	@Override
	public Material find(Integer id) {
		return materialDao.find(id);
	}

	@Override
	public Material find(String name) {
		return materialDao.find(name);
	}

	@Override
	public List<Material> findList() {
		return materialDao.findList();
	}

	@Override
	public List<Material> findList(String name, MaterialTypeEnum type, Integer userId) {
		return materialDao.findList(name, type, userId);
	}

	@Override
	public List<Material> findList(String name, MaterialTypeEnum type, Integer userId, String sort, String order, int pageNo, int pageSize) {
		return materialDao.findList(name, type, userId, sort, order, pageNo, pageSize);
	}

	@Override
	public int count(String name, MaterialTypeEnum type, Integer userId) {
		return materialDao.count(name, type, userId);
	}

	@Override
	public boolean exist(Integer id, String name) {
		Material material = this.find(name);
		if (material == null) {
			return false;
		}
		if (!ValidateUtil.isPK(id)) {
			return true;
		}
		return !material.getId().equals(id);
	}

	@Override
	public boolean relate(Integer id) {
		Food food = materialDao.findFood(id);
		return food != null;
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
