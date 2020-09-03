package com.oket.device.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oket.device.MaintenanceManager;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author sunbiaolong
 * @since 2019-10-15
 */
public interface MaintenanceManagerDAO extends BaseMapper<MaintenanceManager> {

    List<MaintenanceManager> selectPageExt(Page<MaintenanceManager> page, @Param("ew") QueryWrapper wrapper);
}
