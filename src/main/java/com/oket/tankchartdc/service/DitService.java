package com.oket.tankchartdc.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.oket.station.UserEntity;
import com.oket.tank4station.entity.DbInventory;
import com.oket.tank4station.entity.DbInventoryLast;
import com.oket.tankchartdc.entity.DitEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lw
 * @since 2020-03-30
 */
public interface DitService extends IService<DitEntity> {

    /*
     * 更改ifsf和Json的端口号
     */
    int change(DitEntity ditEntity);
    /*
     * 查询ifsf和Json的端口号以及状态
     */
    DitEntity select(DitEntity ditEntity);
    /*
     * 开启ifsf接收器
     */
    void runIfsf(DitEntity ditEntity);

    /*
     * 关闭ifsf接收器
     */
    void closeIfsf(DitEntity ditEntity);

    /*
     * 开启json接收器
     */
    void runJson(DitEntity ditEntity);


    /*
     * 关闭json接收器
     */
    void closeJson(DitEntity ditEntity);

    /*
     * 查询ifsf端口号
     */
    int selectIfsf();

    /*
     * 查询ifsf端口号
     */
    int selectTestIfsf();
    /*
     * 查询json端口号
     */
    int selectJson();
    /*
     * 查询json端口号
     */
    int selectTestJson();
}
