package com.oket.tankchartdc.controller;

import com.alibaba.fastjson.JSONObject;
import com.oket.common.base.BaseController;
import com.oket.delivery.BzDelivery;
import com.oket.tankchartdc.BzTraceRelOutGroup;
import com.oket.tankchartdc.service.BzTraceRelOutGroupService;
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
 * @description: 液位轨迹和付油组合接口
 * @author: SunBiaoLong
 * @create: 2020-04-20 08:29
 **/
@RestController
@Api(tags = "液位轨迹和付油组合接口")
public class BzTraceRelOutGroupController extends BaseController {
	@Autowired
	private BzTraceRelOutGroupService bzTraceRelOutGroupService;

	@GetMapping("/traceRelOutGroup")
	@ApiOperation("查询当前付油组合和液位组合")
	public JSONObject list(HttpServletRequest request) {
		return bzTraceRelOutGroupService.select(CommonUtil.request2Json(request));
	}


	@ApiOperation(value = "导出卸油数据")
	@PostMapping("/traceRelOutGroup/export")
	public void exportDeliveryData(HttpServletResponse response, String excelFileds, String excelTitles, String fileName, String jsonObject, String language) throws Exception {
		super.exportData(response, excelFileds, excelTitles, fileName, jsonObject, language);
	}

	/**
	 * 重写此方法给导出用
	 *
	 * @param requestJson
	 * @return
	 */
	@Override
	protected List doQuery(JSONObject requestJson) {
		return bzTraceRelOutGroupService.export(requestJson);
	}

	@PutMapping("/traceRelOutGroup")
	@ApiOperation("修改当前付油组合和液位组合")
	public JSONObject edit(@RequestBody List<BzTraceRelOutGroup> bzTraceRelOutGroups) {
		bzTraceRelOutGroupService.edit(bzTraceRelOutGroups);
		return CommonUtil.successJson();
	}
}
