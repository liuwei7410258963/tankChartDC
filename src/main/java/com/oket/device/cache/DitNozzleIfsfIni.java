package com.oket.device.cache;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.oket.device.Nozzle4Device;
import com.oket.device.TankInfo;
import com.oket.device.service.TankInfoService;
import org.ini4j.Ini;
import org.ini4j.Wini;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @description: dit 油枪ifsf相关配置文件
 * @author: Longer
 * @create: 2019-11-12 10:03
 **/
@Service
public class DitNozzleIfsfIni {
	private static final Logger logger = LoggerFactory.getLogger(DitNozzleIfsfIni.class);
	/**
	 * 油枪配置缓存
	 */
	private Map<Integer, Nozzle4Device> nozzleIniMap = new HashMap<>(0);
	@Autowired
	private TankInfoService tankInfoService;

	/**
	 * 获取所有油枪信息
	 *
	 * @return
	 */
	public Collection<Nozzle4Device> getAllNozzle4Device() {
		return nozzleIniMap.values();
	}

	public Map<Integer, Nozzle4Device> getNozzleIniMap() {
		return nozzleIniMap;
	}

	/**
	 * 配置ifsf和物理油枪对应关系
	 * 格式样例
	 * [IFSF]
	 * 1,1=1
	 * 2,1=2
	 * 17,1=17
	 * 18,1=18
	 * NODENUM=18
	 * PUMPNUM=18
	 *
	 * @param ini
	 */
	private void setIFSFBasic(Wini ini) {
		Ini.Section section = ini.get("IFSF");
		Set<Map.Entry<String, String>> entries = section.entrySet();
		Iterator<Map.Entry<String, String>> iterator = entries.iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, String> next = iterator.next();
			String key = next.getKey();
			if (!"NODENUM".equals(key) && !"PUMPNUM".equals(key)) {
				String[] split = key.split(",");
				if (split.length == 2) {
					Integer node = Integer.valueOf(split[0]);
					Integer logicNo = Integer.valueOf(split[1]);
					Integer physical = Integer.valueOf(next.getValue());
					Nozzle4Device nozzle4Device = nozzleIniMap.get(physical);
					if (nozzle4Device == null) {
						nozzle4Device = new Nozzle4Device();
						nozzleIniMap.put(physical, nozzle4Device);
					}
					nozzle4Device.setNozzleNo(Integer.valueOf(physical));
					nozzle4Device.setIfsfFuelingPoint(logicNo);
					nozzle4Device.setIfsfNode(node);

				}
			}
		}
	}

	/**
	 * 获取油罐和物理油枪关系
	 * 格式样例
	 * [TANK]
	 * TANKNUM=4
	 * TANK01=15,16
	 * TANK02=1,6,7,12,18
	 * TANK03=2,3,5,8,11,13,14,17
	 * TANK04=4,9,10
	 */
	private void setTankRelHose(Wini ini) {

		Ini.Section section = ini.get("TANK");
		Set<Map.Entry<String, String>> entries = section.entrySet();
		Iterator<Map.Entry<String, String>> iterator = entries.iterator();
		final List<TankInfo> dbLists = tankInfoService.list(new QueryWrapper<>());
		if (dbLists.size() != 0) {
			return;
		}
		List<TankInfo> resultLists = new ArrayList<>();
		while (iterator.hasNext()) {
			Map.Entry<String, String> next = iterator.next();
			String key = next.getKey();
			if (!"TANKNUM".equals(key)) {
				Integer tankNo = Integer.valueOf(key.substring(4));
				TankInfo tankInfo = new TankInfo();
				tankInfo.setTankNo(tankNo);
				tankInfo.setDetailInfo(tankNo + "");
				resultLists.add(tankInfo);
				//物理油枪，逗号分隔
				String physicalNos = next.getValue();
				String[] split = physicalNos.split(",");
				if (split.length > 0) {
					for (String physicalNo : split) {
						Nozzle4Device nozzle4Device = new Nozzle4Device();
						nozzle4Device.setNozzleNo(Integer.valueOf(physicalNo));
						nozzle4Device.setTankNo(tankNo);
						nozzleIniMap.put(nozzle4Device.getNozzleNo(), nozzle4Device);
					}
				}
			}
		}
		tankInfoService.saveBatch(resultLists);
	}

	/**
	 * 读取dit的ifsf相关的配置文件
	 *
	 * @param ditIniFilePath
	 */
	public void init(String ditIniFilePath) {
		Wini ini = null;
		try {
			ini = new Wini(new File(ditIniFilePath));
			setTankRelHose(ini);
			setIFSFBasic(ini);
		} catch (IOException e) {
			logger.error("解析dit配置文件失败", e);
		}
	}


}
