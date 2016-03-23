package com.baiyi.order.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.baiyi.order.dao.TemplateDao;
import com.baiyi.order.dao.TemplateFoodDao;
import com.baiyi.order.dao.TemplateMarqueeDao;
import com.baiyi.order.dao.TemplateMaterialDao;
import com.baiyi.order.model.Template;
import com.baiyi.order.model.TemplateFood;
import com.baiyi.order.model.TemplateMarquee;
import com.baiyi.order.model.TemplateMaterial;
import com.baiyi.order.model.TerminalTemplate;
import com.baiyi.order.service.TemplateService;
import com.baiyi.order.util.EnumList.TemplateMaterialEnum;
import com.baiyi.order.util.EnumList.TemplateTypeEnum;
import com.baiyi.order.util.ValidateUtil;
import com.baiyi.order.vo.TemplateVO;

@Service
public class TemplateServiceImpl implements TemplateService {

	@Resource
	private TemplateDao templateDao;

	@Resource
	private TemplateFoodDao templateFoodDao;

	@Resource
	private TemplateMaterialDao templateMaterialDao;

	@Resource
	private TemplateMarqueeDao templateMarqueeDao;

	@Override
	public void save(Template template, Integer[] foodIds, Integer logoId, Integer numberId, Integer[] videoIds, Integer[] pictureIds, Integer[] marqueeIds) {
		templateDao.save(template);
		Integer templateId = template.getId();

		TemplateFood templateFood = null;
		TemplateMaterial templateMaterial = null;
		TemplateMarquee templateMarquee = null;

		if (ValidateUtil.isNotEmpty(foodIds)) {
			for (Integer foodId : foodIds) {
				templateFood = new TemplateFood();
				templateFood.setFoodId(foodId);
				templateFood.setTemplateId(templateId);
				templateFoodDao.save(templateFood);
			}
		}

		if (ValidateUtil.isPK(logoId)) {
			templateMaterial = new TemplateMaterial();
			templateMaterial.setMaterialId(logoId);
			templateMaterial.setTemplateId(templateId);
			templateMaterial.setType(TemplateMaterialEnum.LOGO);
			templateMaterialDao.save(templateMaterial);
		}

		if (ValidateUtil.isPK(numberId)) {
			templateMaterial = new TemplateMaterial();
			templateMaterial.setMaterialId(numberId);
			templateMaterial.setTemplateId(templateId);
			templateMaterial.setType(TemplateMaterialEnum.NUMBER);
			templateMaterialDao.save(templateMaterial);
		}

		if (ValidateUtil.isNotEmpty(videoIds)) {
			for (Integer materialId : videoIds) {
				templateMaterial = new TemplateMaterial();
				templateMaterial.setMaterialId(materialId);
				templateMaterial.setTemplateId(templateId);
				templateMaterial.setType(TemplateMaterialEnum.VIDEO);
				templateMaterialDao.save(templateMaterial);
			}
		}

		if (ValidateUtil.isNotEmpty(pictureIds)) {
			for (Integer materialId : pictureIds) {
				templateMaterial = new TemplateMaterial();
				templateMaterial.setMaterialId(materialId);
				templateMaterial.setTemplateId(templateId);
				templateMaterial.setType(TemplateMaterialEnum.PICTURE);
				templateMaterialDao.save(templateMaterial);
			}
		}

		if (ValidateUtil.isNotEmpty(marqueeIds)) {
			for (Integer marqueeId : marqueeIds) {
				templateMarquee = new TemplateMarquee();
				templateMarquee.setMarqueeId(marqueeId);
				templateMarquee.setTemplateId(templateId);
				templateMarqueeDao.save(templateMarquee);
			}
		}

	}

	@Override
	public void delete(Integer id) {
		templateFoodDao.deleteByTemplateId(id);
		templateMarqueeDao.deleteByTemplateId(id);
		templateMaterialDao.deleteByTemplateId(id);
		templateDao.delete(id);
	}

	@Override
	public void delete(Template template) {
		if (template == null) {
			return;
		}
		Integer id = template.getId();
		if (ValidateUtil.isPK(id)) {
			this.delete(id);
		}
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
	public void delete(List<Template> templates) {
		if (CollectionUtils.isNotEmpty(templates)) {
			for (Template template : templates) {
				this.delete(template);
			}
		}
	}

	@Override
	public void update(Template template, Integer[] foodIds, Integer logoId, Integer numberId, Integer[] videoIds, Integer[] pictureIds, Integer[] marqueeIds) {
		Integer templateId = template.getId();
		templateFoodDao.deleteByTemplateId(templateId);
		templateMarqueeDao.deleteByTemplateId(templateId);
		templateMaterialDao.deleteByTemplateId(templateId);

		templateDao.update(template);
		TemplateFood templateFood = null;
		TemplateMaterial templateMaterial = null;
		TemplateMarquee templateMarquee = null;

		if (ValidateUtil.isNotEmpty(foodIds)) {
			for (Integer foodId : foodIds) {
				templateFood = new TemplateFood();
				templateFood.setFoodId(foodId);
				templateFood.setTemplateId(templateId);
				templateFoodDao.save(templateFood);
			}
		}

		if (ValidateUtil.isPK(logoId)) {
			templateMaterial = new TemplateMaterial();
			templateMaterial.setMaterialId(logoId);
			templateMaterial.setTemplateId(templateId);
			templateMaterial.setType(TemplateMaterialEnum.LOGO);
			templateMaterialDao.save(templateMaterial);
		}

		if (ValidateUtil.isPK(numberId)) {
			templateMaterial = new TemplateMaterial();
			templateMaterial.setMaterialId(numberId);
			templateMaterial.setTemplateId(templateId);
			templateMaterial.setType(TemplateMaterialEnum.NUMBER);
			templateMaterialDao.save(templateMaterial);
		}

		if (ValidateUtil.isNotEmpty(videoIds)) {
			for (Integer materialId : videoIds) {
				templateMaterial = new TemplateMaterial();
				templateMaterial.setMaterialId(materialId);
				templateMaterial.setTemplateId(templateId);
				templateMaterial.setType(TemplateMaterialEnum.VIDEO);
				templateMaterialDao.save(templateMaterial);
			}
		}

		if (ValidateUtil.isNotEmpty(pictureIds)) {
			for (Integer materialId : pictureIds) {
				templateMaterial = new TemplateMaterial();
				templateMaterial.setMaterialId(materialId);
				templateMaterial.setTemplateId(templateId);
				templateMaterial.setType(TemplateMaterialEnum.PICTURE);
				templateMaterialDao.save(templateMaterial);
			}
		}

		if (ValidateUtil.isNotEmpty(marqueeIds)) {
			for (Integer marqueeId : marqueeIds) {
				templateMarquee = new TemplateMarquee();
				templateMarquee.setMarqueeId(marqueeId);
				templateMarquee.setTemplateId(templateId);
				templateMarqueeDao.save(templateMarquee);
			}
		}
	}

	@Override
	public void merge(Template template, Integer[] foodIds, Integer logoId, Integer numberId, Integer[] videoIds, Integer[] pictureIds, Integer[] marqueeIds) {
		Integer templateId = template.getId();
		if (ValidateUtil.isPK(templateId)) {
			this.update(template, foodIds, logoId, numberId, videoIds, pictureIds, marqueeIds);
		} else {
			this.save(template, foodIds, logoId, numberId, videoIds, pictureIds, marqueeIds);
		}
	}

	@Override
	public Template find(Integer id) {
		return templateDao.find(id);
	}

	@Override
	public Template find(String name) {
		return templateDao.find(name);
	}

	@Override
	public List<Template> findList() {
		return templateDao.findList();
	}

	@Override
	public List<Template> findList(String name, TemplateTypeEnum type, Integer userId) {
		return templateDao.findList(name, type, userId);
	}

	@Override
	public List<Template> findList(String name, TemplateTypeEnum type, Integer userId, String sort, String order, int pageNo, int pageSize) {
		return templateDao.findList(name, type, userId, sort, order, pageNo, pageSize);
	}

	@Override
	public int count(String name, TemplateTypeEnum type, Integer userId) {
		return templateDao.count(name, type, userId);
	}

	@Override
	public TemplateVO findVO(Integer id) {
		return templateDao.findVO(id);
	}

	@Override
	public List<TemplateVO> findVOList() {
		return templateDao.findVOList();
	}

	@Override
	public List<TemplateVO> findVOList(String name, TemplateTypeEnum type, Integer userId) {
		return templateDao.findVOList(name, type, userId);
	}

	@Override
	public List<TemplateVO> findVOList(String name, TemplateTypeEnum type, Integer userId, String sort, String order, int pageNo, int pageSize) {
		return templateDao.findVOList(name, type, userId, sort, order, pageNo, pageSize);
	}

	@Override
	public boolean exist(Integer id, String name) {
		Template template = templateDao.find(name);
		if (template == null) {
			return false;
		}
		if (!ValidateUtil.isPK(id)) {
			return true;
		}
		return !template.getId().equals(id);
	}

	@Override
	public boolean relate(Integer id) {
		// TODO
		List<TerminalTemplate> terminalTemplateList = null;
		if (CollectionUtils.isNotEmpty(terminalTemplateList)) {
			return true;
		}
		return false;
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
