package com.oket.common;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 联合表
 * </p>
 *
 * @author Dxj
 * @since 2019-10-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("union_table")
public class UnionTable implements Serializable {

	//实时罐存
	public static final int UNION_TYPE_INV = 1;
	private static final long serialVersionUID = 6033722889070552476L;
	@TableId(type = IdType.AUTO)
	private Integer id;
	/**
	 * 分表表名
	 */
	@TableField
	private String unionTableName;
	/**
	 * 表的类型
	 */
	@TableField
	private Integer unionTableType;
	@TableField
	private Date insertTime;

}
