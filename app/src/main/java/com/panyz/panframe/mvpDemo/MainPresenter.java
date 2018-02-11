package com.panyz.panframe.mvpDemo;

import android.content.Context;

import com.panyz.corelib.base.BaseBean;
import com.panyz.corelib.mvp.BasePresenter;
import com.panyz.corelib.mvp.BaseView;

/**
 * @Project: PanFrame
 * @Package: com.panyz.panframe
 * @Description: TODO
 * @Autor: panyz
 * @Date: 2017年07月25日 16:43
 */
public class MainPresenter extends BasePresenter<BaseBean> {

    private MainModel model;

    public MainPresenter(BaseView mView, Context context) {
        super(mView, context);
        model = new MainModel(this,context);
    }

    public void login(String account, String password) {
        model.login(account,password);
    }
}
