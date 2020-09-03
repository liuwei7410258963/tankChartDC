package com.oket.tankchartdc.mina.process;

import com.oket.device.service.DeviceService;
import com.oket.station.service.StationService;
import com.oket.tankchartdc.service.BackToTankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description: 基础处理器
 * @author: SunBiaoLong
 * @create: 2019-12-21 15:56
 **/
@Component
public class BaseProcess {
	@Autowired
	protected DeviceService deviceService;
	@Autowired
	protected StationService stationService;
	@Autowired
	BackToTankService backToTankService;

	/**
	 * 根据获取油罐编号
	 * @param nozzleNo
	 * @return
	 */
	public Integer getTankNo(Integer nozzleNo) {
		return deviceService.getTankNo(nozzleNo);
	}
}
