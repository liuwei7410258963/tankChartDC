package com.oket.tankchartdc.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.oket.dispenser.BzNozzleOutGroup;
import com.oket.tank4station.LevelState;
import com.oket.tank4station.entity.DbInventoryTrace;
import com.oket.tankchartdc.BackToTank;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author ：lw
 * @date ：Created in 2020/5/8 11:18
 * @description：校正实体
 */
@Data
public class CorrectingTableDTO {
    //周期id
    private Long cycleId;
    //轨迹id
    private Long traceId;
    //付油组Id
    private Long groupId;
    //高度
    private Double level = 0.0;
    //温度
    private Double temp = 0.0;
    //付出数(和上一笔液位之间付出数)
    private Double outNum = 0.0;
    //付出数合计（付出数总和）
    private Double outSum = 0.0;
    //回罐量
    private Double backNum = 0.0;
    //相对体积(实际体积（容积表里高度对应的体积）-本周期的第一条实际体积的差值。）
    private Double volume = 0.0;
    //相对体积差（与上一条数据的相对体积差值）
    private Double volumeDifference = 0.0;
    //误差（付出数合计-体积）
    private Double error = 0.0;
    //误差率（误差/体积，用千分率表示。）
    private Double errorRate = 0.0;
    //段内mm变化率（相对体积差/高度差（与上一条高度的差值））
    private Double segmentRate = 0.0;
    //段内误差（付出数-相对体积差）
    private Double segmentError = 0.0;
    //当条数据是否是一个新的周期
    private boolean flag = false;
    //轨迹的开始id
    private long startId;
    //轨迹的开始时间
    private Date startTime;
    //轨迹的结束id
    private long endId;
    //轨迹的结束时间
    private Date endTime;
    //罐号
    private long tankNo;
    //液位状态
    private LevelState traceLevelstate;
    //轨迹的开始时间
    private String traceStartTime;
    //轨迹的结束时间
    private String traceEndTime;
    //周期的Id,导出数据用
    private long deliveryId;
    //修正到罐温的vt合计
    private Double tankTempVtSum;

}
