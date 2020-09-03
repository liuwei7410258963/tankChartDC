package com.oket.tank4station.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oket.tank4station.entity.DbInventoryTrace;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "dbInventoryTraceDAO")
public interface DbInventoryTraceDAO extends BaseMapper<DbInventoryTrace> {
    List<DbInventoryTrace> batchSelectList(QueryWrapper wrapper);
}
