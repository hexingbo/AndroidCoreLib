package com.panyz.corelib.net.client;

import com.panyz.corelib.net.listener.NetworkRequestListener;

/**
 * @Project: PanFrame
 * @Package: com.panyz.corelib.net.client
 * @Description: 网络请求处理类
 * @Autor: panyz
 * @Date: 2017年08月07日 14:57
 */
public class NetworkExecutor {
    public NetworkRequestListener mListener = null;

    //将json转换为实体对象
    public Class<?> mClass = null;

    //下载文件保存的路径
    public String filePath = null;

    public NetworkExecutor(NetworkRequestListener mListener) {
        this.mListener = mListener;
    }

    public NetworkExecutor(NetworkRequestListener mListener, Class<?> mClass) {
        this.mListener = mListener;
        this.mClass = mClass;
    }

    public NetworkExecutor(NetworkRequestListener mListener, String filePath) {
        this.mListener = mListener;
        this.filePath = filePath;
    }

}
