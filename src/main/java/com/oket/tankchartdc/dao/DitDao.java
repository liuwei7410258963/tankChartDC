package com.oket.tankchartdc.dao;

import com.oket.tankchartdc.entity.DitEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lw
 * @since 2020-03-30
 */
@Repository
public interface DitDao extends BaseMapper<DitEntity> {
    int selectJson();
    int selectIfsf();
    int selectTestJson();
    int selectTestIfsf();
}
