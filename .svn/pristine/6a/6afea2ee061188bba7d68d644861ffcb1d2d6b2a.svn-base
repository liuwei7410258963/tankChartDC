package com.oket.util.model;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Set;

/**
 * @description: MyBatis的一对多JSON返回对象
 * <p>
 * 处理嵌套查询结果时，MyBatis会根据bean定义的属性类型来初始化嵌套的成员变量，
 * 主要看其是不是Collection
 * 如果这里不定义，那么嵌套返回结果里就只能返回一对一的结果，而不是一对多的
 * <p>
 * 参见MyBatis  DefaultResultSetHandler.instantiateCollectionPropertyIfAppropriate()
 */
public class One2Many extends JSONObject {
	private Set<String> stringSet;
	private Set<Integer> intSet;
	private List<JSONObject> jsonObjectList;
}
