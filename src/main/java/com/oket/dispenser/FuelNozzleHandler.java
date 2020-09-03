package com.oket.dispenser;

import org.apache.mina.statemachine.annotation.State;
import org.apache.mina.statemachine.annotation.Transition;
import org.apache.mina.statemachine.event.Event;
public class FuelNozzleHandler {
    @State public static final String ROOT = "Root";
    /**
     * 加油机不可用状态
     */
    @State(ROOT)
    public static final String INOPERATIVE   = "Inoperative";
    /**
     * 加油机关闭状态
     */
    @State(ROOT)
    public static final String CLOSED  = "Closed";
    /**
     * 加油机处于空闲状态
     */
    @State(ROOT)
    public static final String IDLE = "Idle";
    //@State public static final String PAUSED  = "Paused";

    /**
     * 激活油枪
     * @return
     */

    @Transition(on="operativeNozzle",in=INOPERATIVE,next = CLOSED)
    public void operativeNozzle(){
        System.out.println("激活油枪");

    }
    @Transition(on="unableNozzle",in=CLOSED,  next = INOPERATIVE)
    public void unableNozzle(){
        System.out.println("禁止油枪，油枪处于故障状态");

    }
    /**
     *激活油枪到准备工作状态，也是空闲状态
     */

//    @Transitions({
//            @Transition(on = "openNozzle",in = CLOSED,next = IDLE),
//            @Transition(on = "closeNozzle",in=IDLE,next = CLOSED)
//    })
    @Transition(on = "openNozzle",in = CLOSED,next = IDLE)
    public  void openNozzle(){
        System.out.println("打开油枪，油枪处于空闲状态");

    }
    @Transition(on = "closeNozzle",in =IDLE,next = CLOSED)
    public  void closeNozzle(){
        System.out.println("关闭油枪，油枪处于关机状态");

    }
    @Transition(on = "*", in = ROOT)
    public void error(Event event) {

        System.out.println("Cannot '" + event.getId() + "' current state="
                +event.getContext().getCurrentState().getId());
    }
}
