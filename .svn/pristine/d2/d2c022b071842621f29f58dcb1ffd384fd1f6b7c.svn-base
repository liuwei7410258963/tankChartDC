package com.oket.tankchartdc.mina.ifsf;

import com.oket.tankchartdc.mina.CodecFactory;
import com.oket.tankchartdc.mina.IConnector;
import com.oket.tankchartdc.mina.MinaIoHandler;
import com.oket.tankchartdc.mina.ifsf.codec.IFSFCodecDecoder;
import com.oket.tankchartdc.mina.ifsf.codec.IFSFCodecEncoder;
import com.oket.tankchartdc.service.DitManager;
import com.oket.tankchartdc.service.DitService;
import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.executor.OrderedThreadPoolExecutor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * 接受器
 */
@Service
public class IFSFAcceptor implements IConnector {
	private final static Logger logger = LoggerFactory.getLogger(IFSFAcceptor.class);
	private static final ThreadFactory THREAD_FACTORY = new ThreadFactory() {
		int i = 0;

		@Override
		public Thread newThread(final Runnable r) {
			i++;
			return new Thread(r, "ifsf-" + i);
		}
	};
	//ifsf是否在连接中
	public static boolean ifsfFlag = false;
	@Qualifier("IFSFHandler")
	@Autowired
	public MinaIoHandler handler;
	@Autowired
	DitService ditService;
	private String id;
	private int corePoolSize = 1;
	private int maxPoolSize = 1;
	private int port;

	private NioSocketAcceptor acceptor;

	public IFSFAcceptor() {
	}

	private static String getHostAddress() {
		// 根据网卡取本机配置的IP
		InetAddress inet = null;
		try {
			inet = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			logger.error("无法获取到本地ip：", e);
		}
		//如果获取不到IP地址，还返回IP？？？
		return inet.getHostAddress();
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	private void addLogger(DefaultIoFilterChainBuilder chain)
			throws Exception {
		chain.addLast("logger", new LogFilter());
	}

	@Override
	public int getPort() {
		return port;
	}

	public MinaIoHandler getHandler() {
		return handler;
	}

	public void setHandler(MinaIoHandler handler) {
		this.handler = handler;
	}

	@Override
	public String getIp() {

		String hostAddress = acceptor.getLocalAddress().getAddress().getHostAddress();
		if ("0:0:0:0:0:0:0:1".equals(hostAddress)
				|| "0:0:0:0:0:0:0:0".equals(hostAddress)) {
			return getHostAddress();
		}
		return hostAddress;
	}

	/**
	 * 接收端初始化
	 * 1.设定过滤和编码解码器
	 * 2.设定处理器
	 * 3.绑定监听端口
	 * 4.建立监听
	 */
	@PostConstruct
	public void init() {
		try {
			acceptor = new NioSocketAcceptor();
			DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();
			Executor exec = new OrderedThreadPoolExecutor(corePoolSize, maxPoolSize, 60, TimeUnit.SECONDS, THREAD_FACTORY);
			acceptor.getFilterChain().addLast("threadPool",
					new ExecutorFilter(exec));

			chain.addLast("codec", new ProtocolCodecFilter(
					new CodecFactory(new IFSFCodecDecoder(), new IFSFCodecEncoder())));

			addLogger(chain);
			acceptor.setHandler(handler);
			port = ditService.selectIfsf();
			acceptor.bind(new InetSocketAddress(port));
			buildId();
			DitManager.setIfsfAcceptor(this);

			ifsfFlag = true;
			logger.info("ifsf端口监听开启，端口是：" + port + ";ip:" + getIp());
		} catch (Exception ex) {
			logger.error("建立连接错误：", ex);
		}

	}


//	private void portInit() {
//		Integer port = ditService.selectIfsf();
//		if (port == null) {
//			this.port = IFSF_port;
//			ditService.setIfsfPort(this.port);
//		} else {
//			this.port = port;
//		}
//	}

	/**
	 * 接收端重启
	 * 1.设定过滤和编码解码器
	 * 2.设定处理器
	 * 3.绑定监听端口
	 * 4.建立监听
	 */
	public void restart() {
		if (acceptor != null && acceptor.isActive()) {
			close();
		}
		init();
	}


	public void close() {
		ifsfFlag = false;
		if (acceptor != null) {
			acceptor.dispose();
		} else {
			logger.error("acceptor为空，无法关闭");
		}
	}
}



