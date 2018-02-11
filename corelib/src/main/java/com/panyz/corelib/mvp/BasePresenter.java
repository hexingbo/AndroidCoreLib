package com.panyz.corelib.mvp;

import android.content.Context;

/**
 * @Project: PanFrame
 * @Package: com.panyz.corelib.mvp
 * @Description: MVP架构的Presenter层
 * @Autor: panyz
 * @Date: 2017年07月25日 16:03
 */
public class BasePresenter <T> implements BaseRequestCallBack<T> {

    //持有View层的引用
    private BaseView mView;

    public BasePresenter(BaseView mView, Context context) {
        this.mView = mView;
    }

    /**
     * 以下的回调方法对应View层的相应操作
     */

    @Override
    public void RequestBefore() {
        mView.startLoadView();
    }

    @Override
    public void RequestFail(String msg) {
        mView.onFail(msg);
    }

    @Override
    public void RequestSuccess(T data) {
        mView.onSuccess(data);
    }

    @Override
    public void RequestComplete() {
        mView.stopLoadView();
    }
}
