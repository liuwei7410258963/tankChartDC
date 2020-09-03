package com.oket.dispenser.service.impl;

import com.oket.device.service.DeviceService;
import com.oket.dispenser.NozzleState;
import com.oket.dispenser.service.NozzleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description: 油枪service
 * @author: SunBiaoLong
 * @create: 2019-12-13 13:34
 **/
@Service
@Slf4j
public class NozzleServiceImpl implements NozzleService {

	@Autowired
	private DeviceService deviceService;


	@Override
	public void processFueling(NozzleState fueling) {
//		IFSFNozzleState ifsfNozzleStateFromCache = IFSFNozzleState.getIFSFNozzleState(fueling.getNozzleNo());
//		if (ifsfNozzleStateFromCache == null) {
//			ifsfNozzleStateFromCache = new IFSFNozzleState();
//			Integer tankNo = deviceService.getTankNo(fueling.getNozzleNo());
//			ifsfNozzleStateFromCache.setTankNo(tankNo);
//			ifsfNozzleStateFromCache.setCurrentState(NozzleState.FUELLING);
//			ifsfNozzleStateFromCache.setOnFueling(true);
//			ifsfNozzleStateFromCache.addFueling(fueling);
//			if (ifsfNozzleStateFromCache.getTankNo() != null) {
//				InventoryConsumer.fueling(ifsfNozzleStateFromCache.getTankNo());
//			} else {
//				log.error("找不到对应的油罐:" + ifsfNozzleStateFromCache);
//			}
//			IFSFNozzleState.putIFSFNozzleState(ifsfNozzleStateFromCache);
//		}
	}

	@Override
	public void processNozzleState(NozzleState nozzleState) {
//		final IFSFNozzleState ifsfNozzleStateFromCache = IFSFNozzleState.getIFSFNozzleState(ifsfNozzleState.getNozzleNo());
//		if (ifsfNozzleStateFromCache == null) {
//			Integer tankNo = deviceService.getTankNo(ifsfNozzleState.getNozzleNo());
//			ifsfNozzleState.setTankNo(tankNo);
//			IFSFNozzleState.putIFSFNozzleState(ifsfNozzleState);
//		} else {
//			if (ifsfNozzleState.getCurrentState() != null) {
//				if (!ifsfNozzleState.getCurrentState().equals(NozzleState.FUELLING)
//						|| !ifsfNozzleState.getCurrentState().equals(NozzleState.SUSPENDED_FUELLING)) {
//					ifsfNozzleStateFromCache.clearFueling();
//				}
//			}
//			ifsfNozzleStateFromCache.setCurrentState(ifsfNozzleState.getCurrentState());
//			ifsfNozzleStateFromCache.setOnFueling(ifsfNozzleState.isOnFueling());
//		}
	}


}
