package com.oket.tankchartdc.mina.json.codec;

import com.oket.util.StringExUtils;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description:
 * @author: Longer
 * @create: 2019-11-08 20:09
 **/
public class JSONEncoder extends ProtocolEncoderAdapter {
	private static final Logger logger = LoggerFactory
			.getLogger(JSONEncoder.class);

	@Override
	public void encode(IoSession session, Object message,
	                   ProtocolEncoderOutput out) throws Exception {

		if (message instanceof DitJsonResponse) {
			DitJsonResponse ditJsonResponse = (DitJsonResponse) message;

			byte[] bytes = DitJsonResponse.pack(ditJsonResponse);

			IoBuffer buffer = IoBuffer.allocate(18);
			buffer.setAutoExpand(true);

			buffer.put(bytes);
			buffer.flip();
			logger.info("send message："
					+ StringExUtils.byteArrayToHexString(bytes));
			out.write(buffer);
			out.flush();
			buffer.free();
		}
	}
}