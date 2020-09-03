package com.oket.oil.controller;


import com.alibaba.fastjson.JSONObject;
import com.oket.common.base.BaseController;
import com.oket.oil.OilEntity;
import com.oket.oil.cache.OilCacheManager;
import com.oket.oil.service.OilService;
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
 * <p>
 * 前端控制器
 * </p>
 * 油品
 *
 * @author sunbiaolong
 * @since 2019-10-14
 */
@RestController
@RequestMapping("/oil")
@Api(value = "油品", tags = {"油品接口"})
public class OilController extends BaseController {

	@Autowired
	private OilService oilService;
	@Autowired
	private OilCacheManager oilCacheManager;


	@ApiOperation(value = "查询所有油品列表", responseContainer = "List", response = OilEntity.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "code", value = "油品编码", dataTypeClass = String.class, paramType = "query"),
			@ApiImplicitParam(name = "fullName", value = "油品全称", dataTypeClass = String.class, paramType = "query"),
			@ApiImplicitParam(name = "shortName", value = "油品简称", dataTypeClass = String.class, paramType = "query"),
			@ApiImplicitParam(name = "type", value = "油品类型，GASOLINE=汽油,DIESEL=柴油,OTHER=其他", dataTypeClass = String.class, paramType = "query"),
			@ApiImplicitParam(name = "pageNum", value = "当前页", dataTypeClass = int.class, paramType = "query", required = true),
			@ApiImplicitParam(name = "pageRow", value = "每页行数", dataTypeClass = int.class, paramType = "query", required = true)
	})
	@GetMapping("/list")
	public JSONObject getOil(HttpServletRequest request, @RequestParam(defaultValue = "true") boolean isPage) {
		final JSONObject jsonObject = CommonUtil.request2Json(request);
		if (isPage) {
			CommonUtil.hasAllRequired(jsonObject, "pageNum,pageRow");
		}
		return CommonUtil.successPage(oilService.query(jsonObject, isPage));
	}


	@ApiOperation(value = "查询油品是否存在")
	@PostMapping("/exist")
	public JSONObject exist(@RequestBody OilEntity oilEntity) {
		return CommonUtil.successJson(oilService.queryExistOil(oilEntity));
	}


	@ApiOperation(value = "增加油品")
	@PostMapping("/add")
	public JSONObject addOil(@RequestBody OilEntity oilEntity) {
		oilService.save(oilEntity);
		oilCacheManager.refresh();
		return CommonUtil.successJson();
	}


	@ApiOperation(value = "修改油品")
	@PostMapping("/update")
	public JSONObject updateOil(@RequestBody OilEntity oilEntity) {
		oilService.saveOrUpdate(oilEntity);
		oilCacheManager.refresh();
		return CommonUtil.successJson();
	}


	@Override
	protected List doQuery(JSONObject requestJson) {
		return oilService.query(requestJson, false).getRecords();
	}

	@ApiOperation(value = "导出油品数据")
	@PostMapping("/export")
	public void exportOilData(HttpServletResponse response, String excelFileds, String excelTitles, String fileName, String jsonObject, String language) throws Exception {
		super.exportData(response, excelFileds, excelTitles, fileName, jsonObject, language);
	}
}
