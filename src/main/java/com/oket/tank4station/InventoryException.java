package com.oket.tank4station;

/**
 * @description: 液位异常
 * @author: SunBiaoLong
 * @create: 2020-03-17 13:38
 **/
public class InventoryException extends Exception {
	public InventoryException() {
	}

	public InventoryException(String message) {
		super(message);
	}
}
