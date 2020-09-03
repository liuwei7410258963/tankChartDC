package com.oket.device.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oket.device.Nozzle4Device;
import org.springframework.stereotype.Repository;

@Repository(value = "nozzle4DeviceDAO")
public interface Nozzle4DeviceDAO  extends BaseMapper<Nozzle4Device> {
}
