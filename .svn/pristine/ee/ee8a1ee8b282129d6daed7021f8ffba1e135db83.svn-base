package com.oket.tank4station.websocket;

import com.alibaba.fastjson.JSONObject;
import com.oket.dispenser.service.NozzleOutService;
import com.oket.station.bizservice.BalanceService;
import com.oket.tank4station.InventoryCacheManager;
import com.oket.tank4station.service.DbInventoryService;
import com.oket.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @description: 业务推数据service
 * @author: SunBiaoLong
 * @create: 2019-12-13 14:03
 **/
@Service
public class BizPushDataService {
	/**
	 * 推送的数据
	 */
	private static final BizPushData BIZ_PUSH_DATA = new BizPushData();
	@Value(value = "${websocketKey}")
	private String websocketKey;
	@Value("${websocketValue}")
	private String websocketValue;

	@Autowired
	private NozzleOutService nozzleOutService;

	@Autowired
	private DbInventoryService dbInventoryService;
	@Autowired
	private BalanceService balanceService;

	private BizPushData getBizPushData() {
		BIZ_PUSH_DATA.setNozzleOutLasts(nozzleOutService.getNozzleOutLast());
		BIZ_PUSH_DATA.setDbInventoryLasts(dbInventoryService.getAllDbInventoryLast());
		BIZ_PUSH_DATA.setInventoryAlarms(InventoryCacheManager.getAllInventoryAlarm());
		BIZ_PUSH_DATA.setNozzleStates(balanceService.getAllNozzleState());
		return BIZ_PUSH_DATA;
	}

	/**
	 * 获取发送的消息
	 *
	 * @return
	 */
	public String getSendMessage() {
		final JSONObject jsonObject = CommonUtil.successJson(getBizPushData());
		jsonObject.put("type", BizMessageType.ALL);
		return jsonObject.toJSONString();
	}

	public String getWebsocketKey() {
		return websocketKey;
	}

	public void setWebsocketKey(String websocketKey) {
		this.websocketKey = websocketKey;
	}

	public String getWebsocketValue() {
		return websocketValue;
	}

	public void setWebsocketValue(String websocketValue) {
		this.websocketValue = websocketValue;
	}
}
