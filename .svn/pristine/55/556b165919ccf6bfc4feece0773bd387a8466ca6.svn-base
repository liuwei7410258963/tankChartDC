package com.oket.dispenser.controller;


import com.alibaba.fastjson.JSONObject;
import com.oket.common.base.BaseController;
import com.oket.dispenser.service.NozzleOutService;
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
 * <p>
 * VIEW 前端控制器
 * </p>
 * 销售记录
 *
 * @author sunbiaolong
 * @since 2019-10-21
 */
@RestController
@RequestMapping("/nozzle")
@Api(tags = "销售记录")
public class NozzleOutController extends BaseController {
	@Autowired(required = false)
	private NozzleOutService nozzleOutService;

	/**
	 * 查询销售列表
	 */
	@ApiOperation(value = "查询销售列表")
	@GetMapping("/list")
	public JSONObject listNetBreaks(HttpServletRequest request) {
		return CommonUtil.successPage(nozzleOutService.query(CommonUtil.request2Json(request), true));
	}

	/**
	 * 销售记录油品汇总
	 */
	@ApiOperation(value = "销售各个油品的类型汇总")
	@GetMapping("/summary")
	public JSONObject deliveryOilSum(HttpServletRequest request) {
		return nozzleOutService.getOilSum(CommonUtil.request2Json(request));
	}

	/**
	 * 导出
	 *
	 * @param requestJson
	 * @return
	 */
	@Override
	protected List doQuery(JSONObject requestJson) {
		return nozzleOutService.query(requestJson, false).getRecords();
	}

	@ApiOperation(value = "导出销售记录记录")
	@PostMapping("/export")
	public void exportDeliveryData(HttpServletResponse response, String excelFileds, String excelTitles, String fileName, String jsonObject, String language) throws Exception {
		super.exportData(response, excelFileds, excelTitles, fileName, jsonObject, language);
	}
}
