package com.oket.delivery.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.oket.delivery.DeliveryConfirm;
import com.oket.delivery.service.DeliveryConfirmService;
import com.oket.delivery.service.DeliveryService;
import com.oket.util.CommonUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lw
 * @since 2020-04-08
 */
@RestController
@RequestMapping("/delivery/confirm")
public class DeliveryConfirmController {

    @Autowired
    DeliveryConfirmService deliveryConfirmService;
    /**
     * 查询卸油确认
     */
    @ApiOperation(value = "查询卸油列表")
    @GetMapping("/list")
    public JSONObject listDeliveries(HttpServletRequest request) {
        return deliveryConfirmService.query(CommonUtil.request2Json(request));
    }

    /**
     * 修改卸油确认
     */
    @ApiOperation(value = "修改卸油确认")
    @PostMapping("/update")
    public JSONObject updateDeliveries(HttpServletRequest request) {
        deliveryConfirmService.updateConfirm(CommonUtil.request2Json(request));
        return CommonUtil.successJson();
    }
}
