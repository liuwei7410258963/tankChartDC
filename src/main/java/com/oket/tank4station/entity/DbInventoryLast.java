package com.oket.tank4station.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 罐存信息
 *
 * @author SunBiaoLong
 * @date 2019-12-07
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "db_inventory_last")
public class DbInventoryLast extends DbInventory {
}
