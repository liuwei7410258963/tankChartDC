package com.oket.tank4station.service;


import com.alibaba.fastjson.JSONObject;
import com.oket.common.base.BaseService;
import com.oket.tank4station.entity.UpdateTableInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 容积表操作接口
 */
public interface VolumeTableService extends BaseService<UpdateTableInfo> {
	/**
	 * 添加容积表到版本库
	 *
	 * @param
	 * @return
	 */
	JSONObject addFileToDB(MultipartFile file, JSONObject jsonObject);
    /**
     * 获取正在使用的容积表
     * @param tankNo
     * @return
     */
	UpdateTableInfo getUsedTankChart(int tankNo);

}
