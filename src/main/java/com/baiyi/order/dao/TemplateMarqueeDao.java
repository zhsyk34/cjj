package com.baiyi.order.dao;

import java.util.List;

import com.baiyi.order.model.TemplateMarquee;

public interface TemplateMarqueeDao {

	public void save(TemplateMarquee templateMarquee);

	public void delete(Integer id);

	public void delete(TemplateMarquee templateMarquee);

	public void delete(Integer[] ids);

	public void delete(List<TemplateMarquee> templateMarquees);

	public List<TemplateMarquee> findList();

	public List<TemplateMarquee> findList(Integer templateId, Integer marqueeId);

	public void deleteByTemplateId(Integer templateId);

}
