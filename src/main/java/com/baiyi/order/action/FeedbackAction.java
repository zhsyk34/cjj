package com.baiyi.order.action;

import com.baiyi.order.util.Feedback;

@SuppressWarnings("serial")
public class FeedbackAction extends CommonsAction {

	public String login() {
		jsonData.put("result", Feedback.OFFLINE.toString());
		return SUCCESS;
	}
}
