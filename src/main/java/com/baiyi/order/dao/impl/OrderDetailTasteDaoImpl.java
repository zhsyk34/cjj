package com.baiyi.order.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.baiyi.order.dao.OrderDetailTasteDao;
import com.baiyi.order.model.OrderDetailTaste;
import com.baiyi.order.util.ValidateUtil;

@Repository
public class OrderDetailTasteDaoImpl extends CommonsDaoImpl<OrderDetailTaste> implements OrderDetailTasteDao {

	@Override
	public void delete(List<OrderDetailTaste> orderDetailTastes) {
		super.delete(orderDetailTastes);
	}

	@Override
	public List<OrderDetailTaste> findList(Integer orderDetailId, Integer tasteId) {
		StringBuffer queryString = new StringBuffer("from OrderDetailTaste as orderDetailTaste where 1 = 1");
		Map<String, Object> map = new HashMap<>();

		if (ValidateUtil.isPK(orderDetailId)) {
			queryString.append(" and orderDetailTaste.orderDetailId = :orderDetailId");
			map.put("orderDetailId", orderDetailId);
		}
		if (ValidateUtil.isPK(tasteId)) {
			queryString.append(" and orderDetailTaste.tasteId = :tasteId");
			map.put("tasteId", tasteId);
		}
		return super.findList(queryString.toString(), map);
	}

}
