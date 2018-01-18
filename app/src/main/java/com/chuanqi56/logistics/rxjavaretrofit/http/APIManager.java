package com.chuanqi56.logistics.rxjavaretrofit.http;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.chuanqi56.logistics.utils.DebugLog;

import com.chuanqi56.logistics.rxjavaretrofit.ConstantUrl;

import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

//import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by ycw on 2016/6/6.
 */
public class APIManager {

    private static APIService apiService;
    private static Retrofit retrofit;
//    private static final String host_url = "https://test-apiitem.urfresh.cn/";

    /**
     * 获得app.urfresn.cn主机的请求客服端
     *
     * @return
     */
    public static APIService getClientAPI() {
        if (apiService == null) {
//            String host_url = Global.getHostUrl();
            String host_url = ConstantUrl.HOST;
            if (TextUtils.isEmpty(host_url)) {
                DebugLog.e("服务器host_url is null");
                return null;
            }
            DebugLog.e("服务器host_url:" + host_url);
            retrofit = new Retrofit.Builder()
                    .baseUrl(host_url)
                    .client(RetrofitOkHttpClient.provideClient())
                    .addConverterFactory(provideConverter())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            apiService = retrofit.create(APIService.class);
        }
        return apiService;
    }

    /**
     * Gson转换器单例对象
     *
     * @return Converter.Factory
     */
    public static Converter.Factory provideConverter() {
        return GsonConverterFactory.create(provideGson());
    }

    /**
     * Gson 单例对象
     *
     * @return Gson
     */
    public static Gson provideGson() {
        return new GsonBuilder().serializeNulls().create();
    }
}
