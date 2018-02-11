package com.panyz.corelib.mvp;

/**
 * @Project: PanFrame
 * @Package: com.panyz.corelib.mvp
 * @Description: 关于view层的一些回调接口
 * @Autor: panyz
 * @Date: 2017年07月25日 16:06
 */
public interface BaseRequestCallBack<T> {

    /**
     * 网络请求开始前回调
     */
    void RequestBefore();

    /**
     * 网络请求失败后回调
     */
    void RequestFail(String msg);

    /**
     * 网络请求成功后回调
     * @param data
     */
    void RequestSuccess(T data);

    /**
     * 网络请求结束后回调
     */
    void RequestComplete();
}
