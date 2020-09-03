package com.oket.tankchartdc.controller;

import com.alibaba.fastjson.JSONObject;
import com.oket.tank4station.Inventory;
import com.oket.tankchartdc.mina.dit.DITEmulator;
import com.oket.tankchartdc.mina.dit.ifsf.EmulatorInventory;
import com.oket.tankchartdc.service.DitManager;
import com.oket.tankchartdc.service.SimulatorService;
import com.oket.tankchartdc.upload.GlobalException;
import com.oket.tankchartdc.upload.ReturnMessage;
import com.oket.tankchartdc.websocket.ProtocolBean;
import com.oket.util.CommonUtil;
import com.oket.util.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @description: 协议控制器
 * @author: SunBiaoLong
 * @create: 2019-11-14 14:13
 **/
@RestController
@Slf4j
@Api(value = "dit模拟器控制", tags = {"dit模拟器控制"})
public class ProtocolController {
	@Autowired
	private SimulatorService simulatorService;

	@RequestMapping(value = "/sendOne", method = RequestMethod.POST)
	public JSONObject ajaxSend(@RequestBody SendOne sendOne) throws Exception {
		try {
			//json
			if (sendOne.getType() == 2) {
				simulatorService.send(sendOne.getMessage(), SimulatorService.TYPE.JSON);
			}
			//ifsf
			else if (sendOne.getType() == 3) {
				simulatorService.send(sendOne.getMessage(), SimulatorService.TYPE.IFSF);
			}
		} catch (Exception e) {
			log.error("原因是：" + e.toString());
			throw new GlobalException("发送单条指令失败！");
		}
		return ReturnMessage.successMessage("单条指令发送成功");
	}

	@PostMapping("/dit/emulator/open")
	public Boolean openDitEmulator(int port, String ip,int type) {
		return	simulatorService.open(port,ip,type);
	}

	@PostMapping("/dit/emulator/close")
	public Response<String> closeDitEmulator(int type) {
		simulatorService.close(type);
		return Response.SUCCESS();
	}

	@GetMapping("/dit/all")
	public Response<ProtocolBean> getAll() {
		return Response.SUCCESS(DitManager.getAll());
	}

	@PostMapping("/dit/emulator/inventory")
	public Response<String> sendInventory(@RequestBody Inventory inventory, @Param("id") String id) {
		final DITEmulator ditEmulator = DitManager.getDitEmulator(id);
		if (ditEmulator == null) {
			return Response.FAIL("未能根据id获取到dit模拟器发送端");
		} else if (ditEmulator.getSession() == null || !ditEmulator.getSession().isActive()) {
			DitManager.closeDitEmulator(id);
			return Response.FAIL("dit模拟器发送端session是空的，或者连接已经中断，现已经关闭，请重新建立连接");
		}
		EmulatorInventory emulatorInventory = new EmulatorInventory(inventory);
		ditEmulator.getSession().write(emulatorInventory.encode());
		return Response.SUCCESS();
	}

	@PostMapping(value = "/inventory/send")
	@ApiOperation(value = "可以发送数据，发送频率为5秒一次")
	@ApiImplicitParams({
			@ApiImplicitParam(value = "指令；START:开始执行；STOP：结束执行；DESC：描述当前任务；CHANGE：改变当前液位信息", name = "order"),
			@ApiImplicitParam(value = "时间增长秒数", name = "timeGap"),
			@ApiImplicitParam(value = "液位增长数-可以为负数", name = "levelGap"),
			@ApiImplicitParam(value = "作用域，暂时未用到", name = "scope", required = false),
			@ApiImplicitParam(value = "ifsf的ip，不填默认连接本地", name = "ifsfIp", required = false),
			@ApiImplicitParam(value = "ifsf的端口，不填默认连接本地", name = "ifsfPort", required = false),

	})
	public JSONObject sendInventory(@RequestBody List<Inventory> inventoryList,
	                                String order, int levelGap, int timeGap, Integer scope
			, String ifsfIp, Integer ifsfPort) throws GlobalException {
		switch (order) {
			case "START":
				simulatorService.setLevelGap(levelGap);
				simulatorService.setTimeGap(timeGap);
				if (StringUtils.isNotBlank(ifsfIp) && ifsfPort != null) {
					simulatorService.setIfsfIp(ifsfIp);
					simulatorService.setIfsfPort(ifsfPort);
				}
				simulatorService.start(inventoryList);
				break;
			case "STOP":
				simulatorService.stop();
				break;
			case "CHANGE":
				simulatorService.setInventoryList(inventoryList);
				simulatorService.setLevelGap(levelGap);
				simulatorService.setTimeGap(timeGap);
				if (StringUtils.isNotBlank(ifsfIp) && ifsfPort != null) {
					//先中止之前的，然后启动现在的
					simulatorService.stop();

					simulatorService.setIfsfIp(ifsfIp);
					simulatorService.setIfsfPort(ifsfPort);
				}
				if (simulatorService.getThread() == null || simulatorService.getThread().isInterrupted()) {
					simulatorService.start(inventoryList);
				}
				break;
			case "DESC":
				return CommonUtil.successJson(simulatorService.getMessage());
			default:
				return CommonUtil.failJson();
		}
		return CommonUtil.successJson();
	}

	/**
	 * @description: dit废弃，改用接口发送单条指令,然后后台建立socket传输到ifsf和json handler，处理完之后即可返回前端
	 * @author: lw
	 * @create: 2019/12/19
	 **/
	@Data
	static class SendOne {
		private String message;
		private int type;
	}


}
