package com.oket.tank4station.entity;

import java.math.BigDecimal;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2020-04-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("bd_send_table_info")
public class UpdateTableInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    @TableField("H_V")
    private String hV;

    @TableField("STATION_ID")
    private Long stationId;

    private Long tankCode;

    private double version;

    @TableField("IMPORT_TIME")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date importTime;

    @TableField("IMPORT_USER")
    private String importUser;

    @TableField("REMARK")
    private String remark;

    @TableField("POINT_COUNT")
    private Integer pointCount;

    @TableField("MAX_HEIGHT")
    private double maxHeight;

    @TableField("MAX_VOLUME")
    private double maxVolume;

    @TableField("TANK_ID")
    private Integer tankId;

    @TableField("STATUS")
    private Integer status;

    /**
     * 有效期
     */
    @TableField("VALIDITY")
    private Integer validity;


}
