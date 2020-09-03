package com.oket.tankchartdc.mina.ifsf.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.oket.dispenser.NozzleState;
import com.oket.tankchartdc.mina.ifsf.codec.FuelingPointDB;
import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * @description: 加油中的数据
 * @author: Longer
 * @create: 2019-11-12 19:05
 **/
@Data
public class IFSFOnFueling implements IDitIFSFDataBody {
	/**
	 * 加油机节点
	 */
	private byte node;
	/**
	 * 金额
	 */
	private double amount;
	/**
	 * 体积
	 */
	private double volume;
	private Integer hoseId;
	/**
	 * 数据时间
	 *
	 * @see IDitIFSFDataBody#TIME_FIELD_NAME
	 * 属性名不可以轻易修改
	 */
	@JSONField(format = "YYYY-MM-dd HH:mm:ss")
	private Date time;
	private String desc = "加油中数据";
	private String type;

	public static IFSFOnFueling init(Map<String, Object> dataMap) {
		IFSFOnFueling ifsfOnFueling = new IFSFOnFueling();
		ifsfOnFueling.setAmount((Double) dataMap.get(FuelingPointDB.AMOUNT));
		ifsfOnFueling.setVolume((Double) dataMap.get(FuelingPointDB.VOLUME));
		ifsfOnFueling.setTime(new Date());
		return ifsfOnFueling;
	}

	/**
	 * 加油中数据转为油枪状态信息
	 * @param ifsfOnFueling
	 * @return
	 */
	public static NozzleState convertFromFueling(IFSFOnFueling ifsfOnFueling) {
		NozzleState nozzleState = new NozzleState(ifsfOnFueling.getHoseId(), NozzleState.State.FUELLING);
		NozzleState.TransientAmount fueling = nozzleState.new TransientAmount(
				ifsfOnFueling.getAmount(), ifsfOnFueling.getVolume(), ifsfOnFueling.getTime()
		);
		nozzleState.setTime(ifsfOnFueling.getTime());
		nozzleState.addFueling(fueling);
		return nozzleState;

	}
}
