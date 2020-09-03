package com.oket.common.base;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @description:
 * @author: SunBiaoLong
 * @create: 2020-03-17 13:49
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class BaseAlarm extends BaseEntity {
	private int type;
	private int minType;
	private Date startTime;
	private Date endTime;

}
