package com.oket.dispenser.controller;


import com.alibaba.fastjson.JSONObject;
import com.oket.dispenser.NozzleOutRelGroup;
import com.oket.dispenser.service.VoNozzleOutGroupService;
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
 * @since 2020-04-14
 */
@RestController
@RequestMapping("/dispenser/group")
public class VoNozzleOutGroupController {


    @Autowired(required = false)
    private VoNozzleOutGroupService voNozzleOutGroupService;

    /**
     * 查询销售列表
     */
    @ApiOperation(value = "查询付油组")
    @GetMapping("/list")
    public JSONObject listNozGriop(HttpServletRequest request) {
        return CommonUtil.successPage(voNozzleOutGroupService.query(CommonUtil.request2Json(request), true));
    }

    /**
     * 查询销售列表
     */
    @ApiOperation(value = "查询付油组及其关联的液位Id")
    @GetMapping("/listrel")
    public JSONObject listNozGroupAndRelId(HttpServletRequest request) {
        return voNozzleOutGroupService.listNozGroupAndRelId(CommonUtil.request2Json(request));
    }

    /**
     * 查询销售列表
     */
    @ApiOperation(value = "根据付油组查询付油详情")
    @GetMapping("/nozlist")
    public JSONObject listNoz(HttpServletRequest request) {
        return CommonUtil.successJson(voNozzleOutGroupService.selectNozs(CommonUtil.request2Json(request)));
    }

    /**
     * 拆分付油组展示数据
     */
    @ApiOperation(value = "拆分付油组展示数据")
    @PostMapping("/show")
    public JSONObject showChange(HttpServletRequest request) {
        return CommonUtil.successJson(voNozzleOutGroupService.selectNozzleOutGroup(CommonUtil.request2Json(request)));
    }


    /**
     * 拆分付油组展示数据
     */
    @ApiOperation(value = "合并拆分付油组数据")
    @PostMapping("/update")
    public JSONObject updateChange(@RequestBody List<NozzleOutRelGroup> lists) {
        voNozzleOutGroupService.update(lists);
        return CommonUtil.successJson();
    }

    /**
     *  新建付油组后查询附近轨迹数据
     */
    @ApiOperation(value = "新建付油组后查询附近轨迹数据")
    @GetMapping("/querytrace")
    public JSONObject querytrace(HttpServletRequest request) {
        return CommonUtil.successJson(voNozzleOutGroupService.querytrace(CommonUtil.request2Json(request)));
    }
}
