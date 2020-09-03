package com.oket.device.controller;


import com.alibaba.fastjson.JSONObject;
import com.oket.device.NozTankRelationEntity;
import com.oket.device.Nozzle4Device;
import com.oket.device.cache.DeviceCacheManager;
import com.oket.device.service.NozTankRelationService;
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
 * @since 2020-03-31
 */
@RestController
@RequestMapping("/device/nozreltank")
public class NozTankRelationController {
    @Autowired
    NozTankRelationService nozTankRelationService;

    /*
     * 查询枪罐关系
     */
    @ApiOperation(value = "查询枪罐关系")
    @GetMapping("/list")
    public JSONObject getNoz(HttpServletRequest request) {
        return CommonUtil.successJson(nozTankRelationService.query(CommonUtil.request2Json(request)));
    }

    @ApiOperation(value = "更改枪罐关系")
    @PostMapping("/update")
    public JSONObject updateNozzle(@RequestBody List<NozTankRelationEntity> lists) {
        nozTankRelationService.changeRelation(lists);
        return CommonUtil.successJson();
    }

    @ApiOperation(value = "解除枪罐关系")
    @PostMapping("/cancel")
    public JSONObject cancleNozzle(@RequestBody List<NozTankRelationEntity> lists) {
        nozTankRelationService.cancelRelation(lists);
        return CommonUtil.successJson();
    }
}
