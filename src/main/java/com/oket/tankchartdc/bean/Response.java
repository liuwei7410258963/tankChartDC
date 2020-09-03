package com.oket.tankchartdc.bean;

import lombok.Data;

import java.util.Collection;
import java.util.List;

/**
 * @description:
 * @author: SunBiaoLong
 * @create: 2019-11-14 14:15
 **/
@Data
public class Response<T> {
	private static final String SUCCESS_CODE = "1";
	private static final String SUCCESS = "SUCCESS";
	private static final String FAIL = "failed";
	private String code;
	private String status;
	private Collection<T> data;

	public Response(String status, String code) {
		this.status = status;
		this.code = code;
	}

	public Response(String status, String code, Collection<T> data) {
		this.status = status;
		this.data = data;
	}

	public static Response SUCCESS() {
		return new Response(SUCCESS, SUCCESS_CODE);
	}

	public static <T> Response SUCCESS(Collection<T> data) {
		return new Response(SUCCESS, SUCCESS_CODE, data);
	}

	public static <T> Response FAIL(Collection<T> data) {
//		return new Response(FAIL, data);
		return null;
	}

	public static <T> Response FAIL(String error, Collection<T> data) {
//		return new Response(error, data);
		return null;
	}
}
