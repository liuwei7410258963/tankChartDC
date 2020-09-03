package com.oket.tankchartdc.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.oket.common.base.BaseService;
import com.oket.dispenser.BzNozzleOutGroup;
import com.oket.tank4station.AbstractLevelTrace;
import com.oket.tank4station.entity.DbInventoryTrace;
import com.oket.tankchartdc.BackToTank;
import com.oket.tankchartdc.mina.json.bean.DitBackToTank;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @description:
 * @author: SunBiaoLong
 * @create: 2020-04-17 08:28
 **/
public interface BackToTankService extends IService<BackToTank> {
	/**
	 * 查询回罐集合
	 */
	JSONObject select(JSONObject jsonObject);

	/**
	 * 新增回罐记录
	 */
	void add(BackToTank backToTank);
	/**
	 * 有回罐，有付油
	 * @param trace
	 * @param bzNozzleOutGroup
	 */
	void process(AbstractLevelTrace trace, BzNozzleOutGroup bzNozzleOutGroup);

	/**
	 * 有回罐，有付油
	 * @param trace
	 * @param bzNozzleOutGroup
	 */
	void process(DbInventoryTrace trace, BzNozzleOutGroup bzNozzleOutGroup);

	/**
	 * 仅回罐，没有付油
	 * @param trace
	 */
	void process(AbstractLevelTrace trace);

	/**
	 * 处理dit传过来的回罐数据
	 * @param backToTankList
	 */
	void processDitBackToTank(List<DitBackToTank> backToTankList);
	/**
	 * q确认是否是回罐
	 * @param id
	 * @param isBackToTank
	 */
	void confirm(int id, boolean isBackToTank);

	/**
	 * 禁用回罐
	 * @param levelTraces
	 */
	 void disableBacks(List<AbstractLevelTrace> levelTraces);
	/**
	 * 禁用回罐
	 * @param traces
	 */
	void disableBackToTanks(List<DbInventoryTrace> traces);

	/**
	 * 导入回罐数据
	 * @param
	 */
	List<BackToTank> upload(MultipartFile file) throws IOException;
}
