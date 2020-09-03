package com.oket.tankchartdc.mina.json.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.parser.deserializer.ExtraTypeProvider;
import lombok.Data;

import java.util.Date;

/**
 * @author ：lw
 * @date ：Created in 2020/8/5 11:21
 * @description：dit传过来的回罐数据
 */
@Data
public class DitBackToTank implements IDitJsonDataBody{

    /**
     * 油品编码
     */
    @JSONField(alternateNames = "1")
    private String oilCode;
    /**
     * 回罐量
     */
    @JSONField(alternateNames = "2")
    private Double backNum;
    /**
     * 回罐时间YYYY-MM-DD HH:MI:SS
     */
    @JSONField(alternateNames = "3", format = "yyyy-MM-dd HH:mm:ss")
    private Date backTime;

    /**
     * 营业日
     * YYYY-MM-DD
     */
    @JSONField(alternateNames = "4", format = "yyyy-MM-dd")
    private Date date;

    /**
     * 状态类型，目前全部为3。用于区分回灌油、自用油
     */
    @JSONField(alternateNames = "5")
    private String statusType;

    /**
     * 状态编码，即加油用途：105/自用、107/回灌、110自用（内部车辆用油）、111自用（锅炉用油）、112自用（发电用油）
     */
    @JSONField(alternateNames = "6")
    private String statusCode;


    /**
     * 油枪号
     */
    @JSONField(alternateNames = "7")
    private String nozzleNo;

    /**
     * 交易序号
     */
    @JSONField(alternateNames = "8")
    private Double tradeNo;
}
