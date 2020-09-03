package com.oket.tankchartdc;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.oket.common.base.Status;
import lombok.Data;

/**
 * @description: 回罐关联的付油记录
 * @author: SunBiaoLong
 * @create: 2020-04-17 09:37
 **/
@Data
@TableName(value = "bz_back_to_tank_rel_nozzle_out")
public class BzBackToTankRelNozzleOut {
	@TableId(type = IdType.AUTO)
	private Integer id;
	/**
	 * 付油数据的id
	 */
	private Long nozzleOutId;
	/**
	 * 回罐id
	 */
	private Integer bankToTankId;
	/**
	 * 是否需要确认
	 */
	private boolean confirmed = true;
	private Status status = Status.ENABLE;


	public BzBackToTankRelNozzleOut(Long nozzleOutId, Integer bankToTankId) {
		this.nozzleOutId = nozzleOutId;
		this.bankToTankId = bankToTankId;
	}
}
