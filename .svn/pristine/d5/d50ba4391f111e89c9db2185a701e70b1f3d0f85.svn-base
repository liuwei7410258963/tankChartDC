package com.oket.tankchartdc.controller;

import com.alibaba.fastjson.JSONObject;
import com.oket.common.base.BaseController;
import com.oket.delivery.BzDelivery;
import com.oket.station.HandOver;
import com.oket.tankchartdc.BackToTank;
import com.oket.tankchartdc.entity.BackToTankConfirmEntity;
import com.oket.tankchartdc.service.BackToTankService;
import com.oket.util.AirUtils;
import com.oket.util.CommonUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @description: 回罐接口
 * @author: SunBiaoLong
 * @create: 2020-04-17 08:27
 **/
@RestController
@RequestMapping("/tankchartdc/back")
public class BackToTankController extends BaseController {
	@Autowired
	private BackToTankService backToTankService;

	@GetMapping(value = "/list")
	@ApiImplicitParams({
			@ApiImplicitParam(value = "行数", name = "pageRow", required = true, paramType = "query"),
			@ApiImplicitParam(value = "当前页数", name = "pageNum", required = true, paramType = "query"),
			@ApiImplicitParam(value = "罐号", name = "tankNo", paramType = "query"),
			@ApiImplicitParam(value = "开始时间", name = "startTime", required = true, paramType = "query"),
			@ApiImplicitParam(value = "结束时间", name = "endTime", required = true, paramType = "query"),
			@ApiImplicitParam(value = "是否分页", name = "isPage", required = true, paramType = "query"),
	})
	public JSONObject list(HttpServletRequest request) {
		return backToTankService.select(CommonUtil.request2Json(request));
	}


	/**
	 * 新增回罐及确认数据
	 */
	@PostMapping("/add")
	@ApiOperation(value = "更新回罐确认数据")
	public JSONObject add(@RequestBody BackToTank backToTank) {
		backToTankService.add(backToTank);
		return CommonUtil.successJson();
	}

	/**
	 * 新增回罐及确认数据
	 */
	@PostMapping("/confirm")
	@ApiOperation(value = "确定是不是回罐")
	public JSONObject confirm(int id, boolean isBackToTank) {
		backToTankService.confirm(id, isBackToTank);
		return CommonUtil.successJson();
	}

	/**
	 * 导入回罐数据
	 * @param file 上传的excel文件
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject uploadHandOverMessage(@RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
		List<BackToTank> upload = backToTankService.upload(file);
		if(AirUtils.hv(upload)) {
			return CommonUtil.successJson(upload);
		}
		else{
			return CommonUtil.ErrorJson();
		}
	}




	/**
	 * 重写此方法给导出用
	 *
	 * @param requestJson
	 * @return
	 */
	@Override
	protected List<BackToTank> doQuery(JSONObject requestJson) {
		return backToTankService.select(requestJson).getJSONArray("info").toJavaList(BackToTank.class);
	}

	@Override
	@ApiOperation(value = "导出回罐数据")
	@PostMapping("/export")
	public void exportData(HttpServletResponse response, String excelFileds, String excelTitles, String fileName, String jsonObject, String language) throws Exception {
		super.exportData(response, excelFileds, excelTitles, fileName, jsonObject, language);
	}
}
