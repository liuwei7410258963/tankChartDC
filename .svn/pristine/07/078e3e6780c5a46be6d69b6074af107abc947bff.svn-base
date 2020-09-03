package com.oket.tankchartdc.mina.ifsf.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.oket.dispenser.NozzleState;
import com.oket.tankchartdc.mina.ifsf.codec.FuelingPointDB;
import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * @description: 加油点及逻辑油枪状态
 * @author: Longer
 * @create: 2019-11-12 19:02
 **/
@Data
public class FuelingPointAndHoseStatus implements IDitIFSFDataBody {
    /**
     * 开始加油
     */
    public static final byte STARTED_STATUS = 6;
    /**
     * 加油中
     */
    public static final byte FUELING_STATUS = 8;
    /**
     * 空闲中
     */
    public static final byte IDLE_STATUS = 3;

    private byte fuelingPointStatus;
    private int hoseStatus;
    private Integer hostId;
    private String desc;

    private String dispenserDesc;
    private String type;
    /**
     * 数据时间
     *
     * @see IDitIFSFDataBody#TIME_FIELD_NAME
     * 属性名不可以轻易修改
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date time;

    public static FuelingPointAndHoseStatus init(Map<String, Object> dataMap) {
        FuelingPointAndHoseStatus fuelingPointAndHoseStatus = new FuelingPointAndHoseStatus();
        fuelingPointAndHoseStatus.setFuelingPointStatus((Byte) dataMap.get(FuelingPointDB.FUELING_POINT_STATUS));
        if (fuelingPointAndHoseStatus.getFuelingPointStatus() == STARTED_STATUS) {
            fuelingPointAndHoseStatus.dispenserDesc = "开始加油";
        } else if (fuelingPointAndHoseStatus.getFuelingPointStatus() == FUELING_STATUS) {
            fuelingPointAndHoseStatus.dispenserDesc = "加油中";
        } else if (fuelingPointAndHoseStatus.getFuelingPointStatus() == IDLE_STATUS) {
            fuelingPointAndHoseStatus.dispenserDesc = "加油结束或空闲";
        } else {
            fuelingPointAndHoseStatus.setDispenserDesc(fuelingPointAndHoseStatus.fuelingPointStatus + "");
        }
        fuelingPointAndHoseStatus.setHoseStatus((Integer) dataMap.get(FuelingPointDB.LOGIC_HOSE_STATUS));
        if (fuelingPointAndHoseStatus.getTime()==null){
            fuelingPointAndHoseStatus.setTime(new Date());
        }
        return fuelingPointAndHoseStatus;
    }

    /**
     * 转为油枪状态业务类
     *
     * @param hoseStatus
     * @return
     */
    public static NozzleState convertToState(FuelingPointAndHoseStatus hoseStatus) {
        NozzleState nozzleState
                = new NozzleState(hoseStatus.getHostId(), NozzleState.State.getState(hoseStatus.fuelingPointStatus));
        nozzleState.setTime(hoseStatus.getTime());
        return nozzleState;
    }


}
