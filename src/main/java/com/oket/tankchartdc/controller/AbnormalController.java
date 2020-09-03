package com.oket.tankchartdc.controller;


import com.alibaba.fastjson.JSONObject;
import com.oket.common.base.BaseController;
import com.oket.tankchartdc.entity.AbnormalEntity;
import com.oket.tankchartdc.service.AbnormalService;
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
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lw
 * @since 2020-04-01
 */
@RestController
@RequestMapping("/tankchartdc/abnormal")
public class AbnormalController extends BaseController {

    @Autowired
    AbnormalService abnormalService;

    /**
     * 查询异常信息
     */
    @GetMapping("/select")
    @ApiOperation(value = "查询异常信息")
    public JSONObject select(HttpServletRequest request) {
        return CommonUtil.successPage(abnormalService.query(CommonUtil.request2Json(request), true));
    }

    @Override
    protected List<JSONObject> doQuery(JSONObject requestJson) {
        return abnormalService.exportPumpLists(abnormalService.query(requestJson, false).getRecords());
    }

    @ApiOperation(value = "导出异常信息")
    @PostMapping("/export")
    public void exportTankData(HttpServletResponse response, String excelFileds, String excelTitles, String fileName, String jsonObject, String language) throws Exception {
        super.exportData(response, excelFileds, excelTitles, fileName, jsonObject, language);
    }

    /**
     * 查询异常信息
     */
    @GetMapping("/update")
    @ApiOperation(value = "泵码数确认")
    public JSONObject update(HttpServletRequest request) {
        abnormalService.update(CommonUtil.request2Json(request));
        return CommonUtil.successJson();
    }
}
