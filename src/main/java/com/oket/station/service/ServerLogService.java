package com.oket.station.service;

import com.alibaba.fastjson.JSONObject;
import com.oket.common.base.BaseService;
import com.oket.station.ServerLog;

import java.util.Date;
import java.util.List;

/**
 * @description: 系统运行日志service
 * @author: SunBiaoLong
 * @create: 2020-05-23 13:58
 **/
public interface ServerLogService extends BaseService<ServerLog> {

	/*
	 * 查询异常记录
	 */
	JSONObject select(JSONObject jsonObject);

	/**
	 * 获取最后一条日志
	 * @return
	 */
	ServerLog getLastLog();

	/**
	 * 获取日志信息
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	ServerLog getLog(Date startTime, Date endTime,int type);
}
