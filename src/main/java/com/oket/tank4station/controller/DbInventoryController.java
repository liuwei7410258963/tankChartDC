package com.oket.tank4station.controller;

import com.alibaba.fastjson.JSONObject;
import com.oket.common.base.BaseController;
import com.oket.tank4station.service.DbInventoryService;
import com.oket.util.CommonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * VIEW 罐存
 * </p>
 *
 * @author SunBiaoLong
 * @since 2019-10-12
 */
@RestController
@RequestMapping("/inventory")
@Api(tags = "液位接口")
@Slf4j
public class DbInventoryController extends BaseController {
	@Autowired
	private DbInventoryService dbInventoryService;

	/**
	 * 查询罐存列表分页
	 */
	@ApiOperation(value = "查询罐存列表")
	@GetMapping("/list")
	public JSONObject getStation(HttpServletRequest request) {
		return CommonUtil.successPage(dbInventoryService.query(CommonUtil.request2Json(request), true));
	}

	@Override
	protected List doQuery(JSONObject requestJson) {
		return dbInventoryService.query(requestJson, false).getRecords();
	}

	@ApiOperation(value = "导出罐存数据")
	@PostMapping("/export")
	public void exportInvertoryData(HttpServletResponse response, String excelFileds, String excelTitles, String fileName, String jsonObject, String language) throws Exception {
		super.exportData(response, excelFileds, excelTitles, fileName, jsonObject, language);
	}

	/**
	 * 是否启用批量保存
	 *
	 * @return
	 */
	@ApiOperation(value = "是否启用批量保存")
	@GetMapping(value = "/persistentType")
	public JSONObject isEnableBatchInsert() {
		return CommonUtil.successJson(dbInventoryService.isEnableBatchInsert());
	}

	/**
	 * 设置是否启用批量保存
	 *
	 * @param enableBatchInsert
	 */
	@ApiOperation(value = "设置是否启用批量保存")
	@GetMapping(value = "/persistentType/set")
	@ApiImplicitParams(@ApiImplicitParam(value = "是否启用批量保存", name = "enableBatchInsert", required = true)
	)
	public JSONObject setEnableBatchInsert(boolean enableBatchInsert) {
		try {
			dbInventoryService.isEnableBatchInsert();
			return CommonUtil.successJson();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return CommonUtil.failJson();
		}
	}

	@ApiOperation(value = "获取一段轨迹内的罐存数据")
	@GetMapping(value = "/inventory/inTrace")
	@ApiImplicitParams({@ApiImplicitParam(value = "油罐编号", name = "tankNo", required = true),
					@ApiImplicitParam(value = "开始液位的id", name = "startId", required = true),
					@ApiImplicitParam(value = "结束液位的id", name = "endId", required = true)
	})
	public JSONObject getInventoryInGap(int tankNo, long startId, long endId) {
		return CommonUtil.successJson(dbInventoryService.getInGaps(tankNo, startId, endId));
	}
}
