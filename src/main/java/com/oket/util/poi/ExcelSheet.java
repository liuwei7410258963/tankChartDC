package com.oket.util.poi;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 标注在Excel模型类上，定义Sheet相关属性
 */
@Retention(RUNTIME)
@Target(TYPE)
@Inherited
public @interface ExcelSheet {

	/**
	 * Sheet名称
	 */
	String name() default "";

}
