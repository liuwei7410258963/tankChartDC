package com.oket.device.controller;

import com.alibaba.fastjson.JSONObject;
import com.oket.common.base.BaseController;
import com.oket.device.Nozzle4Device;
import com.oket.device.service.Nozzle4DeviceService;
import com.oket.dispenser.DispenserErrorItem;
import com.oket.dispenser.service.DispenserErrorItemService;
import com.oket.station.bizservice.BalanceService;
import com.oket.util.CommonUtil;
import com.oket.util.StringTools;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


/**
 * 加油枪接口
 *
 * @author lw
 * @since 2019-12-12
 */
@Api(value = "加油枪", tags = {"加油枪接口"})
@RestController
@RequestMapping(value = "/device/nozzle")
public class NozzleController extends BaseController {
	@Autowired
	private Nozzle4DeviceService nozzle4DeviceService;
	@Autowired
	private DispenserErrorItemService dispenserErrorItemService;
	@Autowired
	BalanceService balanceService;

	@ApiOperation(value = "查询加油枪信息", responseContainer = "List", response = Nozzle4Device.class)
	@GetMapping("/list")
	public JSONObject getTank(HttpServletRequest request) {
		final JSONObject jsonObject = CommonUtil.request2Json(request);
		final String nozzle = jsonObject.getString("nozzleNo");
		if (StringUtils.isNotEmpty(nozzle)) {
			if (!StringTools.isNumeric(nozzle)) {
				return CommonUtil.successJson();
			}
		}
		return CommonUtil.successPage(nozzle4DeviceService.query(jsonObject, false));
	}

	@ApiOperation(value = "查询油枪是否存在")
	@PostMapping("/exist")
	public JSONObject exist(@RequestBody Nozzle4Device nozzle4Device) {
		return nozzle4DeviceService.queryExistNozzle(nozzle4Device);
	}

	@ApiOperation(value = "增加加油枪")
	@PostMapping("/add")
	public JSONObject addTank(@RequestBody Nozzle4Device nozzle4Device) {
		nozzle4DeviceService.saveNoz(nozzle4Device);
		final DispenserErrorItem dispenserErrorItem = nozzle4Device.getDispenserErrorItem();
		if (dispenserErrorItem != null) {
			dispenserErrorItemService.saveDispenserErrorItem(dispenserErrorItem);
		}
		return CommonUtil.successJson();
	}


	@ApiOperation(value = "修改加油枪")
	@PostMapping("/update")
	public JSONObject updateTank(@RequestBody Nozzle4Device nozzle4Device) {
		nozzle4DeviceService.saveOrUpdate(nozzle4Device);
		nozzle4DeviceService.updateNozRel(nozzle4Device);
		final DispenserErrorItem dispenserErrorItem = nozzle4Device.getDispenserErrorItem();
		if (dispenserErrorItem != null) {
			dispenserErrorItemService.saveDispenserErrorItem(dispenserErrorItem);
		}
		//加油枪信息更改通知
		List<Nozzle4Device> lists = new ArrayList<>();
		lists.add(nozzle4Device);
		balanceService.nozzlePropertyChanged(lists);
		return CommonUtil.successJson();
	}


	@Override
	protected List doQuery(JSONObject requestJson) {
		return nozzle4DeviceService.query(requestJson, false).getRecords();
	}

	@ApiOperation(value = "导出油罐数据")
	@PostMapping("/export")
	public void exportTankData(HttpServletResponse response, String excelFileds, String excelTitles, String fileName, String jsonObject, String language) throws Exception {
		super.exportData(response, excelFileds, excelTitles, fileName, jsonObject, language);
	}
}
