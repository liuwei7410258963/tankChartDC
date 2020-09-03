package com.oket.dispenser.dao;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oket.dispenser.BzNozzleOut;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * VIEW Mapper 接口
 * </p>
 *
 * @author sunbiaolong
 * @since 2019-10-21
 */
@Repository
public interface NozzleOutDao extends BaseMapper<BzNozzleOut> {
    /**
     * 油品汇总
     * @param jsonObject
     * @return
     */
    List<JSONObject> getOilSum(JSONObject jsonObject);

    /**
     * 获取没有匹配的付油信息
     * @return
     */
    List<BzNozzleOut> getNotMatched();
}
