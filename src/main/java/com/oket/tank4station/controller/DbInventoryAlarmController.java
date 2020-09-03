package com.oket.tank4station.controller;

import com.alibaba.fastjson.JSONObject;
import com.oket.common.base.BaseController;
import com.oket.tank4station.service.DbInventoryAlarmService;
import com.oket.util.CommonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @description:
 * @author: SunBiaoLong
 * @create: 2020-03-19 16:48
 **/
@RestController
@Api(tags = "液位报警")
@RequestMapping("/inventoryAlarm")
public class DbInventoryAlarmController extends BaseController {
	@Autowired
	private DbInventoryAlarmService dbInventoryAlarmService;

	@ApiOperation(value = "查询液位报警")
	@GetMapping("/select")
	public JSONObject get(HttpServletRequest request) {
		return CommonUtil.successJson(dbInventoryAlarmService.query(CommonUtil.request2Json(request), true));
	}


	@Override
	protected List doQuery(JSONObject requestJson) {
		return dbInventoryAlarmService.query(requestJson, false).getRecords();
	}

	@ApiOperation(value = "导出异常信息")
	@PostMapping("/export")
	public void exportTankData(HttpServletResponse response, String excelFileds, String excelTitles, String fileName, String jsonObject, String language) throws Exception {
		super.exportData(response, excelFileds, excelTitles, fileName, jsonObject, language);
	}
}
