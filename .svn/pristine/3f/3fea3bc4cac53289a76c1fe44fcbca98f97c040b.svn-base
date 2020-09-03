package com.oket.dispenser.controller;

import com.alibaba.fastjson.JSONObject;
import com.oket.dispenser.DispenserErrorItem;
import com.oket.dispenser.service.DispenserErrorItemService;
import com.oket.util.CommonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 油枪精度controller
 * @author: SunBiaoLong
 * @create: 2020-02-17 15:52
 **/
@RestController
@Api(value = "油枪精度接口", tags = "油枪精度接口")
public class DispenserErrorController {
	@Autowired
	private DispenserErrorItemService dispenserErrorItemService;
	@Autowired
	private HttpServletRequest request;

	@ApiOperation(value = "油枪精度查询")
	@ApiImplicitParams({
			@ApiImplicitParam(value = "油枪编号", name = "nozzleNo", required = true)
	})
	@GetMapping("/dispenserError/nozzleNo")
	public JSONObject getByNozzleNo(Integer nozzleNo) {
		final JSONObject jsonObject = CommonUtil.convert2JsonAndCheckRequiredColumns(request, "nozzleNo");
		CommonUtil.hasAllRequired(jsonObject, "nozzleNo");
		return CommonUtil.successJson(dispenserErrorItemService.getByNozzleNo(nozzleNo));
	}

	@ApiOperation(value = "油枪精度列表查询")
	@ApiImplicitParams({
			@ApiImplicitParam(value = "油枪编号", name = "nozzleNo"),
			@ApiImplicitParam(value = "开始时间", name = "fromDate"),
			@ApiImplicitParam(value = "结束时间", name = "endDate"),
	})
	@GetMapping("/dispenserError")
	public JSONObject getDispenserErrorItem(HttpServletRequest request) {
		return CommonUtil.successPage(dispenserErrorItemService.query(CommonUtil.request2Json(request), true));
	}


	@ApiOperation(value = "油枪精度保存")
	@PostMapping("/dispenserError")
	public JSONObject saveDispenserErrorItem(@RequestBody DispenserErrorItem dispenserErrorItem) {
		dispenserErrorItemService.saveDispenserErrorItem(dispenserErrorItem);
		return CommonUtil.successJson();
	}
}
