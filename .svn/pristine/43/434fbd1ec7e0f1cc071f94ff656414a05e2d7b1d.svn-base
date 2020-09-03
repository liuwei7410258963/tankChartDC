package com.oket.dispenser;

import java.util.Date;

public class NozzleTransientOut extends Nozzle{
    private Date startTime;
    private Date currentTime;
    private NozzleState state;
    private long pumpNo;
    private double volume;
    public NozzleTransientOut(Date startTime,Date currentTime,NozzleState state,
                              long pumpNo,double volume,double amount,int nozzleID,int dispenserID,String nozzleName){
//        super(nozzleNo,dispenserID,nozzleName);
        this.amount=amount;
        this.startTime=startTime;
        this.currentTime=currentTime;
        this.state=state;
        this.pumpNo=pumpNo;
        this.volume=volume;
    }
    public double getAmount() {
        return amount;
    }

    private double amount;

    public Date getStartTime() {
        return startTime;
    }

    public Date getCurrentTime() {
        return currentTime;
    }

    public NozzleState getState() {
        return state;
    }

    public long getPumpNo() {
        return pumpNo;
    }

    public double getVolume() {
        return volume;
    }
}
