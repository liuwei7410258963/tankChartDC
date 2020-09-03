package com.oket.station.service;

import com.alibaba.fastjson.JSONObject;
import com.oket.station.UserEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lw
 * @since 2020-03-30
 */
public interface UserService extends IService<UserEntity> {
    /**
     * 分页整合
     * @param req
     * isPage :是不是分页的
     * @return
     */
    List<UserEntity> query(JSONObject req);

    /**
     * 获得菜单权限List
     */
    List<String> getMune(JSONObject jsonObject);

    /**
     * 改密码
     */
    void update(UserEntity userEntity);
}
