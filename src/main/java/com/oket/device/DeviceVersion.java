package com.oket.device;

public interface DeviceVersion {
    /**版本类型*/
    public String getDeviceVersion();
    /**设备软件版本号*/
    public String getDeviceSoftVersion();
    /**版权，包含版权时间*/
    public String getCopyright();
    /**版本启用时间*/
    public String getVersionDate();

}
