package com.oket.tank4station;

/**
 * 液位传感器液位计量误差，根据各个厂家不一样设置不同的值
 */
public enum LevelSensorError {
    OKET(0.1,"青岛澳科"),
    VR(0.1,"维德路特");
    private double err;
    private String manufacturer_type;
    LevelSensorError(double err ,String manufacturer_type){
        this.err=err;
        this.manufacturer_type=manufacturer_type;
    }
    public  double getErr(){
        return err;
    }
    public String getManufacturer_type(){
        return manufacturer_type;
    }
}
