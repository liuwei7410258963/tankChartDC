package com.oket.util4biz;

/**
 * 样本误差，误差计算公式：误差率=(检验读数-真值)/真值*100%
 * @since 2.0
 * @author wangheng
 */
public interface SampleError {
    enum ErrorUnit{
        /**相对误差值，不进行%计算*/
        DOUBLE,
        /**相对误差，%表示*/
        PERCENT
    }
    /**返回误差百分比，例如+0.1%，返回0.1*/
    public double getAcountPercent();
    /**返回误差值，例如-0.1%，返回-0.001*/
    public double getAcount();
}
