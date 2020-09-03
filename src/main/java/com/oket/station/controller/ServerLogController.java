package com.oket.station.controller;

import com.alibaba.fastjson.JSONObject;
import com.oket.station.service.ServerLogService;
import com.oket.util.CommonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 系统日志控制器
 * @author: SunBiaoLong
 * @create: 2020-05-23 15:45
 **/
@RestController
@RequestMapping(value = "/serverLog")
@Api(value = "系统日志接口")
public class ServerLogController {

	@Autowired
	private ServerLogService serverLogService;

	/**
	 * 查询异常信息
	 */
	@GetMapping("/select")
	@ApiOperation(value = "查询异常信息")
	public JSONObject select(HttpServletRequest request) {
		return serverLogService.select(CommonUtil.request2Json(request));
	}
}
