package com.hcis.ipr.monogo.utils;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhw
 * @date 2019/10/17
 **/
public class JsonStrToMap {

    public static Map<String, Object> jsonStrToMap(String json){

        Object jsonObject = JSON.parse(json);
        return  (HashMap<String, Object>) jsonObject;
    }
}
