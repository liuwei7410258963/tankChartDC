package com.oket.tankchartdc.service;

import com.alibaba.fastjson.JSONObject;
import com.oket.tank4station.InventoryException;
import com.oket.tank4station.service.vo.InventoryTraceMergeVO;

/**
 * @author ：lw
 * @date ：Created in 2020/4/24 11:18
 * @description：校正service
 */

public interface CorrectingService {
    /*
     * 查询罐存和付油数据
     */
    JSONObject select(JSONObject jsonObject);
    /*
     * 查询罐存和付油数据
     */
    JSONObject selectTable(JSONObject jsonObject);
    /*
     * 合并整合表
     */
    void updateTable(InventoryTraceMergeVO inventoryTraceMergeVO) throws InventoryException;
}
