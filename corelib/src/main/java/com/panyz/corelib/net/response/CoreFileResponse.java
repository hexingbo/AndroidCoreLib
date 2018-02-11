package com.panyz.corelib.net.response;

import android.os.Handler;
import android.os.Looper;

import com.orhanobut.logger.Logger;
import com.panyz.corelib.net.client.NetworkExecutor;
import com.panyz.corelib.net.listener.FileDownLoadListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @Project: PanFrame
 * @Package: com.panyz.corelib.net.response
 * @Description: 网络请求时文件格式响应处理类
 * @Autor: panyz
 * @Date: 2017年08月07日 15:29
 */
public class CoreFileResponse implements Callback {
    private Handler mHandler;
    private String filePath;
    private int progress;
    private FileDownLoadListener mListener;

    public CoreFileResponse(NetworkExecutor executor) {
        this.mListener = (FileDownLoadListener) executor.mListener;
        this.filePath = executor.filePath;
        this.mHandler = new Handler(Looper.getMainLooper());//切换回主线程
    }

    @Override
    public void onFailure(Call call, final IOException e) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Logger.e(e, "下载失败");
                mListener.onRequestFail("下载失败");
                mListener.onRequestComplete();
            }
        });
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        final File file = handleResponse(response);
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (file != null) {
                    mListener.onRequestSuccess(file);
                } else {
                    mListener.onRequestFail("下载失败");
                }
                mListener.onRequestComplete();
            }
        });
    }

    /**
     * 处理服务器返回的响应
     * @param response
     * @return
     */
    private File handleResponse(Response response) {
        if (response == null) {
            return null;
        }
        InputStream inputStream = null;
        File file = null;
        FileOutputStream fileOutputStream = null;
        byte[] buffer = new byte[2048];
        int length;
        int currentLength = 0;//下载文件的当前进度
        double sumLength;//下载文件的总进度
        try {
            checkFilePath(filePath);
            file = new File(filePath);
            fileOutputStream = new FileOutputStream(file);
            inputStream = response.body().byteStream();
            sumLength = response.body().contentLength();
            while ((length = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, length);
                currentLength += length;
                progress = (int) ((currentLength / sumLength) * 100);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mListener.onProgress(progress);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            file = null;
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return file;
    }

    /**
     * 检查存放下载的文件的路径
     *
     * @param filePath
     */
    private void checkFilePath(String filePath) {
        File path = new File(filePath.substring(0, filePath.lastIndexOf("/") + 1));
        File file = new File(filePath);
        if (!path.exists()) {
            path.mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
