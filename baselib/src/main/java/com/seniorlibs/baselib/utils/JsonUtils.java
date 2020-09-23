package com.seniorlibs.baselib.utils;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Author: chen
 * Version: 1.0.0
 * Date: 2020/6/23 0031
 * Mender:
 * Modify:
 * Description: Json解析工具类
 */
public class JsonUtils {

    /**
     * 将java对象转换成json字符串
     *
     * @param obj 待转换的java对象
     * @return
     */
    public static String parseObj2Json(Object obj) {
        return parseObj2Json(obj, null);
    }

    /**
     * 将java对象转换成json字符串
     * 只序列化一部分字段，将需要序列化的字段名配置到数组中，filter = null时则序列化全部字段
     * SimplePropertyPreFilter filter = new SimplePropertyPreFilter(User.class, newString[]{"name"});只序列化User中的name字段
     *
     * @param obj
     * @param filter
     * @return
     */
    public static String parseObj2Json(Object obj, SimplePropertyPreFilter filter) {
        if (obj == null) {
            return null;
        }

        try {
            String json = null;
            if (filter != null) {
                json = JSON.toJSONString(obj, filter);
            } else {
                json = JSON.toJSONString(obj);
            }
            return json;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 将json字符串转换成java对象
     *
     * @param json  待转换的json字符串
     * @param clazz java对象的类
     * @return
     */
    public static <T> T parseJson2Obj(String json, Class<T> clazz) {
        if (TextUtils.isEmpty(json)) {
            return null;
        }

        try {
            return JSON.parseObject(json, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 将list对象转换成json字符串
     *
     * @param list 待转换的list对象
     * @return
     */
    public static String parseList2Json(List list) {
        if (list == null || list.size() == 0) {
            return null;
        }

        try {
            return JSON.toJSONString(list);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 将json字符串转换成list对象
     *
     * @param json  待转换的json字符串
     * @param clazz list对象元素的类
     * @return
     */
    public static <T> List<T> parseJson2List(String json, Class<T> clazz) {
        if (TextUtils.isEmpty(json)) {
            return null;
        }

        try {
            return JSON.parseArray(json, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 将json字符串转为JSONObect对象
     *
     * @param json 待转换的json字符串
     * @return
     */
    public static JSONObject parseJson2JSONObject(String json) {
        if (TextUtils.isEmpty(json)) {
            return null;
        }

        try {
            return (JSONObject) JSON.parse(json);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 将json字符串转为JSONArray对象
     *
     * @param json 待转换的json字符串
     * @return
     */
    public static JSONArray parseJson2JSONArray(String json) {
        if (TextUtils.isEmpty(json)) {
            return null;
        }

        try {
            return JSON.parseArray(json);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 将json字符串转为Map对象
     *
     * @param json 待转换的json字符串
     * @return
     */
    public static HashMap<String, String> parseJson2StringMap(String json) {
        if (TextUtils.isEmpty(json)) {
            return null;
        }

        try {
            HashMap<String, String> hashMap = new HashMap<>();
            JSONObject jsonObject = (JSONObject) JSON.parse(json);
            Iterator<Map.Entry<String, Object>> entries = jsonObject.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry<String, Object> entry = entries.next();
                hashMap.put(entry.getKey(), String.valueOf(entry.getValue() + ""));
            }

            return hashMap;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 将json字符串转为Map对象
     *
     * @param json 待转换的json字符串
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> HashMap<K, V> parseJson2Map(String json) {
        if (TextUtils.isEmpty(json)) {
            return null;
        }

        try {
            HashMap<K, V> hashMap = new HashMap<>();
            Map<K, V> map = JSON.parseObject(json, new TypeReference<Map<K, V>>() {});
            Iterator<Map.Entry<K, V>> entries = map.entrySet().iterator();
            while (entries.hasNext()) {
                Map.Entry<K, V> entry = entries.next();
                hashMap.put(entry.getKey(), entry.getValue());
            }

            return hashMap;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
