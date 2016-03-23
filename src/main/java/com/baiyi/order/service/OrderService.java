package com.baiyi.order.service;

import java.util.Date;
import java.util.List;

import com.baiyi.order.model.OrderDetail;
import com.baiyi.order.model.OrderInfo;
import com.baiyi.order.util.EnumList.OrderStatus;
import com.baiyi.order.vo.OrderDetailVO;
import com.baiyi.order.vo.OrderInfoVO;
import com.baiyi.order.vo.OrderVO;

public interface OrderService {

	public void save(OrderInfo orderInfo, List<OrderDetailVO> orderDetails);

	public void revoke(Integer id);// 撤销订单,不删除...

	public void update(OrderInfo orderInfo, List<OrderDetailVO> orderDetails);

	public OrderInfo find(Integer id);

	public List<OrderInfo> findList();

	public List<OrderInfo> findList(String orderNo);// 修改订单时保留原订单,编号一致

	public List<OrderInfo> findList(String orderNo, String shop, String kitchen, Date begin, Date end, Integer userId, OrderStatus status);

	public List<OrderInfo> findList(String orderNo, String shop, String kitchen, Date begin, Date end, Integer userId, OrderStatus status, String sort, String order, int pageNo, int pageSize);

	public int count(String orderNo, String shop, String kitchen, Date begin, Date end, Integer userId, OrderStatus status);

	/* join search */
	public List<OrderInfoVO> findVOList();

	public List<OrderInfoVO> findVOList(String orderNo, String shop, String kitchen, Date begin, Date end, Integer userId, OrderStatus status);

	public List<OrderInfoVO> findVOList(String orderNo, String shop, String kitchen, Date begin, Date end, Integer userId, OrderStatus status, String sort, String order, int pageNo, int pageSize);

	/* detail */
	public List<OrderDetail> findDetail(String orderNo, String shop, String kitchen, Date begin, Date end, Integer userId, OrderStatus status);

	public List<OrderVO> findOrderList(String orderNo, String shop, String kitchen, Date begin, Date end, Integer userId, OrderStatus status);

}
