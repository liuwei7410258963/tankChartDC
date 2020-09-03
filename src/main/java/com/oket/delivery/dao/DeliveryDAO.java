package com.oket.delivery.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oket.delivery.BzDelivery;
import org.springframework.stereotype.Repository;

/**
 * 〈一句话功能简述〉<br>
 * 〈卸油记录的dao接口〉
 *
 * @author sunbiaolong
 * @create 2019/3/14
 * @since 1.0.0
 */
@Repository
public interface DeliveryDAO extends BaseMapper<BzDelivery> {
}
