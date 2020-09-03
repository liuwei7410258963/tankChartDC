package com.oket.common.base;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @description: 基础service，封装一些基本常用方法
 * @author: SunBiaoLong
 * @create: 2019-12-06 16:42
 **/
public interface BaseService<T> extends IService<T> {

	/**
	 * 分页整合
	 *
	 * @param req isPage :是不是分页的
	 * @return
	 */
	IPage<T> query(JSONObject req, boolean isPage);
}
