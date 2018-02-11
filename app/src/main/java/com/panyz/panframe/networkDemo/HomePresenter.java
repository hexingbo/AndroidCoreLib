package com.panyz.panframe.networkDemo;

import android.content.Context;

import com.panyz.corelib.mvp.BasePresenter;
import com.panyz.corelib.mvp.BaseView;

/**
 * @Project: PanFrame
 * @Package: com.panyz.panframe.networkDemo
 * @Description: TODO
 * @Company: 深圳君南信息系统有限公司
 * @Autor: panyz
 * @Date: 2017年08月07日 16:27
 */
public class HomePresenter extends BasePresenter<HomeBean> {

    private HomeModel model;

    public HomePresenter(BaseView mView, Context context) {
        super(mView, context);
        model = new HomeModel(this);
    }

    public void loadData() {
        model.loadData();
    }

    public void loadDataGet() {
        model.loadDataGet();
    }
}
