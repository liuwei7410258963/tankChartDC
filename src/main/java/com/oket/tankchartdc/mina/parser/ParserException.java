package com.oket.tankchartdc.mina.parser;

/**
 * @description: 解析异常
 * @author: Longer
 * @create: 2019-11-08 22:29
 **/
public class ParserException extends Exception {

	public ParserException() {
		super();
	}

	public ParserException(String message) {
		super(message);
	}

	public ParserException(String message, Throwable cause) {
		super(message, cause);
	}
}
