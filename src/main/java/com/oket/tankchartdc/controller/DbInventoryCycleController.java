package com.oket.tankchartdc.controller;

import com.alibaba.fastjson.JSONObject;
import com.oket.tankchartdc.service.DbInventoryCycleService;
import com.oket.util.CommonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 液位周期controller
 * @author: SunBiaoLong
 * @create: 2020-03-19 16:47
 **/
@RestController
@Api(tags ="液位周期" )
@RequestMapping("/tankchartdc/inventoryCycle")
public class DbInventoryCycleController  {
	@Autowired
	private DbInventoryCycleService dbInventoryCycleService;

	@ApiOperation(value = "查询液位周期")
	@GetMapping("/select")
	public JSONObject get(HttpServletRequest request) {
		return CommonUtil.successJson(dbInventoryCycleService.query(CommonUtil.request2Json(request), false));
	}
}
