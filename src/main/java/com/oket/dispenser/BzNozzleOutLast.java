package com.oket.dispenser;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @description: 油枪最后一笔数据
 * @author: SunBiaoLong
 * @create: 2019-12-13 13:07
 **/
@Data
@TableName(value = "bz_nozzle_out_last")
public class BzNozzleOutLast extends BzNozzleOut {

	/**
	 * 从销售数据中获取最后一笔销售数据
	 *
	 * @param bzNozzleOut
	 * @return
	 */
	public static BzNozzleOutLast convertToLast(BzNozzleOut bzNozzleOut) {
		BzNozzleOutLast bzNozzleOutLast = new BzNozzleOutLast();
		bzNozzleOutLast.setType(bzNozzleOut.getType());
		bzNozzleOutLast.setVolume(bzNozzleOut.getVolume());
		bzNozzleOutLast.setVolume(bzNozzleOut.getVolume());
		bzNozzleOutLast.setStartTime(bzNozzleOut.getStartTime());
		bzNozzleOutLast.setEndTime(bzNozzleOut.getEndTime());
		bzNozzleOutLast.setNozzleNo(bzNozzleOut.getNozzleNo());
		bzNozzleOutLast.setPumpSum(bzNozzleOut.getPumpSum());
		bzNozzleOutLast.setOilCode(bzNozzleOut.getOilCode());
		bzNozzleOutLast.setOilName(bzNozzleOut.getOilName());
		return bzNozzleOutLast;
	}
}
