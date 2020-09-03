package com.oket.station;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
@Data
public class AbstractAddress implements Serializable, Address {
    //每个站只有一条数据。默认id为1
    private long id=1L;
    /**
     * 主键id
     */
    //每个站只有一条数据。默认id为1
    private long stationId =1L;
    private String detailInfo;
    private AddressType addressType;
    /**地址版本，如果地址名称改变，可以置为1，2，3....当前版本永远为0*/
    private int version;
    /**生成时间或者更新时间*/
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
