package com.baiyi.order.web;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.baiyi.order.model.Terminal;
import com.baiyi.order.model.TerminalConnect;
import com.baiyi.order.service.TerminalService;
import com.baiyi.order.util.FormatUtil;
import com.baiyi.order.util.WebContext;
import com.baiyi.order.vo.Record;

public class ServerSockThread extends Thread {

	/* ServerSocket */
	private ServerSocket server = null;
	private final static int SERVERPORT = 5199;
	private final static int POOLSIZE = 10;

	private final Map<String, String> hostMap = new HashMap<>();

	// 初始化终端连线信息
	public ServerSockThread() {
		// private TerminalService terminalService = null;
		// List<Terminal> terminals = terminalService.findList();
		// if (CollectionUtils.isNotEmpty(terminals)) {
		// for (Terminal terminal : terminals) {
		// Record record = terminalService.findLastRecord(terminal.getId());
		// WebContext.ConnectMap.put(terminal.getTerminalNo(), record);
		// }
		// }
	}

	@Override
	public void run() {
		try {
			server = new ServerSocket(SERVERPORT);
			int processor = Runtime.getRuntime().availableProcessors();
			ExecutorService executorService = Executors.newFixedThreadPool(POOLSIZE * processor);
			System.out.println("服务端正在启动中...");
			while (true) {
				Socket client = server.accept();
				executorService.execute(new Handler(client));

				String hostname = client.getInetAddress().getHostName();
				hostMap.put(hostname, null);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {// TODO
			if (server != null && server.isClosed()) {
				// server.close();
			}
		}
	}
}

class Handler implements Runnable {
	TerminalService terminalService = null;

	private Socket socket = null;
	private final static int BUFFERSIZE = 999999;
	private final static int FREQUENCY = 20;

	private ObjectInputStream ois = null;
	private ObjectOutputStream oos = null;

	public Handler(Socket socket) {
		this.terminalService = null;
		try {
			this.socket = socket;
			socket.setReceiveBufferSize(BUFFERSIZE);
			socket.setKeepAlive(true);
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("连接终端失败...");
		}
	}

	public void run() {
		TerminalConnect terminalConnect = null;
		String terminalNo = null;
		boolean checknew = false;

		while (socket != null && socket.isConnected() && !socket.isClosed()) {
			try {
				socket.setSoTimeout(FREQUENCY * 1000 + 2 * 60 * 1000);
				oos.writeObject(FREQUENCY);
				oos.flush();

				Object info = ois.readObject();
				checknew = !(info instanceof TerminalConnect);
				if (checknew) {
					terminalConnect = (TerminalConnect) info;
					terminalService.saveConnect(terminalConnect);
				} else {
					oos.writeObject("1||" + FREQUENCY);
					oos.flush();
				}

				/* 远程管理 */

				// 校正时间
				String date = FormatUtil.dateToString(new Date(), null);
				String dateStr = WebContext.checkTimeMap.containsKey(terminalNo) ? date : "";
				dateStr = (checknew ? "2||" : "") + dateStr;

				System.out.println("發送時間給" + terminalNo);
				oos.writeObject(dateStr);
				oos.flush();

				String string = (String) ois.readObject();
				if (string.equals("changetimesuccess")) {
					System.out.println("終端號：" + terminalNo + "時間修改成功");
					WebContext.checkTimeMap.remove(terminalNo);
				}

				// 重启
				if (checknew) {
					if (WebContext.rebootMap.containsKey(terminalNo)) {
						oos.writeObject("3||" + "reboot");
						oos.flush();
						if ("rebootsuccess".equals((String) ois.readObject())) {
							System.out.println("終端號：" + terminalNo + "重啟成功");
							WebContext.rebootMap.remove(terminalNo);
						}
					} else {
						oos.writeObject("3||");
						oos.flush();
						ois.readObject();
					}
				}
				// ===================關閉===================//TODO
				if (checknew) {
					if (WebContext.shutDownMap.containsKey(terminalNo)) {
						System.out.println("發送給" + terminalNo + "關閉");
						oos.writeObject("4||" + "shutdown");
						oos.flush();
						String terminalReply = (String) ois.readObject();
						if (terminalReply.equals("shutdown")) {
							System.out.println("終端號：" + terminalNo + "關閉成功");
							WebContext.shutDownMap.remove(terminalNo);
						}
					} else {
						oos.writeObject("4||");
						oos.flush();
						ois.readObject();
					}
				}

				// ===================啟動遠端===================
				if (checknew) {
					if (WebContext.bootTeamViewer.containsKey(terminalNo)) {
						System.out.println("發送給" + terminalNo + "啟動遠端");
						oos.writeObject("6||" + "bootTeamViewer");

						oos.flush();
						String terminalReply = (String) ois.readObject();
						if (terminalReply.equals("bootsuccess")) {
							System.out.println("終端號：" + terminalNo + "啟動遠端成功");
							WebContext.bootTeamViewer.remove(terminalNo);
						}
					} else {
						oos.writeObject("6||");
						oos.flush();
						ois.readObject();
					}
				}
				// ===================關閉遠端===================
				if (checknew) {
					if (WebContext.closeTeamViewer.containsKey(terminalNo)) {
						System.out.println("發送給" + terminalNo + "關閉遠端程序");
						oos.writeObject("7||" + "closeTeamViewer");

						oos.flush();
						String terminalReply = (String) ois.readObject();
						if (terminalReply.equals("closesuccess")) {
							System.out.println("終端號：" + terminalNo + "關閉遠端程序成功");
							WebContext.closeTeamViewer.remove(terminalNo);
						}
					} else {
						oos.writeObject("7||");
						oos.flush();
						ois.readObject();
					}
				}
			} catch (Exception exception) {
				if (StringUtils.isNotBlank(terminalNo)) {
					terminalConnect = new TerminalConnect();
					terminalConnect.setDate(new Date());
					terminalConnect.setOnline(false);
					terminalService.saveConnect(terminalConnect);
				}
			} finally {

			}
		}
	}
}
