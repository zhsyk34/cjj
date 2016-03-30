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

import com.baiyi.order.dao.ActivityDao;
import com.baiyi.order.dao.CashboxDao;
import com.baiyi.order.dao.FoodDao;
import com.baiyi.order.dao.TemplateDao;
import com.baiyi.order.dao.TerminalConnectDao;
import com.baiyi.order.dao.TerminalDao;
import com.baiyi.order.dao.TerminalSeatDao;
import com.baiyi.order.dao.TerminalTemplateDao;
import com.baiyi.order.dao.TerminalTimeDao;
import com.baiyi.order.model.Activity;
import com.baiyi.order.model.Cashbox;
import com.baiyi.order.model.Food;
import com.baiyi.order.model.Template;
import com.baiyi.order.model.Terminal;
import com.baiyi.order.model.TerminalConnect;
import com.baiyi.order.model.TerminalSeat;
import com.baiyi.order.model.TerminalTemplate;
import com.baiyi.order.model.TerminalTime;
import com.baiyi.order.service.TerminalService;
import com.baiyi.order.util.EnumList.ActivityTypeEnum;
import com.baiyi.order.util.EnumList.RemoteTimeEnum;
import com.baiyi.order.util.EnumList.TemplateDownEnum;
import com.baiyi.order.util.EnumList.TerminalTypeEnum;
import com.baiyi.order.util.FormatUtil;
import com.baiyi.order.util.ValidateUtil;
import com.baiyi.order.vo.Record;
import com.baiyi.order.vo.TemplateStatusVO;
import com.baiyi.order.vo.TerminalVO;

@Service
public class TerminalServiceImpl implements TerminalService {

	@Resource
	private TerminalDao terminalDao;
	@Resource
	private TerminalSeatDao terminalSeatDao;
	@Resource
	private TerminalTemplateDao terminalTemplateDao;
	@Resource
	private TerminalTimeDao terminalTimeDao;
	@Resource
	private TerminalConnectDao terminalConnectDao;
	@Resource
	private CashboxDao cashboxDao;
	@Resource
	private TemplateDao templateDao;
	@Resource
	private ActivityDao activityDao;
	@Resource
	private FoodDao foodDao;

	@Override
	public void save(Terminal terminal) {
		terminalDao.save(terminal);
		Integer terminalId = terminal.getId();
		// 活动
		if (terminal.getType() == TerminalTypeEnum.KITCHEN) {
			List<Food> foods = foodDao.findList();
			if (CollectionUtils.isNotEmpty(foods)) {
				for (Food food : foods) {
					Activity activity = new Activity();
					activity.setKitchenId(terminalId);
					activity.setFoodId(food.getId());
					activity.setType(ActivityTypeEnum.NORMAL);
					activityDao.save(activity);
				}
			}
		}
		// 币值存量
		Cashbox cashbox = new Cashbox();
		cashbox.setTerminalId(terminalId);
		cashboxDao.save(cashbox);
	}

	@Override
	public void delete(Integer id) {
		// TODO 禁止删除
	}

	@Override
	public void delete(Terminal terminal) {
		this.delete(terminal.getId());
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
	public void delete(List<Terminal> terminals) {
		if (CollectionUtils.isNotEmpty(terminals)) {
			for (Terminal terminal : terminals) {
				this.delete(terminal);
			}
		}
	}

	@Override
	public void update(Terminal terminal) {// TODO 禁止修改终端类型
		terminalDao.update(terminal);
	}

	@Override
	public void merge(Terminal terminal) {
		terminalDao.merge(terminal);
	}

	@Override
	public Terminal find(Integer id) {
		return terminalDao.find(id);
	}

	@Override
	public Terminal find(String terminalNo) {
		return terminalDao.find(terminalNo);
	}

	@Override
	public List<Terminal> findList() {
		return terminalDao.findList();
	}

	@Override
	public List<Terminal> findList(String terminalNo, TerminalTypeEnum type, Integer userId) {
		return terminalDao.findList(terminalNo, type, userId);
	}

	@Override
	public List<Terminal> findList(String terminalNo, TerminalTypeEnum type, Integer userId, String sort, String order, int pageNo, int pageSize) {
		return terminalDao.findList(terminalNo, type, userId, sort, order, pageNo, pageSize);
	}

	@Override
	public int count(String terminalNo, TerminalTypeEnum type, Integer userId) {
		return terminalDao.count(terminalNo, type, userId);
	}

	@Override
	public boolean exist(Integer id, String terminalNo) {
		Terminal terminal = this.find(terminalNo);
		if (terminal == null) {
			return false;
		}
		if (!ValidateUtil.isPK(id)) {
			return true;
		}
		return !terminal.getId().equals(id);
	}

	@Override
	public List<TerminalVO> findVOList() {
		return this.findVOList(null, null, null);
	}

	@Override
	public List<TerminalVO> findVOList(String terminalNo, TerminalTypeEnum type, Integer userId) {
		return this.findVOList(terminalNo, type, userId, null, null, -1, -1);
	}

	@Override
	public List<TerminalVO> findVOList(String terminalNo, TerminalTypeEnum type, Integer userId, String sort, String order, int pageNo, int pageSize) {
		List<Terminal> terminals = terminalDao.findList(terminalNo, type, userId, sort, order, pageNo, pageSize);
		if (CollectionUtils.isEmpty(terminals)) {
			return null;
		}

		List<TerminalVO> list = new ArrayList<>();
		for (Terminal terminal : terminals) {
			TerminalVO terminalVO = new TerminalVO();
			//
			BeanUtilsBean.getInstance().getConvertUtils().register(false, true, 0);
			try {
				BeanUtils.copyProperties(terminalVO, terminal);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}

			Integer terminalId = terminal.getId();
			// seats
			List<Integer> seats = this.findSeat(terminalId);
			terminalVO.setSeats(seats);
			// times
			List<String> bootList = new ArrayList<>();
			List<String> shutList = new ArrayList<>();
			List<TerminalTime> remoteTimeList = this.findTime(terminalId);
			if (CollectionUtils.isNotEmpty(remoteTimeList)) {
				for (TerminalTime terminalTime : remoteTimeList) {
					RemoteTimeEnum remoteTimeEnum = terminalTime.getType();
					String time = FormatUtil.dateToString(terminalTime.getTime(), "HH:mm:ss");
					switch (remoteTimeEnum) {
					case BOOT:
						bootList.add(time);
						break;
					case SHUT:
						shutList.add(time);
						break;
					}
				}
			}
			terminalVO.setBootList(bootList);
			terminalVO.setShutList(shutList);

			list.add(terminalVO);
		}
		return list;
	}

	@Override
	public void update(Terminal terminal, List<Date> boots, List<Date> shuts, Integer[] seatIds) {
		this.update(terminal);
		this.updateTime(terminal.getId(), boots, shuts);
		this.updateSeat(terminal.getId(), seatIds);

	}

	@Override
	public void saveConnect(TerminalConnect terminalConnect) {
		terminalConnectDao.save(terminalConnect);
	}

	@Override
	public List<Terminal> findOnLine(int pageNo, int pageSize) {
		List<Terminal> terminals = terminalDao.findList();
		if (CollectionUtils.isEmpty(terminals)) {
			return null;
		}
		List<Terminal> list = new ArrayList<>();
		for (Terminal terminal : terminals) {
			TerminalConnect terminalConnect = terminalConnectDao.findLast(terminal.getId());
			if (terminalConnect != null && terminalConnect.isOnline()) {
				list.add(terminal);
			}
		}
		int count = CollectionUtils.isEmpty(list) ? 0 : list.size();
		if (pageNo > 0 && pageSize > 0) {
			list = list.subList((pageNo - 1) * pageSize, Math.min(count, pageNo * pageSize));
		}
		return list;
	}

	@Override
	public int countOnLine() {
		List<Terminal> list = this.findOnLine(-1, -1);
		return CollectionUtils.isEmpty(list) ? 0 : list.size();
	}

	@Override
	public List<Record> findRecordList(String terminalNo, Date begin, Date end, Boolean online) {
		return terminalConnectDao.findVOList(terminalNo, begin, end, online);
	}

	@Override
	public List<Record> findRecordList(String terminalNo, Date begin, Date end, Boolean online, String sort, String order, int pageNo, int pageSize) {
		return terminalConnectDao.findVOList(terminalNo, begin, end, online, sort, order, pageNo, pageSize);
	}

	@Override
	public int countRecord(String terminalNo, Date begin, Date end, Boolean online) {
		return terminalConnectDao.countVO(terminalNo, begin, end, online);
	}

	@Override
	public Record findLastRecord(Integer terminalId) {
		Terminal terminal = terminalDao.find(terminalId);
		TerminalConnect terminalConnect = terminalConnectDao.findLast(terminalId);
		if (terminal == null || terminalConnect == null) {
			return null;
		}
		Record record = new Record();
		try {
			BeanUtils.copyProperties(record, terminalConnect);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		record.setTerminalNo(terminal.getTerminalNo());
		record.setLocation(terminal.getLocation());

		return record;
	}

	@Override
	public void updateSeat(Integer terminalId, Integer[] seatIds) {
		if (ValidateUtil.isEmpty(seatIds)) {
			return;
		}
		List<TerminalSeat> terminalSeats = terminalSeatDao.findList(terminalId, null);
		if (CollectionUtils.isNotEmpty(terminalSeats)) {
			terminalSeatDao.delete(terminalSeats);
		}
		for (Integer seatId : seatIds) {
			TerminalSeat terminalSeat = new TerminalSeat();
			terminalSeat.setTerminalId(terminalId);
			terminalSeat.setSeatId(seatId);
			terminalSeatDao.save(terminalSeat);
		}
	}

	@Override
	public List<Integer> findSeat(Integer terminalId) {
		List<TerminalSeat> terminalSeats = terminalSeatDao.findList(terminalId, null);
		if (CollectionUtils.isEmpty(terminalSeats)) {
			return null;
		}
		List<Integer> list = new ArrayList<>();
		for (TerminalSeat terminalSeat : terminalSeats) {
			list.add(terminalSeat.getSeatId());
		}
		return list;
	}

	@Override
	public void updateTime(Integer terminalId, List<Date> boots, List<Date> shuts) {
		List<TerminalTime> terminalTimes = terminalTimeDao.findList(terminalId, null);
		if (CollectionUtils.isNotEmpty(terminalTimes)) {
			terminalTimeDao.delete(terminalTimes);
		}

		if (CollectionUtils.isNotEmpty(boots)) {
			for (Date boot : boots) {
				TerminalTime terminalTime = new TerminalTime();
				terminalTime.setTerminalId(terminalId);
				terminalTime.setTime(boot);
				terminalTime.setType(RemoteTimeEnum.BOOT);
				terminalTimeDao.save(terminalTime);
			}
		}

		if (CollectionUtils.isNotEmpty(shuts)) {
			for (Date shut : shuts) {
				TerminalTime terminalTime = new TerminalTime();
				terminalTime.setTerminalId(terminalId);
				terminalTime.setTime(shut);
				terminalTime.setType(RemoteTimeEnum.SHUT);
				terminalTimeDao.save(terminalTime);
			}
		}
	}

	@Override
	public List<TerminalTime> findTime(Integer terminalId) {
		return terminalTimeDao.findList(terminalId, null);
	}

	@Override
	public void mergeTemplate(TerminalTemplate terminalTemplate) {
		terminalTemplateDao.merge(terminalTemplate);
	}

	@Override
	public TerminalTemplate findTemplate(Integer terminalId, Integer templateId) {
		return terminalTemplateDao.find(terminalId, templateId);
	}

	@Override
	public Template findUsedTemplate(Integer terminalId) {
		TerminalTemplate terminalTemplate = terminalTemplateDao.findUsed(terminalId);
		return terminalTemplate == null ? null : templateDao.find(terminalTemplate.getTemplateId());
	}

	@Override
	public List<TerminalTemplate> findTemplateList(Integer terminalId, Integer templateId, TemplateDownEnum status, Boolean renew, Boolean used) {
		return terminalTemplateDao.findList(terminalId, templateId, status, renew, used);
	}

	@Override
	public List<TemplateStatusVO> findTemplateList(Integer terminalId, String templateName, int pageNo, int pageSize) {
		Terminal terminal = terminalDao.find(terminalId);
		List<Template> templates = templateDao.findList(templateName, null, null);

		if (CollectionUtils.isEmpty(templates) || terminal == null || terminal.getType() != TerminalTypeEnum.KITCHEN) {
			return null;
		}

		List<TemplateStatusVO> list = new ArrayList<>();
		for (Template template : templates) {
			Integer templateId = template.getId();
			TemplateStatusVO templateStatus = new TemplateStatusVO();
			templateStatus.setTerminalId(terminalId);
			templateStatus.setTerminalNo(terminal.getTerminalNo());
			templateStatus.setTemplateId(templateId);
			templateStatus.setTemplateName(template.getName());
			// TODO
			TerminalTemplate terminalTemplate = terminalTemplateDao.find(terminalId, templateId);
			if (terminalTemplate != null) {
				templateStatus.setStatus(terminalTemplate.getStatus().name().toLowerCase());
				templateStatus.setRenew(terminalTemplate.isRenew());
				templateStatus.setUsed(terminalTemplate.isUsed());
				templateStatus.setDate(terminalTemplate.getDate());
				templateStatus.setTotal(terminalTemplate.getTotal());
				templateStatus.setDown(terminalTemplate.getDown());
			}
			list.add(templateStatus);
		}
		int count = CollectionUtils.isEmpty(list) ? 0 : list.size();
		if (pageNo > 0 && pageSize > 0) {
			list = list.subList((pageNo - 1) * pageSize, Math.min(count, pageNo * pageSize));
		}
		return list;
	}

	@Override
	public void setUsedTemplate(Integer terminalId, Integer templateId) {
		TerminalTemplate current = terminalTemplateDao.find(terminalId, templateId);
		if (current == null || current.isUsed() || current.getStatus() != TemplateDownEnum.HASDOWN) {
			return;
		}

		TerminalTemplate original = terminalTemplateDao.findUsed(terminalId);
		if (original != null) {
			original.setUsed(false);
			terminalTemplateDao.update(original);
		}
		current.setUsed(true);
		terminalTemplateDao.update(current);
	}

	@Override
	public List<Record> findLastRecord(String terminalNo, Boolean online, int pageNo, int pageSize) {
		List<Terminal> terminals = terminalDao.findList(terminalNo, null, null);
		if (CollectionUtils.isEmpty(terminals)) {
			return null;
		}
		List<Record> list = new ArrayList<>();
		for (Terminal terminal : terminals) {
			Record record = this.findLastRecord(terminal.getId());
			if (online == null || record.isOnline() == online) {
				list.add(record);
			}
		}
		int count = CollectionUtils.isEmpty(list) ? 0 : list.size();
		if (pageNo > 0 && pageSize > 0) {
			list = list.subList((pageNo - 1) * pageSize, Math.min(count, pageNo * pageSize));
		}
		return list;
	}

	@Override
	public int countLastRecord(String terminalNo, Boolean online) {
		List<Record> list = this.findLastRecord(terminalNo, online, -1, -1);
		return CollectionUtils.isEmpty(list) ? 0 : list.size();
	}

	@Override
	public int countTemplate(Integer terminalId, String templateName) {
		return this.findTemplateList(terminalId, templateName, -1, -1).size();
	}

}
