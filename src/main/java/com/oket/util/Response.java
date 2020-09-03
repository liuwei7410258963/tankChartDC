package com.oket.util;

import lombok.Data;

import java.util.Collection;

/**
 * @description:
 * @author: SunBiaoLong
 * @create: 2019-11-14 14:15
 **/
@Data
public class Response<T> {
	private static final String SUCCESS = "success";
	private static final String FAIL = "failed";
	private String status;
	private String message;
	private Collection<T> data;

	public Response(String status) {
		this.status = status;
	}

	public Response(String status, Collection<T> data) {
		this.status = status;
		this.data = data;
	}

	public Response(String status, String message) {
		this.status = status;
		this.message = message;
	}

	public static Response SUCCESS() {
		return new Response(SUCCESS);
	}

	public static <T> Response SUCCESS(Collection<T> data) {
		return new Response(SUCCESS, data);
	}

	public static <T> Response FAIL(String message) {
		return new Response(FAIL, message);
	}

	public static <T> Response FAIL(String error, Collection<T> data) {
		return new Response(error, data);
	}

}
