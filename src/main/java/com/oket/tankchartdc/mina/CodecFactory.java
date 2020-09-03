package com.oket.tankchartdc.mina;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

/**
 * @description: 编码解码工厂
 * @author: Longer
 * @create: 2019-11-08 20:12
 **/
public class CodecFactory implements ProtocolCodecFactory {
	private ProtocolDecoder decoder;
	private ProtocolEncoder encoder;

	public CodecFactory(ProtocolDecoder decoder, ProtocolEncoder encoder) {
		this.decoder = decoder;
		this.encoder = encoder;
	}

	@Override
	public ProtocolDecoder getDecoder(IoSession session) throws Exception {
		return this.decoder;
	}

	@Override
	public ProtocolEncoder getEncoder(IoSession session) throws Exception {
		return this.encoder;
	}
}
