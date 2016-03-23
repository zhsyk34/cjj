package com.baiyi.order.dao;

import java.util.List;

import com.baiyi.order.model.TemplateMaterial;

public interface TemplateMaterialDao {

	public void save(TemplateMaterial templateMaterial);

	public void delete(Integer id);

	public void delete(TemplateMaterial templateMaterial);

	public void delete(Integer[] ids);

	public void delete(List<TemplateMaterial> templateMaterials);

	public List<TemplateMaterial> findList();

	public List<TemplateMaterial> findList(Integer type, Integer templateId, Integer materialId);

	public void deleteByTemplateId(Integer templateId);

}
