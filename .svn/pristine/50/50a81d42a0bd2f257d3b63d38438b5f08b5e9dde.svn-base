package com.oket.transport.protocol;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.concurrent.Callable;



public interface BizProtocol{
    /**
     * 协议的交互模式：
     * 1、等待接收，且响应接收成功；WAIT_RECEIVER_ECHO
     * 2、等待接收，但不响应返回；WAIT_RECEIVER_NO_ECHO
     * 3、发送并等待返回响应；SEND_WAIT_ECHO
     * 4、发送，但不返回；SEND_NOECHO
     */
    enum ActionModeType{
        WAIT_RECEIVER_ECHO,
        WAIT_RECEIVER_NO_ECHO,
        SEND_WAIT_ECHO,
        SEND_NOECHO
    }
    /**
     * 协议标识，一个应用中需要唯一
     * @return
     */
    public int getID();
    public int getTypeID();

    /**
     * 建立协议帧
     * @return
     */
    public  boolean encodeFrame();

    /**
     * 协议名称
     * @return
     */
    public  String getTypeName();

    /**
     * 解析协议
     * @return
     */

    public List<Object> decodeFrame();

    /**
     * 一个完整帧内容
     * @return
     */
    public ByteBuffer getProtocolContent();
    public  boolean addContent(byte []newByte);

    /**
     * 前后两个动作的等待时间（秒）
     * @return
     */
    public  int getWaitEchoSecond();
    /**
     * 前后两个动作的等待时间（秒）
     * @return
     */
    public  int getWaitEchoMillisecond();

    /**
     * 指令之间的最小间隔时间，即每个Protocol之间必须等待的最小时间
     * @return
     */
    public  int minIntervalSecond();

    /**
     * 协议的最大间隔时间
     * @return
     */
    public  int maxIntervalMillisecond();

    public  List<Callable> getRoutines();

    public  ProtocolFrame getEchoMessage();
    public ProtocolFrame getWriteOutMessage();

}
