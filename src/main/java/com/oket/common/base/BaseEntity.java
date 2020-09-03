package com.oket.common.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 〈基础实体〉
 *
 * @author SunBiaoLong
 * @create 2019/10/11
 * @since 1.0.0
 */
@Data
@Accessors(chain = true)
public class BaseEntity {
    /**
     * 主键id
     */
    @TableId(type= IdType.AUTO)
    private Integer id;

}
