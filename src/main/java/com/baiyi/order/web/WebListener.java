package com.baiyi.order.web;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class WebListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
		System.out.println("init listener");
		// WebApplicationContext springContext =
		// WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
		// SpringBean bean = (SpringBean) springContext.getBean("springBean");

	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Enumeration<Driver> drivers = DriverManager.getDrivers();
		while (drivers.hasMoreElements()) {
			Driver driver = drivers.nextElement();
			if (driver.getClass().getClassLoader() == loader) {
				try {
					DriverManager.deregisterDriver(driver);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
