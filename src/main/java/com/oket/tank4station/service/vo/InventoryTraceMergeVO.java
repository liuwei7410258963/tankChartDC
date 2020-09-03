package com.oket.tank4station.service.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.oket.tank4station.LevelState;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @description: 液位轨迹拆分、合并VO
 * @author: SunBiaoLong
 * @create: 2020-04-11 18:10
 **/
@Data
@NoArgsConstructor
@ApiModel(description = "液位轨迹拆分、合并VO")
public class InventoryTraceMergeVO {
	@ApiModelProperty(value = "油罐编号")
	@JSONField(serialize = false)
	@NotNull(message = "油罐编号不能为空")
	private Integer tankNo;

	@ApiModelProperty(value = "开始时间")
	@JSONField(serialize = false)
	@NotNull(message = "总体开始时间不能为空")
	private Date startTime;

	@ApiModelProperty(value = "结束时间")
	@JSONField(serialize = false)
	@NotNull(message = "总体结束时间不能为空")
	private Date endTime;

	@NotEmpty(message = "分段液位信息不能为空")
	private List<Segment> segments;

	@Data
	@NoArgsConstructor
	@ApiModel(description = "分段具体液位组")
	public static class Segment {
		/**
		 * 液位id
		 */
		@ApiModelProperty(value = "开始液位id")
		@JSONField(serialize = false)
		@NotNull(message = "开始液位的id不能为空")
		private Long startId;

		@ApiModelProperty(value = "结束液位id集合")
		@JSONField(serialize = false)
		@NotNull(message = "结束液位的id不能为空")
		private Long endId;

		/**
		 * 新建液位的时候，用户必须选择
		 */
		@ApiModelProperty(value = "液位状态")
		@JSONField(serialize = false)
		@NotNull(message = "液位状态不能为空")
		private LevelState levelState;

		public Segment(@NotNull(message = "开始液位的id不能为空") Long startId, @NotNull(message = "结束液位的id不能为空") Long endId, @NotNull(message = "液位状态不能为空") LevelState levelState) {
			this.startId = startId;
			this.endId = endId;
			this.levelState = levelState;
		}
	}
	//合并整合表数据用
	private String groupIds;
}
