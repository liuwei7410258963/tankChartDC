package com.oket.tankchartdc.controller;

import com.alibaba.fastjson.JSONObject;
import com.oket.tankchartdc.mina.dit.DitEmulatorProcess;
import com.oket.tankchartdc.service.UploadService;
import com.oket.tankchartdc.upload.*;
import com.oket.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

import static com.oket.tankchartdc.upload.SendSocketMessage.*;
import static com.oket.util.constants.ErrorEnum.E_90003;

/**
 * 上传处理文件接口
 *
 * @author lw
 * @since 2019-11-26
 */
@RestController
@Scope(value = "singleton")
@Slf4j
public class UploadController {

	//倍速
	public static int speed = 1;
	public static String status;
	private SimulatorSendThread simulatorSendThread;

	@Autowired
	private UploadService uploadService;
	@Value("${uploadFilePath}")
	private String uploadFilePath;
	@Value("${modifiablePortAndIpPath}")
	private String modifiablePortAndIpPath;


	/**
	 * 获取配置文件接口
	 *
	 * @return portMap
	 */
	@RequestMapping(value = "/port", method = RequestMethod.GET)
	public JSONObject port() throws Exception {
		try {
			ModifiablePortService modifiablePortService = new ModifiablePortService();
			return CommonUtil.successJson(modifiablePortService.loadProperties(modifiablePortAndIpPath));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GlobalException("修改配置文件失败！");
		}
	}

	/**
	 * 返回用户已选的类型、文件以及速度
	 *
	 * @return simulation
	 */
	@RequestMapping(value = "/type", method = RequestMethod.GET)
	public JSONObject type() throws Exception {
		try {
			Simulation simulation = new Simulation();
			simulation.setFileName(returnFileName);
			simulation.setFunctionType(returnfunctionType);
			simulation.setSpeed(returnSpeed);
			simulation.setStatus(status);
			return CommonUtil.successJson(simulation);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GlobalException("返回用户已选的类型和文件失败");
		}
	}

	/**
	 * 修改配置ifsf，json端口ip地址文件接口
	 * 现在仅保存和展示，将来再修改
	 *
	 * @return
	 */
	@RequestMapping(value = "/updatePort", method = RequestMethod.POST)
	public JSONObject updatePort(@RequestBody ModifiablePort modifiablePort) throws Exception {
		try {
			ModifiablePortService modifiablePortService = new ModifiablePortService();
			modifiablePortService.changePortAndIp(modifiablePort, modifiablePortAndIpPath);
			return ReturnMessage.successMessage("修改配置文件成功");
		} catch (Exception e) {
			throw new GlobalException("修改配置文件失败！");
		}
	}

	/**
	 * 上传日志接口
	 *
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/upload")
	public JSONObject logUpload(@RequestParam("file_data") MultipartFile file) throws Exception {
		log.info("单文件开始上传");
		return uploadService.logUpload(file, uploadFilePath);
	}

	/**
	 * 批量上传日志接口
	 *
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/uploads")
	public JSONObject logUploads(HttpServletRequest request) throws Exception {
		log.info("多文件开始上传");
		try {
			return uploadService.logUploads(request, uploadFilePath);
		} catch (GlobalException e) {
			log.error("原因是：" + e.toString());
			return CommonUtil.errorJson(E_90003);
		}
	}

	/**
	 * 查看所有文件
	 */
	@RequestMapping(value = "/allfile", method = RequestMethod.GET)
	public JSONObject chakanmingxi() {
		File file = new File(uploadFilePath);
		File[] fileList = file.listFiles();
		return ReturnMessage.createReturnMessage(fileList);
	}

	@RequestMapping(value = "/run", method = RequestMethod.POST)
	public JSONObject out(@RequestBody Simulation simulation) throws Exception {
		DitEmulatorProcess.setEnableDitFileEmulator(true);
		try {
			status = "run";
			simulatorSendThread = new SimulatorSendThread(simulation);
			simulatorSendThread.start();
		} catch (Exception e) {
			log.error("原因是：" + e.toString());
			throw new GlobalException("文件读取失败！");
		}
		return ReturnMessage.successMessage("读取文件完毕");
	}

	@RequestMapping(value = "/pause", method = RequestMethod.GET)
	public JSONObject pause() throws Exception {
		try {
			log.info("暂停推送数据");
			status = "pause";
			simulatorSendThread.pauseThread();
		} catch (Exception e) {
			log.error("原因是：" + e.toString());
			throw new GlobalException("暂停失败！");
		}
		return ReturnMessage.successMessage("暂停推送数据");
	}

	@RequestMapping(value = "/stop", method = RequestMethod.GET)
	public JSONObject stop() throws Exception {
		try {
			log.info("停止推送数据");
			status = "stop";
			clearReturnType();
			simulatorSendThread.stopThread();
		} catch (Exception e) {
			log.error("原因是：" + e.toString(), e);
			throw new GlobalException("中止失败！");
		}
		return ReturnMessage.successMessage("停止推送数据");
	}

	@RequestMapping(value = "/resume", method = RequestMethod.GET)
	public JSONObject resume() throws Exception {
		try {
			log.info("继续推送数据");
			status = "resume";
			simulatorSendThread.resumeThread();
		} catch (Exception e) {
			log.error("原因是：" + e.toString());
			throw new GlobalException("恢复失败！");
		}
		return ReturnMessage.successMessage("继续推送数据");
	}

	@RequestMapping(value = "/changeSpeed", method = RequestMethod.POST)
	public JSONObject changeSpeed(@RequestBody int spe) throws Exception {
		try {
			speed = spe;
			return ReturnMessage.successMessage("修改倍速成功！");
		} catch (Exception e) {
			log.error("原因是：" + e.toString());
			throw new GlobalException("修改倍速失败！");
		}
	}

}
