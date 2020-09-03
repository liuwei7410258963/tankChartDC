package com.oket.station;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import com.oket.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @description: 系统日志
 * @author: SunBiaoLong
 * @create: 2020-05-23 13:39
 **/
@EqualsAndHashCode(callSuper = true)
@TableName(value = "server_log")
@Data
@Table(name="server_log")
public class ServerLog extends BaseEntity {
	/**
	 * 运行
	 */
	public static int RUNNING = 1;
	/**
	 * 离线
	 */
	public static int OFFLINE = 2;
	/**
	 * 类型
	 */
	@Column(type = MySqlTypeConstant.INT,name = "type")
	private int type;
	/**
	 * 开始时间
	 */

	@Column(name="update_time",type=MySqlTypeConstant.DATETIME)
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date startTime;
	/**
	 * 结束时间
	 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;
}
