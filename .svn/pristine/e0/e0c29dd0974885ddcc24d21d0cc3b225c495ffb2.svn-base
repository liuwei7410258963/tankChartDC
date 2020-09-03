package com.oket.tankchartdc.mina.dit;

import com.oket.tankchartdc.mina.MinaIoHandler;
import com.oket.tankchartdc.service.DitManager;
import com.oket.tankchartdc.websocket.WebSocketServer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.FilterEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DITEmulatorHandler extends IoHandlerAdapter implements MinaIoHandler {
	private final static Logger logger = LoggerFactory.getLogger(DITEmulatorHandler.class);

	private WebSocketServer webSocketServer;

	@Override
	public WebSocketServer getWebSocketServer() {
		return webSocketServer;
	}

	@Override
	public void setWebSocketServer(WebSocketServer webSocketServer) {
		this.webSocketServer = webSocketServer;
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
//        super.sessionCreated(session);

	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
//        super.sessionOpened(session);
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		final String ditEmulatorId = (String) session.getAttribute(DITEmulator.ID);
		session.closeNow();
		DitManager.removeEmulator(ditEmulatorId);
		logger.info("session关闭");
//        super.sessionClosed(session);
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
//        super.sessionIdle(session, status);
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		logger.error("dit模拟器连接异常", cause);
		System.out.println(cause.toString());
//        super.exceptionCaught(session, cause);
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		if (message instanceof String) {
			websocketSendMessage("received:" + message.toString());
			logger.info("接收到数据:" + message.toString());
		}

	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		websocketSendMessage("send:" + message.toString());
		logger.info("发送数据:" + message.toString());
//        super.messageSent(session, message);
	}

	@Override
	public void inputClosed(IoSession session) throws Exception {
//        super.inputClosed(session);
	}

	@Override
	public void event(IoSession session, FilterEvent event) throws Exception {
//        super.event(session, event);
	}

}
