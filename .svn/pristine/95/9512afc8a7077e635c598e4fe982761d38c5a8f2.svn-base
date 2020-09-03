package com.oket.tankchartdc.bean;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.Date;

/**
 * @description: 文件内容
 * @author: SunBiaoLong
 * @create: 2019-11-28 16:07
 **/
@Data
public class FileContent<T> {
	private Date time;
	private T data;
	private ContentType contentType;

	public FileContent(Date time, T data,ContentType contentType) {
		this.time = time;
		this.data = data;
		this.contentType = contentType;
	}


	/**
	 * 构造文件内容
	 * @param data 数据
	 * @param time 数据的时间
	 * @param <T>
	 * @return
	 */
	public static <T> String buildContent(T data, Date time,ContentType contentType) {
		FileContent<T> fileContent = new FileContent(time, data,contentType);
		return JSONObject.toJSONString(fileContent);
	}
}

