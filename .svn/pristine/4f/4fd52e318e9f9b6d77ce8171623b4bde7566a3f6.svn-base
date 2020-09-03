package com.oket.dispenser;

import com.oket.util4biz.SampleError;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 油枪误差
 * @since 2.0.0
 */
public class NozzleError {
    /**
     * 油枪编号是与
     * @see Nozzle#nozzleID
     * 一致
     */
    private long nozzleID;
    private List<NozzleErrorItem> errs=new CopyOnWriteArrayList<NozzleErrorItem>();
    public NozzleError(long nozzleID,Collection<NozzleErrorItem> errs) {
    //TODO 需要实现拷贝
        if(errs!=null){

        }
    }
    public NozzleError(long nozzleID){
        this(nozzleID,null);
    }
    public int size(){
        return errs.size();
    }
    /**
     * 返回最后一条误差值
     * */
    public SampleError getSampleError(){
        return errs.size()==0?null:((SampleError)errs.get(errs.size()-1));
    }
    public List<NozzleErrorItem>getSampleErrors(){
        return errs;
    }

    /**
     * 返回指定日期之后的该记录的误差精度
     * @param date
     * @return
     */
    public SampleError getSampleError(Date date ){
        return null;
    }

    /**
     * 相对误差，不采用%表示；
     * @param coe
     * @param sampleDate
     * @return
     */

    public boolean addError(double coe, Date sampleDate){
        return addError(coe,sampleDate, SampleError.ErrorUnit.DOUBLE);
    }
    /**
     * 添加油枪误差。
     * 规则:
     * 1)列表插入，需要按照时间系列；
     * 2）同一个日期只能有一条误差记录；
     *
     * @param coe
     * @param sampleDate
     * @param unit coe 表示方式，采用%表示还是原始相对误差值表示
     * @return
     */
    public boolean addError(double coe, Date sampleDate, SampleError.ErrorUnit unit){
        boolean ret=false;
        if(sampleDate!=null){
            double tmpCoe=coe;
            if(unit== SampleError.ErrorUnit.PERCENT){
                tmpCoe=coe/100;
            }
            Date  lastDate=null;
            SampleError tmpErr=getSampleError();
            lastDate=tmpErr!=null?((NozzleErrorItem)tmpErr).getSampleDate():null;



            CopyOnWriteArrayList tmp=(CopyOnWriteArrayList)errs;
            if(lastDate!=null&&sampleDate.before(lastDate)){
                System.out.println(" i here ,but  don't something ");
                //TODO
//                tmp.
//                ret=().addIfAbsent(new NozzleErrorItem(sampleDate,tmpCoe));
            }else{
                ret=tmp.addIfAbsent(new NozzleErrorItem(sampleDate,tmpCoe));
            }

            ret=true;
        }
        return ret;
    }
    final class NozzleErrorItem implements SampleError {
    /**采样日期*/
    private Date sampleDate;
    /**误差率，采用±变数误差的偏移量，误差计算公式：误差率=(检验读数-真值)/真值*100%；如果误差为﹢1则 对样本修正为读数值*（1+0.01）*/
    private double errorCoe;

    NozzleErrorItem(Date sampleDate,double coe){
        this.sampleDate=sampleDate;
        this.errorCoe=coe;
    }
    public Date getSampleDate(){
        return sampleDate;
    }
    @Override
    public double getAcountPercent() {
        return errorCoe*100;
    }

    @Override
    public double getAcount() {
        return errorCoe;
    }


        @Override
        public boolean equals(Object obj) {
           return sampleDate.equals(((NozzleErrorItem)obj).getSampleDate());
        }
    }
}
