package com.oket.tankchartdc.entity;

import com.alibaba.fastjson.JSONObject;
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
 * @since 2020-04-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("bz_abnormal")
public class AbnormalEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    //IFSF还是JSON
    private String ditType;

    /*
     * 1: 断开连接
     * 2：数据接收时间过长（json的没有）
     * 3: 付油数据泵码数异常
     */
    private Integer errorType;

    //数据接收时间过长的上一次时间（json的没有）
    //断开连接时没有上一次的时间，只有当前时间（now）
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    //报警内容（可自定义）
    private String info;
    //说明
    private String remark;
    //编码
    private String code;
    //状态
    private Boolean status;
    //备用
    private String bak;
    //用于返回其他类型的数据
    @TableField(exist =false)
    private JSONObject result;
}
