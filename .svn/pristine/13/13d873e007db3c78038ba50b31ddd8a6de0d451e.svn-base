package com.oket.tank4station;

import com.oket.dispenser.NozzleState;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class AbstractLevelTraceTest {
    @Test
    public void testCopyList() throws InterruptedException {
        List<TankLevel> tankLevels = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                if (!tankLevels.add(new Inventory())) {
                    System.out.println("t:" + Thread.currentThread().getName() + ":" + tankLevels.add(new Inventory()));
                    System.out.println("t:" + Thread.currentThread().getName() + ":" + tankLevels.add(new Inventory()));
                    System.out.println("t:" + Thread.currentThread().getName() + ":" + tankLevels.add(new Inventory()));
                    System.out.println("t:" + Thread.currentThread().getName() + ":" + tankLevels.add(new Inventory()));
                } else {
                    System.out.println("t:" + Thread.currentThread().getName() + ":" + tankLevels.add(new Inventory()));
                }

            }).start();
        }
        TimeUnit.SECONDS.sleep(2);
        System.out.println(tankLevels.size());
        System.out.println(tankLevels);
    }


    @Test
    public void testNoStateChange() throws InventoryException {
        AbstractTankSession tankSession = new TankStationSession(1);
        AbstractLevelTrace trace = new AscendingInventoryTrace(null);
        tankSession.setLevelTrace(trace);



        NozzleState nozzleState = new NozzleState(1, NozzleState.State.FUELLING);
        nozzleState.setTime(new Date());

        NozzleState nozzleState2 = new NozzleState(1, NozzleState.State.FUELLING);
        nozzleState2.setTime(new Date());
        nozzleState2.setLastAmount(nozzleState.new TransientAmount(1.1, 6.7, new Date()));

        NozzleState nozzleState3 = new NozzleState(1, NozzleState.State.FUELLING);
        nozzleState3.setTime(new Date());
        nozzleState3.setLastAmount(nozzleState.new TransientAmount(2.1, 12.7, new Date()));

        NozzleState nozzleState4 = new NozzleState(1, NozzleState.State.FUELLING);
        nozzleState4.setTime(new Date());
        nozzleState4.setLastAmount(nozzleState.new TransientAmount(3.1, 18.7, new Date()));

        NozzleState nozzleState5 = new NozzleState(1, NozzleState.State.IDLE);
        nozzleState5.setTime(new Date());

        trace.updateNozzleState(nozzleState);
        tankSession.updateNozzleState(nozzleState);
        assertEquals(trace.getSumNozzleOut(), 0, 0.001);
        assertEquals(tankSession.isOnFueling(),true);
        tankSession.updateNozzleState(nozzleState2);
//        trace.updateNozzleState(nozzleState2);
        assertEquals(trace.getSumNozzleOut(), 6.7, 0.001);
        tankSession.updateNozzleState(nozzleState3);
//        trace.updateNozzleState(nozzleState3);
        assertEquals(trace.getSumNozzleOut(), 12.7, 0.001);
        tankSession.updateNozzleState(nozzleState4);
//        trace.updateNozzleState(nozzleState4);
        assertEquals(trace.getSumNozzleOut(), 18.7, 0.001);
        tankSession.updateNozzleState(nozzleState5);
//        trace.updateNozzleState(nozzleState5);
        assertEquals(trace.getSumNozzleOut(), 18.7, 0.001);
        assertEquals(tankSession.isOnFueling(),false);


        NozzleState nozzleState7 = new NozzleState(1, NozzleState.State.FUELLING);
        nozzleState7.setTime(new Date());
        nozzleState7.setLastAmount(nozzleState.new TransientAmount(1.1, 6.7, new Date()));
        trace.updateNozzleState(nozzleState7);
        assertEquals(trace.getSumNozzleOut(), 25.4, 0.001);
        assertEquals(trace.getCurrentOut(), 6.7, 0.001);

        trace.end();
        AbstractLevelTrace trace1 = trace.newTrace(LevelState.LEVEL_STABLE);
        assertEquals(trace1.getCurrentOut(), 6.7, 0.001);
        assertEquals(trace1.getCurrentOut(), 6.7, 0.001);

    }
}