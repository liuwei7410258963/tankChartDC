package com.oket.device;



import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

final public class DevicesFactory implements Devices {
    private final Set<DeviceItem> addresses = Collections
            .synchronizedSet(new HashSet<DeviceItem>());
    @Override
    public Set<DeviceItem> getDevices() {
        return null;
    }

    @Override
    public boolean setDevices(Collection<DeviceItem> deviceItems) {
        return false;
    }

    @Override
    public boolean addDevices(Collection<DeviceItem> deviceItems) {
        return false;
    }

    @Override
    public boolean addDeviceItem(DeviceItem item) {
        return false;
    }

    @Override
    public boolean removeDeviceItem(DeviceItem item) {
        return false;
    }

    @Override
    public boolean removeDevices(Collection<DeviceItem> deviceItems) {
        return false;
    }

    @Override
    public Set<DeviceItem> getDevicesByDeviceType(DeviceType deviceType) {
        return null;
    }
}
