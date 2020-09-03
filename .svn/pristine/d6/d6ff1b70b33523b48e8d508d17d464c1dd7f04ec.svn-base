package com.oket.tankchartdc.entity;

import com.oket.dispenser.BzNozzleOutGroup;
import com.oket.tank4station.entity.DbInventoryTrace;
import com.oket.tankchartdc.BackToTank;
import lombok.Data;

import java.util.List;

/**
 * @author ：lw
 * @date ：Created in 2020/4/24 11:18
 * @description：校正实体
 */
@Data
public class CorrectingDTO {
	/**
	 * 周期id
	 */
	private long cycleId;
	/**
	 * 卸油的id
	 */
	private int deliveryId;
	//周期内的数据
	private List<InnerCorrectingDTO> innerCorrectingDTOs;

}

@Data
class InnerCorrectingDTO {
	/**
	 *付油数据
	 */
	private DbInventoryTrace dbInventoryTrace;
	/**
	 *液位数据
	 */
	private BzNozzleOutGroup group;
	/**
	 *回罐数据
	 */
	private BackToTank backToTank;

	/**
	 * 液位变化率
	 */
	private Double inventoryRate;

	/**
	 *付油变化率
	 */
	private Double outRate;

	/**
	 * 液位变化率
	 * @return
	 */
	public Double getInventoryRate() {
		Double result = 0.0D;
		if (dbInventoryTrace != null) {
			if (dbInventoryTrace.getEndLevel() - dbInventoryTrace.getStartLevel() == 0) {
				return result;
			}

			result = (dbInventoryTrace.getEndVolume()-dbInventoryTrace.getStartVolume())
					/ (dbInventoryTrace.getEndLevel() - dbInventoryTrace.getStartLevel());
			return result < 0 ? -result : result;
		}
//		if (backToTank!=null){
//			result = ( Math.abs(dbInventoryTrace.getEndVolume() - dbInventoryTrace.getStartVolume())
//					-backToTank.getTrueVolume())
//					/ Math.abs(dbInventoryTrace.getEndLevel() - dbInventoryTrace.getStartLevel());
//			return Double.parseDouble(ddf.format(result));
//		}else {
//			result = (dbInventoryTrace.getEndVolume() - dbInventoryTrace.getStartVolume())
//					/ (dbInventoryTrace.getEndLevel() - dbInventoryTrace.getStartLevel());
//			return Double.parseDouble(ddf.format(result));
//		}
		return inventoryRate;
	}


	/**
	 * 付油变化率
	 * @return dbInventoryTrace.getDeltaVolume()>134&&dbInventoryTrace.getDeltaVolume()<137
	 */

	public Double getOutRate() {
		if (dbInventoryTrace != null) {
			if (dbInventoryTrace.getEndLevel() - dbInventoryTrace.getStartLevel() == 0) {
				return 0.0D;
			}
			if(dbInventoryTrace.getDeltaNozzleVolume()!=0){
				double rate = (dbInventoryTrace.getDeltaNozzleVolume())
						/ (dbInventoryTrace.getEndLevel() - dbInventoryTrace.getStartLevel());
				return rate < 0 ? -rate : rate;
			}
			else if(dbInventoryTrace.getDeltaVolume()!=0){
				double rate = (dbInventoryTrace.getDeltaVolume())
						/ (dbInventoryTrace.getEndLevel() - dbInventoryTrace.getStartLevel());
				return rate < 0 ? -rate : rate;
			}
			else{
				double rate = (group.getVolume())
						/ (dbInventoryTrace.getEndLevel() - dbInventoryTrace.getStartLevel());
				return rate < 0 ? -rate : rate;
			}
		}
		return outRate;
	}
}
