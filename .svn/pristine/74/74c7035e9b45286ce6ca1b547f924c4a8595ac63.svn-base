package com.oket.station;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.oket.common.base.BaseEntity;
import com.oket.device.DeviceItem;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

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
@TableName("address_gps")
public class GpsEntity extends AbstractAddress {

    private static final long serialVersionUID = 1L;

    //经度
    private String longitude;

    //纬度
    private String latitude;

    public boolean addAddress(AbstractAddress address){
        return true;
    }
    public boolean removeAddress(AbstractAddress address){
        return false;
    }
    public boolean addDevice(DeviceItem item){

        return false;
    }
    public boolean removeDevice(DeviceItem item){
        return false;
    }
}
