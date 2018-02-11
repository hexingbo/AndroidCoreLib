package com.panyz.corelib.mvp;

/**
 * @Project: PanFrame
 * @Package: com.panyz.corelib.mvp
 * @Description: MVP架构中的View层
 * @Autor: panyz
 * @Date: 2017年07月25日 15:57
 */
public interface BaseView<T> {

    /**
     * 打开加载框
     */
    void startLoadView();

    /**
     * 关闭加载框
     */
    void stopLoadView();

    /**
     * 网络请求成功后回调
     * @param data
     */
    void onSuccess(T data);

    /**
     * 网络请求失败后回调
     * @param msg
     */
    void onFail(String msg);

}
