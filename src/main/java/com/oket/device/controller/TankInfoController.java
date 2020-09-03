package com.oket.device.controller;


import com.alibaba.fastjson.JSONObject;
import com.oket.common.base.BaseController;
import com.oket.device.TankInfo;
import com.oket.device.cache.DeviceCacheManager;
import com.oket.device.service.TankInfoService;
import com.oket.tank4station.service.VolumeTableService;
import com.oket.util.CommonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 油罐接口
 * @author lw
 * @since 2019-12-12
 */

@Api(value = "油罐", tags = {"油罐接口"})
@RestController
@RequestMapping("/device/tank")
public class TankInfoController extends BaseController {
	@Autowired
	private TankInfoService tankInfoService;
	@Autowired
	private VolumeTableService volumeTableService;


	@ApiOperation(value = "查询油罐列表")
	@GetMapping("/list")
	public JSONObject getTank(HttpServletRequest request) {
		return CommonUtil.successJson(tankInfoService.query(CommonUtil.request2Json(request)));
	}

	@ApiOperation(value = "查询油罐列表(不包含枪罐关系)")
	@GetMapping("/query")
	public JSONObject queryTank(HttpServletRequest request) {
		return CommonUtil.successJson(tankInfoService.queryTank(CommonUtil.request2Json(request)));
	}


	@ApiOperation(value = "查询油罐是否存在")
	@PostMapping("/exist")
	public JSONObject exist(@RequestBody TankInfo tankInfo) {
		return tankInfoService.queryExistTank(tankInfo);
	}


	@ApiOperation(value = "增加油罐")
	@PostMapping("/add")
	public JSONObject addTank(@RequestParam(required = false)MultipartFile profile,HttpServletRequest requestJson) {
		JSONObject jsonObject = CommonUtil.request2Json(requestJson);
		TankInfo tankInfo = tankInfoService.save(jsonObject);
		return	volumeTableService.addFileToDB(profile,CommonUtil.request2Json(requestJson));
	}

	@ApiOperation(value = "修改油罐")
	@PostMapping("/update")
	public JSONObject updateTank(@RequestParam(required = false) MultipartFile profile,HttpServletRequest requestJson) {
		JSONObject jsonObject = CommonUtil.request2Json(requestJson);
		TankInfo tankInfo = tankInfoService.update(jsonObject);
		return	volumeTableService.addFileToDB(profile,jsonObject);
	}


	@Override
	protected List doQuery(JSONObject requestJson) {
		return tankInfoService.query(requestJson);
	}

	@ApiOperation(value = "导出油罐数据")
	@PostMapping("/export")
	public void exportTankData(HttpServletResponse response, String excelFileds, String excelTitles, String fileName, String jsonObject, String language) throws Exception {
		super.exportData(response, excelFileds, excelTitles, fileName, jsonObject, language);
	}
}
