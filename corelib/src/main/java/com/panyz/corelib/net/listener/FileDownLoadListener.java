package com.panyz.corelib.net.listener;

/**
 * @Project: PanFrame
 * @Package: com.panyz.corelib.net.listener
 * @Description: 文件下载的网络请求接口
 * @Autor: panyz
 * @Date: 2017年08月07日 15:28
 */
public interface FileDownLoadListener extends NetworkRequestListener {
    void onProgress(int progress);
}
