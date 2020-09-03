package com.oket.tankchartdc.websocket;

import com.oket.tankchartdc.mina.dit.DITEmulator;
import com.oket.tankchartdc.service.DitManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @description: websocket server
 * @author: Longer
 * @create: 2019-11-11 14:22
 **/
@ServerEndpoint("/dit/websocket/{type}/{id}")
@Component
public class WebSocketServer {

	private final static Logger log = LoggerFactory.getLogger(WebSocketServer.class);
	/**
	 * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
	 */
	private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<>();

	/**
	 * 与某个客户端的连接会话，需要通过它来给客户端发送数据
	 */
	private Session session;

	//接收sid
	private String id;
	private int type;

	private boolean isAlive = true;
	private DITEmulator ditEmulator;

	/**
	 * 群发自定义消息
	 */
	public static void sendInfo(String message, @PathParam("id") String id, @PathParam("type") int type) throws IOException {
		log.info("推送消息到窗口" + id + "，推送内容:" + message);
		for (WebSocketServer item : webSocketSet) {
			try {
				//这里可以设定只推送给这个sid的，为null则全部推送
				if (id == null) {
					item.sendMessage(message);
				} else if (item.id.equals(id)) {
					item.sendMessage(message);
				}
			} catch (IOException e) {
				log.error("", e);
			}
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public DITEmulator getDitEmulator() {
		return ditEmulator;
	}

	public void setDitEmulator(DITEmulator ditEmulator) {
		this.ditEmulator = ditEmulator;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public void close() {
		try {
			this.session.close();
			isAlive = false;
			DitManager.handlerRemoveWebSocketServer(this);
		} catch (IOException e) {
			log.error("session close IO异常");
		}
	}

	/**
	 * 连接建立成功调用的方法
	 */
	@OnOpen
	public void onOpen(Session session, @PathParam("type") int type, @PathParam("id") String id) {
		this.session = session;
		webSocketSet.add(this);
		this.id = id;
		this.type = type;
		final boolean b = processHandler(this);
		if (!b) {
			log.error("未找到接收器或模拟器，id：" + id + ",type:" + type);
			close();
		} else {
			//加入set中

			String typeString = getString(type);
			log.info(typeString + "建立连接:id" + id);
			try {
				sendMessage(typeString + id + "连接成功");
			} catch (IOException e) {
				log.error("websocket IO异常");
			}
		}
	}

	private boolean processHandler(WebSocketServer webSocketServer) {
		return DitManager.handlerSetWebSocketServer(webSocketServer);
	}

	private String getString(@PathParam("type") int type) {
		String typeString = null;
		switch (type) {
			case ProtocolBean.TYPE_DIT_EMULATOR:
				typeString = "dit模拟器";
				break;
			case ProtocolBean.TYPE_IFSF_ACCEPTOR:
				typeString = "IFSF接收器";
				break;
			case ProtocolBean.TYPE_JSON_ACCEPTOR:
				typeString = "JSON接收器";
				break;
			default:
				break;
		}
		return typeString;
	}

	/**
	 * 连接关闭调用的方法
	 */
	@OnClose
	public void onClose() {
		webSocketSet.remove(this);
		log.error(getString(this.type) + id + "连接关闭！");
		close();
	}

	/**
	 * 收到客户端消息后调用的方法
	 *
	 * @param message 客户端发送过来的消息
	 */
	@OnMessage
	public void onMessage(String message, Session session) {

		log.info("收到来自窗口" + id + "的信息:" + message);
		if (ditEmulator != null) {
			final boolean sendSuccess = ditEmulator.sendMessage(message);
			if (!sendSuccess) {
				ditEmulator.sendMessage("dit模拟器与接收器的连接已经断开");
				return;
			}
		} else {
			ditEmulator.sendMessage("dit模拟器与接收器的连接已经断开");
		}
	}

	/**
	 * @param session
	 * @param error
	 */
	@OnError
	public void onError(Session session, Throwable error) {
		log.error("发生错误", error);
	}

	/**
	 * 实现服务器主动推送
	 */
	public void sendMessage(String message) throws IOException {
		if (session != null && session.isOpen()) {
			synchronized (session) {
				if (isAlive) {
					this.session.getBasicRemote().sendText(message);
				}
			}
		}

	}
}
