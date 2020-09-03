package com.oket.tankchartdc.controller;


import com.alibaba.fastjson.JSONObject;
import com.oket.common.base.BaseController;
import com.oket.tankchartdc.entity.CorrectSamples;
import com.oket.tankchartdc.service.CorrectSamplesService;
import com.oket.tankchartdc.service.CorrectingService;
import com.oket.util.CommonUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author ：lw
 * @date ：Created in 2020/4/24 11:15
 * @description：校正页面 新
 *
 * @author lw
 * @since 2020-07-22
 */
@RestController
@RequestMapping("/correcting")
public class CorrectSamplesController extends BaseController {

    @Autowired
    CorrectSamplesService correctSamplesService;

    /**
     * 返回该罐的周期
     */
    @GetMapping("/selectdelivery")
    @ApiOperation(value = "返回该罐的周期")
    public JSONObject backDeliveryId(HttpServletRequest request) {
        return correctSamplesService.selectDeliveryId(CommonUtil.request2Json(request));
    }

    /**
     * 生成数据
     */
    @GetMapping("/generate")
    @ApiOperation(value = "生成数据")
    public JSONObject generate(HttpServletRequest request) {
        correctSamplesService.generateSamples(CommonUtil.request2Json(request));
        return CommonUtil.successJson();
    }
    /**
     * 查询整合图
     */
    @GetMapping("/select")
    @ApiOperation(value = "查询整合图")
    public JSONObject select(HttpServletRequest request) {
        return CommonUtil.successJson(correctSamplesService.selectChart(CommonUtil.request2Json(request)));
    }

    /**
     * 查询整合图的弹窗
     */
    @GetMapping("/traceRelOutGroup")
    @ApiOperation(value = "查询整合图的弹窗")
    public JSONObject selectChartAlert(HttpServletRequest request) {
        return correctSamplesService.selectChartAlert(CommonUtil.request2Json(request));
    }

    /**
     * 查询整合表
     */
    @GetMapping("/selecttable")
    @ApiOperation(value = "查询整合表")
    public JSONObject selecttable(HttpServletRequest request) {
        return CommonUtil.successJson(correctSamplesService.selectTable(CommonUtil.request2Json(request)));
    }


    @Override
    protected List<CorrectSamples> doQuery(JSONObject requestJson) {
        return correctSamplesService.exportTable(requestJson);
    }

    @ApiOperation(value = "导出异常信息")
    @PostMapping("/exporttable")
    public void exportTankData(HttpServletResponse response, String excelFileds, String excelTitles, String fileName, String jsonObject, String language) throws Exception {
        super.exportData(response, excelFileds, excelTitles, fileName, jsonObject, language);
    }
}
