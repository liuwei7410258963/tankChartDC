package com.oket.tankchartdc.mina.ifsf.codec;

import com.oket.util.StringExUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import java.nio.charset.StandardCharsets;

/**
 * @description: 编码器
 * @author: SunBiaoLong
 * @create: 2019-10-15 11:45
 **/
@Slf4j
public class IFSFCodecEncoder implements ProtocolEncoder {

	@Override
	public void encode(IoSession ioSession, Object msg, ProtocolEncoderOutput out) throws Exception {
		byte[] send = null;
		if (msg instanceof byte[]) {
			send = (byte[]) msg;
			log.info("out.write==" + StringExUtils.byteArrayToHexString(send));
		} else {
			send = msg.toString().getBytes(StandardCharsets.UTF_8);
			log.info("out.write==" + msg.toString());
		}

		//根据报文长度开辟空间
		IoBuffer buff = IoBuffer.allocate(send.length);
		//设置为可自动扩展空间
		buff.setAutoExpand(true);
		//将报文中的信息添加到buff中
		buff.put(send);
		buff.flip();
		//将报文发送出去
		out.write(buff);
		out.flush();
		buff.free();
	}

	@Override
	public void dispose(IoSession ioSession) throws Exception {

	}
}
