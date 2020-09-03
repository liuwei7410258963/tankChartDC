package com.oket.tankchartdc;

import com.oket.dispenser.TransientOut;
import com.oket.tank4station.*;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.jupiter.api.Assertions.*;

class NozzleOutGroupProcessorTest {

	@Test
	public void test() {
		Map<Integer, List<String>> mapAAA = new ConcurrentHashMap<>();
		Map<Integer, List<String>> mapBBB = new ConcurrentHashMap<>();
		mapAAA.put(1, Arrays.asList("aaa", "asas"));
		mapBBB.put(1, Arrays.asList("aaa", "aaa","232"));
		mapBBB.putAll(mapAAA);
		System.out.println(mapBBB);

	}


	@Test
	public void testEmpty(){
		List<AbstractLevelTrace> traces = new ArrayList<>();
		AbstractLevelTrace dd = new NewInventoryTrace(null);
		AbstractLevelTrace st = new StableInventoryTrace(null);
		traces.add(dd);
		traces.add(st);

		AbstractLevelTrace trace = traces.get(traces.size() - 1);
		while (!traces.isEmpty()) {
			if (trace.getLevelState().equals(LevelState.LEVEL_STABLE)) {
				traces.remove(traces.size() - 1);
				if (traces.isEmpty()) {
					break;
				} else {
					trace = traces.get(traces.size() - 1);
				}
			}
		}
		if (traces.isEmpty()){
			System.out.println(true);
		}
	}

	@Test
	public void testRemove(){
		List<AbstractLevelTrace> traces = new CopyOnWriteArrayList<>();
		AbstractLevelTrace dd = new NewInventoryTrace(null);
		AbstractLevelTrace st = new StableInventoryTrace(null);
		traces.add(dd);
		traces.add(st);

		for (AbstractLevelTrace trace : traces) {
			traces.remove(trace);
		}
		System.out.println(traces);
	}
}