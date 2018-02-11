package com.panyz.panframe.networkDemo;

import com.panyz.corelib.mvp.BaseModel;
import com.panyz.corelib.mvp.BaseRequestCallBack;
import com.panyz.corelib.net.client.NetworkExecutor;
import com.panyz.corelib.net.request.CoreRequest;
import com.panyz.corelib.net.request.RequestParams;

import okhttp3.Request;

/**
 * @Project: PanFrame
 * @Package: com.panyz.panframe.networkDemo
 * @Description: TODO
 * @Company: 深圳君南信息系统有限公司
 * @Autor: panyz
 * @Date: 2017年08月07日 16:29
 *
 * https://api.douban.com/v2/book/search?q=金瓶梅&tag=&start=0&count=1
 */
public class HomeModel extends BaseModel {

    String urlPost = "https://api.douban.com/v2/book/search?";
    String urlGet = "https://api.douban.com/v2/book/search?q=金瓶梅&tag=&start=0&count=1";

    public HomeModel(BaseRequestCallBack callBack) {
        super(callBack);
    }

    public void loadData() {
        RequestParams params = new RequestParams();
        params.put("q","金瓶梅");
        params.put("tag","");
        params.put("start","0");
        params.put("count","1");
//        Request request = CoreRequest.doGetRequest(url, params);
        Request request = CoreRequest.doPostRequest(urlPost, params);
//        NetworkExecutor executor = getExecutor(HomeBean.class);
        NetworkExecutor executor = getExecutor(HomeBean.class);
        httpClient.sendRequest(request,executor);
    }
    public void loadDataGet() {
        RequestParams params = new RequestParams();
        Request request = CoreRequest.doGetRequest(urlGet, params);
        NetworkExecutor executor = getExecutor(HomeBean.class);
        httpClient.sendRequest(request,executor);
    }
}
