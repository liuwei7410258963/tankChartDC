package com.oket.oil;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.oket.common.base.BaseEntity;
import com.oket.common.base.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * <p>
 *
 * </p>
 *
 * @author sunbiaolong
 * @since 2019-10-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("bd_oil_info")
public class OilEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@TableId(value = "ID", type = IdType.AUTO)
	private Integer id;
	/**
	 * 油品全名
	 */
	@TableField("FULL_NAME")
	private String fullName;

	/**
	 * 油品简称
	 */
	@TableField("SHORT_NAME")
	private String shortName;
	/**
	 * 油品类型 1汽油 2柴油 3其他
	 */
	@TableField("TYPE")
	private OilType type;
	/**
	 * 油品编号
	 */
	@TableField("CODE")
	private String code;
	/**
	 * 油品密度
	 */
	@TableField("DENSITY")
	private BigDecimal density;
	/**
	 * 0：启用 1：禁用
	 */
	private Status status = Status.ENABLE;
	/**
	 * 颜色
	 */
	private String color;

	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 油品膨胀率
	 */
	@TableField(exist = false)
	private double oilExpansionRate;

}
