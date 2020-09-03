package com.oket.station;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

import com.oket.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 通讯位置
 * </p>
 *
 * @author lw
 * @since 2019-11-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("address_mail")
public class MailEntity extends AbstractAddress {

    private static final long serialVersionUID = 1L;

    private String mail;

    private String zip;


}
