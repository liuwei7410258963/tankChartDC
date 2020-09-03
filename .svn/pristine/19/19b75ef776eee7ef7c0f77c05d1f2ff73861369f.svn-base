package com.oket.tank4station.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oket.tank4station.entity.DbInventory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 罐存dao
 */
@Repository(value = "dbInventoryDAO")
public interface DbInventoryDAO extends BaseMapper<DbInventory> {


	/**
	 * 往指定表插入罐存
	 *
	 * @param dbInventory 罐存信息
	 * @param tableName   要插入的表名
	 */
	void saveInventory(@Param("dbInventory") DbInventory dbInventory, @Param("tableName") String tableName);


	/**
	 * 批量保存
	 *
	 * @param inventoryList
	 * @param tableName
	 */
	void batchInsert(@Param("inventoryList") List<DbInventory> inventoryList, @Param("tableName") String tableName);

}
