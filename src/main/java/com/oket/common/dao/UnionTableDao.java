package com.oket.common.dao;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oket.common.UnionTable;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * Mapper 联合表接口
 * </p>
 *
 * @author Dxj
 * @since 2019-10-24
 */
public interface UnionTableDao extends BaseMapper<UnionTable> {
    /**
     * 根据 分表得l类型去表里查询 这个类型下得得表
     *
     * @param unionType
     * @return
     */
    String getUnionTableName(@Param("unionType") Integer unionType);

    /**
     * 修改总表得表结构
     *
     * @param jsonObject
     */
    void alertSumTable(JSONObject jsonObject);

    /**
     * 创建罐存分表
     *
     * @param tableName
     */
    void createInvTable(@Param("tableName") String tableName);


    /**
     * 删除指定表
     *
     * @param tableName
     */
    @Update("drop table ${tableName}")
    void dropTable(@Param("tableName") String tableName);

    /**
     * 删除指定联合表
     *
     * @param tableName
     */
    @Delete("delete from union_table where union_table_name = '${tableName}'")
    void deleteUnionTable(@Param("tableName") String tableName);
}
