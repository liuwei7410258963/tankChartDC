package com.oket.tank4station.service.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.oket.tank4station.LevelState;
import com.oket.tank4station.entity.DbInventory;
import com.oket.tank4station.entity.DbInventoryTrace;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @description: 液位轨迹VO
 * @author: SunBiaoLong
 * @create: 2020-04-10 14:57
 **/
@Data
@NoArgsConstructor
@ApiModel(description = "液位组，含液位详细信息")
public class InventoryTraceVO {
	/**
	 * 液位轨迹id
	 */
	@ApiModelProperty(value = "液位轨迹的id")
	@JSONField(serialize = false)
	private Long dbTraceId;
	/**
	 * 液位id
	 */
	@ApiModelProperty(value = "开始液位id")
	@JSONField(serialize = false)
	@NotNull
	private Long startId;

	@ApiModelProperty(value = "结束液位id集合")
	@JSONField(serialize = false)
	@NotNull
	private Long endId;

	/**
	 * 新建液位的时候，用户必须选择
	 */
	@ApiModelProperty(value = "液位状态")
	@JSONField(serialize = false)
	private LevelState levelState;

	@ApiModelProperty(value = "液位集合")
	private List<DbInventory> dbInventories;
	@ApiModelProperty(value = "液位轨迹")
	private DbInventoryTrace trace;
}
