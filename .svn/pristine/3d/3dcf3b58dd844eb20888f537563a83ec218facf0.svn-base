package com.oket.tank4station.service.impl;

import com.oket.TankChartDCApplication;
import com.oket.tank4station.InventoryException;
import com.oket.tank4station.LevelState;
import com.oket.tank4station.entity.DbInventory;
import com.oket.tank4station.entity.DbInventoryTrace;
import com.oket.tank4station.service.DbInventoryService;
import com.oket.tank4station.service.DbInventoryTraceService;
import com.oket.tank4station.service.vo.InventoryTraceMergeVO;
import com.oket.util.TimeUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.NotEmpty;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TankChartDCApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class DbInventoryTraceServiceImplTest {

	@SpyBean
	private DbInventoryTraceService dbInventoryTraceService;
	@MockBean
	private DbInventoryService dbInventoryService;

	@BeforeEach
	void setUp() {
	}

	@AfterEach
	void tearDown() {
	}

	@Test
	void testMock() {
//模拟创建一个List对象
		List<Integer> mock = Mockito.mock(List.class);
//调用mock对象的方法
		mock.add(1);
		mock.clear();
//验证方法是否执行
		Mockito.verify(mock).add(1);
		Mockito.verify(mock).clear();
	}

	@Test
	void getBeforeTrace() {
	}

	@Test
	void getTrace() {
	}

	@Test
	void testGetTrace() {
	}

	@Test
	void queryInventories() {
	}

	@Test
	void getAllLastTrace() {
	}

	@Test
	void merge() throws ParseException, InventoryException {
		InventoryTraceMergeVO mergeVO = new InventoryTraceMergeVO();
		mergeVO.setTankNo(5);
		mergeVO.setStartTime(TimeUtils.parseToSecond("2020-04-15 00:00:00"));
		mergeVO.setEndTime(TimeUtils.parseToSecond("2020-04-16 00:00:00"));
		mergeVO.setSegments(Arrays.asList(
				new InventoryTraceMergeVO.Segment(1L, 5L, LevelState.LEVEL_STABLE),
				new InventoryTraceMergeVO.Segment(5L, 7L, LevelState.LEVEL_STABLE),
				new InventoryTraceMergeVO.Segment(7L, 9L, LevelState.LEVEL_STABLE)));



		List<DbInventoryTrace> traces = new ArrayList<>();
		DbInventoryTrace dbInventoryTrace = new DbInventoryTrace();
		dbInventoryTrace.setId(1L);
		dbInventoryTrace.setStartId(1L);
		dbInventoryTrace.setStartTime(TimeUtils.parseToSecond("2020-04-15 00:00:00"));
		dbInventoryTrace.setEndId(6L);
		dbInventoryTrace.setEndTime(TimeUtils.parseToSecond("2020-04-15 02:00:00"));
		traces.add(dbInventoryTrace);

		DbInventoryTrace dbInventoryTrace2 = new DbInventoryTrace();
		dbInventoryTrace2.setId(2L);
		dbInventoryTrace2.setStartId(6L);
		dbInventoryTrace2.setStartTime(TimeUtils.parseToSecond("2020-04-15 02:00:00"));
		dbInventoryTrace2.setEndId(9L);
		dbInventoryTrace2.setEndTime(TimeUtils.parseToSecond("2020-04-16 00:00:00"));
		traces.add(dbInventoryTrace2);

		final DbInventory dbInventory1 = new DbInventory();
		dbInventory1.setId(1L);
		dbInventory1.setTime(TimeUtils.parseToSecond("2020-04-15 00:00:00"));
		dbInventory1.setVolume(1230);

		final DbInventory dbInventory5 = new DbInventory();
		dbInventory5.setId(5L);
		dbInventory5.setTime(TimeUtils.parseToSecond("2020-04-15 01:00:00"));
		dbInventory5.setVolume(1230);

		final DbInventory dbInventory7 = new DbInventory();
		dbInventory7.setId(7L);
		dbInventory7.setTime(TimeUtils.parseToSecond("2020-04-15 03:00:00"));
		dbInventory7.setVolume(1230);

		final DbInventory dbInventory9 = new DbInventory();
		dbInventory9.setId(9L);
		dbInventory9.setTime(TimeUtils.parseToSecond("2020-04-16 00:00:00"));
		dbInventory9.setVolume(1230);



		//轨迹
		given(dbInventoryTraceService.getTrace(mergeVO.getStartTime(), mergeVO.getEndTime(), mergeVO.getTankNo()))
				.willReturn(traces);

		//液位
		given(dbInventoryService.getById(1L)).willReturn(dbInventory1);
		given(dbInventoryService.getById(5L)).willReturn(dbInventory5);
		given(dbInventoryService.getById(7L)).willReturn(dbInventory7);
		given(dbInventoryService.getById(9L)).willReturn(dbInventory9);
//		given(dbInventoryTraceService.getDbInventoriesByIds(mergeVO))
//				.willReturn(Arrays.asList(dbInventory1,dbInventory5,dbInventory7,dbInventory9));
//
//		//数量
//		given(dbInventoryService.count(mergeVO.getTankNo(),1L,9L)).willReturn(200);
//		dbInventoryTraceService.merge(mergeVO);
	}

	@Test
	void save() {
	}

	@Test
	void getLastTrace() {
	}

	@Test
	void query() {
	}
}