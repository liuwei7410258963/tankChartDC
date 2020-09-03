package com.oket.device.controller;


import com.alibaba.fastjson.JSONObject;
import com.oket.common.base.BaseController;
import com.oket.device.Dispenser4Device;
import com.oket.device.MaintenanceManager;
import com.oket.device.service.MaintenanceManagerService;
import com.oket.util.CommonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 运维单位接口
 * @author lw
 * @since 2019-12-12
 */

@Api(value = "运维单位", tags = {"运维单位接口"})
@RestController
@RequestMapping("/device/maintenanceManager")
public class MaintenanceManagerController extends BaseController {
	@Autowired(required = false)
	private MaintenanceManagerService maintenanceManagerService;

	@ApiOperation(value = "查询所有运维单位信息", responseContainer = "List", response = MaintenanceManager.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "name", value = "单位名", dataTypeClass = String.class, paramType = "query")
	})
	@GetMapping("/list")
	public JSONObject getMaintenanceManager(HttpServletRequest request) {
		return CommonUtil.successPage(maintenanceManagerService.query(CommonUtil.request2Json(request), true));
	}


	@ApiOperation(value = "查询运维单位是否存在")
	@PostMapping("/exist")
	public JSONObject exist(@RequestBody MaintenanceManager maintenanceManager) {
		return CommonUtil.successJson(
				maintenanceManagerService.queryExistMaintenanceManager(maintenanceManager));
	}

	@ApiOperation(value = "增加运维单位")
	@PostMapping("/add")
	public JSONObject addMaintenanceManager(@RequestBody MaintenanceManager maintenanceManager) {
		return CommonUtil.successJson(maintenanceManagerService.save(maintenanceManager));
	}

	@ApiOperation(value = "修改运维单位")
	@PostMapping("/update")
	public JSONObject updateMaintenanceManager(@RequestBody MaintenanceManager maintenanceManager) {
		return CommonUtil.successJson(maintenanceManagerService.saveOrUpdate(maintenanceManager));
	}


	@Override
	protected List doQuery(JSONObject requestJson) {
		return maintenanceManagerService.query(requestJson, false).getRecords();
	}

	@ApiOperation(value = "导出运维单位数据")
	@PostMapping("/export")
	public void exportMaintenanceManager(HttpServletResponse response, String excelFileds, String excelTitles, String fileName, String jsonObject, String language) throws Exception {
		super.exportData(response, excelFileds, excelTitles, fileName, jsonObject, language);
	}
}
