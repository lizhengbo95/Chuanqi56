package com.chuanqi56.logistics.rxjavaretrofit.subscribers;

/**
 * 说明：网络数据请求成功监听类，用于activity等界面的数据处理，从而不需要关系失败的情况
 * 作者：lizhengbo95
 * 时间：2016/7/28 11:58
 */
public interface SubscriberOnNextListener<T> {
    void onNext(T t);
}
