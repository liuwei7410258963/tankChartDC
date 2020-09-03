package com.oket.tank4station;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.TimeUnit;

class StableLevelTraceTest {
    static List<TankLevel> list= new ArrayList<TankLevel>();
    private static StableInventoryTrace staleLevel=new StableInventoryTrace(null);
    static int addCount;
    static long intevalTime;
    static long firsTime=0;
    private static boolean ini=false;
//   static boolean isSetup=false;
//    @BeforeEach
//    void setUp() {
//        if(ini){
//            return;
//        }
//       ini=true;
//        list= new ArrayList<TankLevel>();
//        staleLevel=new StableInventoryTrace();
//
//        for (int id=1;id<=5;id++){
//            long time=System.currentTimeMillis();
//            if(firsTime==0){
//                firsTime=time;
//            }
//            Inventory info=new Inventory(id,1,new Date(time),100,0,20.4);
//            list.add(info);
//            staleLevel.addLevelInfo(info);
//            addCount++;
//            try {
//                //1秒
//                TimeUnit.SECONDS.sleep(1);
//            }catch (Exception e){
//
//            }
//            intevalTime=time-firsTime;
//            //System.out.println("intevalTime["+addCount+"]="+intevalTime);
//        }
//
//    }

    @AfterEach
    void tearDown() {

    }

//    @Test
//    void addLevelInfo() {
//        for (TankLevel tmp:list ) {
//            assert(staleLevel.addLevelInfo(tmp));
//        }
//
//
//    }

//    @Test
//    void addLevelInfos() {
//    }

    @Test
    void getLevelCount() {
        assert(staleLevel.getLevelCount()==list.size());
    }

    @Test
    void getLevelIntervalTime() {
//        System.out.println("intevalTime="+intevalTime+" staleLevel MICROSECONDS="+staleLevel.getLevelIntervalTime(TimeUnit.MICROSECONDS));
//        System.out.println("intevalTime="+intevalTime+" staleLevel MILLISECONDS="+staleLevel.getLevelIntervalTime(TimeUnit.MILLISECONDS));
//        System.out.println("intevalTime="+intevalTime+" staleLevel SECONDS="+staleLevel.getLevelIntervalTime(TimeUnit.SECONDS));


        assert(staleLevel.getLevelIntervalTime(TimeUnit.MILLISECONDS)==intevalTime);
        assert(staleLevel.getLevelIntervalTime(TimeUnit.MICROSECONDS)!=intevalTime);
        assert(staleLevel.getLevelIntervalTime(TimeUnit.SECONDS)!=intevalTime);
    }

    @Test
    void getLastLevelInfo() {
        assert(staleLevel.getLastLevel()==list.get(list.size()-1));
    }

    @Test
    void getFirstLevelInfo() {
//        System.out.println("first="+staleLevel.getFirstLevelInfo()+"list0="+list.get(0));
        assert(staleLevel.getFirstLevel()==list.get(0));
    }

    @Test
    void getLastMinuteMeanLevelInfo() {
    }



    @Test
    void getLevelState() {

        assert(staleLevel.getLevelState()==LevelState.LEVEL_STABLE);
    }

    @Test
    void getMeanLevelInfo() {
    }
}