package com.oket.station.dao;

import com.oket.station.GpsEntity;
import com.oket.station.UrlEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 网址 Mapper 接口
 * </p>
 *
 * @author lw
 * @since 2019-11-26
 */
public interface UrlDao extends BaseMapper<UrlEntity> {
    boolean saveOrUpdate(UrlEntity urlEntity);
}
