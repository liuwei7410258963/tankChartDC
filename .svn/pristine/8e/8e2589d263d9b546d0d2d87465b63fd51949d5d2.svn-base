package com.oket.tankchartdc.mina.ifsf.cache;

import com.oket.dispenser.BzNozzleOut;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: Longer
 * @create: 2019-11-21 10:46
 **/
public class CacheManager {
	private static Map<String, BzNozzleOut> HOSE_OUT_MAP = new HashMap<>();


	public static BzNozzleOut getHoseOut(String hostId) {
		return HOSE_OUT_MAP.get(hostId);
	}


	public static void addHoseOut(BzNozzleOut hoseOut) {
		HOSE_OUT_MAP.put(hoseOut.getNozzleNo(), hoseOut);
	}

	public static void removeHoseOut(String hoseOutId) {
		HOSE_OUT_MAP.remove(hoseOutId);
	}
}
