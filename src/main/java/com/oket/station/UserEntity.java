package com.oket.station;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2020-03-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("c_user_info")
public class UserEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /*
     * 账号
     */
    @TableField("USER_NAME")
    private String userName;


    /*
     * 真实姓名
     */
    @TableField("USER_REAL_NAME")
    private String userRealName;


    /*
     * 密码
     */
    @TableField("PWD")
    private String pwd;


    /*
     * 状态 0正常 1禁用
     */
    @TableField("STATUS")
    private Integer status;


    /*
     * 组织
     */
    @TableField("ORG")
    private Integer org;


    /*
     * 性别
     */
    @TableField("SEX")
    private String sex;


    /*
     * 电话
     */
    @TableField("PHONE")
    private String phone;


    /*
     * qq
     */
    @TableField("QQ")
    private String qq;


    /*
     * 微信
     */
    @TableField("WEIXIN")
    private String weixin;


}
