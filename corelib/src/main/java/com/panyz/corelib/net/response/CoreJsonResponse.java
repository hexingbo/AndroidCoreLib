package com.panyz.corelib.net.response;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.panyz.corelib.net.client.NetworkExecutor;
import com.panyz.corelib.net.listener.NetworkRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @Project: PanFrame
 * @Package: com.panyz.corelib.net.response
 * @Description: 网络请求Json格式响应处理类
 * @Autor: panyz
 * @Date: 2017年08月07日 15:02
 */
public class CoreJsonResponse implements Callback {
    private static final String RESULT_CODE = "status";
    private static final String RESULT_MSG = "message";
    private static final String RESULT_CODE_VALUE = "1";

    private Class<?> mClass;
    private Handler mHandler;
    private Gson gson = new Gson();
    private NetworkRequestListener mListener;

    public CoreJsonResponse(NetworkExecutor networkExecutor) {
        this.mClass = networkExecutor.mClass;
        this.mListener = networkExecutor.mListener;
        this.mHandler = new Handler(Looper.getMainLooper());//切换回主线程
    }

    @Override
    public void onFailure(Call call, final IOException e) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Logger.e(e, "接口请求失败");
                mListener.onRequestFail("网络异常");
                mListener.onRequestComplete();
            }
        });
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        final String result = response.body().string();
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                handleResponse(result);
            }
        });
    }

    /**
     * 处理服务器返回的响应
     *
     * @param result
     */
    private void handleResponse(String result) {
        if (result == null && result.equals("")) {
            mListener.onRequestFail("网络异常");
            mListener.onRequestComplete();
            return;
        }
        Logger.json(result);

        // TODO: 2018/2/11 直接返回请求的数据，（实体对象直接返回），不做同意处理
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(result);

            if (mClass == null) {
                //直接返回json字段
                mListener.onRequestSuccess(jsonObject);
            } else {
                //将json转换为实体对象
                Object object = gson.fromJson(jsonObject.toString(), mClass);
                if (object == null) {
                    mListener.onRequestFail("json解析失败");
                } else {
                    //返回实体对象
                    mListener.onRequestSuccess(object);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        // TODO: 2018/2/11  根据后台接口返回数据  统一处理之后再返回对应的实体对象
//        try {
//            JSONObject jsonObject = new JSONObject(result);
//            if (jsonObject.has(RESULT_CODE)) {
//                if (jsonObject.optString(RESULT_CODE).equals(RESULT_CODE_VALUE)) {
//                    if (mClass == null) {
//                        //直接返回json字段
//                        mListener.onRequestSuccess(jsonObject);
//                    } else {
//                        //将json转换为实体对象
//                        Object object = gson.fromJson(jsonObject.toString(), mClass);
//                        if (object == null) {
//                            mListener.onRequestFail("json解析失败");
//                        } else {
//                            //返回实体对象
//                            mListener.onRequestSuccess(object);
//                        }
//                    }
//                } else {
//                    mListener.onRequestFail(jsonObject.optString(RESULT_MSG));
//                }
//            } else {
//                Logger.e("JSON字段中没有对应的验证码");
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        mListener.onRequestComplete();
    }
}
