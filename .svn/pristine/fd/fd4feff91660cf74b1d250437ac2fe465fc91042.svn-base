package com.oket.tankchartdc.mina.dit;

import com.oket.tankchartdc.mina.CodecFactory;
import com.oket.tankchartdc.mina.IConnector;
import com.oket.tankchartdc.mina.MinaIoHandler;
import com.oket.tankchartdc.mina.dit.listener.IoListenerAdaptor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.mina.core.RuntimeIoException;
import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Slf4j
public class DITEmulator implements IConnector {
	public static final String ID = "ID";
	private IoSession sessionLinkedRemoteServer;
	private NioSocketConnector connector;
	private MinaIoHandler handler;
	private String id;
	private String ip;
	private int port;
	private IoSession session;
	public boolean open;

	public DITEmulator(String ip, int port) {
		this.ip = ip;
		this.port = port;
		init();
		buildId();
	}


	public void init() {
		connect(ip, port);
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public void connect(String ip, int port) {
		SocketAddress address = new InetSocketAddress(ip, port);
		if (session != null && session.isConnected()) {
			throw new IllegalStateException(
					"Already connected. Disconnect first.");
		}

		try {
			connector = new NioSocketConnector();
			IoFilter CODEC_FILTER = new ProtocolCodecFilter(
					new CodecFactory(new DITEmulatorDncoder(), new DITEmulatorEncoder()));
			connector.getFilterChain().addLast("codec", CODEC_FILTER);
			handler = new DITEmulatorHandler();
			connector.setHandler(handler);
			ConnectFuture future1 = connector.connect(address);
//			listener();

			future1.awaitUninterruptibly();
			if (!future1.isConnected()) {

			}
			session = future1.getSession();
			session.setAttribute(ID, this.id);
			buildId();
			this.setOpen(true);
		} catch (Exception e) {
			this.setOpen(false);
			log.error("建立连接出错了，原因是"+e.getMessage(),e);
		}
	}

	/**
	 * 断线重连监听
	 */
	private void listener() {
		//断线重连机制
		connector.addListener(new IoListenerAdaptor() {
			@Override
			public void sessionDestroyed(IoSession ioSession) throws Exception {
				log.info("连接断开,正在重连...");
				for (; ; ) {
					try {
						Thread.sleep(3000);
						ConnectFuture future = connector.connect();
						future.awaitUninterruptibly();
						sessionLinkedRemoteServer = future.getSession();
						if (sessionLinkedRemoteServer.isConnected()) {
							log.info("断线重连成功");
							break;
						}
					} catch (Exception e) {
						log.info("重连服务器登录失败,3秒再连接一次:" + e.getMessage());
					}

				}
			}
		});

		//初始连接..如果连接不成功那么5s重连
		for (; ; ) {
			try {

				ConnectFuture future = connector.connect();
				//等待连接创建成功
				future.awaitUninterruptibly();
				//获取会话
				sessionLinkedRemoteServer = future.getSession();
				if (sessionLinkedRemoteServer.isConnected()) {
					log.info("连接外部服务器成功,时间:"
							+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

					//	sessionLinkedRemoteServer.write(getLoginInfo());
					break;
				}
			} catch (RuntimeIoException e) {
				log.error("连接外部服务器失败,时间:"
						+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
						+ "异常内容" + e.getMessage() + "  正在尝试重连(5s一次)", e
				);
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e1) {
					log.error(e.getMessage(), e);
				}
			}

		}
	}


	public boolean sendMessage(String message) {
		if (message == null || message.isEmpty()
				|| session == null || !session.isActive()) {
			return false;
		}
		session.write(message);
		return true;
	}

	public void close() {
		connector.dispose();
		session.closeNow();
	}
}
