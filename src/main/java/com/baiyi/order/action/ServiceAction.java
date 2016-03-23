package com.baiyi.order.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.baiyi.order.model.Activity;
import com.baiyi.order.model.Cashbox;
import com.baiyi.order.model.OrderInfo;
import com.baiyi.order.model.OrderRule;
import com.baiyi.order.model.Refund;
import com.baiyi.order.model.Terminal;
import com.baiyi.order.model.TerminalTemplate;
import com.baiyi.order.util.EnumList.ActivityTypeEnum;
import com.baiyi.order.util.EnumList.OrderStatus;
import com.baiyi.order.util.EnumList.RefundReasonEnum;
import com.baiyi.order.util.EnumList.RefundTypeEnum;
import com.baiyi.order.util.EnumList.TemplateDownEnum;
import com.baiyi.order.util.Feedback;
import com.baiyi.order.util.FormatUtil;
import com.baiyi.order.vo.OrderDetailVO;
import com.baiyi.order.vo.TemplateVO;

//终端交互
@SuppressWarnings("serial")
public class ServiceAction extends CommonsAction {

	// 更新终端版本号
	public String updateVersion() {
		Terminal terminal = terminalService.find(terminalNo);
		if (terminal != null) {
			terminal.setVersion(version);
			terminalService.update(terminal);
		}
		return SUCCESS;
	}

	// 下载模板
	public String downTemplate() {// TODO
		List<TerminalTemplate> terminalTemplates = terminalService.findTemplateList(terminalId, null, null, null, null);
		if (CollectionUtils.isEmpty(terminalTemplates)) {
			jsonData.put("result", "not need");
		}
		List<TemplateVO> list = new ArrayList<>();
		for (TerminalTemplate terminalTemplate : terminalTemplates) {
			Integer templateId = terminalTemplate.getTemplateId();
			TemplateVO templateVO = templateService.findVO(templateId);
			if (templateVO == null) {
				continue;
			}
			list.add(templateVO);
		}
		jsonData.put("list", list);
		return SUCCESS;
	}

	// 更新下载进度
	public String uploadDownProgress() {
		if (StringUtils.isBlank(terminalNo)) {
			jsonData.put("result", "终端编号错误");
			return SUCCESS;
		}
		Terminal terminal = terminalService.find(terminalNo);
		if (terminal == null) {
			jsonData.put("result", "终端不存在");
			return SUCCESS;
		}

		Integer terminalId = terminalService.find(terminalNo).getId();
		TerminalTemplate terminalTemplate = terminalService.findTemplate(terminalId, templateId);

		if (terminalTemplate == null) {
			jsonData.put("status", "cancle");
			jsonData.put("result", "已取消下载...");
			return SUCCESS;
		}
		TemplateDownEnum status = terminalTemplate.getStatus();
		if (status == TemplateDownEnum.HASDOWN) {
			jsonData.put("result", "已下载...");
		} else if (status == TemplateDownEnum.WAITDOWN) {
			if (total > 0) {
				terminalTemplate.setTotal(total);
			}
			terminalTemplate.setDown(terminalTemplate.getDown() + down);
			jsonData.put("result", "已更新下载进度...");
			terminalService.mergeTemplate(terminalTemplate);
		} else {
			jsonData.put("status", "cancle");
			jsonData.put("result", "已取消下载...");
		}

		return SUCCESS;
	}

	// 更新现金数量
	public String updateCashbox() {
		Terminal terminal = terminalService.find(terminalNo);
		Integer terminalId = terminal.getId();

		Cashbox cashbox = cashboxService.findByTerminal(terminalId);
		if (cashbox == null) {
			cashbox = new Cashbox();
			cashbox.setTerminalId(terminalId);
		}

		cashbox.setNd100tw100(nd100tw100);
		cashbox.setNd100cn10(nd100cn10);

		cashbox.setNv9tw100(nv9tw100);
		cashbox.setNv9tw500(nv9tw500);
		cashbox.setNv9tw1000(nv9tw1000);
		cashbox.setNv9cn1(nv9cn1);
		cashbox.setNv9cn5(nv9cn5);
		cashbox.setNv9cn10(nv9cn10);
		cashbox.setNv9cn20(nv9cn20);
		cashbox.setNv9cn50(nv9cn50);
		cashbox.setNv9cn100(nv9cn100);

		cashbox.setHoppertw1(hoppertw1);
		cashbox.setHoppertw5(hoppertw5);
		cashbox.setHoppertw10(hoppertw10);
		cashbox.setHoppertw50(hoppertw50);
		cashbox.setHoppercn01(hoppercn01);
		cashbox.setHoppercn05(hoppercn05);
		cashbox.setHoppercn1(hoppercn1);

		cashboxService.merge(cashbox);
		jsonData.put("result", Feedback.SUCCESS.toString());
		return SUCCESS;
	}

	// 退币异常
	public String saveRefund() {
		Refund refund = new Refund();
		refund.setAuthenticode(authenticode);
		refund.setOrderNo(orderNo);
		refund.setTerminalNo(terminalNo);
		refund.setReason(FormatUtil.getEnum(RefundReasonEnum.class, reason));
		refund.setType(FormatUtil.getEnum(RefundTypeEnum.class, type));
		refund.setAmount(amount);
		refund.setHappentime(FormatUtil.stringToDate(happentime, null));
		refund.setOver(false);

		jsonData.put("result", Feedback.SUCCESS.toString());
		return SUCCESS;
	}

	// 查询厨房端 TODO
	public String findTerminal() {
		jsonData.put("result", Feedback.SUCCESS.toString());
		return SUCCESS;
	}

	// 订单规则 TODO
	public String findOrderRule() {
		OrderRule orderRule = orderRuleService.findUsed();
		jsonData.put("list", orderRule);
		jsonData.put("result", Feedback.SUCCESS.toString());
		return SUCCESS;
	}

	// 上传订单 TODO
	public String saveOrder() {
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setShop(shop);
		orderInfo.setKitchen(kitchen);
		orderInfo.setTotal(total);
		orderInfo.setIncome(income);
		orderInfo.setExpense(expense);
		orderInfo.setCreatetime(new Date());
		orderInfo.setUpdatetime(null);
		orderInfo.setUserId(loginId);
		orderInfo.setStatus(OrderStatus.NORMAL);

		if (CollectionUtils.isEmpty(foods)) {
			jsonData.put("result", Feedback.ERROR.toString());
			return SUCCESS;
		}

		List<OrderDetailVO> orderDetails = new ArrayList<>();
		for (int i = 0; i < foods.size(); i++) {
			OrderDetailVO orderDetailVO = new OrderDetailVO();
			orderDetailVO.setFood(foods.get(i));
			orderDetailVO.setPrice(prices.get(i));
			orderDetailVO.setCount(counts.get(i));

			List<String> tasteList = null;
			String tasteString = tastes.get(i);
			if (StringUtils.isNotBlank(tasteString)) {
				String[] tasteArr = tasteString.split("\\s*,\\s*");
				tasteList = Arrays.asList(tasteArr);
			}
			orderDetailVO.setTasteList(tasteList);

			orderDetails.add(orderDetailVO);
		}
		orderService.save(orderInfo, orderDetails);
		jsonData.put("result", Feedback.SUCCESS.toString());
		return SUCCESS;
	}

	// TODO
	public String A() {
		jsonData.put("result", Feedback.SUCCESS.toString());
		return SUCCESS;
	}

	// 更新餐点活动送出数量
	public String setActivitySend() {
		Activity activity = activityService.find(id);
		activity.setSend(send);
		activityService.update(activity);
		return SUCCESS;
	}

	// 分类读取餐点活动数据
	public String getActivity() {
		List<Activity> stopList = activityService.findList(terminalId, null, ActivityTypeEnum.STOP, true);
		List<Activity> giftList = activityService.findList(terminalId, null, ActivityTypeEnum.GIFT, true);
		List<Activity> discountList = activityService.findList(terminalId, null, ActivityTypeEnum.DISCOUNT, true);

		jsonData.put("stopList", stopList);
		jsonData.put("giftList", giftList);
		jsonData.put("discountList", discountList);
		return SUCCESS;
	}

	private Integer id;

	private int send;

	private Integer terminalId;

	private String terminalNo;

	private String version;

	private long down;
	private long total;
	private Integer templateId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getSend() {
		return send;
	}

	public void setSend(int send) {
		this.send = send;
	}

	public Integer getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(Integer terminalId) {
		this.terminalId = terminalId;
	}

	public String getTerminalNo() {
		return terminalNo;
	}

	public void setTerminalNo(String terminalNo) {
		this.terminalNo = terminalNo;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public long getDown() {
		return down;
	}

	public void setDown(long down) {
		this.down = down;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	/* ====================== */
	/* 找钞 */
	private int nd100tw100;
	private int nd100cn10;
	/* 收钞 */
	private int nv9tw100;
	private int nv9tw500;
	private int nv9tw1000;
	private int nv9cn1;
	private int nv9cn5;
	private int nv9cn10;
	private int nv9cn20;
	private int nv9cn50;
	private int nv9cn100;
	/* 硬币 */
	private int hoppertw1;
	private int hoppertw5;
	private int hoppertw10;
	private int hoppertw50;
	private int hoppercn01;
	private int hoppercn05;
	private int hoppercn1;

	/* ================== */
	private String authenticode;

	private String orderNo;

	private String reason;

	private String type;

	private double amount;

	private String happentime;

	/**/
	private String shop;

	private String kitchen;

	private double income;

	private double expense;
	private List<String> foods;
	private List<Double> prices;
	private List<Integer> counts;
	private List<String> tastes;

}
