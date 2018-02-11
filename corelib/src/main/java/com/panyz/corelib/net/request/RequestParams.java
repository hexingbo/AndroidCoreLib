package com.panyz.corelib.net.request;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Project: PanFrame
 * @Package: com.panyz.corelib.net.request
 * @Description: 网络请求参数类
 * @Autor: panyz
 * @Date: 2017年08月07日 11:06
 */
public class RequestParams {
    public ConcurrentHashMap<String, String> stringParams = new ConcurrentHashMap<>();
    public ConcurrentHashMap<String, Object> objectParams = new ConcurrentHashMap<>();

    public RequestParams(){}

    public void put(String key, String value){
        if (key != null && value != null) {
            stringParams.put(key, value);
        }
    }

    public void put(String key, Object value) {
        if (key != null) {
            objectParams.put(key, value);
        }
    }
}
