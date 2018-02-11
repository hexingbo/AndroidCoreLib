package com.panyz.corelib.mvp;

import com.panyz.corelib.net.client.NetworkExecutor;
import com.panyz.corelib.net.client.NetworkRequestClient;
import com.panyz.corelib.net.listener.NetworkRequestListener;

/**
 * @Project: PanFrame
 * @Package: com.panyz.corelib.mvp
 * @Description: MVP架构的Model层
 * @Autor: panyz
 * @Date: 2017年07月25日 16:02
 */
public class BaseModel {

    private BaseRequestCallBack callBack;
    public NetworkRequestClient httpClient;

    public BaseModel(BaseRequestCallBack callBack) {
        this.callBack = callBack;
        httpClient = NetworkRequestClient.getInstance();
    }

    public NetworkExecutor getExecutor(Class<?> mClass) {
        NetworkExecutor networkExecutor = new NetworkExecutor(new NetworkRequestListener() {
            @Override
            public void onRequestBefore() {
                callBack.RequestBefore();
            }

            @Override
            public void onRequestSuccess(Object object) {
                callBack.RequestSuccess(object);
            }

            @Override
            public void onRequestFail(String msg) {
                callBack.RequestFail(msg);
            }

            @Override
            public void onRequestComplete() {
                callBack.RequestComplete();
            }
        }, mClass);
        return networkExecutor;
    }

}
