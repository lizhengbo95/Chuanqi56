package com.chuanqi56.logistics.rxjavaretrofit.http;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.chuanqi56.logistics.utils.DebugLog;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Locale;

import com.chuanqi56.logistics.entity.JsonResult;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.Buffer;

/**
 * okhttp3网络数据请求日志拦截器，实现返回的json数据打印
 * 作者：lizhengbo95 on 2016/7/28 09:55
 */
public class LogInterceptor implements Interceptor {

    private static final Charset UTF8 = Charset.forName("UTF-8");
    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        // （版本：V2.8.0；作者：lizhengbo95；时间：2017/3/6 17:35 ）说明：
        RequestBody requestBody = request.body();
        Buffer buffer = new Buffer();
        requestBody.writeTo(buffer);
        Charset charset = UTF8;
        MediaType contentType = requestBody.contentType();
        if (contentType != null) {
            charset = contentType.charset(UTF8);
        }

        String readString = buffer.readString(charset);
        readString = readString.replaceAll("="," = ");
        DebugLog.e("【请求参数】：\n" + readString.replace("&","\n"));

//        DebugLog.e("okhttp3——>URL:" + request.toString());
        long t1 = System.nanoTime();
        okhttp3.Response response = chain.proceed(chain.request());
        long t2 = System.nanoTime();
        HttpUrl httpUrl = response.request().url();
        DebugLog.e("okhttp3——>" + String.format(Locale.getDefault(), "Received response for %s in %.1fms%n%s",
                httpUrl, (t2 - t1) / 1e6d, response.headers()));
        okhttp3.MediaType mediaType = response.body().contentType();
        String content = response.body().string();
//        DebugLog.e("okhttp3——>response body:");
//        DebugLog.e("okhttp3——>response body_msg:" + content);
        DebugLog.subJsonLog(content);//拼接太长了的日志

        upResultInfo(httpUrl.toString(), content);

        return response.newBuilder()
                .body(okhttp3.ResponseBody.create(mediaType, content))
                .build();
    }

    /**
     * 接口统一处理上报埋点
     * @param httpUrl
     * @param content
     */
    private void upResultInfo(String httpUrl, String content) {
        try {
            Gson gson = new Gson();
            JsonResult jsonResult = gson.fromJson(content, JsonResult.class);
            String formatDebugLog = "";
            if (jsonResult != null) {
                formatDebugLog = String.format("ret=%s;meg=%s", jsonResult.ret, jsonResult.msg);
            }
            DebugLog.e(httpUrl + "【接口结果】：" +formatDebugLog);

//            Map<String, String> mapKV = new HashMap<String, String>();
//            mapKV.put("返回信息", formatDebugLog);
//            TalkingDataUtils.onEvent(MyApplication.getApplication(), "okhttp网络接口", httpUrl, mapKV);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
    }

}
