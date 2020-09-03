package com.oket.dispenser;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.oket.common.base.BaseEntity;
import com.oket.common.base.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author lw
 * @since 2020-04-14
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("bz_nozzle_out_rel_group")
public class NozzleOutRelGroup extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 付油数据的id
	 */
	private Long outId;

	/**
	 * 付油组的id
	 */
	private Long groupId;

	/**
	 * 是否启用
	 */
	private Status status = Status.ENABLE;

	@TableField(exist = false)
	private double volume;
	@TableField(exist = false)
	private String ids;
	@TableField(exist = false)
	private Date startTime;
	@TableField(exist = false)
	private Date endTime;
	@TableField(exist = false)
	private int tankNo;
	@TableField(exist = false)
	private int oldTraceId;
	/**
	 * 付油组需要关联的轨迹id
	 */
	@TableField(exist = false)
	private long traceId;
	/**
	 * 	新建付油组时需要关联的轨迹id
	 * 	是否轨迹已经关联了付油组，如果关联了：需要把轨迹关联的付油组和当前的付油组合并，
	 * 	如果没有关联，直接关联轨迹
	 */
	@TableField(exist = false)
	private long relGroupId;
	public NozzleOutRelGroup(Long outId, Long groupId) {
		this.outId = outId;
		this.groupId = groupId;
	}
}
