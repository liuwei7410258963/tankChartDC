package com.oket.delivery.controller;

import com.alibaba.fastjson.JSONObject;
import com.oket.common.base.BaseController;
import com.oket.delivery.BzDelivery;
import com.oket.delivery.service.DeliveryService;
import com.oket.util.CommonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
 * 〈卸油记录〉
 */
@RestController
@RequestMapping(value = "/delivery")
@Api(value = "卸油接口", tags = {"卸油接口"})
public class DeliveryController extends BaseController {
	@Autowired(required = false)
	private DeliveryService deliveryService;

	/**
	 * 查询卸油列表
	 */
	@ApiOperation(value = "查询卸油列表", response = BzDelivery.class)
	@ApiImplicitParams({
			@ApiImplicitParam(value = "行数", name = "pageRow", required = true, paramType = "query"),
			@ApiImplicitParam(value = "当前页数", name = "pageNum", required = true, paramType = "query"),
			@ApiImplicitParam(value = "罐号", name = "tankNo", paramType = "query"),
			@ApiImplicitParam(value = "油品编码", name = "oilCode", paramType = "query"),
			@ApiImplicitParam(value = "开始时间", name = "startTime", required = true, paramType = "query"),
			@ApiImplicitParam(value = "结束时间", name = "endTime", required = true, paramType = "query"),
	})
	@GetMapping("/list")
	public JSONObject listDeliveries(HttpServletRequest request) {
		final JSONObject jsonObject = CommonUtil.request2Json(request);
		return CommonUtil.successPage(deliveryService.query(jsonObject, true));
	}




	/**
	 * 重写此方法给导出用
	 *
	 * @param requestJson
	 * @return
	 */
	@Override
	protected List<BzDelivery> doQuery(JSONObject requestJson) {
		return deliveryService.query(requestJson,false).getRecords();
	}

	@ApiOperation(value = "导出卸油数据")
	@PostMapping("/export")
	public void exportDeliveryData(HttpServletResponse response, String excelFileds, String excelTitles, String fileName, String jsonObject, String language) throws Exception {
		super.exportData(response, excelFileds, excelTitles, fileName, jsonObject, language);
	}

}
