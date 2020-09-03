package com.oket.tankchartdc.mina.dit.listener;

import org.apache.mina.core.service.IoService;
import org.apache.mina.core.service.IoServiceListener;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * @program: car
 * @description: 监听器--用于监听连接断开
 * @author: SunBiaoLong
 * @create: 2018-12-14 17:00
 **/
public class IoListenerAdaptor implements IoServiceListener {

	@Override
	public void serviceActivated(IoService ioService) throws Exception {

	}

	@Override
	public void serviceIdle(IoService ioService, IdleStatus idleStatus) throws Exception {

	}

	@Override
	public void serviceDeactivated(IoService ioService) throws Exception {

	}

	@Override
	public void sessionCreated(IoSession ioSession) throws Exception {

	}

	@Override
	public void sessionClosed(IoSession ioSession) throws Exception {

	}

	@Override
	public void sessionDestroyed(IoSession ioSession) throws Exception {

	}
}
