package com.oket.device.controller;

import com.alibaba.fastjson.JSONObject;
import com.oket.common.base.BaseController;
import com.oket.device.Dispenser4Device;
import com.oket.device.TankInfo;
import com.oket.device.cache.DeviceCacheManager;
import com.oket.device.service.Dispenser4DeviceService;
import com.oket.oil.OilEntity;
import com.oket.station.bizservice.BalanceService;
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
 * 加油机接口
 * @author lw
 * @since 2019-12-12
 */

@Api(value = "加油机", tags = {"加油机接口"})
@RestController
@RequestMapping(value = "/device/dispenser")
public class Dispenser4DeviceController extends BaseController {
	@Autowired
	private Dispenser4DeviceService dispenser4DeviceService;
	@Autowired
	BalanceService balanceService;
	/**
	 * 增加加油机
	 */
	@PostMapping("/add")
	public JSONObject saveDispenser(@RequestBody Dispenser4Device dispenser4Devices) {
		dispenser4DeviceService.save(dispenser4Devices);
		return CommonUtil.successJson();
	}

	@ApiOperation(value = "查询加油机是否存在")
	@PostMapping("/exist")
	public JSONObject exist(@RequestBody Dispenser4Device dispenser4Device) {
		return dispenser4DeviceService.queryExistDispenser(dispenser4Device);
	}


	/**
	 * 修改加油机信息
	 */
	@PostMapping("/update")
	public JSONObject updateDispenser(@RequestBody Dispenser4Device dispenser4Devices) {
		dispenser4DeviceService.saveOrUpdate(dispenser4Devices);
		//加油机信息更改通知处理端
		balanceService.dispenserPropertyChanged(dispenser4Devices);
		return CommonUtil.successJson();
	}

	@ApiOperation(value = "查询所有加油机列表", responseContainer = "List", response = Dispenser4Device.class)
	@GetMapping("/list")
	public JSONObject listDispensers(HttpServletRequest request) {
		return CommonUtil.successJson(dispenser4DeviceService.query(CommonUtil.request2Json(request)));
	}

	/**
	 * 加油机列表导出
	 * @param requestJson
	 * @return
	 */
	@Override
	protected List doQuery(JSONObject requestJson) {
		return dispenser4DeviceService.query(requestJson);
	}

	@ApiOperation(value = "加油机列表导出")
	@PostMapping("/export")
	public void exportOilWaterData(HttpServletResponse response, String excelFileds, String excelTitles, String fileName, String jsonObject, String language) throws Exception {
		super.exportData(response, excelFileds, excelTitles, fileName, jsonObject, language);
	}


}
