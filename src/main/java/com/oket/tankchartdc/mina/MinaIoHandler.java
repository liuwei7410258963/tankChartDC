package com.oket.tankchartdc.mina;

import com.oket.tankchartdc.websocket.WebSocketServer;
import org.apache.mina.core.service.IoHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @description:
 * @author: SunBiaoLong
 * @create: 2019-11-14 18:42
 **/
public interface MinaIoHandler extends IoHandler {
	Logger logger = LoggerFactory.getLogger(MinaIoHandler.class);

	/**
	 * 设置websocket
	 *
	 * @param websocketServer
	 */
	void setWebSocketServer(WebSocketServer websocketServer);

	WebSocketServer getWebSocketServer();

	default void websocketSendMessage(String content) {
		final WebSocketServer webSocketServer = getWebSocketServer();
		if (webSocketServer != null) {
			try {
				webSocketServer.sendMessage(content);
			} catch (IOException e) {
				logger.error("推送消息失败,原消息是" + content, e);
			}
		}
	}
}
