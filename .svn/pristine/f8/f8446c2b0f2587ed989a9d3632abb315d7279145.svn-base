package com.oket.tankchartdc.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.oket.tankchartdc.DbInventoryCycle;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author ：lw
 * @date ：Created in 2020/5/26 13:54
 * @description：导出诊断数据Service
 */
public interface ExportService {
    /*
     * 查询可导出的数据
     */
    IPage<DbInventoryCycle> select(JSONObject jsonObject);

    /*
     * 导出
     */
    JSONObject export( List<JSONObject> lists);

    /*
     * 下载
     */
    void download(HttpServletResponse response,List<JSONObject> lists);
}
