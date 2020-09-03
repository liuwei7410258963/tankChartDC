package com.oket.delivery.service.impl;

import com.oket.delivery.BzDelivery;
import com.oket.delivery.DeliveryType;
import com.oket.delivery.service.DeliveryService;
import com.oket.tankchartdc.mina.json.bean.Delivery;
import com.oket.tankchartdc.mina.json.bean.DeliveryLossAlarm;
import com.oket.tankchartdc.mina.json.bean.NozzleOutWhenDelivery;
import com.oket.tankchartdc.mina.process.DitJsonProcess;
import com.oket.util.TimeUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.swing.table.TableModel;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(value = SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DeliveryServiceImplTest {
    @Autowired
    private DitJsonProcess ditJsonProcess;
    @Test
    void abc() throws ParseException {
        Date now = new Date();
        List<Delivery> deliverieList = new ArrayList<>();
        Delivery delivery = new Delivery();
        delivery.setDeliveryNo("3");
        delivery.setAuditor("test");
        delivery.setTankNo("3");
        delivery.setLitre(333.3);
        delivery.setReceiveTime(now);
        delivery.setAuditorTime(now);
        delivery.setOilNo("300863");
        delivery.setReceiveMode("1");
        deliverieList.add(delivery);
        ditJsonProcess.processDelivery(deliverieList);

        List<NozzleOutWhenDelivery> nozzleOutWhenDeliveryList= new ArrayList<>();
        NozzleOutWhenDelivery nozzleOutWhenDelivery = new NozzleOutWhenDelivery();
        nozzleOutWhenDelivery.setDeliveryNo("3");
        nozzleOutWhenDelivery.setTankNo("3");
        nozzleOutWhenDelivery.setStartTime(TimeUtils.formatDateTime("2019-12-01 00:00:00"));
        nozzleOutWhenDelivery.setNozzleOut(33.3);
        nozzleOutWhenDelivery.setEndTime(new Date());
        nozzleOutWhenDeliveryList.add(nozzleOutWhenDelivery);
        ditJsonProcess.processNozzleOutWhenDelivery(nozzleOutWhenDeliveryList);


        List<DeliveryLossAlarm> deliveries = new ArrayList<>();

        DeliveryLossAlarm deliveryLossAlarm = new DeliveryLossAlarm();
        deliveryLossAlarm.setLoss(6.6);
        deliveryLossAlarm.setReceiveLitre(666.6);
        deliveryLossAlarm.setOilCode("300863");
        deliveryLossAlarm.setDeliveryNo("3");
        deliveryLossAlarm.setDate(now);

        deliveryLossAlarm.setDeliveryAlarmCode(1);
        deliveries.add(deliveryLossAlarm);

        ditJsonProcess.processDeliveryLossAlarm(deliveries);
    }
}