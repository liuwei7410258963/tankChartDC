package com.oket.tankchartdc.mina.ifsf.bean;

import com.oket.dispenser.BzNozzleOut;
import com.oket.tankchartdc.mina.ifsf.codec.TransactionDB;
import com.oket.util.TimeUtils;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

/**
 * @description: 离线数据
 * @author: Longer
 * @create: 2019-11-12 12:13
 **/
@Data
public class IFSFOfflineRecord implements IDitIFSFDataBody {
	private static final Logger logger = LoggerFactory.getLogger(IFSFOfflineRecord.class);
	/**
	 * 交易序列号
	 */
	private Integer transactionSerialNO;
	/**
	 * 金额
	 */
	private Double amount;
	/**
	 * 体积
	 */
	private Double volume;
	/**
	 * 逻辑油枪
	 */
	private Integer logicHose;
	/**
	 * 单价
	 */
	private Double unitPrice;
	/**
	 * 油品
	 */
	private String oilNo;
	/**
	 * 时间
	 */
	private Date offlineTime;
	/**
	 * 数据时间
	 * @see IDitIFSFDataBody#TIME_FIELD_NAME
	 * 属性名不可以轻易修改
	 */
	private Date time;

	private String desc = "脱机交易数据";


	/**
	 * 离线数据初始化
	 *
	 * @param objectMap
	 * @return
	 */
	public static IFSFOfflineRecord init(Map<String, Object> objectMap) {
		IFSFOfflineRecord ifsfOfflineRecord = new IFSFOfflineRecord();
		ifsfOfflineRecord.setAmount((Double) objectMap.get(TransactionDB.AMOUNT_DATA_ID));
		ifsfOfflineRecord.setLogicHose((Integer) objectMap.get(TransactionDB.LOGIC_HOSE_DATA_ID));
		ifsfOfflineRecord.setOilNo((String) objectMap.get(TransactionDB.OIL_NO_DATA_ID));
		ifsfOfflineRecord.setUnitPrice((Double) objectMap.get(TransactionDB.UNIT_PRICE_DATA_ID));
		ifsfOfflineRecord.setVolume((Double) objectMap.get(TransactionDB.VOLUME_DATA_ID));
		String timeStr = objectMap.get(TransactionDB.DATE_DATA_ID).toString() + objectMap.get(TransactionDB.TIME_DATA_ID).toString();
		try {
			ifsfOfflineRecord.setOfflineTime(TimeUtils.parseTime(timeStr));
		} catch (ParseException e) {
			logger.error("解析日期失败，timeStr：" + timeStr, e);
		}
		if (ifsfOfflineRecord.getTime()==null){
			ifsfOfflineRecord.setTime(new Date());
		}
		return ifsfOfflineRecord;
	}

	/**
	 * 转为销售数据
	 *
	 * @param ifsfOfflineRecord
	 * @param nozzleNo
	 * @return
	 */
	public static BzNozzleOut convertHoseOut(IFSFOfflineRecord ifsfOfflineRecord, Integer nozzleNo) {
		if (ifsfOfflineRecord == null) {
			return null;
		} else {
			BzNozzleOut hoseOut = new BzNozzleOut();
			hoseOut.setEndTime(ifsfOfflineRecord.time);
			if (nozzleNo!=null) {
				hoseOut.setNozzleNo(nozzleNo.toString());
			}
			hoseOut.setVolume(ifsfOfflineRecord.volume);
			hoseOut.setOilCode(ifsfOfflineRecord.getOilNo());
			hoseOut.setId(ifsfOfflineRecord.time.getTime());
			return hoseOut;
		}
	}

}
