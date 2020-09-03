package com.oket.station.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oket.station.*;
import com.oket.station.dao.*;
import com.oket.station.service.StationService;
import com.oket.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class StationServiceImpl extends ServiceImpl<StationDao, StationEntity> implements StationService {
	@Resource
	private StationDao stationDao;
	@Autowired(required = false)
	private UrlDao urlDao;
	@Autowired(required = false)
	private GpsDao gpsDao;
	@Autowired(required = false)
	private MailDao mailDao;
	@Autowired(required = false)
	private LocationDao locationDao;

	/**
	 * 油站信息
	 */
	private StationEntity stationEntity;

	@PostConstruct
	public void init() {
		try {
			stationEntity = getStation();
			if (stationEntity == null) {
				stationEntity = new StationEntity();
				stationDao.insert(stationEntity);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * 油站详情信息
	 *
	 * @param jsonObject
	 * @return
	 */
	@Override
	public JSONObject selectStationAddress(JSONObject jsonObject) {
		return CommonUtil.successPage(stationDao.selectStaionAddress(jsonObject));
	}

	/**
	 * 油站信息修改
	 *
	 * @param lists
	 * @return
	 */
	@Override
	public JSONObject updateStationAddress(List<JSONObject> lists) {
		Date now = new Date();

		for (JSONObject list : lists) {
			String type = list.getString("type");
			switch (type) {
				//修改油站信息
				case "station":
					StationEntity stationEntity = list.toJavaObject(StationEntity.class);
					stationDao.updateById(stationEntity);
					this.stationEntity = stationEntity;
					break;
				//gps地址
				case "gps":
					GpsEntity gpsEntity = list.toJavaObject(GpsEntity.class);
					gpsEntity.setUpdateTime(now);
					gpsDao.saveOrUpdate(gpsEntity);
					break;
				//围栏地址
				case "location":
					LocationEntity locationEntity = list.toJavaObject(LocationEntity.class);
					locationEntity.setUpdateTime(now);
					locationDao.saveOrUpdate(locationEntity);
					break;
				//邮编地址
				case "mail":
					MailEntity mailEntity = list.toJavaObject(MailEntity.class);
					mailEntity.setUpdateTime(now);
					mailDao.saveOrUpdate(mailEntity);
					break;
				//url地址
				case "url":
					UrlEntity urlEntity = list.toJavaObject(UrlEntity.class);
					urlEntity.setUpdateTime(now);
					urlDao.saveOrUpdate(urlEntity);
					break;
				default:
					break;
			}
		}
		return CommonUtil.successJson();
	}

	@Override
	public void processStation(StationEntity stationEntity) {
		StationEntity station = getStation();
		if (station == null) {
			stationEntity.setStationId(1L);
			stationEntity.setStatus(1);
			stationDao.insert(stationEntity);
		} else {
			if (!stationEntity.getStationCode().equals(station.getStationCode())) {
				station.setStationCode(stationEntity.getStationCode());
				stationDao.updateById(station);
			}
		}
	}

	@Override
	public StationEntity getStation() {
		if (stationEntity == null) {
			stationEntity = stationDao.selectById(1L);
		}
		return stationEntity;
	}
}
