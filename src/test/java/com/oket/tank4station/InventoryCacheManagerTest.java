package com.oket.tank4station;

import com.oket.tank4station.entity.DbInventoryLast;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.*;

class InventoryCacheManagerTest {

	@Test
	public void test(){
		 Map<Integer, DbInventoryLast> LAST_INVENTORY_MAP = new ConcurrentHashMap<>();

		LAST_INVENTORY_MAP.remove(1);
	}

}