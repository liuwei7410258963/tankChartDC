package com.oket.tankchartdc.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.oket.common.base.BaseEntity;
import com.oket.tank4station.entity.DbInventory;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

/**
 * <p>
 * 
 * </p>
 *
 * @author lw
 * @since 2020-07-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("bz_correct_samples")
public class CorrectSamples extends BaseEntity {
    private static final long serialVersionUID = 1L;
    private Integer cycleId;
    private Integer deliveryId;
    //付油组Ids
    private String groupIds;
    //开始液位
    private Long startInventoryId;
    //结束液位
    private Long endInventoryId;
    //当条数据是否是一个新的周期
    private Boolean flag;
    //罐号
    private Integer tankNo;
    //付出数(和上一笔液位之间付出数)
    private Double outNum = 0.0;
    //付出数vt(和上一笔液位之间付出数)
    private Double outNumV20 = 0.0;
    //用油罐温度修正付出数
    private Double deltaVolume;
    //用油枪温度修正付出数
    private Double deltaNozzleVolume;
    //付油组Ids
    @TableField(exist = false)
    private Long[] groupIdArr;
    //开始液位
    @TableField(exist = false)
    private DbInventory startInventory;
    //结束液位
    @TableField(exist = false)
    private DbInventory endInventory;
    //液位变化率
    @TableField(exist = false)
    private Double inventoryRate;
    //付油变化率
    @TableField(exist = false)
    private Double outRate;
    @TableField(exist = false)
    //付出数合计（付出数总和）
    private Double outSum = 0.0;
    @TableField(exist = false)
    //相对体积(实际体积（容积表里高度对应的体积）-本周期的第一条实际体积的差值。）
    private Double volume = 0.0;
    @TableField(exist = false)
    //相对体积差（与上一条数据的相对体积差值）
    private Double volumeDifference = 0.0;
    @TableField(exist = false)
    //误差（付出数合计-体积）
    private Double error = 0.0;
    @TableField(exist = false)
    //误差率（误差/体积，用千分率表示。）
    private Double errorRate = 0.0;
    @TableField(exist = false)
    //段内mm变化率（相对体积差/高度差（与上一条高度的差值））
    private Double segmentRate = 0.0;
    @TableField(exist = false)
    //段内误差（付出数-相对体积差）
    private Double segmentError = 0.0;
    @TableField(exist = false)
    //温度
    private Double temp = 0.0;
    @TableField(exist = false)
    //高度
    private Double level = 0.0;
}
