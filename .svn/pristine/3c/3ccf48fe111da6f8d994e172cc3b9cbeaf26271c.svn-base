package com.oket.device;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.oket.common.base.BaseEntity;
import com.oket.common.base.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 运维单位
 * </p>
 *
 * @author sunbiaolong
 * @since 2019-10-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("dev_maintenance_manager")
public class MaintenanceManager extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@TableId(value = "ID", type = IdType.AUTO)
	private Integer id;
	/**
	 * 单位名
	 */
	@TableField("NAME")
	private String name;
	/**
	 * 运维人员+电话号
	 * 张三:13112345678;李四:131212345678
	 */
	@TableField("LINK_NAME")
	private String linkName;
	/**
	 * 状态
	 */
	@TableField("STATUS")
	private Status status;
}
