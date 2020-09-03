package com.oket.station.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.oket.station.HandOver;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lw
 * @since 2020-08-19
 */
public interface HandOverService extends IService<HandOver> {
    /**
     * 分页整合
     *
     * @param req isPage :是不是分页的
     * @return
     */
    IPage<HandOver> query(JSONObject req, boolean isPage);

    /**
     * 导入交班数据
     *
     */
    List<HandOver> upload(MultipartFile file) throws IOException;

    /**
     * 导入交班数据
     */
    void delete(JSONObject jsonObject);
}
