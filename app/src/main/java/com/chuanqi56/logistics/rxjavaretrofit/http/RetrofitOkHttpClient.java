package com.chuanqi56.logistics.rxjavaretrofit.http;

import com.chuanqi56.logistics.rxjavaretrofit.ConstantUrl;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 作者：lizhengbo95 on 2017/7/19 13:54
 */

public class RetrofitOkHttpClient {

    private static final int DEFAULT_TIMEOUT = 10;//手动创建一个OkHttpClient并设置超时时间
    private static final int WRITE_DEFAULT_TIMEOUT = 30;//手动创建一个OkHttpClient并设置超时时间
    /**
     * OkHttp客户端单例对象
     *
     * @return OkHttpClient
     */
    public static final OkHttpClient provideClient() {

        //OkHttp自带的log日志打印方式，为了方便查看日志此处重新它，使用自己的log
        HttpLoggingInterceptor.Level logLevel = HttpLoggingInterceptor.Level.BODY;
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(logLevel);

        // （版本：V2.6.0；作者：lizhengbo95；时间：2016/12/21 17:48 ）说明：HTTPS证书校验，第三方接口不需要验证，否则会包异常
//        SSLSocketFactory sslSocketFactory = NetworkUtils.getSslSocketFactory(MyApplication.getApplication());
        // （版本：V3.1.0；作者：lizhengbo95；时间：2017/5/27 17:45 ）说明：
//        X509TrustManager x509TrustManager = NetworkUtils.getX509TrustManager(MyApplication.getApplication());

        // 添加公共参数拦截器
        HttpCommonInterceptor commonInterceptor = new HttpCommonInterceptor.Builder()
                .addHeaderParams("control_app_version", ConstantUrl.CONTROL_APP_VERSION)
                .build();

        OkHttpClient client = new OkHttpClient.Builder()
//                .sslSocketFactory(sslSocketFactory)
                .addInterceptor(commonInterceptor)
                .addInterceptor(new LogInterceptor())
                .addInterceptor(interceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(WRITE_DEFAULT_TIMEOUT, TimeUnit.SECONDS)
//                .addNetworkInterceptor(new StethoInterceptor())
                .build();
        return client;
    }
}
