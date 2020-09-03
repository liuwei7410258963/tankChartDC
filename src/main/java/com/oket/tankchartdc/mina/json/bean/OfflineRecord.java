package com.oket.tankchartdc.mina.json.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.parser.deserializer.ExtraTypeProvider;
import com.oket.dispenser.BzNozzleOut;
import com.oket.dispenser.NozzleOutType;
import lombok.Data;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 离线记录
 * @author: Longer
 * @create: 2019-11-08 23:12
 **/
@Data
public class OfflineRecord implements ExtraTypeProvider, IDitJsonDataBody {
	/**
	 * 油枪号
	 */
	@JSONField(alternateNames = "1")
	private String nozzleCode;
	/**
	 * 油品编码
	 */
	@JSONField(alternateNames = "2")
	private String oilCode;
	/**
	 * 交易升数
	 */
	@JSONField(alternateNames = "3")
	private Double litre;
	/**
	 * 交易金额
	 */
	@JSONField(alternateNames = "4")
	private Double amount;
	/**
	 * 交易单价
	 */
	@JSONField(alternateNames = "5")
	private Double unitPrice;
	/**
	 * 交易完成时间
	 */
	@JSONField(alternateNames = "6", format = "yyyy-MM-dd HH:mm:ss")
	private Date finishTime;
	/**
	 * 开始泵码数
	 */
	@JSONField(alternateNames = "7")
	private Double startPumpCode;
	/**
	 * 结束泵码数
	 */
	@JSONField(alternateNames = "8")
	private Double endPumpCode;

	@Override
	public Type getExtraType(Object object, String key) {
		switch (key) {
			case "3":
			case "4":
			case "5":
			case "7":
			case "8":
				return Double.class;
			default:
				break;
		}
		return null;
	}

	public static List<BzNozzleOut> convertToHoseOut(List<OfflineRecord> offlineRecords) {
		if (offlineRecords == null || offlineRecords.isEmpty()) {
			return null;
		} else {
			return offlineRecords.parallelStream().map(offlineRecord -> {
				BzNozzleOut bzNozzleOut = new BzNozzleOut();
				bzNozzleOut.setPumpSum(offlineRecord.endPumpCode);
				bzNozzleOut.setVolume(offlineRecord.litre);
				bzNozzleOut.setNozzleNo(offlineRecord.nozzleCode);
				bzNozzleOut.setEndTime(offlineRecord.finishTime);
				bzNozzleOut.setType(NozzleOutType.OFFLINE);
				bzNozzleOut.setOilCode(offlineRecord.getOilCode());
				return bzNozzleOut;
			}).collect(Collectors.toList());

		}

	}

}
