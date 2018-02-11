package com.panyz.corelib.net.client;

import com.panyz.corelib.net.response.CoreFileResponse;
import com.panyz.corelib.net.response.CoreJsonResponse;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * @Project: PanFrame
 * @Package: com.panyz.corelib.net.client
 * @Description: 网络请求框架的客户端
 * @Autor: panyz
 * @Date: 2017年08月07日 15:51
 */
public class NetworkRequestClient {
    private static NetworkRequestClient instance;
    private OkHttpClient mOkHttpClient;
    private static final int TIME_OUT = 10;

    private NetworkRequestClient(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //支持Https
        builder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        builder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
//        builder.readTimeout(TIME_OUT, TimeUnit.SECONDS);
//        builder.writeTimeout(TIME_OUT, TimeUnit.SECONDS);
        mOkHttpClient = builder.build();
    }

    public static NetworkRequestClient getInstance(){
        if (instance == null) {
            synchronized (NetworkRequestClient.class) {
                if (instance == null) {
                    instance = new NetworkRequestClient();
                }
            }
        }
        return instance;
    }

    /**
     * 发送请求
     * @param request
     * @param executor
     */
    public void sendRequest(Request request, NetworkExecutor executor) {
        Call call = mOkHttpClient.newCall(request);
        executor.mListener.onRequestBefore();
        call.enqueue(new CoreJsonResponse(executor));
    }

    /**
     * 文件下载
     * @param request
     * @param executor
     */
    public void downloadFile(Request request, NetworkExecutor executor) {
        Call call = mOkHttpClient.newCall(request);
        executor.mListener.onRequestBefore();
        call.enqueue(new CoreFileResponse(executor));
    }
}
