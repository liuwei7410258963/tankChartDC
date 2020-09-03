package com.oket.tankchartdc.dao;

import com.alibaba.fastjson.JSONObject;
import com.oket.common.base.BaseDao;
import com.oket.tankchartdc.BzTraceRelOutGroup;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description: 液位轨迹关联付油组DAO
 * @author: SunBiaoLong
 * @create: 2020-04-14 14:29
 **/
@Repository
public interface BzTraceRelOutGroupDAO extends BaseDao<BzTraceRelOutGroup> {
	/**
	 * 获取关联关系
	 *
	 * @return
	 */
	List<BzTraceRelOutGroup> getRels(JSONObject req);

	/**
	 * 获取关联关系条数
	 *
	 * @return
	 */
	int count(JSONObject req);
}
