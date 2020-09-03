package com.oket.tankchartdc.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.util.Date;

import com.oket.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author lw
 * @since 2020-04-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("back_to_tank_confirm")
public class BackToTankConfirmEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer tankNo;

    /*
     * 本条关联数据的付油量
     */
    private Double volume;

    private Integer outId;

    private Integer backId;
    /*
     * 是否确认
     */
    private boolean confirm;

    /*
     * 前端传过来的总回罐量
     */
    @TableField(exist = false)
    private Double trueVolumeAll;
    /*
     * 前端传过来的总付油量
     */
    @TableField(exist = false)
    private Double volumeAll;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

}
