package com.baiyi.order.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.baiyi.order.dao.OrderDetailDao;
import com.baiyi.order.dao.OrderDetailTasteDao;
import com.baiyi.order.dao.OrderInfoDao;
import com.baiyi.order.model.OrderDetail;
import com.baiyi.order.model.OrderDetailTaste;
import com.baiyi.order.model.OrderInfo;
import com.baiyi.order.service.OrderService;
import com.baiyi.order.util.EnumList.OrderStatus;
import com.baiyi.order.vo.OrderDetailVO;
import com.baiyi.order.vo.OrderInfoVO;
import com.baiyi.order.vo.OrderVO;

@Service
public class OrderServiceImpl implements OrderService {

	@Resource
	private OrderInfoDao orderInfoDao;
	@Resource
	private OrderDetailDao orderDetailDao;
	@Resource
	private OrderDetailTasteDao orderDetailTasteDao;

	@Override
	public void save(OrderInfo orderInfo, List<OrderDetailVO> orderDetails) {
		orderInfoDao.save(orderInfo);

		Integer orderId = orderInfo.getId();

		if (CollectionUtils.isNotEmpty(orderDetails)) {
			for (OrderDetailVO orderDetailVO : orderDetails) {
				OrderDetail orderDetail = new OrderDetail();

				try {
					BeanUtils.copyProperties(orderDetail, orderDetailVO);
				} catch (IllegalAccessException | InvocationTargetException e) {
					e.printStackTrace();
				}
				orderDetail.setOrderId(orderId);

				orderDetailDao.save(orderDetail);

				Integer orderDetailId = orderDetail.getId();

				List<String> tasteList = orderDetailVO.getTasteList();
				if (CollectionUtils.isNotEmpty(tasteList)) {
					for (String taste : tasteList) {
						OrderDetailTaste orderDetailTaste = new OrderDetailTaste();
						orderDetailTaste.setOrderDetailId(orderDetailId);
						orderDetailTaste.setName(taste);

						orderDetailTasteDao.save(orderDetailTaste);
					}
				}
			}
		}
	}

	@Override
	public void revoke(Integer id) {
		OrderInfo orderInfo = orderInfoDao.find(id);
		orderInfo.setStatus(OrderStatus.NULLIFY);
		orderInfoDao.save(orderInfo);
	}

	@Override
	public void update(OrderInfo orderInfo, List<OrderDetailVO> orderDetails) {
		Integer id = orderInfo.getId();
		this.revoke(id);
		orderInfo.setId(null);
		this.save(orderInfo, orderDetails);
	}

	@Override
	public OrderInfo find(Integer id) {
		return orderInfoDao.find(id);
	}

	@Override
	public List<OrderInfo> findList() {
		return orderInfoDao.findList();
	}

	@Override
	public List<OrderInfo> findList(String orderNo) {
		return orderInfoDao.findList(orderNo);
	}

	@Override
	public List<OrderInfo> findList(String orderNo, String shop, String kitchen, Date begin, Date end, Integer userId, OrderStatus status) {
		return orderInfoDao.findList(orderNo, shop, kitchen, begin, end, userId, status);
	}

	@Override
	public List<OrderInfo> findList(String orderNo, String shop, String kitchen, Date begin, Date end, Integer userId, OrderStatus status, String sort, String order, int pageNo, int pageSize) {
		return orderInfoDao.findList(orderNo, shop, kitchen, begin, end, userId, status, sort, order, pageNo, pageSize);
	}

	@Override
	public int count(String orderNo, String shop, String kitchen, Date begin, Date end, Integer userId, OrderStatus status) {
		return orderInfoDao.count(orderNo, shop, kitchen, begin, end, userId, status);
	}

	@Override
	public List<OrderInfoVO> findVOList() {
		return this.findVOList(null, null, null, null, null, null, null);
	}

	@Override
	public List<OrderInfoVO> findVOList(String orderNo, String shop, String kitchen, Date begin, Date end, Integer userId, OrderStatus status) {
		return this.findVOList(orderNo, shop, kitchen, begin, end, userId, status, null, null, -1, -1);
	}

	@Override
	public List<OrderInfoVO> findVOList(String orderNo, String shop, String kitchen, Date begin, Date end, Integer userId, OrderStatus status, String sort, String order, int pageNo, int pageSize) {
		List<OrderInfo> orderInfoList = orderInfoDao.findList(orderNo, shop, kitchen, begin, end, userId, status, sort, order, pageNo, pageSize);
		if (CollectionUtils.isEmpty(orderInfoList)) {
			return null;
		}

		List<OrderInfoVO> orderInfoVOList = new ArrayList<>();
		for (OrderInfo orderInfo : orderInfoList) {
			Integer orderId = orderInfo.getId();

			OrderInfoVO orderInfoVO = new OrderInfoVO();
			// for null date
			BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);
			try {
				BeanUtils.copyProperties(orderInfoVO, orderInfo);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}

			orderInfoVO.setId(orderId);

			List<OrderDetail> orderDetailList = orderDetailDao.findList(orderId, null);
			List<OrderDetailVO> detailList = new ArrayList<>();
			for (OrderDetail orderDetail : orderDetailList) {
				Integer orderDetailId = orderDetail.getId();

				OrderDetailVO orderDetailVO = new OrderDetailVO();
				try {
					BeanUtils.copyProperties(orderDetailVO, orderDetail);
				} catch (IllegalAccessException | InvocationTargetException e) {
					e.printStackTrace();
				}

				orderDetailVO.setTasteList(orderDetailDao.findTaste(orderDetailId));
				detailList.add(orderDetailVO);
			}

			orderInfoVO.setDetailList(detailList);

			orderInfoVOList.add(orderInfoVO);
		}
		return orderInfoVOList;
	}

	@Override
	public List<OrderDetail> findDetail(String orderNo, String shop, String kitchen, Date begin, Date end, Integer userId, OrderStatus status) {
		List<OrderInfo> orderInfoList = orderInfoDao.findList(orderNo, shop, kitchen, begin, end, userId, status);
		if (CollectionUtils.isEmpty(orderInfoList)) {
			return null;
		}

		List<OrderDetail> list = new ArrayList<>();
		for (OrderInfo orderInfo : orderInfoList) {
			Integer orderId = orderInfo.getId();
			List<OrderDetail> orderDetailList = orderDetailDao.findList(orderId, null);
			list.addAll(orderDetailList);
		}
		return list;
	}

	@Override
	public List<OrderVO> findOrderList(String orderNo, String shop, String kitchen, Date begin, Date end, Integer userId, OrderStatus status) {
		List<OrderInfo> orderInfoList = orderInfoDao.findList(orderNo, shop, kitchen, begin, end, userId, status);
		if (CollectionUtils.isEmpty(orderInfoList)) {
			return null;
		}

		List<OrderVO> list = new ArrayList<>();
		for (OrderInfo orderInfo : orderInfoList) {
			Integer orderId = orderInfo.getId();

			OrderVO orderVO = new OrderVO();
			BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);
			try {
				BeanUtils.copyProperties(orderVO, orderInfo);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}

			List<OrderDetail> orderDetailList = orderDetailDao.findList(orderId, null);
			orderVO.setDetailList(orderDetailList);

			list.add(orderVO);
		}
		return list;
	}
}
