package com.oket.tankchartdc.controller;

import com.alibaba.fastjson.JSONObject;
import com.oket.tankchartdc.service.ExportService;
import com.oket.util.CommonUtil;
import com.oket.util.Response;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author ：lw
 * @date ：Created in 2020/5/26 13:53
 * @description：导出诊断数据
 */
@RestController
@RequestMapping("/tankchartdc/export")
public class ExportController {
    @Autowired
    ExportService exportService;

    /**
     * 查询异常信息
     */
    @GetMapping("/select")
    @ApiOperation(value = "查询可导出的诊断数据")
    public JSONObject select(HttpServletRequest request) {
        return CommonUtil.successJson(exportService.select(CommonUtil.request2Json(request)));
    }

    /**
     * 查询异常信息
     */
    @PostMapping("/export")
    @ApiOperation(value = "导出诊断数据")
    public JSONObject export(@RequestBody List<JSONObject> lists) {
        return exportService.export(lists);
    }

    /**
     * 下载
     * @param response
     */
    @ApiOperation(value = "导出诊断数据")
    @PostMapping("/download")
    public void imgDownload(HttpServletResponse response,@RequestBody List<JSONObject> lists) {
        exportService.download(response,lists);
    }
}
