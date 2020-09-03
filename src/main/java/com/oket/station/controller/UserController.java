package com.oket.station.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.oket.station.UserEntity;
import com.oket.station.service.UserService;
import com.oket.util.CommonUtil;
import com.oket.util.constants.ErrorEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lw
 * @since 2020-03-30
 */
@RestController
@RequestMapping("/station/user")
@Api(tags = "用户接口")
public class UserController {
    @Autowired
    UserService userService;

    /**
     * 登录
     */
    @PostMapping("/login")
    @ApiOperation(value = "登陆")
    public JSONObject login(@RequestBody JSONObject requestJson) {
        CommonUtil.hasAllRequired(requestJson, "userName,pwd");
        return userService.query(requestJson).size()!=0?CommonUtil.successJson(requestJson.getString("userName")):CommonUtil.errorJson(ErrorEnum.E_60005);
    }

    /**
     * 登录
     */
    @PostMapping("/check")
    @ApiOperation(value = "登陆")
    public JSONObject check(@RequestBody JSONObject requestJson) {
        CommonUtil.hasAllRequired(requestJson, "userName,pwd");
        return userService.query(requestJson).size()!=0?CommonUtil.successJson(true):CommonUtil.successJson(false);
    }

    /**
     * 菜单权限
     */
    @PostMapping("/menu")
    @ApiOperation(value = "菜单权限")
    public JSONObject menu(@RequestBody JSONObject requestJson) {
        CommonUtil.hasAllRequired(requestJson, "userName");
        return CommonUtil.successJson(userService.getMune(requestJson));
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "修改")
    public JSONObject update(@RequestBody UserEntity userEntity) {
        userService.update(userEntity);
        return CommonUtil.successJson();
    }

}
