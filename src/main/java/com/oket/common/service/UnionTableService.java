package com.oket.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.oket.common.IParameter;
import com.oket.common.UnionTable;
import com.oket.tank4station.InventoryException;

/**
 * @Description: 联合表服务接口
 * @Author: Dxj
 * @Date: 2019/10/24 14:46
 */
public interface UnionTableService extends IService<UnionTable> {
	/**
	 * 获取数据库中该类型数据的表
	 *
	 * @param type
	 * @return
	 */
	String getUnionTable(int type);

	/**
	 * 获取表名时间（月份或年份）
	 *
	 * @param parameter
	 * @return
	 */
	String getUnionTime(IParameter parameter);

	/**
	 * 检查表
	 * 1.创建这个表//2.插入联合表//3.修改表结构
	 *
	 * @param type
	 * @param saveTableName
	 * @param alterTableName
	 * @throws Exception
	 */
	void checkTable(int type, String saveTableName, String alterTableName) throws Exception;


	/**
	 * 删除表
	 *
	 * @param saveTableName
	 */
	void dropTable(String saveTableName);

	/**
	 * 删除总表
	 *
	 * @param saveTableName
	 */
	void deleteUnionTable(String saveTableName);

	String getAndCheckSubTable(String subTableTime, String subTableNamePrefix, int type) throws Exception;
}
