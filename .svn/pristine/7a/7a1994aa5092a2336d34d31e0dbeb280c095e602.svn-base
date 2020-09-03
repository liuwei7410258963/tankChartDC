package com.oket.common.base;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 〈DAO基础类〉
 *
 * @author SunBiaoLong
 * @create 2019/3/11
 * @since 1.0.0
 */
public interface BaseDao<T> extends BaseMapper<T> {
	/**
	 * 分页查询
	 *
	 * @param page    分页对象
	 * @param wrapper 查询对象
	 * @return
	 */
	List<T> queryPage(IPage<T> page, @Param("ew") QueryWrapper wrapper);
}
