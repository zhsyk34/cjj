package com.baiyi.order.dao;

import java.util.List;

import com.baiyi.order.model.OrderDetail;

public interface OrderDetailDao {

	public void save(OrderDetail orderDetail);

	public void delete(Integer id);

	public void delete(OrderDetail orderDetail);

	public void delete(Integer[] ids);

	public void delete(List<OrderDetail> orderDetails);

	public void update(OrderDetail orderDetail);

	public void merge(OrderDetail orderDetail);

	public OrderDetail find(Integer id);

	public List<OrderDetail> findList();

	public List<OrderDetail> findList(Integer orderId, String food);

	public int count(Integer orderId, String food);

	/* 删除订单明细数据:目前采取备份数据方法,未使用... */
	public void deleteByOrderId(Integer orderId);

	public List<String> findTaste(Integer id);
}
