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
 * @description: 交易数据
 * @author: Longer
 * @create: 2019-11-08 23:02
 **/
@Data
public class OilTransaction implements ExtraTypeProvider {
	/**
	 * 油枪号
	 */
	@JSONField(alternateNames = "1")
	private String nozzleNo;
	/**
	 * 油品号
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
	@JSONField(alternateNames = "6", format = "yyyy-MM-dd HH:mm:ss")
	private Date startTime;
	/**
	 * 完成时间
	 */
	@JSONField(alternateNames = "7", format = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;
	/**
	 * 开始泵码数
	 */
	@JSONField(alternateNames = "8")
	private Double startPumpCode;
	/**
	 * 结束泵码数
	 */
	@JSONField(alternateNames = "9")
	private Double endPumpCode;

	@Override
	public Type getExtraType(Object object, String key) {
		switch (key) {
			case "3":
			case "4":
			case "5":
			case "8":
			case "9":
				return Double.class;
			default:
				break;
		}
		return null;
	}

	/**
	 * 转为HoseOut数据
	 *
	 * @param transactionList
	 * @return
	 */
	public static List<BzNozzleOut> convertHoseOut(List<OilTransaction> transactionList) {
		if (transactionList == null || transactionList.isEmpty()) {
			return null;
		} else {
			return transactionList.parallelStream().map(oilTransaction -> {
				BzNozzleOut bzNozzleOut = new BzNozzleOut();
				bzNozzleOut.setVolume(oilTransaction.getLitre());
				bzNozzleOut.setNozzleNo(oilTransaction.getNozzleNo());
				bzNozzleOut.setStartTime(oilTransaction.getStartTime());
				bzNozzleOut.setEndTime(oilTransaction.getEndTime());
				bzNozzleOut.setPumpSum(oilTransaction.getEndPumpCode());
				bzNozzleOut.setType(NozzleOutType.NORMAL);
				bzNozzleOut.setOilCode(oilTransaction.getOilCode());
				return bzNozzleOut;
			}).collect(Collectors.toList());
		}
	}
}
