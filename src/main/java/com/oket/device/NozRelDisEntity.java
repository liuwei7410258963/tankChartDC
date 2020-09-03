package com.oket.device;

import com.alibaba.fastjson.annotation.JSONField;
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
 * @since 2020-04-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("dev_noz_rel_dis")
public class NozRelDisEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    //油枪编号
    private Integer nozzleCode;

    //加油机编号
    private Integer dispenserNo;

    //该对应关系使用的开始时间
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    //该对应关系使用的结束时间（为空为正在使用）
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;


}
