package com.oket.station.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.oket.station.service.StationService;
import com.oket.util.CommonUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 油站接口
 * @author lw
 * @since 2019-11-26
 */
@RestController
@RequestMapping(value = "/station")
@Api(tags = "油站接口")
public class StationController {

    @Autowired(required = false)
    private StationService stationService;

    /**
     * 进入页面返回油站所有信息
     */
    @GetMapping("/getall")
    public JSONObject allListDeliveries(HttpServletRequest request) {
        return stationService.selectStationAddress(CommonUtil.request2Json(request));
    }

    /**
     * 修改油站信息
     */
    @PostMapping("/update")
    public JSONObject updateDeliveries(@RequestBody List<JSONObject> lists) {

        return stationService.updateStationAddress(lists);
    }

}
