package com.oket.common.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oket.common.IParameter;
import com.oket.common.UnionTable;
import com.oket.common.dao.UnionTableDao;
import com.oket.common.service.UnionTableService;
import com.oket.tank4station.InventoryException;
import com.oket.util.StringExUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 联合表服务实现类
 * @author: Dxj
 * @create: 2019-10-24 14:46
 **/
@Slf4j
@Service
public class UnionTableServiceImpl extends ServiceImpl<UnionTableDao, UnionTable> implements UnionTableService {
	/**
	 * 联合表集合
	 */
	private static Map<Integer, String> unionTableMap = new HashMap<>();

	@Override
	public String getUnionTable(int type) {
		if (unionTableMap == null || unionTableMap.size() == 0 || unionTableMap.get(type) == null) {
			log.info("getUnionTable:" + type);
			refreshUnionTableMap(type);
		}
		return unionTableMap.get(type);
	}

	@Override
	public String getUnionTime(IParameter parameter) {
		return parameter.getSubTableTime();
	}

	/**
	 * unionTable 中加入
	 *
	 * @param type
	 * @param saveTableName
	 */
	private void insertUnionTable(int type, String saveTableName) {
		UnionTable unionTable = new UnionTable();
		unionTable.setInsertTime(new Date());
		unionTable.setUnionTableName(saveTableName);
		unionTable.setUnionTableType(type);
		//插入联合表
		baseMapper.insert(unionTable);
		//刷新联合表缓存
		refreshUnionTableMap(type);
	}

	/**
	 * 修改总表得表结构
	 *
	 * @param tableName
	 * @param unionTableName
	 */
	private void alterSumTable(String tableName, String unionTableName) {
		JSONObject alertT = new JSONObject();
		alertT.put("alertTable", tableName);
		alertT.put("tableOthers", unionTableName);
		log.debug("修改表结构" + alertT);
		//修改表结构
		baseMapper.alertSumTable(alertT);
	}

	/**
	 * 创建指定类型分表
	 *
	 * @param type
	 * @param saveTableName
	 */
	private void createInvTable(int type, String saveTableName) {
		switch (type) {
			case UnionTable.UNION_TYPE_INV:
				baseMapper.createInvTable(saveTableName);
				break;
			default:
				break;
		}


	}

	@Override
	public void checkTable(int type, String saveTableName, String alterTableName) throws Exception {
		try {
			//1.创建这个表
			createInvTable(type, saveTableName);
			//2.插入联合表
			insertUnionTable(type, saveTableName);
			String unionTable = getUnionTable(type);
			alterSumTable(alterTableName, unionTable);
		} catch (Exception e) {
			log.error("操作联合表出现异常", e);
			throw e;
		}
	}

	@Override
	public void dropTable(String saveTableName) {
		baseMapper.dropTable(saveTableName);

	}

	@Override
	public void deleteUnionTable(String saveTableName) {
		baseMapper.deleteUnionTable(saveTableName);
	}

	/**
	 * 刷新联合表缓存
	 *
	 * @param type
	 */
	private void refreshUnionTableMap(int type) {
		//根据 分表得类型去表里查询 这个类型下得得表
		String tableNames = baseMapper.getUnionTableName(type);
		if (tableNames != null && tableNames.length() > 0) {
			unionTableMap.put(type, tableNames);
			log.debug("类型：" + type + "==" + tableNames);
		}
	}


	/**
	 * 获取分表名称，并校验
	 * 如果没有指定表，需要新建相关表
	 *
	 * @param subTableTime
	 * @return
	 * @throws InventoryException
	 */
	@Override
	public String getAndCheckSubTable(String subTableTime, String subTableNamePrefix, int type) throws Exception {
		StringBuilder currUnionTableName = new StringBuilder();
		String unionTableName = this.getUnionTable(type);
		currUnionTableName.append(subTableNamePrefix).append(StringExUtils.UNDERLINE)
				.append(subTableTime);
		String saveTableName = currUnionTableName.toString();
		if (unionTableName == null || !unionTableName.contains(saveTableName)) {
			try {
				//联合表操作
				this.checkTable(type, saveTableName, subTableNamePrefix);
			} catch (Exception e) {
				try {
					log.error("doSaveAlarm method exception", e);
					this.dropTable(saveTableName);
					log.info("drop table " + saveTableName);
					this.deleteUnionTable(saveTableName);
					log.info("delete from s_dc_union_table where union_table_name = '" + saveTableName + "'");
				} catch (Exception e1) {
					log.error("", e1);
				}
				throw e;
			}
		}
		return saveTableName;
	}

}
