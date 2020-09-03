package com.oket;


import com.oket.dispenser.FuelNozzle;
import com.oket.dispenser.FuelNozzleHandler;
import org.apache.mina.statemachine.StateMachine;
import org.apache.mina.statemachine.StateMachineFactory;
import org.apache.mina.statemachine.StateMachineProxyBuilder;
import org.apache.mina.statemachine.annotation.Transition;

public class StationTanksManage {
    StationTanksManage(){
        System.out.println("StationTanksManage ");
        FuelNozzleHandler handler=new FuelNozzleHandler();
        StateMachine stateMachine= StateMachineFactory.getInstance(Transition.class).create(FuelNozzleHandler.INOPERATIVE,handler);
        FuelNozzle fuelNozzle=new StateMachineProxyBuilder().create(FuelNozzle.class,stateMachine);

        fuelNozzle.operativeNozzle();
        fuelNozzle.openNozzle();
        fuelNozzle.openNozzle();

//        fuelNozzle.closeNozzle();
//        fuelNozzle.unableNozzle();

       // fuelNozzle.closeNozzle();

    }
}
