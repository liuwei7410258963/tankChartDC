package com.oket.station.controller;


import com.alibaba.fastjson.JSONObject;
import com.oket.common.base.BaseController;
import com.oket.delivery.BzDelivery;
import com.oket.station.HandOver;
import com.oket.station.service.HandOverService;
import com.oket.util.AirUtils;
import com.oket.util.CommonUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lw
 * @since 2020-08-19
 */
@RestController
@RequestMapping("/station/handover")
public class HandOverController extends BaseController{

    @Autowired
    HandOverService handOverService;


    /**
     * 导入交班数据
     * @param file 上传的excel文件
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject uploadHandOverMessage(@RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        List<HandOver> upload = handOverService.upload(file);
        if(AirUtils.hv(upload)) {
            return CommonUtil.successJson(upload);
        }
        else{
            return CommonUtil.ErrorJson();
        }
    }

    /**
     * 查询交班数据
     */
    @ApiOperation(value = "查询交班数据")
    @GetMapping("/list")
    public JSONObject select(HttpServletRequest request) {
        return CommonUtil.successPage(handOverService.query(CommonUtil.request2Json(request), true));
    }


    /**
     * 作废交班数据
     */
    @ApiOperation(value = "作废交班数据")
    @GetMapping("/delete")
    public JSONObject delete(HttpServletRequest request) {
        handOverService.delete(CommonUtil.request2Json(request));
        return CommonUtil.successJson();
    }

    @Override
    protected List doQuery(JSONObject requestJson) {
        return handOverService.query(requestJson, false).getRecords();
    }

    @ApiOperation(value = "导出交班数据")
    @PostMapping("/export")
    public void exportDeliveryData(HttpServletResponse response, String excelFileds, String excelTitles, String fileName, String jsonObject, String language) throws Exception {
        super.exportData(response, excelFileds, excelTitles, fileName, jsonObject, language);
    }
}
