package com.oket.tank4station.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oket.tank4station.dao.UpdateTableInfoDAO;
import com.oket.tank4station.entity.UpdateTableInfo;
import com.oket.tank4station.service.VolumeTableService;
import com.oket.tank4station.tankchart.TankChartManager;
import com.oket.util.CommonUtil;
import com.oket.util.ParamUtils;
import com.oket.util.constants.ErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: 容积表操作实现类
 * @Author: lw
 * @Date: 2020/04/01
 */
@Service
@Slf4j
public class VolumeTableServiceImpl extends ServiceImpl<UpdateTableInfoDAO, UpdateTableInfo> implements VolumeTableService {

	@Autowired(required = false)
	UpdateTableInfoDAO updateTableInfoDAO;
	/**
	 * 保存平台下发的罐表的表名
	 */
	private String tableName = "bd_send_table_info";

	@Autowired
	private TankChartManager tankChartManager;

	@Override
	public IPage<UpdateTableInfo> query(JSONObject req, boolean isPage) {
		// 参数根据需要判断来写
		IPage<UpdateTableInfo> page = new Page<>(req.getIntValue("pageNum"), req.getIntValue("pageRow"));
		//查询条件 根据需要写
		QueryWrapper<UpdateTableInfo> wrapper = new QueryWrapper<>();
		wrapper.eq("tank_code", req.getIntValue("tankCode"));
		wrapper.orderByDesc("version");
		if (!isPage) {
			//不分页的数据
			page.setRecords(baseMapper.selectList(wrapper));
		} else {
			// 分页的数据
			page = baseMapper.selectPage(page, wrapper);
		}
		return page;
	}


	@Override
	public UpdateTableInfo getUsedTankChart(int tankNo) {
		QueryWrapper<UpdateTableInfo> wrapper = new QueryWrapper<>();
		wrapper.lambda().eq(UpdateTableInfo::getTankCode, tankNo);
		wrapper.lambda().orderByDesc(UpdateTableInfo::getVersion);
		wrapper.last("LIMIT 1");
		return getOne(wrapper);
	}

	/**
	 * 添加容积表到版本库
	 *
	 * @param file
	 * @param
	 * @return
	 */
	@Override
	public JSONObject addFileToDB(MultipartFile file, JSONObject jsonObj) {
		if (null != file) {
			String jsonStr = jsonObj.getString("tankInfo");
			JSONObject jsonObject = JSONObject.parseObject(jsonStr);
			Long tankCode = jsonObject.getLongValue("tankNo");
			//是否是表格
			Boolean isExcel = true;
			//容积表内容
			String tableInfo;
			//表格工具
			Workbook wb = null;
			//上传的油罐罐表详细信息
			UpdateTableInfo info = new UpdateTableInfo();
			List<String> infos = new ArrayList<String>();
			try {
				//据不同的后缀名解析成不同的文件格式
				wb = parseFile(file);
				//判断是否是表格
				if (wb == null) {
					isExcel = false;
				}
				//容积表文件是否错误
				boolean hasError = false;
				if (isExcel) {
					//读取表格格式的容积表内容
					tableInfo = combinTableInfo(wb, infos);
				} else {
					//读取text格式的容积表内容
					tableInfo = combinTable(file, infos);
				}
				if (tableInfo == null) {
					hasError = true;
				}
				//容积表文件错误时退出
				if (hasError) {
					return CommonUtil.errorJson(ErrorEnum.E_70014);
				}
				if (tableInfo.length() > 0) {
					tableInfo = tableInfo.substring(0, tableInfo.length() - 1);
				}
				jsonObject.put("tankCode", tankCode);
				jsonObject.put("stationId", 1L);
				//新增有效期
				int validity = jsonObject.getIntValue("validity");
				if (validity == 0) {
					//添加默认有效期
					validity = 3 * 365;
				}
				info.setValidity(validity);
				// 获取版本号
				String version = tableVersion(jsonObject, tableInfo);
				//保存油罐罐表详细信息
				saveInfo(jsonObject, info, infos, tableInfo, version);
			} catch (Exception e) {
				log.error("容积表添加失败:" + e.getMessage(), e);
				return CommonUtil.errorJson(ErrorEnum.E_70014);
			}
		}
		return CommonUtil.successJson();
	}

	/**
	 * 获取版本号
	 *
	 * @param jsonO
	 * @param tableInfo
	 * @return
	 */
	private String tableVersion(JSONObject jsonO, String tableInfo) {
		String versionResult = "1.0";
		JSONObject versionJson = updateTableInfoDAO.getMaxTableInfoByTankId(jsonO.getLongValue("tankCode"), tableName);
		if (versionJson != null) {
			DecimalFormat df1 = new DecimalFormat("0.0");
			Double version = versionJson.getDouble("version");
			String hv = versionJson.getString("hv");
			versionResult = hv.equals(tableInfo) ? df1.format(version) : df1.format(version + 0.1);
		}
		return versionResult;
	}

	/**
	 * 根据不同的后缀名解析成不同的文件格式
	 *
	 * @param file
	 * @return null的时候就不是excel
	 */
	private Workbook parseFile(MultipartFile file) throws IOException {
		String[] split = file.getOriginalFilename().split("\\.");
		if ("xls".equals(split[1])) {
			return new HSSFWorkbook(file.getInputStream());
		} else if ("xlsx".equals(split[1])) {
			return new XSSFWorkbook(file.getInputStream());
		} else {
			return null;
		}
	}

	/**
	 * 读取表格格式的容积表内容
	 *
	 * @param wb
	 * @return
	 */
	private String combinTableInfo(Workbook wb, List<String> infos) {
		StringBuffer tableInfo = new StringBuffer();
		//开始解析 读取sheet 0
		Sheet sheet = wb.getSheetAt(0);
		String h = "";
		String v = "";
		String hBefore = "";
		String vBefore = "";
		//行数(表头的目录不需要，从1开始)
		for (int j = 0; j <= sheet.getLastRowNum(); j++) {
			Row row = sheet.getRow(j);
			//高度必须为整数
			if (row == null || !ParamUtils.isNum(row.getCell(0).toString())) {
				continue;
			}
			h = row.getCell(0).toString();
			v = row.getCell(1).toString();
			//验证通过
			if (isValid(h, v, hBefore, vBefore)) {
				String tempTableInfo = (int) Double.parseDouble(h) + "," + Float.parseFloat(v);
				tableInfo.append(tempTableInfo).append("|");
				hBefore = h;
				vBefore = v;
				infos.add(tempTableInfo);
			} else {
				return null;
			}
		}
		return tableInfo.toString();
	}


	/**
	 * 读取text格式的容积表内容
	 *
	 * @param file
	 * @return
	 * @throws IOException
	 */
	private String combinTable(MultipartFile file, List<String> infos) throws IOException {
		StringBuffer tableInfo = new StringBuffer();
		BufferedReader reader;//获取文件读取流
		reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
		String tempString = null;
		String hBefore = "";
		String vBefore = "";
		//是否是csv文件的第一行
		boolean flag = true;
		// 一次读入一行，直到读入null为文件结束
		while ((tempString = reader.readLine()) != null) {
			if (tempString.trim().equals("")) {
				continue;
			}
			String[] nums = null;
			if (flag && file.getOriginalFilename().endsWith(".csv")) {
				flag = false;
				nums = tempString.split(" ");
				if (ParamUtils.isNum(nums[0]) && ParamUtils.isNum(nums[1])) {
					//csv容积表格式错误时
					if (!isCsvValid(nums[0], nums[1], hBefore, vBefore)) {
						return null;
					}
					String tempTableInfo = nums[0] + "," + Float.parseFloat(nums[1]);
					tableInfo.append(tempTableInfo).append("|");
					hBefore = nums[0];
					vBefore = nums[1];
					infos.add(tempTableInfo);
				}
				continue;
			}
			if (tempString.indexOf("\t") == -1 && tempString.indexOf(" ") == -1 && tempString.indexOf(",") == -1) {
				return null;
			}
			if (tempString.indexOf("\t") != -1) {
				nums = tempString.trim().split("\t");
			} else if (tempString.indexOf(" ") != -1) {
				nums = tempString.split(" ");
			} else {
				nums = tempString.trim().split(",");
			}
			if (nums.length != 2) {
				return null;
			} else {
				if (isValid(nums[0], nums[1], hBefore, vBefore)) {
					String tempTableInfo = nums[0] + "," + Float.parseFloat(nums[1]);
					tableInfo.append(tempTableInfo).append("|");
					hBefore = nums[0];
					vBefore = nums[1];
					infos.add(tempTableInfo);
				} else {
					return null;
				}
			}
		}
		reader.close();
		return tableInfo.toString();
	}

	/**
	 * 验证csv容积表是否合法
	 *
	 * @param h
	 * @param v
	 * @param hBefore
	 * @param vBefore
	 * @return
	 */
	private boolean isCsvValid(String h, String v, String hBefore, String vBefore) {
		if (hBefore.isEmpty() && vBefore.isEmpty()) {
			return true;
		}
		return (int) Double.parseDouble(h) > (int) Double.parseDouble(hBefore) && Float.parseFloat(v) > Float.parseFloat(vBefore);
	}

	/**
	 * 验证xls/xlsx容积表是否合法
	 *
	 * @param h
	 * @param v
	 * @param hBefore
	 * @param vBefore
	 * @return
	 */
	private boolean isValid(String h, String v, String hBefore, String vBefore) {
		if (hBefore.isEmpty() && vBefore.isEmpty()) {
			return ParamUtils.isNum(h) && ParamUtils.isNum(v);
		}
		return ParamUtils.isNum(h) && ParamUtils.isNum(v) && (int) Double.parseDouble(h) > (int) Double.parseDouble(hBefore) && Float.parseFloat(v) > Float.parseFloat(vBefore);
	}

	/**
	 * 填充并保存油罐罐表详细信息
	 *
	 * @param jsonO
	 * @param info
	 * @param infos
	 */
	private void saveInfo(JSONObject jsonO, UpdateTableInfo info, List<String> infos, String tableInfo, String version) {
		info.setHV(tableInfo);
		info.setImportUser("admin");
		//版本号
		info.setVersion(Double.valueOf(version));
		info.setImportTime(new Date());
		//infos是一个List<String>的对象，保存k,v,k,v,k,v......
		info.setPointCount(infos.size());
		//最大容量和最大的高度，在读取完文件的时候就已经拿到最大值
		info.setMaxVolume(Double.parseDouble(infos.get(infos.size() - 1).split(",")[1]));
		info.setMaxHeight(Double.parseDouble(infos.get(infos.size() - 1).split(",")[0]));
		info.setTankCode((Long) jsonO.get("tankCode"));
		info.setRemark("");
		info.setStationId((long) jsonO.get("stationId"));
		//保存油罐罐表详细信息
		updateTableInfoDAO.addTable(info, tableName);
		//更新缓存中的容积表
		tankChartManager.putIntoCache( jsonO.getLong("tankCode").intValue(), info);
		log.info("更新缓存中的容积表：" + jsonO.get("tankCode"));
	}

}