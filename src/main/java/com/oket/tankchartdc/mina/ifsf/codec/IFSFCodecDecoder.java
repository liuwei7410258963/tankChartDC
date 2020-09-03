package com.oket.tankchartdc.mina.ifsf.codec;

import com.oket.tankchartdc.mina.dit.DitEmulatorProcess;
import com.oket.util.StringExUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description: ifsf解码器
 * @author: SunBiaoLong
 * @create: 2019-10-15 11:44
 **/
@Slf4j
public class IFSFCodecDecoder extends CumulativeProtocolDecoder {
	public static final int HEADER_LENGTH = 8;
	private static final Logger logger = LoggerFactory.getLogger(IFSFCodecDecoder.class);
	private static IFSFParser parser = new IFSFParser();

	/**
	 * @param session
	 * @param ioBuffer
	 * @param out
	 * @return 返回true说明收到的能够正常的解码了。 doDecode方法之后正常是调用decode方法，这里并没有使用decode
	 * 返回false说明收到的数据还不够，需要等下次读取完毕之后再进行。
	 * @throws Exception
	 */
	@Override
	protected boolean doDecode(IoSession session, IoBuffer ioBuffer, ProtocolDecoderOutput out) throws Exception {
		logger.debug("in.remaining :" + ioBuffer.remaining() + ",receive message："
				+ StringExUtils.byteArrayToHexString(ioBuffer.array(), ioBuffer.limit())
				+ ";mark:" + ioBuffer.position());
		if (ioBuffer.remaining() <= HEADER_LENGTH) {
			logger.debug("data length小于消息头长度,传入消息长度:" + ioBuffer.remaining());
			return false;
		}
		ioBuffer.mark();
		byte[] headerBytes = new byte[HEADER_LENGTH];
		ioBuffer.get(headerBytes, 0, HEADER_LENGTH);
		IFSFHeader ifsfHeader = parser.parseHeader(headerBytes);
		logger.debug("bodyLength:" + ifsfHeader.getBodyLength() + ";sendSubnet:" + ifsfHeader.getSendSubnet() + ";sendNode:" + ifsfHeader.getSendNode());
		//判断是否接受数据完整
		if (ifsfHeader.getBodyLength() <= ioBuffer.remaining()
				&& ifsfHeader.getBodyLength() > 0) {
			String type = ifsfHeader.getMessageStatus().getType();
			logger.debug("消息类型：" + type);
			if (type.equals(MessageStatus.ACTIVE_MESSAGE_WITH_CONFIRMATION)
					|| type.equals(MessageStatus.ACTIVE_MESSAGE_WITHOUT_CONFIRMATION)
					|| type.equals(MessageStatus.RESPONSE_MESSAGE)) {
				byte[] bodyBytes = new byte[ifsfHeader.getBodyLength()];
				ioBuffer.get(bodyBytes);
				final boolean receivedAllDitEmulator = receivedAllDitEmulator(ioBuffer, ifsfHeader);
				if (!receivedAllDitEmulator) {
					return false;
				}
				IFSFMessage ifsfMessage = parser.parseMessage(bodyBytes, ifsfHeader);
				if (ifsfMessage != null) {
					ifsfMessage.setOriginBytes(headerBytes, bodyBytes);
					out.write(ifsfMessage);
				}else {
					logger.error("解析ifsf数据失败");
				}
				//判断是否后面还有数据
				if (ioBuffer.hasRemaining()) {
					return true;
				}
			} else {
				//其他类型消息，不用管
				byte[] bodyBytes = new byte[ifsfHeader.getBodyLength()];
				ioBuffer.get(bodyBytes);
				if (ioBuffer.hasRemaining()) {
					return true;
				}
			}

		} else {
			if (ifsfHeader.getBodyLength() == 0) {
				logger.error("body:" + 0);
			} else {
				ioBuffer.reset();
				return false;
			}
		}

		return false;
	}

	/**
	 * 是否获取全部的dit模拟数据
	 * 如果没启用,直接返回true
	 *
	 * @param in
	 * @param ifsfHeader
	 * @return
	 */
	private boolean receivedAllDitEmulator(IoBuffer in, IFSFHeader ifsfHeader) {
		//是否使用的dit模拟器
		boolean isDitEmulatorEnable = DitEmulatorProcess.isEnableDitFileEmulator();
		if (isDitEmulatorEnable) {
			if (in.remaining() < 2) {
				in.reset();
				return false;
			}
			final byte gapLength = in.get();
			final byte millisLength = in.get();
			if (in.remaining() < gapLength + millisLength) {
				in.reset();
				return false;
			}
			byte[] gapBytes = new byte[gapLength];
			in.get(gapBytes);
			byte[] millisBytes = new byte[millisLength];
			in.get(millisBytes);
//			数据时间间隔
			long gap = Long.parseLong(new String(gapBytes));
			//当前时间
			long millis = Long.parseLong(new String(millisBytes));
			ifsfHeader.setMillis(millis);
			ifsfHeader.setMillisGap(gap);
			ifsfHeader.setDitEmulatorEnable(true);
			logger.debug("dit模拟器发送时间：" + millis);
		}
		return true;
	}

}
