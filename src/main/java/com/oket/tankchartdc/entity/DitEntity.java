package com.oket.tankchartdc.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

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
 * @since 2020-03-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("c_dit_port")
public class DitEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /*
     * isf和json的端口号
     */
    private Integer ifsf;
    private Integer json;
    private String ip;
    /*
     * 1 服务端
     * 2 测试端
     */
    private int type;

    //websocket的端口号和ip
    @TableField(exist = false)
    private String websocketIp;
    @TableField(exist = false)
    private int websocketPort;

    /*
     * isf和json接收器是否正在运行
     */
    @TableField(exist = false)
    private boolean ifsfIsRunning;
    @TableField(exist = false)
    private boolean jsonIsRunning;


}
