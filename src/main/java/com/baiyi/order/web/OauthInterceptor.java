package com.baiyi.order.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.baiyi.order.model.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("serial")
public class OauthInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		// invocation.addPreResultListener(new PreResultListener() {
		// @Override
		// public void beforeResult(ActionInvocation invocation, String
		// resultCode) {
		//
		// }
		// });

		ActionContext context = invocation.getInvocationContext();

		// pass action
		String actionName = context.getName();
		System.out.println(actionName);
		if (actionName.matches("^Feedback_.*|^User_login.*")) {
			System.out.println("pass action");
			return invocation.invoke();
		}

		// validate login
		Map<String, Object> session = context.getSession();
		User user = (User) session.get("user");
		if (user != null) {
			return invocation.invoke();
		}

		System.out.println("未登录");

		// check type
		HttpServletRequest request = ServletActionContext.getRequest();
		String type = request.getHeader("X-Requested-With");
		if ("XMLHttpRequest".equalsIgnoreCase(type)) {
			System.out.println("ajax visit");
			// context.put(jsonData, jsonData);
			return "redirect-login";
		} else {
			System.out.println("action redirect");
			return "login";
		}

		// exception

		// ActionProxy proxy = invocation.getProxy();
		// System.out.println(proxy.getClass());
		// System.out.println(proxy.getAction());
		// System.out.println(proxy.getActionName());
		// System.out.println(proxy.getMethod());
		// System.out.println(proxy.getNamespace());
		//
		// Class<?> actionClass = proxy.getAction().getClass();
		// System.out.println(actionClass.getName());
		// Field[] fields = actionClass.getDeclaredFields();
		// for (Field field : fields) {
		// System.out.println(field.getName());
		// }

	}

}
