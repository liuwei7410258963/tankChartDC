package com.oket.common;

/**
 * @Description: 实体数据
 * @Author: Dxj
 * @Date: 2019/10/16 11:23
 */
public interface IParameter {
	/**
	 * 获取分表名称的时间部分
	 * 分表名称： 分表名+时间（一般是yyyyMM）
	 *
	 * @return
	 */
	String getSubTableTime();
}
