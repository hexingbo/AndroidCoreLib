package com.panyz.panframe.mvpDemo;

import android.content.Context;

import com.panyz.corelib.base.BaseBean;
import com.panyz.corelib.mvp.BaseModel;
import com.panyz.corelib.mvp.BaseRequestCallBack;

/**
 * @Project: PanFrame
 * @Package: com.panyz.panframe
 * @Description: TODO
 * @Autor: panyz
 * @Date: 2017年07月25日 16:58
 */
public class MainModel extends BaseModel {

    private BaseRequestCallBack callBack;
    private MainActivity activity;

    public MainModel(BaseRequestCallBack callBack, Context context) {
        super(callBack);
        activity = (MainActivity) context;
        this.callBack = callBack;
    }

    public void login(final String account, final String password) {
        callBack.RequestBefore();
        if (account.isEmpty() || password.isEmpty()) {
            callBack.RequestFail("请输入登录账号或密码");
        } else {
            BaseBean baseBean = new BaseBean();
            baseBean.statu = "1";
            baseBean.message = "登录成功！";
            callBack.RequestSuccess(baseBean);
        }
        callBack.RequestComplete();
    }

}
