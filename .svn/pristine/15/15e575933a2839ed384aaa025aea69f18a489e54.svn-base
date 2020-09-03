package com.oket.dispenser.cnpcdit;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IFSFCodecDecoder4Dit extends CumulativeProtocolDecoder {
    private static final Logger logger = LoggerFactory.getLogger(IFSFCodecDecoder4Dit.class);
    /**
     * Implement this method to consume the specified cumulative buffer and
     * decode its content into message(s).
     *
     * @param session The current Session
     * @param in      the cumulative buffer
     * @param out     The {@link ProtocolDecoderOutput} that will receive the decoded message
     * @return <tt>true</tt> if and only if there's more to decode in the buffer
     * and you want to have <tt>doDecode</tt> method invoked again.
     * Return <tt>false</tt> if remaining data is not enough to decode,
     * then this method will be invoked again when more data is
     * cumulated.
     * @throws Exception if cannot decode <tt>in</tt>.
     */
    @Override
    protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
        return false;
    }



}
