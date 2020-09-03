package com.oket.tankchartdc.mina.ifsf.bean;

import com.oket.dispenser.BzNozzleOut;
import com.oket.tankchartdc.mina.ifsf.codec.TransactionDB;
import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * @description: 交易数据
 * @author: Longer
 * @create: 2019-11-12 18:57
 **/
@Data
public class IFSFTransaction implements IDitIFSFDataBody {
	private Double amount;
	private Double volume;
	private Integer logicHose;
	private Double unitPrice;
	private String oilNo;
	private Double startPumpCode;
	private Double endPumpCode;
	/**
	 * 数据时间
	 *
	 * @see IDitIFSFDataBody#TIME_FIELD_NAME
	 * 属性名不可以轻易修改
	 */
	private Date time;
	private String desc = "正常交易数据";

	public static IFSFTransaction init(Map<String, Object> dataMap) {
		IFSFTransaction ifsfTransaction = new IFSFTransaction();
		ifsfTransaction.setAmount((Double) dataMap.get(TransactionDB.AMOUNT_DATA_ID));
		ifsfTransaction.setVolume((Double) dataMap.get(TransactionDB.VOLUME_DATA_ID));
		ifsfTransaction.setLogicHose((Integer) dataMap.get(TransactionDB.LOGIC_HOSE_DATA_ID));
		ifsfTransaction.setUnitPrice((Double) dataMap.get(TransactionDB.UNIT_PRICE_DATA_ID));
		ifsfTransaction.setOilNo((String) dataMap.get(TransactionDB.OIL_NO_DATA_ID));
		ifsfTransaction.setStartPumpCode((Double) dataMap.get(TransactionDB.START_PUMP_CODE));
		ifsfTransaction.setEndPumpCode((Double) dataMap.get(TransactionDB.END_PUMP_CODE));
		if (ifsfTransaction.getTime()==null){
			ifsfTransaction.setTime(new Date());
		}
		return ifsfTransaction;
	}

	public static BzNozzleOut convertHoseOut(IFSFTransaction ifsfTransaction, Integer nozzleNo) {
		if (ifsfTransaction == null) {
			return null;
		} else {
//			Date date = new Date();
			BzNozzleOut hoseOut = new BzNozzleOut();
//			hoseOut.setId(date.getMilles());
			hoseOut.setVolume(ifsfTransaction.volume);
			hoseOut.setPumpSum(ifsfTransaction.endPumpCode - ifsfTransaction.startPumpCode);
			if (nozzleNo != null) {
				hoseOut.setNozzleNo(nozzleNo.toString());
			}
//			hoseOut.setEndTime(date);
			return hoseOut;
		}
	}
}
