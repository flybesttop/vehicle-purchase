package com.vp.util;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 工具类
 * @author flybesttop
 * @date 2020/11/25
 */
public class CommonUtils {

    // 判断是否为空
    public static boolean isEmpty(Object o) {
        return o == null || "".equals(o + "");
    }

    // 判断是否为非空
    public static boolean isNotEmpty(Object o) {
        return !(o == null || "".equals(o + ""));
    }

    //把map转换成list的公共方法
    public static List<Map<String,Object>> mapToList(Map<String,Object> map){
        List<Map<String,Object>> list=new ArrayList<>();
        Iterator<String> it=map.keySet().iterator();
        while(it.hasNext()){
            String key=it.next();
            list.add(JSONObject.parseObject(JSONObject.toJSONString(map.get(key))));
        }
        return list;
    }
}
