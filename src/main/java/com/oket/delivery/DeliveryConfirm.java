package com.oket.delivery;

import com.baomidou.mybatisplus.annotation.TableName;
import com.oket.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author lw
 * @since 2020-04-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("bz_delivery_confirm")
public class DeliveryConfirm extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer deliveryId;

    private Integer cycleId;

    private Boolean flag;


}
