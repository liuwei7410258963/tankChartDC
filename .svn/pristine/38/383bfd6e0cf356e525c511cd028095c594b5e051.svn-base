package com.oket.tank4station.service;

import com.oket.tank4station.Inventory;
import com.oket.tank4station.LevelState;
import com.oket.tank4station.dao.DbInventoryDAO;
import com.oket.tank4station.dao.DbInventoryTraceDAO;
import com.oket.tank4station.entity.DbInventory;
import com.oket.tank4station.entity.DbInventoryTrace;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.Date;
//@ExtendWith(SpringExtension.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT))

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class InventoryServiceImplTest {
    @Resource
    private DbInventoryDAO dbInventoryDAO;
    @Resource
    private DbInventoryTraceDAO dbInventoryTraceDAO;
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void processInventory() {
        DbInventoryTrace trace=new DbInventoryTrace();
        Inventory inventory = new Inventory( 1, new Date(),
                1230.1, 0.0, 23.5, 4500.23, 4300.2);

        trace.setLevelState(LevelState.LEVEL_STABLE);


        trace.setStartTime(inventory.getTime());
        trace.setEndTime(inventory.getTime());
//        long minutes = trace.getEndTime().getMilles() - trace.getStartTime().getMilles();
//        minutes = minutes / 60 / 1000;
//        trace.setMinutes((int) minutes);
        trace.setEndLevel(inventory.getLevel());
        trace.setEndTemperature(inventory.getTemperature());
        trace.setEndVolume(inventory.getVolume());
        trace.setEndWaterLevel(inventory.getWaterLevel());
        //TODO: inventory没有水体积
        trace.setEndWaterVolume(inventory.getWaterLevel());
        trace.getInventories().add(inventory);

        DbInventory dbInventory=DbInventory.convertFromInventory(inventory);





        System.out.println("before traceID="+trace.getId());
        dbInventoryTraceDAO.insert(trace);
        System.out.println("after traceID="+trace.getId());
        System.out.println("before dbinventory="+dbInventory.getId());
        dbInventory.setId(trace.getId());
        dbInventoryDAO.insert(dbInventory);
        System.out.println("after dbinventory="+ inventory.getId());

//        inventoryService.processInventory(inventory);

    }
}