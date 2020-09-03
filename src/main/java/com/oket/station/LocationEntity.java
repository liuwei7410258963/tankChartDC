package com.oket.station;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

import com.oket.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 地理位置
 * </p>
 *
 * @author lw
 * @since 2019-11-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("address_location")
public class LocationEntity  extends AbstractAddress  {

    private static final long serialVersionUID = 1L;

    //围栏
    private String location;


}
