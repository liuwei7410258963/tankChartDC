package com.oket.tankchartdc.controller;


import com.alibaba.fastjson.JSONObject;
import com.oket.tankchartdc.entity.BackToTankConfirmEntity;
import com.oket.tankchartdc.service.impl.BackToTankConfirmServiceImpl;
import com.oket.util.CommonUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lw
 * @since 2020-04-24
 */
@RestController
@RequestMapping("/tankchartdc/backConfirm")
public class BackToTankConfirmController {

    @Autowired
    BackToTankConfirmServiceImpl backToTankConfirmService;

    /**
     * 查询回罐确认数据
     */
    @GetMapping("/list")
    @ApiOperation(value = "查询回罐确认数据")
    public JSONObject select(HttpServletRequest request) {
        return CommonUtil.successJson(backToTankConfirmService.select(CommonUtil.request2Json(request)));
    }

    /**
     * 查询回罐确认数据
     */
    @PostMapping("/update")
    @ApiOperation(value = "更新回罐确认数据")
    public JSONObject select1(@RequestBody List<BackToTankConfirmEntity> lists) {
        backToTankConfirmService.update(lists);
        return  CommonUtil.successJson();
    }

}
