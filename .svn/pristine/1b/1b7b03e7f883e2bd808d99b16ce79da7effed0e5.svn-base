package com.oket.oil.cache;

import com.alibaba.fastjson.JSONObject;
import com.oket.common.CommonObservable;
import com.oket.oil.OilEntity;
import com.oket.oil.dao.OilDAO;
import com.oket.util.DoubleUtils;
import com.oket.util.YmlUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Observer;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 油品缓存数据
 * @author: SunBiaoLong
 * @create: 2019-12-10 14:44
 **/
@Component
@Slf4j
public class OilCacheManager {
	private static final Map<String, OilEntity> OIL_ENTITY_MAP = new ConcurrentHashMap<>();
	@Resource
	private OilDAO oilDAO;
	/**
	 * double的精度
	 */
	private final static double DOUBLE_ACCURACY = 0.000001;

	@Autowired
	private OilConfig oilConfig;


	public void addObserver(Observer observer) {
		oilObservable.addObserver(observer);
	}

	@Getter
	private final CommonObservable oilObservable = new CommonObservable();

	/**
	 * 存入油品数据
	 *
	 * @param oilCode   油品编号
	 * @param oilEntity 油品实体
	 */
	public static void putOil(String oilCode, OilEntity oilEntity) {
		OIL_ENTITY_MAP.put(oilCode, oilEntity);
	}

	/**
	 * 获取油品数据
	 *
	 * @param oilCode 油品编号
	 */
	public static OilEntity getOil(String oilCode) {
		return OIL_ENTITY_MAP.get(oilCode);
	}

	/**
	 * 获取V20
	 *
	 * @param oilCode
	 * @param volume
	 * @return
	 */
	public static double getV20(String oilCode, double volume, double temperature) {
		if (StringUtils.isNotBlank(oilCode)) {
			final OilEntity oil = OilCacheManager.getOil(oilCode);
			if (oil != null && oil.getOilExpansionRate() != 0.0) {
				return DoubleUtils.round(volume * (1 - (temperature - 20) * oil.getOilExpansionRate()), 3);
			}
		}
		return volume;
	}

	/**
	 * 初始化缓存--从数据库获取
	 */
	@PostConstruct
	public void init() {
		try {
			System.out.println(oilConfig.getExpansionRate());
			refresh();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * 刷新缓存
	 */
	public void refresh() {
		final List<OilEntity> oilEntities = oilDAO.selectList(null);
		if (oilEntities != null && !oilEntities.isEmpty()) {
			for (OilEntity oilEntity : oilEntities) {
				getOilExpansionRate(oilEntity);
				putOil(oilEntity.getCode(), oilEntity);
				notifyOilObservable();
			}
		}
	}

	/**
	 * 通知观察者
	 */
	private void notifyOilObservable() {
		JSONObject oilMessage = new JSONObject();
		OIL_ENTITY_MAP.forEach((a, b) -> {
			oilMessage.put(a, b.getOilExpansionRate());
		});
		oilMessage.put("TYPE", "OIL");
		oilObservable.setChanged();
		oilObservable.notifyObservers(oilMessage);
	}

	/**
	 * 获取油品膨胀率
	 *
	 * @param oilEntity
	 */
	private void getOilExpansionRate(OilEntity oilEntity) {
		Double oilExpansionRate = getDouble(oilEntity.getCode());
		//double类型不能用==判断，精度缺失
		if (oilExpansionRate == null || oilExpansionRate <= DOUBLE_ACCURACY) {
			oilExpansionRate =
					(oilEntity.getType() != null) ? getDouble(oilEntity.getType().name())
							: getDouble("DEFAULT");
		}
		oilEntity.setOilExpansionRate(oilExpansionRate);
		log.info("oilCode:{},oilExpansionRate:{}", oilEntity.getCode(), oilEntity.getOilExpansionRate());

	}

	/**
	 * 获取double
	 *
	 * @param code
	 * @return
	 */
	private Double getDouble(String code) {
		return oilConfig.getExpansionRate().get(code);
	}
}
