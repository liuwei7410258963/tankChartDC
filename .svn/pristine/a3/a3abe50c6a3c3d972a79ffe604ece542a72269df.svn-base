package com.oket.tankchartdc.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.List;

/**
 * @author ：lw
 * @date ：Created in 2020/5/26 15:43
 * @description：导出样本数据
 */
@Data
public class Samples {
    //一个连续系列编号
    private long s_ID;
    //油品膨胀率
    private Double oilExpansionRate;
    //给dll的数据
    private List<ExportCorrectingSamples> valuesEx;
    //最终的数据
    private List<SamplesValues> values;
}
