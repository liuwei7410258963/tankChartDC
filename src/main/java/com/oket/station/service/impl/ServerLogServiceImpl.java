package com.oket.station.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oket.station.RunTimeErrorVO;
import com.oket.station.ServerLog;
import com.oket.station.dao.ServerLogDAO;
import com.oket.station.service.ServerLogService;
import com.oket.util.CommonUtil;
import net.sf.jsqlparser.statement.select.Wait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @description: 系统运行日志service
 * @author: SunBiaoLong
 * @create: 2020-05-23 13:58
 **/
@Service
public class ServerLogServiceImpl extends ServiceImpl<ServerLogDAO, ServerLog> implements ServerLogService {
	@Autowired
	ServerLogDAO serverLogDAO;
	@Override
	public JSONObject select(JSONObject jsonObject){
		List<JSONObject> serverLists = serverLogDAO.selectServer(jsonObject);
		List<JSONObject> abnormalLists = serverLogDAO.selectAbnormal(jsonObject);
		JSONObject firstTimeJson = serverLogDAO.selectTime(jsonObject);
		List<JSONObject> dbLists = new ArrayList<>();
		dbLists.addAll(serverLists);
		dbLists.addAll(abnormalLists);
		dbLists.stream().forEach(x->{
			//异常数据
			if(x.get("error_type")!=null){
				//断开连接
				if(x.getIntValue("error_type")==1){
					if(x.getString("dit_type").equals("IFSF")){
						x.put("typeString","IFSF断开连接");
					}
					else if(x.getString("dit_type").equals("JSON")){
						x.put("typeString","JSON断开连接");
					}
				}
				//ifsf长时间未发送
				else if(x.getIntValue("error_type")==2){
					x.put("typeString","IFSF接收时间间隔过长");
				}
			}
			//离线数据
			else{
				x.put("typeString","离线");
			}
			if(x.getDate("end_time")!=null){
				x.put("min",(x.getDate("end_time").getTime()-x.getDate("start_time").getTime())/1000/60);
			}
			else{
				x.put("min",0);
			}
		});
		List<JSONObject> result;
		result = dbLists.stream().filter(x->x.getDate("endTime")==null)
				.sorted(Comparator.comparing(x -> x.getDate("endTime"), Comparator.nullsLast(Date::compareTo).reversed()))
				.collect(toList());
		List<JSONObject> temp = dbLists.stream().filter(x->x.getDate("endTime")!=null).sorted(Comparator.comparing(x -> x.getDate("startTime"), Comparator.nullsLast(Date::compareTo).reversed())).collect(toList());
		result.addAll(temp);
		Date now = new Date();
		RunTimeErrorVO runTimeErrorVO = new RunTimeErrorVO();
		runTimeErrorVO.setStartTime(firstTimeJson.getString("startTime"));
		runTimeErrorVO.setDay((int) Math.ceil((double)(now.getTime()-firstTimeJson.getDate("start_time").getTime())/1000/60/60/24));
		runTimeErrorVO.setResultLists(result);
		return 	CommonUtil.successJson(runTimeErrorVO);

	}
	@Override
	public IPage<ServerLog> query(JSONObject req, boolean isPage) {

		IPage<ServerLog> page = new Page<>(req.getIntValue("pageNum"), req.getIntValue("pageRow"));

		QueryWrapper<ServerLog> wrapper = new QueryWrapper<>();
		if (req.getString("startTime") != null && req.getString("endTime") != null) {
			wrapper.lambda().ge(ServerLog::getStartTime, req.get("startTime"));
			wrapper.lambda().le(ServerLog::getEndTime, req.get("endTime"));
		}
		wrapper.lambda().orderByAsc(ServerLog::getStartTime);
		if (isPage) {
			return baseMapper.selectPage(page, wrapper);
		}
		page.setRecords(list(wrapper));
		return page;
	}

	@Override
	public ServerLog getLog(Date startTime, Date endTime, int type) {
		QueryWrapper<ServerLog> wrapper = new QueryWrapper<>();
		wrapper.lambda().ge(ServerLog::getStartTime, startTime);
		wrapper.lambda().le(ServerLog::getEndTime, endTime);
		wrapper.lambda().orderByDesc(ServerLog::getStartTime);
		wrapper.last("LIMIT 1");
		return getOne(wrapper);
	}

	@Override
	public ServerLog getLastLog() {
		QueryWrapper<ServerLog> wrapper = new QueryWrapper<>();
		wrapper.lambda().orderByDesc(ServerLog::getStartTime);
		wrapper.last("LIMIT 1");
		return getOne(wrapper);
	}
}
