package com.oket.station;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author ：lw
 * @date ：Created in 2020/6/6 10:55
 * @description：监控中心异常类
 */
@Data
public class RunTimeErrorVO {
    private String startTime;
    private int day;
    private List<JSONObject> resultLists;
}
