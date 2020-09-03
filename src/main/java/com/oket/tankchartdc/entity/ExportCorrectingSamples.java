package com.oket.tankchartdc.entity;

import com.oket.tank4station.Inventory;
import com.oket.tank4station.LevelState;
import com.oket.tank4station.entity.DbInventory;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author ：lw
 * @date ：Created in 2020/5/8 11:18
 * @description：校正实体
 */
@Data
public class ExportCorrectingSamples {
    //周期id
    private Long cycleId;
    //付油组Ids
    private Long[] groupIds;
    //开始液位
    private DbInventory startInventory;
    //结束液位
    private DbInventory endInventory;
    //付出数(和上一笔液位之间付出数)
    private Double outNum = 0.0;
    //付出数 修正到罐温的vt(和上一笔液位之间付出数)
    private Double outNumVt = 0.0;
    //回罐量
    private Double backNum = 0.0;
    //当条数据是否是一个新的周期
    private boolean flag = false;
    //罐号
    private long tankNo;
    //周期的Id,导出数据用
    private long deliveryId;
    //————————————返回的——————————
    //油罐内温度变化修正体积
    private Double deltaVolume;
    //油枪温度变化修正体积
    private Double deltaNozzleVolume;

}
