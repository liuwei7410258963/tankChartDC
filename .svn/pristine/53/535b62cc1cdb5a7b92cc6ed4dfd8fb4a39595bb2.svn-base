package com.oket.tankchartdc.mina.ifsf;

import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description: 日志过滤器
 * @author: Longer
 * @create: 2019-11-08 20:52
 **/
public class LogFilter extends IoFilterAdapter {
	private Logger logger = LoggerFactory.getLogger(LogFilter.class);


	/**
	 * 解析传来的数据，格式化为json数据
	 *
	 * @param nextFilter
	 * @param session
	 * @param message
	 * @throws Exception
	 */
	@Override

	public void messageReceived(NextFilter nextFilter, IoSession session, Object message) throws Exception {
		if (message instanceof String) {
			logger.info((String) message);
		}
		super.messageReceived(nextFilter, session, message);
	}
}
