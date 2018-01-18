package com.chuanqi56.logistics.rxjavaretrofit.subscribers;

import android.content.Context;

import com.chuanqi56.logistics.utils.DebugLog;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
//import rx.Subscriber;

/**
 * 说明：普通请求数据方式，需要调用者自己监听成功返回数据，并对请求数据进行处理
 * 作者：lizhengbo95
 * 时间：2016/7/28 10:58
 */
public class CommonSubscriber<T> implements Observer<T> {

    private Context context;
    private SubscriberOnNextListener mSubscriberOnNextListener;//成功返回的数据监听处理

    public CommonSubscriber(SubscriberOnNextListener mSubscriberOnNextListener) {
        this.mSubscriberOnNextListener = mSubscriberOnNextListener;
    }
    public CommonSubscriber(Context context, SubscriberOnNextListener mSubscriberOnNextListener) {
        this.context = context;
        this.mSubscriberOnNextListener = mSubscriberOnNextListener;
    }

//    /**
//     * 订阅开始时调用
//     * 显示ProgressDialog
//     */
//    @Override
//    public void onStart() {
//    }
//
//    /**
//     * 完成，隐藏ProgressDialog
//     */
//    @Override
//    public void onCompleted() {
//        DebugLog.e("网络请求数据完成");
//    }

    /**
     * 对错误进行统一处理
     * 隐藏ProgressDialog
     *
     * @param e
     */
    @Override
    public void onError(Throwable e) {
//        String message = "数据获取失败";
//        if (e instanceof SocketTimeoutException || e instanceof ConnectException) {
//            message = "网络中断，请检查您的网络状态";
//        }
//        DebugLog.e(message + e.getMessage());
//        CommonUtils.showLongToast(context, message);
//        TalkingDataUtils.onEvent(context, "okhttp3的POST请求错误", e.getMessage());

        DebugLog.e("异常接口：" + e.getMessage());
    }

    @Override
    public void onComplete() {
        DebugLog.e("网络请求数据完成");
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    /**
     * 将onNext方法中的返回结果交给Activity或Fragment自己处理
     *
     * @param t 创建Subscriber时的泛型类型
     */
    @Override
    public void onNext(T t) {
        if (mSubscriberOnNextListener != null) {
            mSubscriberOnNextListener.onNext(t);
        }
    }
}