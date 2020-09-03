package com.oket.device.controller;


import com.alibaba.fastjson.JSONObject;
import com.oket.device.NozRelDisEntity;
import com.oket.device.NozTankRelationEntity;
import com.oket.device.service.NozRelDisService;
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
 * @since 2020-04-01
 */
@RestController
@RequestMapping("/device/nozdisrel")
public class NozRelDisController {

    @Autowired
    NozRelDisService nozRelDisService;

    /*
     * 查询枪罐关系
     */
    @ApiOperation(value = "查询油枪加油机关系")
    @GetMapping("/list")
    public JSONObject getNoz(HttpServletRequest request) {
        return CommonUtil.successJson(nozRelDisService.query(CommonUtil.request2Json(request)));
    }

    @ApiOperation(value = "更改油枪加油机关系")
    @PostMapping("/update")
    public JSONObject updateNozzle(@RequestBody List<NozRelDisEntity> lists) {
        nozRelDisService.changeRelation(lists);
        return CommonUtil.successJson();
    }

    @ApiOperation(value = "解除油枪加油机关系")
    @PostMapping("/cancel")
    public JSONObject cancleNozzle(@RequestBody List<NozRelDisEntity> lists) {
        nozRelDisService.cancelRelation(lists);
        return CommonUtil.successJson();
    }
}
