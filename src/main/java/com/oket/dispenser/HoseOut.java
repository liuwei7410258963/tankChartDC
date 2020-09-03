package com.oket.dispenser;

import java.util.Date;
import java.io.Serializable;
/**
 * @author 王恒
 * @since 2016-04-27
 *
 */
public class HoseOut implements Cloneable, Comparable<HoseOut> ,Serializable{


    private static final long serialVersionUID = 1L;
    /** volume double 单笔加油数据付油量，单位L，精确到小数点2位 */
    protected double volume;
    /** startTime Date 加油开始时间（提枪时间），精确到秒*/
    protected Date startTime;
    /**  * endTime Date 加油结束时间（挂枪时间），精确到秒 */
    protected Date endTime;
    /** hoseId String 加油枪编号，加油站给定的编号，非系统标识*/
    protected String hoseId;
    /*** double 加油机泵码总累计数，单位升，精确到小数点后2位  */
    protected Double pumpSum;
    /** used boolean 此笔加油数据是否被付油数据合并中，汇总合并应用。只有当数据被有效合并置为true */
    protected boolean used = false;
    /** 油枪精度*/
    protected DispenserErrorItem errItem;

    /** 流水号*/
    private long id=0;
    /**
     * 排序，如果时间相同，且油枪编号相同，泵码数相同才对象相同。且对重复装载的数据做清0，以便后续去除。
     */
    @Override
    public int compareTo(HoseOut o) {
        int c= getStartTime().compareTo(o.getStartTime());
        if(c==0){

            c = getEndTime().compareTo(o.getEndTime());
        }
        if(c==0){
            c=this.getPumpSum().compareTo(o.getPumpSum());
            if(c==0){
                if(o.getHoseId().equals(hoseId)){
                    System.out.println("RARHoseOut比较对象，数据出现重复："+o.toString()+"  this="+toString());
                    o.setVolume(0);
                }
            }

        }
        return c;
    }
    @Override
    public boolean equals(Object e){
        boolean ret=false;
        HoseOut o=(HoseOut)e;
        int c= getStartTime().compareTo(o.getStartTime());
        if(c==0){

            c = getEndTime().compareTo(o.getEndTime());
        }
        if(c==0){
            c=this.getPumpSum().compareTo(o.getPumpSum());
            if(c==0){
                if(o.getHoseId().equals(hoseId)){
                    ret=true;
                }
            }

        }

        return ret;
    }
    /**
     * 克隆一个HoseOut对象
     *
     * @return HoseOut
     * */
    public HoseOut clone() {
        try {
            super.clone();
        } catch (CloneNotSupportedException e) {
        }
        HoseOut out = new HoseOut();
        out.volume = this.volume;
        out.startTime = startTime == null ? null
                : new Date(startTime.getTime());
        out.endTime = endTime == null ? null : new Date(endTime.getTime());
        out.hoseId = this.hoseId;
        out.pumpSum = this.pumpSum;
        out.id=this.id;
        out.errItem=errItem;
        return out;
    }
    private double getErrorValue(){
        double tmpErr=0;
        if(errItem!=null){
            tmpErr=errItem.getErrorCoe();
        }
        return tmpErr;
    }
    /**
     * 将HoseOut对象转换成字符串
     *
     * @return String
     * */
    public String toString() {
        double tmpErr=getErrorValue();
        String ret=String.format("%1$d,%2$s,%3$.4f,%4$tY-%4$tm-%4$td %4$tT~%5$tT,%6$.2f,%7$.1f‰", id,hoseId,volume,startTime,endTime,pumpSum,tmpErr);
        return ret + "," + used;
    }
    /**
     * 返回油枪误差修正后的体积
     * @return
     */
    public double getTrueVolume() {

        return volume*(getErrorValue()+1);
    }
    /**
     *返回加油机付出数的读数值
     */
    public double getVolume() {
        return volume;
    }
    public void setVolume(double volume) {
        this.volume = volume;
    }
    public Date getStartTime() {
        return startTime;
    }
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    public Date getEndTime() {
        return endTime;
    }
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    public String getHoseId() {
        return hoseId;
    }
    public void setHoseId(String hoseId) {
        this.hoseId = hoseId;
    }
    public Double getPumpSum() {
        return pumpSum;
    }
    public void setPumpSum(Double pumpSum) {
        this.pumpSum = pumpSum;
    }
    public boolean isUsed() {
        return used;
    }
    public void setUsed(boolean used) {
        this.used = used;
    }
    public DispenserErrorItem getErrItem() {
        return errItem;
    }
    public void setErrItem(DispenserErrorItem errItem) {
        this.errItem = errItem;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
}


