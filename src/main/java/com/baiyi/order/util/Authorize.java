package com.baiyi.order.util;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;

/**
 * 如果有插入加密狗服務端就不用驗證，加密狗的數據必須是youhome 若沒有加密狗則需通過驗證主機驗證serverID
 * 
 * @author Administrator
 * 
 */
public class Authorize implements Runnable {

	private String availableMachine;

	public Authorize(String availableMachine) {
		this.availableMachine = availableMachine;
	}

	public void run() {
		while (true) {
			System.out.println("====server confirm ");
			if (StringUtils.isNotBlank(WebContext.serverid)) {
				WebContext.empower = confirm();
				System.out.println(confirm() ? "success====" : "error3====");
			} else {
				WebContext.empower = false;
				System.out.println("error2====");
			}
			try {
				Thread.sleep(30 * 60 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		WebContext.cusurl = "http://192.168.1.221:8181/customerControl/";
		WebContext.path = "D:/apache-tomcat-6.0.32/webapps/TopSetWebNetBef/";
		WebContext.serverid = "testserver";
		// confirmTerminal();
	}

	public boolean confirm() {
		boolean flag = false;
		try {
			// 服务端序列化文件路径
			File serialdir = new File(WebContext.path + "manage" + File.separator + "tconfirm" + File.separator);
			if (!serialdir.exists()) {
				serialdir.mkdirs();
			}
			String serilaPath = WebContext.path + "manage" + File.separator + "tconfirm" + File.separator + WebContext.serverid + ".data";
			File file = new File(serilaPath);
			if (!file.exists()) {
				// 如果不存在就去客管後臺驗證該服務器
				// String filePath = WebContext.path+"serverid.txt";
				// String serverid = IOUtil.readerFile(filePath);
				String param = "serverid=" + URLEncoder.encode(WebContext.serverid, "utf-8") + "&type=1";
				String response = Utility.sendRequest2(WebContext.mirror + "checkServer.do", param);
				if (response != null && response.length() > 0) {
					JSONObject object = (JSONObject) JSONValue.parse(response);
					if (object.get("success") != null && object.get("success").toString().equals("true")) {
						String isOpen = object.get("isOpen").toString();
						if (isOpen.equals("true")) {
							DESPlus desPlus = new DESPlus();
							// 如果客管通過認證
							String terminalNumObj = (String) object.get("terminalNum");
							String serverIdObj = (String) object.get("serverid");
							String beginObj = (String) object.get("begin");
							String endObj = (String) object.get("end");
							JSONObject objectSerila = new JSONObject();
							objectSerila.put("serverid", serverIdObj);
							objectSerila.put("begin", beginObj);
							objectSerila.put("end", endObj);
							objectSerila.put("terminalCount", terminalNumObj);
							servletContext.setAttribute("availableCount", desPlus.decrypt(terminalNumObj));
							WebContext.availableCount = Integer.parseInt(desPlus.decrypt(terminalNumObj));
							Utility.writeObject(serilaPath, objectSerila);
							flag = true;
							System.out.println("====server confirm success====");
						} else {
							// 已經認證過了但是序列化文件被刪除了，認證不被通過
							flag = false;
						}
					} else if (object.get("success") != null && object.get("success").toString().equals("false")) {
						// 認證不通過
						flag = false;
					}
				}
			} else {
				// 如果存在了就根據序列化文件驗證驗證
				DESPlus desplus = new DESPlus();
				JSONObject object = Utility.readObject(serilaPath);
				String terminalCount = (String) object.get("terminalCount");
				String serverid = (String) object.get("serverid");
				// 驗證serverId是否一致
				if (!WebContext.serverid.equals(desplus.decrypt(serverid))) {
					flag = false;
				} else {
					terminalCount = desplus.decrypt(terminalCount);
					// 設置服務端的終端數量
					servletContext.setAttribute("availableCount", terminalCount);
					WebContext.availableCount = Integer.parseInt(terminalCount);
					flag = true;
				}
			}
			// {"terminalNum":"069726005eaf18c1","serverid":"AL0001","success":true,"end":"4ca2ab63835298d6caa441dc77dd93ab","begin":"4ca2ab63835298d66b5d676ed0fb4c30"}
			// 解析返回的信息

		} catch (IOException ioe) {
			// 如果是網絡問題就不驗證了，允許通過
			ioe.printStackTrace();
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		} catch (Error e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}
}
