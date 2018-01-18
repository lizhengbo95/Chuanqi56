package com.chuanqi56.logistics.rxjavaretrofit.http;


import android.content.Context;

import com.chuanqi56.logistics.entity.JsonResult;
import com.chuanqi56.logistics.rxjavaretrofit.subscribers.SubscriberOnNextListener;
import com.chuanqi56.logistics.utils.DebugLog;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.Map;

import com.chuanqi56.logistics.entity.BarcodeSku;
import com.chuanqi56.logistics.rxjavaretrofit.subscribers.CommonSubscriber;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 说明：rxjava + retrofit网络数据请求
 * 作者：lizhengbo95
 * 时间：2016/7/28 10:03
 */
public class HttpMethods {
    private APIService apiService;

    private HttpMethods() {
        apiService = APIManager.getClientAPI();
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    //获取单例
    public static HttpMethods getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private <T> void toSubscribe(Context context, Observable<T> o, SubscriberOnNextListener subscriberOnNextListener) {
        Observer s = new CommonSubscriber(context, subscriberOnNextListener);

        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(doOnError())
//                .doOnNext(doOnNext(pathName))
                .subscribe(s);
    }

    private <T> void toSubscribe(Observable<T> o, Observer<T> s) {
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(doOnError())
//                .doOnNext(doOnNext(pathName))
                .subscribe(s);
    }

    private Consumer<Throwable> doOnError() {
//        final String url = pathName;
        return new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
//                DebugLog.e("网络接口POST请求执行【异常】:" + url);

//                Map<String, String> mapKV = new HashMap<String, String>();
//                mapKV.put("异常信息", throwable.getMessage());
//                TalkingDataUtils.onEvent(MyApplication.getApplication(), "网络接口POST请求错误", url, mapKV);

                String message = "数据获取失败";
                if (throwable instanceof SocketTimeoutException) {
                    message = "网络访问超时";
                } else if (throwable instanceof ConnectException) {
                    message = "网络中断，请检查您的网络状态";
                }
//                CommonUtils.showLongToast(MyApplication.getApplication().getBaseContext(), message);
                DebugLog.e(throwable.getMessage());
                DebugLog.e(message);
            }
        };
    }


//    /**
//     * 余额交易明细接口
//     *
//     * @param subscriber
//     * @param pathName
//     * @param map
//     */
//    public void postBalanceDetail(Observer<JsonResult<ArrayList<BalanceDetailBean>>> subscriber, String pathName, Map<String, String> map) {
//        // （版本：V2.6.0；作者：lizhengbo95；时间：2016/12/20 19:26 ）说明：所有接口都加入版本号control_app_version，提供给后台，不需加入签名
//        map.put("control_app_version", ConstantUrl.CONTROL_APP_VERSION);
//
//        Observable observable = apiService.postBalanceDetail(map).map(new JsonResultFunc());
//        toSubscribe(observable, subscriber, pathName);
//    }
//
//    /**
//     * 地址选择页的城市列表
//     *
//     * @param context
//     * @param subscriberOnNextListener
//     */
//    public void postAddrCityList(Context context, SubscriberOnNextListener subscriberOnNextListener) {
//        Observer<JsonResult<ArrayList<SelectZoneData>>> subscriber = new CommonSubscriber(context, subscriberOnNextListener);
////        CityListParameters cityParameters = new CityListParameters(context);
//        AddrParameters addrParameters = new AddrParameters(context);
//        Map<String, String> map = addrParameters.getCityListParamersMap();
//        // （版本：V2.6.0；作者：lizhengbo95；时间：2016/12/20 19:26 ）说明：所有接口都加入版本号control_app_version，提供给后台，不需加入签名
//        map.put("control_app_version", ConstantUrl.CONTROL_APP_VERSION);
//
//        String pathName = ConstantUrl.CITY_LIST_URL;
//        Observable observable = apiService.postAddrCityList(map).map(new JsonResultFunc());
//        toSubscribe(observable, subscriber, pathName);
//    }


    public void postBarcode_sku(Map<String, String> map, Observer<JsonResult<BarcodeSku>> subscriber) {
        Observable observable = apiService.postBarcode_sku(map).map(new JsonResultFunc());
        toSubscribe(observable, subscriber);
    }
}
