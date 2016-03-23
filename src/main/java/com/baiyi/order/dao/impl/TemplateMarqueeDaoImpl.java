package com.baiyi.order.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.baiyi.order.dao.TemplateMarqueeDao;
import com.baiyi.order.model.TemplateMarquee;
import com.baiyi.order.util.ValidateUtil;

@Repository
public class TemplateMarqueeDaoImpl extends CommonsDaoImpl<TemplateMarquee> implements TemplateMarqueeDao {

	@Override
	public void delete(List<TemplateMarquee> templateMarquees) {
		super.delete(templateMarquees);
	}

	@Override
	public List<TemplateMarquee> findList(Integer templateId, Integer marqueeId) {
		StringBuffer queryString = new StringBuffer("from TemplateMarquee as templateMarquee where 1 = 1");
		Map<String, Object> map = new HashMap<>();

		if (ValidateUtil.isPK(templateId)) {
			queryString.append(" and templateMarquee.templateId = :templateId");
			map.put("templateId", templateId);
		}
		if (ValidateUtil.isPK(marqueeId)) {
			queryString.append(" and templateMarquee.marqueeId = :marqueeId");
			map.put("marqueeId", marqueeId);
		}

		return super.findList(queryString.toString(), -1, -1, map);
	}

	@Override
	public void deleteByTemplateId(Integer templateId) {
		String queryString = "delete TemplateMarquee as templateMarquee where templateMarquee.templateId = :templateId";
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		Query query = session.createQuery(queryString).setParameter("templateId", templateId);
		query.executeUpdate();
	}

}
