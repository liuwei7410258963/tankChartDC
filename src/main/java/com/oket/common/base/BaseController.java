package com.oket.common.base;

import com.alibaba.fastjson.JSONObject;
import com.oket.util.poi.ExportExcelUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈controller 基础类〉
 *
 * @author SunBiaoLong
 * @create 2019/3/11
 * @since 1.0.0
 */
public abstract class BaseController {
    /**
     * 导出数据列表
     */
    protected abstract List doQuery(JSONObject requestJson);

    /**
     * @Description: exportUserData
     * @Param: [response, excelFileds导出的字段, excelTitles 名称, fileName 文件名称, jsonObject（查询条件），language 中英文]
     * @returns: void
     * @Author: ysh
     * @Date: 2019/3/11 11:26
     */
    public void exportData(HttpServletResponse response, String excelFileds, String excelTitles, String fileName, String jsonObject, String language) throws Exception {
        JSONObject requestJson = new JSONObject();
        if (!StringUtils.isEmpty(jsonObject)) {
            requestJson = JSONObject.parseObject(jsonObject);
        }
        requestJson.put("excelFileds", excelFileds);
        requestJson.put("excelTitles", excelTitles);
        //删除这两个参数,防止导出不是全部
        requestJson.remove("pageNum");
        requestJson.remove("pageRow");
        ExportExcelUtils.exportExcel(response, fileName, doQuery(requestJson), requestJson, language);
    }
}