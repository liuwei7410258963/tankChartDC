package com.oket.tankchartdc.mina.dit;

import com.oket.util.StringExUtils;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description:
 * @author: SunBiaoLong
 * @create: 2019-11-14 21:18
 **/
public class DITEmulatorEncoder extends ProtocolEncoderAdapter {
	private static final Logger logger = LoggerFactory
			.getLogger(DITEmulatorEncoder.class);

	@Override
	public void encode(IoSession ioSession, Object o, ProtocolEncoderOutput out) throws Exception {
		if (o instanceof String) {
			String message = (String) o;
			final byte[] bytes = StringExUtils.hexStringToByte(message);

			IoBuffer buffer = IoBuffer.allocate(18);
			buffer.setAutoExpand(true);

			buffer.put(bytes);
			buffer.flip();
			logger.info("send message："
					+ StringExUtils.byteArrayToHexString(bytes));
			out.write(buffer);
			out.flush();
			buffer.free();
		}else if (o instanceof AbstractDitMessage){
			AbstractDitMessage abstractDitMessage = (AbstractDitMessage) o;
			IoBuffer buffer = IoBuffer.allocate(100);
			buffer.setAutoExpand(true);
			final byte[] encode = abstractDitMessage.encode();
			buffer.put(encode);
			buffer.flip();
			logger.info("send message："
					+ StringExUtils.byteArrayToHexString(encode));
			out.write(buffer);
			out.flush();
			buffer.free();
		}
	}
}
