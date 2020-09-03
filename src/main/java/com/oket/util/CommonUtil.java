package com.oket.util;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.oket.config.exception.CommonJsonException;
import com.oket.util.constants.ErrorEnum;
import com.oket.util.constants.HttpResponseConstants;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

/**
 * @author ysh
 * @description: 本后台接口系统常用的json工具类
 */
public class CommonUtil {

	/**
	 * 返回一个info为空对象的成功消息的json
	 */
	public static JSONObject successJson() {
		JSONObject info = new JSONObject();
		info.put("result", "success");
		JSONObject resultJson = new JSONObject();
		resultJson.put("code", HttpResponseConstants.SUCCESS_CODE);
		resultJson.put("msg", HttpResponseConstants.SUCCESS_MSG);
		resultJson.put("info", info);
		return successJson(resultJson);
	}

	/**
	 * 失败，，返回一个返回码为400的json
	 */
	public static JSONObject failJson() {
		JSONObject info = new JSONObject();
		info.put("result", "fail");
		JSONObject resultJson = new JSONObject();
		resultJson.put("code", HttpResponseConstants.FAIL_CODE);
		resultJson.put("msg", HttpResponseConstants.FAIL_MSG);
		resultJson.put("info", info);
		return successJson(resultJson);
	}

	/**
	 * 格式不正确
	 */
	public static JSONObject ErrorJson() {
		JSONObject info = new JSONObject();
		info.put("result", "fail");
		JSONObject resultJson = new JSONObject();
		resultJson.put("code", HttpResponseConstants.FAIL_CODE);
		resultJson.put("msg", HttpResponseConstants.ERROR_MSG);
		resultJson.put("info", info);
		return successJson(resultJson);
	}


	/**
	 * 返回一个返回码为100的json
	 */
	public static JSONObject successJson(Object info) {
		JSONObject resultJson = new JSONObject();
		resultJson.put("code", HttpResponseConstants.SUCCESS_CODE);
		resultJson.put("msg", HttpResponseConstants.SUCCESS_MSG);
		resultJson.put("token", "token");
		resultJson.put("info", info);
		return resultJson;
	}

	public static String successStr(Object object) {
		JSONObject resultJson = new JSONObject();
		resultJson.put("code", HttpResponseConstants.SUCCESS_CODE);
		resultJson.put("msg", HttpResponseConstants.SUCCESS_MSG);
		resultJson.put("info", object);
		return resultJson.toJSONString();
	}

	/**
	 * 返回错误信息JSON
	 */
	public static JSONObject errorJson(ErrorEnum errorEnum) {
		JSONObject resultJson = new JSONObject();
		resultJson.put("code", errorEnum.getErrorCode());
		resultJson.put("msg", errorEnum.getErrorMsg());
		resultJson.put("info", new JSONObject());
		return resultJson;
	}

	public static JSONObject error(String errorCode, String message) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", errorCode);
		jsonObject.put("msg", message);
		return jsonObject;
	}

	/**
	 * mybatis 查询分页封装
	 */
	public static JSONObject successPage(IPage iPage) {
		JSONObject result = successJson();
		JSONObject info = new JSONObject();
		info.put("list", iPage.getRecords());
		info.put("totalCount", iPage.getTotal());
		info.put("totalPage", iPage.getPages());
		result.put("info", info);
		return result;
	}

	/**
	 * 查询分页结果后的封装工具方法
	 *
	 * @param requestJson 请求参数json,此json在之前调用fillPageParam 方法时,已经将pageRow放入
	 * @param list        查询分页对象list
	 * @param totalCount  查询出记录的总条数
	 */
	public static JSONObject successPage(final JSONObject requestJson,Object list, int totalCount) {
		int pageRow = requestJson.getIntValue("pageRow");
		int totalPage = getPageCounts(pageRow, totalCount);
		JSONObject result = successJson();
		JSONObject info = new JSONObject();
		info.put("list", list);
		info.put("totalCount", totalCount);
		info.put("totalPage", totalPage);
		result.put("info", info);
		return result;
	}

	/**
	 * 查询分页结果后的封装工具方法
	 *
	 * @param requestJson 请求参数json,此json在之前调用fillPageParam 方法时,已经将pageRow放入
	 * @param list        查询分页对象list
	 * @param totalCount  查询出记录的总条数
	 */
	public static JSONObject successPage1(final JSONObject requestJson,Object list, int totalCount) {
		int totalPage = getPageCounts1(totalCount);
		JSONObject result = successJson();
		JSONObject info = new JSONObject();
		info.put("list", list);
		info.put("totalCount", totalCount);
		info.put("totalPage", totalPage);
		result.put("info", info);
		return result;
	}

	/**
	 * 查询分页结果后的封装工具方法
	 *
	 * @param requestJson 请求参数json,此json在之前调用fillPageParam 方法时,已经将pageRow放入
	 * @param list        查询分页对象list
	 * @param totalCount  查询出记录的总条数
	 */
	public static JSONObject successPage(final JSONObject requestJson,List<JSONObject> list, int totalCount) {
		int pageRow = requestJson.getIntValue("pageRow");
		int totalPage = getPageCounts(pageRow, totalCount);
		JSONObject result = successJson();
		JSONObject info = new JSONObject();
		info.put("list", list);
		info.put("totalCount", totalCount);
		info.put("totalPage", totalPage);
		result.put("info", info);
		return result;
	}

	/**
	 * 查询分页结果后的封装工具方法
	 *
	 * @param list 查询分页对象list
	 */
	public static JSONObject successPage(List<JSONObject> list) {
		JSONObject result = successJson();
		JSONObject info = new JSONObject();
		info.put("list", list);
		result.put("info", info);
		return result;
	}

	/**
	 * 获取总页数
	 *
	 * @param pageRow   每页行数
	 * @param itemCount 结果的总条数
	 */
	private static int getPageCounts(int pageRow, int itemCount) {
		if (itemCount == 0) {
			return 1;
		}
		if (pageRow != 0) {
			return itemCount % pageRow > 0 ?
					itemCount / pageRow + 1 :
					itemCount / pageRow;
		} else {
			return itemCount;
		}
	}

	/**
	 * 获取总页数(整合图用)
	 *  1-5条数据返回 1
	 *  6-9 返回 2
	 *  10-13 返回 3
	 *  14-17 返回 4
	 * @param itemCount 结果的总条数
	 */
	private static int getPageCounts1(int itemCount) {
		if (itemCount <= 5) {
			return 1;
		}
		else{
			return (int)Math.ceil((itemCount-5)/4.0)+1;
		}
	}

	/**
	 * 将request参数值转为json
	 */
	public static JSONObject request2Json(HttpServletRequest request) {
		JSONObject requestJson = new JSONObject();
		Enumeration paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			String[] pv = request.getParameterValues(paramName);
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < pv.length; i++) {
				if (pv[i].length() > 0) {
					if (i > 0) {
						sb.append(",");
					}
					sb.append(pv[i]);
				}
			}
			requestJson.put(paramName, sb.toString());
		}
		return requestJson;
	}

	/**
	 * 将request转JSON
	 * 并且验证非空字段
	 */
	public static JSONObject convert2JsonAndCheckRequiredColumns(HttpServletRequest request, String requiredColumns) {
		JSONObject jsonObject = request2Json(request);
		hasAllRequired(jsonObject, requiredColumns);
		return jsonObject;
	}

	/**
	 * 验证是否含有全部必填字段
	 *
	 * @param requiredColumns 必填的参数字段名称 逗号隔开 比如"userId,name,telephone"
	 */
	public static void hasAllRequired(final JSONObject jsonObject, String requiredColumns) {
		if (!StringTools.isNullOrEmpty(requiredColumns)) {
			//验证字段非空
			String[] columns = requiredColumns.split(",");
			String missCol = "";
			for (String column : columns) {
				Object val = jsonObject.get(column.trim());
				if (StringTools.isNullOrEmpty(val)) {
					missCol += column + "  ";
				}
			}
			if (!StringTools.isNullOrEmpty(missCol)) {
				jsonObject.clear();
				jsonObject.put("code", ErrorEnum.E_1001.getErrorCode());
				jsonObject.put("msg", "缺少必填参数:" + missCol.trim());
				jsonObject.put("info", new JSONObject());
				throw new CommonJsonException(jsonObject);
			}
		}
	}


	/**
	 * 在分页查询之前,为查询条件里加上分页参数
	 *
	 * @param paramObject    查询条件json
	 * @param defaultPageRow 默认的每页条数,即前端不传pageRow参数时的每页条数
	 */
	public static void fillPageParam(final JSONObject paramObject, int defaultPageRow) {
		int pageNum = paramObject.getIntValue("pageNum");
		pageNum = pageNum == 0 ? 1 : pageNum;
		int pageRow = paramObject.getIntValue("pageRow");
		pageRow = pageRow == 0 ? defaultPageRow : pageRow;
		paramObject.put("offSet", (pageNum - 1) * pageRow);
		paramObject.put("pageRow", pageRow);
		paramObject.put("pageNum", pageNum);
		//删除此参数,防止前端传了这个参数,pageHelper分页插件检测到之后,拦截导致SQL错误
		paramObject.remove("pageSize");
	}

	/**
	 * 分页查询之前的处理参数
	 * 没有传pageRow参数时,默认每页10条.
	 */
	public static void fillPageParam(final JSONObject paramObject) {
		fillPageParam(paramObject, 10);
	}


	/**
	 * @param sourceCompanyList
	 * @return
	 */
	public static List<JSONObject> buildCompanyTree(List<JSONObject> sourceCompanyList) {

		if (sourceCompanyList.isEmpty()) {
			return sourceCompanyList;
		}
		List<List<JSONObject>> comLevels = new ArrayList<>();

		for (JSONObject company : sourceCompanyList) {
			String[] ranks = company.getString("rank").split(",");
			if (ranks.length > comLevels.size()) {
				int gap = ranks.length - comLevels.size();
				for (int i = 0; i < gap; i++) {
					List<JSONObject> temp = new ArrayList<>();
					comLevels.add(temp);
				}
			}
			comLevels.get(ranks.length - 1).add(company);
		}
		if (comLevels.size() == 1) {
			return comLevels.get(0);
		}
		List<JSONObject> finalCompanyList = new ArrayList<>();
		//每一个层次都被放置在一个数组中
		//从倒数第二个数组开始,依次将子集放置在此层级组织的children属性中,直到最高层次

		for (int j = comLevels.size() - 2; j >= 0; j--) {
			List<JSONObject> tempList = comLevels.get(j);
			if (tempList.isEmpty()) {
//				if (jsonObjects.isEmpty()){
				finalCompanyList.addAll(comLevels.get(j + 1));
//				}
			} else {
				//遍历此层级,将设定自己的子集
				for (JSONObject temp : tempList) {
					List<JSONObject> children = new ArrayList<>();
					//寻找子集
					Iterator<JSONObject> childIterator = comLevels.get(j + 1).iterator();
					while (childIterator.hasNext()) {
						final JSONObject child = childIterator.next();
						if (child.getIntValue("parentId") == temp.getIntValue("companyId")) {
							children.add(child);
							childIterator.remove();
						}
					}
					temp.put("children", children);
				}
				finalCompanyList.addAll(comLevels.get(j + 1));
				if (j == 0) {
					finalCompanyList.addAll(comLevels.get(0));
				}
			}

		}
		return finalCompanyList;
	}


}
