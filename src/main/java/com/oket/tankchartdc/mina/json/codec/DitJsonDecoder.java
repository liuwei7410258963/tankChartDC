package com.oket.tankchartdc.mina.json.codec;

import com.oket.tankchartdc.mina.dit.DitEmulatorProcess;
import com.oket.tankchartdc.mina.parser.ParserException;
import com.oket.util.StringExUtils;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;


/**
 * @description: json数据解析
 * @author: Longer
 * @create: 2019-11-08 20:08
 **/
public class DitJsonDecoder extends CumulativeProtocolDecoder {
    private static final Logger logger = LoggerFactory.getLogger(DitJsonDecoder.class);

    @Override
    protected boolean doDecode(IoSession session, IoBuffer in,
                               ProtocolDecoderOutput out) throws Exception {

        logger.debug("in.remaining :" + in.remaining() + ",receive message："
                + StringExUtils.byteArrayToHexString(in.array(), in.limit()));
        //判断长度是不是够
        if (in.remaining() < 5) {
            return false;
        }
        byte[] startSignBytes = new byte[5];
        //？
        in.mark();
        in.get(startSignBytes, 0, 5);
        final String startSign = new String(startSignBytes, StandardCharsets.UTF_8);

        if (in.remaining() <= 14) {
            in.reset();
            return false;
        }
        if (startSign.equals(DitJsonHeader.START_SIGN)) {
            byte[] ditJsonHeadBytes = new byte[14];
            in.get(ditJsonHeadBytes, 0, 14);
            DitJsonParser ditJsonParser = new DitJsonParser();
            final DitJsonHeader ditJsonHeader = ditJsonParser.parseHeader(ditJsonHeadBytes);
            //判断是否接受完整，如果没有带接收完整后重新读取
            if (in.remaining() < ditJsonHeader.getBodyLength()) {
                in.reset();
                return false;
            } else {
                byte[] jsonBytes = new byte[ditJsonHeader.getBodyLength()];
                in.get(jsonBytes);
                if (!receivedAllDitEmulator(in, ditJsonHeader)) {
                    return false;
                }
                String json = new String(jsonBytes, StandardCharsets.UTF_8);
                logger.debug("json:" + json);
                DitJsonMessage ditJsonMessage = ditJsonParser.parseMessage(jsonBytes, ditJsonHeader);
                setOriginBytes(startSignBytes, ditJsonHeadBytes,jsonBytes, ditJsonMessage);
                out.write(ditJsonMessage);
                if (in.hasRemaining()) {
                    return true;
                }
            }
        } else {
            int remaining = in.remaining();
            byte[] temp = new byte[remaining];
            in.get(temp);
            logger.error("5个字节的报文起始符，统一规定为字符串 20101;收到的是：" + StringExUtils.byteArrayToHexString(startSignBytes)
                    + ",剩余所有数据是：" + StringExUtils.byteArrayToHexString(temp));
            return false;
        }
        return false;
    }

    /**
     *
     * @param startSignBytes
     * @param jsonBytes
     * @param ditJsonMessage
     */
    private void setOriginBytes(byte[] startSignBytes, byte[]ditJsonHeadBytes,byte[] jsonBytes, DitJsonMessage ditJsonMessage) {
        byte[] originBytes = new byte[startSignBytes.length + ditJsonHeadBytes.length+jsonBytes.length];
        System.arraycopy(startSignBytes, 0, originBytes, 0, startSignBytes.length);
        System.arraycopy(ditJsonHeadBytes, 0, originBytes, startSignBytes.length, ditJsonHeadBytes.length);
        System.arraycopy(jsonBytes, 0, originBytes, startSignBytes.length+ditJsonHeadBytes.length, jsonBytes.length);
        ditJsonMessage.setOriginHex(StringExUtils.byteArrayToHexString(originBytes));
    }

    /**
     * 是否获取全部的dit模拟数据
     * 如果没启用,直接返回true
     *
     * @param in
     * @param ditJsonHeader
     * @return
     */
    private boolean receivedAllDitEmulator(IoBuffer in, DitJsonHeader ditJsonHeader) {
        boolean isDitEmulatorEnable = DitEmulatorProcess.isEnableDitFileEmulator();
        if (isDitEmulatorEnable) {
            if (in.remaining() < 1) {
                in.reset();
                return false;
            }
            final byte gapLength = in.get();
            if (in.remaining() < gapLength) {
                in.reset();
                return false;
            }

            byte[] gapBytes = new byte[gapLength];
            in.get(gapBytes);
//			数据时间间隔
            long gap = Long.parseLong(new String(gapBytes));
            ditJsonHeader.setMillisGap(gap);
            ditJsonHeader.setDitEmulatorEnable(true);
            logger.debug("dit模拟器发送时间：" + gap);
        }
        return true;
    }


}
