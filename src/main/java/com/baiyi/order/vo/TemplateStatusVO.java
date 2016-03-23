package com.baiyi.order.vo;

import java.util.Date;

public class TemplateStatusVO {

	private Integer id;

	private Integer terminalId;

	private String terminalNo;// VO

	private Integer templateId;

	private String templateName;// VO

	private String status;// 状态

	/* 下载进度 */
	private long total;// 总大小
	private long down;// 已下载

	private boolean renew;// 更新

	private boolean used;// 启用

	private Date date;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getDown() {
		return down;
	}

	public void setDown(long down) {
		this.down = down;
	}

	public boolean isRenew() {
		return renew;
	}

	public void setRenew(boolean renew) {
		this.renew = renew;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
