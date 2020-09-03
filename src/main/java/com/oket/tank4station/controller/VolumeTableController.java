package com.oket.tank4station.controller;

import com.alibaba.fastjson.JSONObject;
import com.oket.tank4station.service.VolumeTableService;
import com.oket.util.CommonUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 容积表操作
 */
@RestController
@RequestMapping("/volumeTable")
public class VolumeTableController {

    @Autowired
    private VolumeTableService volumeTableService;

    @ApiOperation(value = "读取容积表")
    @GetMapping("/get")
    public JSONObject get(HttpServletRequest request) {
        return CommonUtil.successJson(volumeTableService.query(CommonUtil.request2Json(request), false));
    }
}
