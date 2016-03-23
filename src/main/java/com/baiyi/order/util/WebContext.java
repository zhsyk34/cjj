package com.baiyi.order.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.context.ServletContextAware;

import com.baiyi.order.model.User;
import com.baiyi.order.service.TerminalService;
import com.baiyi.order.service.UserService;
import com.baiyi.order.vo.Record;

//@Component
public class WebContext implements ServletContextAware, InitializingBean {

	/* 系统配置 */
	public final static String UPLOAD = "upload";// 上传素材目录
	public final static String FFMPEG = "tool" + File.separator + "convert" + File.separator + "ffmpeg.exe";// ffmpeg
	public final static String MENCODER = "tool" + File.separator + "convert" + File.separator + "mencoder.exe";// mencoder
	public final static String CAPTURE = "capture";// 上传截图路径
	public final static String TEMP = "temp";// 临时目录

	/* 初始化数据 */
	public static String os;// 系统
	public static String rootPath;// 项目根目录
	public static String version;
	public static String serverid;

	/* 托管对象 */
	@Resource
	private UserService userService;
	@Resource
	private TerminalService terminalService;

	/* 容器全局变量 */
	// 终端连线 key:terminalNo
	public final static Map<String, Record> ConnectMap = new HashMap<>();
	// 终端下载 key:terminalNo+templateId
	// public final static Map<String, DownProgress> downMap = new HashMap<>();
	// 远程控制
	public final static Map<String, Boolean> checkTimeMap = new HashMap<>();
	public final static Map<String, Boolean> rebootMap = new HashMap<>();
	public final static Map<String, Boolean> shutDownMap = new HashMap<>();
	public final static Map<String, Boolean> bootTeamViewer = new HashMap<>();
	public final static Map<String, Boolean> closeTeamViewer = new HashMap<>();

	@Override
	public void afterPropertiesSet() throws Exception {
		// 系统
		os = System.getProperty("os.name");
		System.out.println(os);
		if (os.toLowerCase().startsWith("windows")) {
			os = "windows";
		} else if (os.toLowerCase().startsWith("linux")) {
			os = "linux";
		}

		// WEB rootPath
		if (servletContext != null) {
			rootPath = servletContext.getRealPath("/");
			System.out.println("rootPath: " + rootPath);
		}

		// read file to set prop
		// String rootFile = rootPath + "WEB-INF" + File.separator + "classes";
		// String rootFile = ClassLoader.getSystemResource("").getFile();//error
		String rootFile = this.getClass().getClassLoader().getResource("").getFile();
		File versionFile = new File(rootFile, "version.txt");
		File serveridFile = new File(rootFile, "serverid.txt");
		try {
			version = FileUtils.readFileToString(versionFile);
			serverid = FileUtils.readFileToString(serveridFile);
			servletContext.setAttribute("version", version);
			servletContext.setAttribute("serverid", serverid);
			System.out.printf("version: %s, server: %s", version, serverid);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// base user
		User user = userService.find("root");
		if (user == null) {
			user = new User();
			user.setName("root");
			user.setPassword(Encryption.encrypt("root"));
			userService.save(user);
		}
	}

	private ServletContext servletContext;

	public ServletContext getServletContext() {
		return servletContext;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
}
