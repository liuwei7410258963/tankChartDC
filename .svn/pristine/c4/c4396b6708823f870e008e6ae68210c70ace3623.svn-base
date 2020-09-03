package com.oket.device.controller;


import com.alibaba.fastjson.JSONObject;
import com.oket.common.base.BaseController;
import com.oket.device.Product;
import com.oket.device.service.ProductService;
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
 * 设备接口
 * @author lw
 * @since 2019-12-12
 */

@Api(value = "设备", tags = {"设备接口"})
@RestController
@RequestMapping("/device/product")
public class ProductController extends BaseController {
	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@ApiOperation(value = "查询所有设备列表", responseContainer = "List", response = Product.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "name", value = "设备名称", dataTypeClass = String.class, paramType = "query"),
			@ApiImplicitParam(name = "deviceType", value = "设备类型", dataTypeClass = String.class, paramType = "query"),
			@ApiImplicitParam(name = "productType", value = "设备型号", dataTypeClass = String.class, paramType = "query"),
			@ApiImplicitParam(name = "pageNum", value = "当前页", dataTypeClass = int.class, paramType = "query", required = true),
			@ApiImplicitParam(name = "pageRow", value = "每页行数", dataTypeClass = int.class, paramType = "query", required = true)
	})
	@GetMapping("/list")
	public JSONObject getDeviceList(HttpServletRequest request) {
		final JSONObject jsonObject = CommonUtil.request2Json(request);
		CommonUtil.hasAllRequired(jsonObject, "pageNum,pageRow");
		return CommonUtil.successPage(productService.query(jsonObject, true));
	}

	@Override
	protected List doQuery(JSONObject requestJson) {
		return productService.query(requestJson, false).getRecords();
	}


	@ApiOperation(value = "导出产品数据")
	@PostMapping("/export")
	public void exportDevice(HttpServletResponse response, String excelFileds, String excelTitles, String fileName, String jsonObject, String language) throws Exception {
		super.exportData(response, excelFileds, excelTitles, fileName, jsonObject, language);
	}

	@ApiOperation(value = "查询产品是否存在")
	@PostMapping("/exist")
	public JSONObject exist(@RequestBody Product product) {
		return CommonUtil.successJson(productService.queryExistDevice(product));
	}

	@ApiOperation(value = "增加产品")
	@PostMapping("/add")
	public JSONObject add(@RequestBody Product product) {
		return CommonUtil.successJson(productService.save(product));
	}

	@ApiOperation(value = "增加修改产品")
	@PostMapping("/update")
	public JSONObject update(@RequestBody Product product) {
		return CommonUtil.successJson(productService.saveOrUpdate(product));
	}
}
