package com.baiyi.order.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.baiyi.order.dao.SeatDao;
import com.baiyi.order.model.Seat;
import com.baiyi.order.service.SeatService;
import com.baiyi.order.util.ValidateUtil;

@Service
public class SeatServiceImpl implements SeatService {

	@Resource
	private SeatDao seatDao;

	@Override
	public void save(Seat seat) {
		seatDao.save(seat);
	}

	@Override
	public void delete(Integer id) {
		seatDao.delete(id);
	}

	@Override
	public void delete(Seat seat) {
		seatDao.delete(seat);
	}

	@Override
	public void delete(Integer[] ids) {
		seatDao.delete(ids);
	}

	@Override
	public void delete(List<Seat> seats) {
		seatDao.delete(seats);
	}

	@Override
	public void update(Seat seat) {
		seatDao.update(seat);
	}

	@Override
	public void merge(Seat seat) {
		seatDao.merge(seat);
	}

	@Override
	public Seat find(Integer id) {
		return seatDao.find(id);
	}

	@Override
	public Seat find(String name) {
		return seatDao.find(name);
	}

	@Override
	public List<Seat> findList() {
		return seatDao.findList();
	}

	@Override
	public List<Seat> findList(String name, Integer userId) {
		return seatDao.findList(name, userId);
	}

	@Override
	public List<Seat> findList(String name, Integer userId, String sort, String order, int pageNo, int pageSize) {
		return seatDao.findList(name, userId, sort, order, pageNo, pageSize);
	}

	@Override
	public int count(String name, Integer userId) {
		return seatDao.count(name, userId);
	}

	@Override
	public void save(List<Seat> seats) {
		if (CollectionUtils.isNotEmpty(seats)) {
			for (Seat seat : seats) {
				seatDao.save(seat);
			}
		}
	}

	@Override
	public boolean exist(Integer id, String name) {
		Seat seat = this.find(name);
		if (seat == null) {
			return false;
		}
		if (!ValidateUtil.isPK(id)) {
			return true;
		}
		return !seat.getId().equals(id);
	}

	@Override
	public boolean relate(Integer id) {
		// TODO Auto-generated method stub
		// terminal
		return false;
	}

	@Override
	public boolean relate(Integer[] ids) {
		// TODO Auto-generated method stub
		return false;
	}

}
