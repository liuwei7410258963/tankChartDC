package com.oket.tankchartdc.mina.json.codec;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessor;
import com.alibaba.fastjson.parser.deserializer.ParseProcess;
import com.oket.tankchartdc.mina.json.bean.DITApiEnum;
import com.oket.tankchartdc.mina.json.bean.Tank;
import com.oket.tankchartdc.mina.parser.*;
import com.oket.util.MathExtend;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static com.oket.tankchartdc.mina.json.bean.DITApiEnum.TANK;

/**
 * @description: dit json 解析
 * @author: Longer
 * @create: 2019-11-08 21:46
 **/
public class DitJsonParser implements DitParser<DitJsonHeader, DitJsonMessage> {
	private static final String DATA_FIELD_NAME = "data";

	/**
	 * dit json头部长度（不包含起始标志20101）
	 */
	private static final int DIT_JSON_HEAD_LENGTH = 14;
	private static final Logger logger = LoggerFactory.getLogger(DitJsonParser.class);


	@Override

	public DitJsonMessage parseMessage(byte[] bodyBytes, DitJsonHeader header) throws ParserException, IllegalAccessException, InstantiationException {
		String json = new String(bodyBytes, StandardCharsets.UTF_8);
		JSONObject jsonObject = JSONObject.parseObject(json);
		DitJsonBody ditJsonBody = jsonObject.toJavaObject(DitJsonBody.class);
		//根据PID获取对应的枚举类型
		DITApiEnum anEnum = DITApiEnum.getEnum(header.getPid());
		DitJsonMessage ditJsonMessage = new DitJsonMessage();
		ditJsonMessage.setHeader(header);
		ditJsonMessage.setBody(ditJsonBody);
		if (anEnum == null) {
			logger.error("数据有误无法解析:" + json + ";类型：" + header.getPid());
			return ditJsonMessage;
		}
		List list = getList(header, jsonObject, anEnum);
		ditJsonBody.setBizData(list);
		ditJsonBody.setDitApiEnum(anEnum);
		header.setDesc(anEnum.getDesc());
		return ditJsonMessage;
	}

	/**
	 * 获取业务数据
	 *
	 * @param header
	 * @param jsonObject
	 * @param anEnum
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	private List getList(DitJsonHeader header, JSONObject jsonObject, DITApiEnum anEnum) throws InstantiationException, IllegalAccessException {
		List list = new ArrayList();
		JSONArray jsonArray = jsonObject.getJSONArray(DATA_FIELD_NAME);
		if (!jsonArray.isEmpty()) {

			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject1 = jsonArray.getJSONObject(i);
				jsonObject1.toJSONString();
				Object object = null;
				if (ExtraProcessor.class.isAssignableFrom(anEnum.getClazz())) {
					object = JSON.parseObject(jsonObject1.toJSONString(), anEnum.getClazz(), (ParseProcess) anEnum.getClazz().newInstance());
				} else {
					object = JSON.parseObject(jsonObject1.toJSONString(), anEnum.getClazz());
				}

				//判断是否是模拟数据
				if (header.isDitEmulatorEnable()) {
					processTime(object, header.getMillisGap());
				}
				list.add(object);
			}
		}
		return list;
	}


	@Override
	public DitJsonHeader parseHeader(byte[] headerBytes) throws ParserException {
		if (headerBytes == null || headerBytes.length != DIT_JSON_HEAD_LENGTH) {
			throw new ParserException("无效的json头部数组");
		}
		DitJsonHeader ditJsonHeader = new DitJsonHeader();
		ditJsonHeader.setHeadLength(headerBytes[0]);
		ditJsonHeader.setPid(new String(Arrays.copyOfRange(headerBytes, 1, ditJsonHeader.getHeadLength() + 1), StandardCharsets.UTF_8));
		logger.debug("pid:" + ditJsonHeader.getPid());
		ditJsonHeader.setBodyLength(MathExtend.bytes2Int4(Arrays.copyOfRange(headerBytes, ditJsonHeader.getHeadLength() + 1, DIT_JSON_HEAD_LENGTH)));
		return ditJsonHeader;
	}

	/**
	 * 处理时间间隔
	 * 当使用dit模拟数据的时候，会传输一个时间间隔，将所有获取到的数据加上这个时间间隔
	 *
	 * @param object
	 * @param gap
	 */
	private void processTime(Object object, long gap) {
		final Class<?> aClass = object.getClass();
		final Field[] fields = aClass.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			if (field.getType().equals(Date.class)) {
				try {
					field.setAccessible(true);
					Date date = (Date) field.get(object);
					if (date != null) {
						date.setTime(date.getTime() + gap);
					}
				} catch (IllegalAccessException e) {
					logger.error("处理时间间隔失败", e);
				}
			}
		}
	}
}
