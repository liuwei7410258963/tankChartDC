package com.oket.util;

import com.alibaba.fastjson.JSONObject;

import java.util.Collections;
import java.util.List;

public class PageUtils {
	/**
	 * lw
	 *
	 * @param size1 每页显示的条数
	 * @param page  页码
	 * @param list1 要进行分页的list集合
	 * @return
	 */
	public static List<JSONObject> page(int size1, int page, List<JSONObject> list1) {
		// 每页多少条
		int count = size1;
		// 总条数
		int size = list1.size();
		if (size != 0) {
			// 总页数
			int pageCount = size % count == 0 ? size / count : size / count + 1;
			int fromIndex = count * (page - 1);
			int toIndex = fromIndex + count;
			if (toIndex >= size) {
				toIndex = size;
			}
			if (page > pageCount + 1) {
				fromIndex = 0;
				toIndex = 0;
			}
			// 分页之后 索引 (包含）（不包含）
            return list1.subList(fromIndex, toIndex);
		}
		return Collections.emptyList();
	}
}
