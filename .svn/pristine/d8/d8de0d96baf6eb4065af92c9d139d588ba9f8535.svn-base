package com.oket.device;

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
 * @since 2020-03-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("dev_noz_rel_tank")
public class NozTankRelationEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    //油枪编号
    private Integer nozzleCode;

    //油罐编号
    private Integer tankNo;

    //该对应关系使用的开始时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    //该对应关系使用的结束时间（为空为正在使用）
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /**
     * 油品code
     */
    @TableField(exist = false)
    private String oilCode;
    /**
     * 油品名称
     */
    @TableField(exist = false)
    private String oilName;


}
