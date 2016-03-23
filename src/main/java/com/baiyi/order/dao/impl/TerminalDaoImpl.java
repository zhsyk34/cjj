package com.baiyi.order.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.baiyi.order.dao.TerminalDao;
import com.baiyi.order.model.Terminal;
import com.baiyi.order.util.EnumList.TerminalTypeEnum;
import com.baiyi.order.util.ValidateUtil;

@Repository
public class TerminalDaoImpl extends CommonsDaoImpl<Terminal> implements TerminalDao {

	@Override
	public void delete(List<Terminal> terminals) {
		super.delete(terminals);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Terminal find(String terminalNo) {
		List<Terminal> list = (List<Terminal>) hibernateTemplate.find("from Terminal as terminal where terminal.terminalNo = ?", terminalNo);
		return CollectionUtils.isEmpty(list) ? null : list.get(0);
	}

	@Override
	public List<Terminal> findList(String terminalNo, TerminalTypeEnum type, Integer userId) {
		return this.findList(terminalNo, type, userId, null, null, -1, -1);
	}

	@Override
	public List<Terminal> findList(String terminalNo, TerminalTypeEnum type, Integer userId, String sort, String order, int pageNo, int pageSize) {
		StringBuffer queryString = new StringBuffer("from Terminal as terminal where 1 = 1");
		Map<String, Object> map = new HashMap<>();

		if (StringUtils.isNotBlank(terminalNo)) {
			queryString.append(" and terminal.terminalNo like :terminalNo");
			map.put("terminalNo", "%" + terminalNo + "%");
		}
		if (type != null) {
			queryString.append(" and terminal.type = :type");
			map.put("type", type);
		}
		if (ValidateUtil.isPK(userId)) {
			queryString.append(" and terminal.userId = :userId");
			map.put("userId", userId);
		}
		if (StringUtils.isNotBlank(sort) && StringUtils.isNotBlank(order)) {
			queryString.append(" order by terminal." + sort + " " + order);
		}
		return super.findList(queryString.toString(), (pageNo - 1) * pageSize, pageSize, map);
	}

	@Override
	public int count(String terminalNo, TerminalTypeEnum type, Integer userId) {
		StringBuffer queryString = new StringBuffer("select count(*) from Terminal as terminal where 1 = 1");
		Map<String, Object> map = new HashMap<>();

		if (StringUtils.isNotBlank(terminalNo)) {
			queryString.append(" and terminal.terminalNo like :terminalNo");
			map.put("terminalNo", "%" + terminalNo + "%");
		}
		if (type != null) {
			queryString.append(" and terminal.type = :type");
			map.put("type", type);
		}
		if (ValidateUtil.isPK(userId)) {
			queryString.append(" and terminal.userId = :userId");
			map.put("userId", userId);
		}
		return super.count(queryString.toString(), map);
	}

	// @SuppressWarnings("unchecked")
	// @Override
	// public List<Integer> findSeat(Integer terminalId) {
	// StringBuffer queryString = new StringBuffer("select seat.id from Seat as
	// seat, TerminalSeat as terminalSeat");
	// queryString.append(" where seat.id = terminalSeat.seatId and
	// terminalSeat.terminalId = ?");
	// return (List<Integer>) hibernateTemplate.find(queryString.toString(),
	// terminalId);
	// }
	//
	// @SuppressWarnings("unchecked")
	// @Override
	// public List<Template> findTemplate(Integer terminalId, TemplateDownEnum
	// status) {
	// StringBuffer queryString = new StringBuffer("select template from
	// Template as template, TerminalTemplate as terminalTemplate");
	// queryString.append(" where template.id = terminalTemplate.templateId");
	// Map<String, Object> map = new HashMap<>();
	//
	// if (ValidateUtil.isPK(terminalId)) {
	// queryString.append(" and terminalTemplate.terminalId = :terminalId");
	// map.put("terminalId", terminalId);
	// }
	// if (status != null) {
	// queryString.append(" and terminalTemplate.status = :status");
	// map.put("status", status);
	// }
	//
	// Session session =
	// hibernateTemplate.getSessionFactory().getCurrentSession();
	// Query query = session.createQuery(queryString.toString());
	// if (map != null) {
	// query.setProperties(map);
	// }
	// return query.list();
	// }

}
