package com.oket.tank4station.controller;

import com.alibaba.fastjson.JSONObject;
import com.oket.tank4station.InventoryException;
import com.oket.tank4station.service.DbInventoryTraceService;
import com.oket.tank4station.service.vo.InventoryTraceMergeVO;
import com.oket.util.CommonUtil;
import com.oket.util.constants.ErrorEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @description: 液位轨迹controller
 * @author: SunBiaoLong
 * @create: 2020-03-19 16:39
 **/
@RestController
@Api(tags = "液位轨迹")
@Slf4j
public class DbInventoryTraceController {
	@Autowired
	private DbInventoryTraceService dbInventoryTraceService;

	@ApiOperation(value = "查询液位轨迹")
	@GetMapping("/inventoryTrace")
	public JSONObject get(HttpServletRequest request, boolean isPage) {
		return CommonUtil.successJson(dbInventoryTraceService.query(CommonUtil.request2Json(request), isPage));
	}

	@ApiOperation(value = "查询液位轨迹，时间交集内的")
	@GetMapping("/inventoryTraceInTime")
	public JSONObject getTrace(HttpServletRequest request) {
		return CommonUtil.successJson(dbInventoryTraceService.getTrace(CommonUtil.request2Json(request)));
	}

	@ApiOperation(value = "液位轨迹的拆分合并")
	@PostMapping("/inventoryTraces")
	public JSONObject merge(@Valid @RequestBody InventoryTraceMergeVO traceMergeVO) {
		try {
			dbInventoryTraceService.merge(traceMergeVO);
		} catch (InventoryException e) {
			log.error("处理轨迹的拆分和合并失败，传入的信息：" + traceMergeVO + "\n;error:" + e.getMessage(), e);
			return CommonUtil.error(ErrorEnum.E_400.getErrorCode(), e.getMessage());
		}
		return CommonUtil.successJson();
	}

	@ApiOperation(value = "查询液位轨迹以及详情液位")
	@GetMapping("/inventoryTrace/{traceIds}")
	public JSONObject getWithInventory(@PathVariable String traceIds) {
		return CommonUtil.successJson(dbInventoryTraceService.queryInventories(Stream.of(traceIds.split(",")).map(Long::valueOf).collect(Collectors.toList())));
	}
}
