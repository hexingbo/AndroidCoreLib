package com.panyz.corelib.net.request;

import com.orhanobut.logger.Logger;

import java.io.File;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * @Project: PanFrame
 * @Package: com.panyz.corelib.net.request
 * @Description: 网络请求核心类，负责创建各种请求类型
 * @Autor: panyz
 * @Date: 2017年08月07日 11:16
 */
public class CoreRequest {
    private static final MediaType FILE_TYPE = MediaType.parse("application/octet-stream");

    /**
     * get请求
     * @param url
     * @param params
     * @return
     */
    public static Request doGetRequest(String url, RequestParams params) {
        StringBuilder sb = new StringBuilder(url).append("?");
        if (params != null) {
            for (Map.Entry<String, String> entry : params.stringParams.entrySet()) {
                sb.append(entry.getKey())
                        .append("=")
                        .append(entry.getValue())
                        .append("&");
            }
        }
        Logger.d("URL = " + sb.substring(0, sb.length() - 1));
        Request request = new Request.Builder()
                .get()
                .url(sb.substring(0, sb.length() - 1))
                .build();
        return request;
    }

    /**
     * post请求
     * @param url
     * @param params
     * @return
     */
    public static Request doPostRequest(String url, RequestParams params) {
        FormBody.Builder builder = new FormBody.Builder();
        if (params != null) {
            for (Map.Entry<String, String> entry : params.stringParams.entrySet()) {
                builder.add(entry.getKey(), entry.getValue());
            }
        }
        Logger.d("URL = " + url);
        FormBody body = builder.build();
        Request request = new Request.Builder()
                .post(body)
                .url(url)
                .build();
        return request;
    }

    /**
     * 上传文件请求
     * @param url
     * @param params
     * @return
     */
    public static Request uploadFileRequest(String url, RequestParams params) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(FILE_TYPE);
        if (params != null) {
            for (Map.Entry<String, Object> entry : params.objectParams.entrySet()) {
                if (entry.getValue() instanceof File) {
                    builder.addPart(MultipartBody.Part.createFormData(
                            entry.getKey(),
                            null,
                            RequestBody.create(FILE_TYPE, (File) entry.getValue())
                    ));
                } else if (entry.getValue() instanceof String) {
                    builder.addFormDataPart(entry.getKey(), String.valueOf(entry.getValue()));
                }
            }
        }
        Logger.d("URL = " + url);
        RequestBody body = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        return request;
    }

}
