package com.oket.station;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.oket.common.base.BaseEntity;
import com.oket.common.base.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Objects;

/**
 * <p>
 * 
 * </p>
 *
 * @author lw
 * @since 2020-08-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("bz_hand_over")
public class HandOver extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 罐号
     */
    private String tankNo;

    /**
     * 交班时间
     */
    private String time;

    /**
     * 类型（班结计量 卸前计量 卸后计量）
     */
    private String type;

    /**
     * 原发数量
     */
    private Double originalNum;

    /**
     * 实收数量(v20)
     */
    private Double receivedNum;

    /**
     * 溢耗量
     */
    private Double overflowNum;

    /**
     * 加油机发出量Vt(L)
     */
    private Double dispenserOutNum;

    /**
     * 回罐数量Vt(L)
     */
    private Double backNum;

    /**
     * 加油机实出量V20(L)
     */
    private Double dispenserTrueOutNum;

    /**
     * 油罐实出量V20(L)
     */
    private Double tankOutNum;

    /**
     * 零售溢耗量V20(L)
     */
    private Double lossNum;

    /**
     * 油水总高(mm)
     */
    private Double allHeight;

    /**
     * 水高(mm)
     */
    private Double waterHeight;

    /**
     * 油品体积Vt(L)
     */
    private Double oilVlume;

    /**
     * 试验温度(℃)
     */
    private Double temp;

    /**
     * 试验密度(kg/m3)
     */
    private Double experimentalDensity;

    /**
     * 标准密度ρ20(kg/m3)
     */
    private Double standardDensity;

    /**
     * 油温(℃)
     */
    private Double oilTemp;

    /**
     * VCF20
     */
    private Double vcf20;

    /**
     * 油品标准体积 V20(L)
     */
    private Double oilTrueVolume;

    /**
     * 溢耗合计V20(L)
     */
    private Double lossSum;

    /**
     * 预留字段
     */
    private Double reservedField;

    /**
     * 状态
     */
    private Status status;

    /**
     * 该数据是否被导入库
     * true 是 false 否
     */
    @TableField(exist = false)
    private boolean flag;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        HandOver handOver = (HandOver) o;
        boolean equal = Objects.equals(tankNo, handOver.tankNo) && Objects.equals(time, handOver.time);
        return equal;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tankNo, time);
    }
}
