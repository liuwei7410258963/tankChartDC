package com.oket.tankchartdc.mina.dit;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import java.nio.charset.StandardCharsets;

/**
 * @description:
 * @author: SunBiaoLong
 * @create: 2019-11-14 21:18
 **/
public class DITEmulatorDncoder extends CumulativeProtocolDecoder {
	@Override
	protected boolean doDecode(IoSession ioSession, IoBuffer ioBuffer, ProtocolDecoderOutput out) throws Exception {
		final int remaining = ioBuffer.remaining();
		byte[] bytes = new byte[remaining];
		ioBuffer.get(bytes);
		String message = new String(bytes, StandardCharsets.UTF_8);
		out.write(message);
		return false;
	}
}
