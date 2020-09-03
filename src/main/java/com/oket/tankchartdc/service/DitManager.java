package com.oket.tankchartdc.service;

import com.oket.tankchartdc.mina.dit.DITEmulator;
import com.oket.tankchartdc.mina.ifsf.IFSFAcceptor;
import com.oket.tankchartdc.mina.json.JSONAcceptor;
import com.oket.tankchartdc.websocket.ProtocolBean;
import com.oket.tankchartdc.websocket.WebSocketServer;

import java.util.*;

/**
 * @description:
 * @author: Longer
 * @create: 2019-11-12 22:33
 **/
public class DitManager {
	public static final Set<DITEmulator> EMULATOR_HASH_SET = new HashSet<>();

	private static JSONAcceptor jsonAcceptor;
	private static IFSFAcceptor ifsfAcceptor;

	private static final Collection<ProtocolBean> PROTOCOL_BEANS = new ArrayList<>();

	public static DITEmulator getDitEmulator(String id) {
		for (DITEmulator ditEmulator : EMULATOR_HASH_SET) {
			if (ditEmulator.getId().equals(id)) {
				return ditEmulator;
			}
		}
		return null;
	}

	public static void setJsonAcceptor(JSONAcceptor jsonAcceptor) {
		DitManager.jsonAcceptor = jsonAcceptor;
		ProtocolBean protocolBean = new ProtocolBean(
				ProtocolBean.TYPE_JSON_ACCEPTOR,
				jsonAcceptor.getPort(),
				jsonAcceptor.getIp(),
				jsonAcceptor.getId());
		addProtocolBean(protocolBean);
	}

	public static void setIfsfAcceptor(IFSFAcceptor ifsfAcceptor) {
		DitManager.ifsfAcceptor = ifsfAcceptor;
		ProtocolBean protocolBean = new ProtocolBean(
				ProtocolBean.TYPE_IFSF_ACCEPTOR,
				ifsfAcceptor.getPort(),
				ifsfAcceptor.getIp(),
				ifsfAcceptor.getId());
		addProtocolBean(protocolBean);
	}

	public static synchronized void addProtocolBean(ProtocolBean protocolBean) {
		PROTOCOL_BEANS.add(protocolBean);
	}

	/**
	 * 创建模拟器
	 * @param ip
	 * @param port
	 * @return
	 */
	public static ProtocolBean createEmulator(String ip, int port) {
		DITEmulator ditEmulator = new DITEmulator(ip, port);
		addEmulator(ditEmulator);
		ProtocolBean protocolBean = new ProtocolBean(
				ProtocolBean.TYPE_DIT_EMULATOR,
				ditEmulator.getPort(),
				ditEmulator.getIp(),
				ditEmulator.getId());
		addProtocolBean(protocolBean);
		return protocolBean;
	}



	/**
	 * 关闭模拟器
	 * @param id
	 */
	public static void closeDitEmulator(String id) {
		final Iterator<DITEmulator> iterator = EMULATOR_HASH_SET.iterator();
		while (iterator.hasNext()) {
			final DITEmulator ditEmulator = iterator.next();
			if (ditEmulator.getId().equals(id)) {
				ditEmulator.close();
				ditEmulator.getHandler().getWebSocketServer().close();
				break;
			}
		}
		removeEmulator(id);

	}

	public static synchronized void addEmulator(DITEmulator ditEmulator) {
		EMULATOR_HASH_SET.add(ditEmulator);
	}

	public static synchronized void removeEmulator(String id) {
		EMULATOR_HASH_SET.removeIf(ditEmulator -> ditEmulator.getId().equals(id));
		PROTOCOL_BEANS.removeIf(protocolBean -> protocolBean.getId().equals(id));
	}

	public static Collection<ProtocolBean> getAll() {
		return PROTOCOL_BEANS;
	}

	/**
	 * websocket建立连接后，与mina的handler关联，这里hander接收和发送消息，就可以推送到前端了
	 *
	 *
	 * @param webSocketServer
	 * @return
	 */
	public static boolean handlerSetWebSocketServer(WebSocketServer webSocketServer) {
//		判断前端接收的是那种类型数据
		if (webSocketServer.getType() == ProtocolBean.TYPE_DIT_EMULATOR) {
			for (DITEmulator ditEmulator : EMULATOR_HASH_SET) {
				if (ditEmulator.getId().equals(webSocketServer.getId())) {
					ditEmulator.getHandler().setWebSocketServer(webSocketServer);
					webSocketServer.setDitEmulator(ditEmulator);
					return true;
				}
			}

		} else if (webSocketServer.getType() == ProtocolBean.TYPE_IFSF_ACCEPTOR) {
			if (ifsfAcceptor.getId().equals(webSocketServer.getId())) {
				ifsfAcceptor.getHandler().setWebSocketServer(webSocketServer);
				return true;
			}
		} else if (webSocketServer.getType() == ProtocolBean.TYPE_JSON_ACCEPTOR) {
			if (jsonAcceptor.getId().equals(webSocketServer.getId())) {
				jsonAcceptor.getHandler().setWebSocketServer(webSocketServer);
				return true;
			}
		}
		return false;
	}


	/**
	 * websocket连接关闭后mina的handler取消连接，handler接收和发送消息就不用推送数据了，否则会出现异常
	 *
	 * @param webSocketServer
	 * @return
	 */
	public static void handlerRemoveWebSocketServer(WebSocketServer webSocketServer) {
		if (webSocketServer.getType() == ProtocolBean.TYPE_DIT_EMULATOR) {
			for (DITEmulator ditEmulator : EMULATOR_HASH_SET) {
				if (ditEmulator.getId().equals(webSocketServer.getId())) {
					ditEmulator.getHandler().setWebSocketServer(null);
				}
			}

		} else if (webSocketServer.getType() == ProtocolBean.TYPE_IFSF_ACCEPTOR) {
			if (ifsfAcceptor.getId().equals(webSocketServer.getId())) {
				ifsfAcceptor.getHandler().setWebSocketServer(null);
			}
		} else if (webSocketServer.getType() == ProtocolBean.TYPE_JSON_ACCEPTOR) {
			if (jsonAcceptor.getId().equals(webSocketServer.getId())) {
				jsonAcceptor.getHandler().setWebSocketServer(null);
			}
		}
	}
}
