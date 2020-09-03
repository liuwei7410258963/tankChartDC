/**
 *
 */
package com.oket.dispenser;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.oket.common.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.Objects;

/**
 * 样本误差系数
 * @author 王恒
 * @since 2016-04-04
 *
 */
@Data
@TableName(value = "dev_dispenser_error_item")
@ApiModel(description = "油枪精度")
public class DispenserErrorItem extends BaseEntity {
	/**样本ID,例如采用加油机付油数为付出数做样本的*/
	private Integer sequence;
	/**
	 * 油枪编号
	 */
	@ApiModelProperty(value = "油枪编号", required = true)
	private Integer nozzleNo;
	/**
	 * 检定不确定度值
	 * 浮点数用%分号表示值；数值范围±1 内
	 *
	 */
	@ApiModelProperty(value = "检定不确定度值")
	private double errorCoe;
	/**
	 * 校正不确定度值：校正不确定度值是指当检定值超过±0.3%内，技术监
	 * 督局做的修正值；
	 * 值的范围：数值范围±1 内
	 */
	@ApiModelProperty(value = "校正不确定度值")
	private double correctUncertainty;
	/** 误差作用的有效开始时间，如果开始时间为null，这样本结束时间以前的全部样本都采用此误差系数  */
	@JSONField(format = "yyyy-MM-dd")
	@ApiModelProperty(value = "开始时间", required = true)
	private Date fromDate;
	/** 误差作用的有效结束时间，如果结束时间为null，则开始时间非null，之后的所有样本采用此误差系数，如果开始时间也为null，这此id下的所有样本误差采用次系数  */
	@JSONField(format = "yyyy-MM-dd")
	@ApiModelProperty(value = "结束时间")
	private Date endDate;

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		if (!super.equals(o)) {
			return false;
		}
		DispenserErrorItem that = (DispenserErrorItem) o;
		return Double.compare(that.errorCoe, errorCoe) == 0 &&
				Double.compare(that.correctUncertainty, correctUncertainty) == 0 &&
				Objects.equals(nozzleNo, that.nozzleNo) &&
				Objects.equals(fromDate, that.fromDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), nozzleNo, errorCoe, correctUncertainty, fromDate);
	}
}
