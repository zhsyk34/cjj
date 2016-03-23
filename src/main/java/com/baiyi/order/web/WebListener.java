package com.baiyi.order.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class WebListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// WebApplicationContext springContext =
		// WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
		// SpringBean bean = (SpringBean) springContext.getBean("springBean");

	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {

	}

}
