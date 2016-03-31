package com.test;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baiyi.order.dao.ActivityDao;
import com.baiyi.order.dao.TerminalDao;
import com.baiyi.order.dao.TerminalTemplateDao;
import com.baiyi.order.model.Activity;
import com.baiyi.order.model.Terminal;
import com.baiyi.order.model.TerminalTemplate;
import com.baiyi.order.service.TerminalService;
import com.baiyi.order.util.EnumList.ActivityTypeEnum;
import com.baiyi.order.util.EnumList.TerminalTypeEnum;
import com.baiyi.order.util.FormatUtil;
import com.baiyi.order.util.RandomUtil;
import com.baiyi.order.vo.ActivityVO;

import net.sf.json.JSONArray;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring2.xml")
public class TerminalTest {

	@Resource
	private TerminalService terminalService;
	@Resource
	private TerminalTemplateDao terminalTemplateDao;
	@Resource
	private TerminalDao terminalDao;
	@Resource
	private ActivityDao activityDao;

	@Test
	public void findActivity() {
		// List<ActivityVO> list = activityDao.findVOList();
		// System.out.println(JSONArray.fromObject(list));
		// System.out.println(list.size());

		List<ActivityVO> list1 = activityDao.findVOList("001", null, null, null);
		System.out.println(JSONArray.fromObject(list1));
		System.out.println(list1.size());

		List<ActivityVO> list2 = activityDao.findVOList("001", null, null, null, true);
		System.out.println(JSONArray.fromObject(list2));
		System.out.println(list2.size());

		List<ActivityVO> list3 = activityDao.findVOList(null, "3", null, null, true);
		System.out.println(JSONArray.fromObject(list3));
		System.out.println(list3.size());
	}

	@Test
	public void findTerminal() {
		Terminal terminal = terminalDao.find(1);
		if (terminal != null) {
			System.out.println(terminal.getTerminalNo());
		}
		Terminal terminal2 = terminalDao.find("测试客户端");
		if (terminal2 != null) {
			System.out.println(terminal2.getTerminalNo());
		}
	}

	@Test
	public void findUsedTemplate() {
		TerminalTemplate t = terminalTemplateDao.findUsed(1);
		System.out.println(t == null ? "null" : t.getId());
	}

	@Test
	public void findTemplate() {
		// List<TemplateStatus> list = terminalService.findTemplate(1, null);
		TerminalTemplate tt = terminalTemplateDao.find(1, 4);
		System.out.println(tt);
	}

	@Test
	public void saveSeat() {
	}

	@Test
	public void findSeat() {
	}

	@Test
	public void update() {
		// List<Remote> remotes = null;
		// System.out.println(remotes.size());
		//
		// String[] boots = { "07:00:00", "13:00:00", "19:35:22" };
		// String[] shuts = { "12:30:10", "19:22:22", "23:56:58" };
		//
		// List<Date> boottimes = new ArrayList<>();
		// List<Date> shuttimes = new ArrayList<>();
		//
		// if (ValidateUtil.isNotEmpty(boots)) {
		// for (String boot : boots) {
		// Date date = FormatUtil.stringToDate(boot, "HH:mm:ss");
		// boottimes.add(date);
		// }
		// }
		// if (ValidateUtil.isNotEmpty(boots)) {
		// for (String shut : shuts) {
		// Date date = FormatUtil.stringToDate(shut, "HH:mm:ss");
		// shuttimes.add(date);
		// }
		// }
		//
		// for (int i = 0; i < remotes.size(); i++) {
		// Remote remote = remotes.get(i);
		//
		// int begin = RandomUtil.randomInteger(0, 1);
		// int end = begin + RandomUtil.randomInteger(0, 3 - begin);
		// List<Date> boottimess = boottimes.subList(begin, end);
		// List<Date> shuttimess = shuttimes.subList(begin, end);
		// // remote.setTeamViewer("teamId" + RandomUtil.randomInteger(1, 9));
		// // remoteService.update(remote, boottimess, shuttimess);
		// }
	}

	@Test
	public void save() {
		for (int i = 0; i < 5; i++) {
			Terminal terminal = new Terminal();
			terminal.setTerminalNo("tno1000" + i);
			terminal.setType(FormatUtil.getEnum(TerminalTypeEnum.class, RandomUtil.randomInteger(0, 1)));
			terminal.setLocation("xm001");
			terminal.setCreatetime(new Date());
			terminal.setUserId(1);
			terminalDao.save(terminal);
		}

		for (int i = 1; i < 5; i++) {
			for (int j = 1; j < 7;) {
				Activity a = new Activity();
				a.setType(ActivityTypeEnum.values()[RandomUtil.randomInteger(0, 2)]);

				a.setKitchenId(i);
				a.setFoodId(j);

				activityDao.save(a);
				j += RandomUtil.randomInteger(1, 3);
			}
		}
	}

}
