package com.oket.device;

import java.util.Collection;
import java.util.Set;

public interface Devices {
    public Set<DeviceItem> getDevices();
    public boolean setDevices(Collection<DeviceItem> deviceItems);
    public boolean addDevices(Collection<DeviceItem> deviceItems);
    public boolean addDeviceItem(DeviceItem item);
    public boolean removeDeviceItem(DeviceItem item);
    public boolean removeDevices(Collection<DeviceItem> deviceItems);
    public Set<DeviceItem> getDevicesByDeviceType(DeviceType deviceType);
}
