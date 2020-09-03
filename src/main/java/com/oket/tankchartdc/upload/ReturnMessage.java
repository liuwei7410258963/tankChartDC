package com.oket.tankchartdc.upload;


import com.alibaba.fastjson.JSONObject;


public class ReturnMessage {

    private ReturnMessage() {
    }

    public static JSONObject success() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", "1");
        jsonObject.put("message", "上传成功");
        return jsonObject;
    }

    public static JSONObject successMessage(Object object) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", "1");
        jsonObject.put("message", object);
        return jsonObject;
    }



    public static JSONObject createReturnMessage(Object data) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", "1");
        jsonObject.put("message", "上传成功");
        jsonObject.put("data", data);

        return jsonObject;
    }
}
