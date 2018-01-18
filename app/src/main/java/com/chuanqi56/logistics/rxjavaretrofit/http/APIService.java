package com.chuanqi56.logistics.rxjavaretrofit.http;


import com.chuanqi56.logistics.entity.JsonResult;

import java.util.Map;

import com.chuanqi56.logistics.entity.BarcodeSku;
import com.chuanqi56.logistics.rxjavaretrofit.ConstantUrl;
import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 说明：API接口参数
 * 作者：lizhengbo95
 * 时间：2016/7/28 10:11
 */
public interface APIService {

//    @FormUrlEncoded
//    @POST(ConstantUrl.LOCATE_SHOP3_URL)
//    Observable<JsonResult<LocatShopData>> postByPath(@FieldMap Map<String, String> userInfoMap);
//
//    @FormUrlEncoded
//    @POST(ConstantUrl.BALANCE_DETAIL_URL)
//    Observable<JsonResult<ArrayList<BalanceDetailBean>>> postBalanceDetail(@FieldMap Map<String, String> userInfoMap);

    @FormUrlEncoded
    @POST(ConstantUrl.BARCODE_SKU)
    Observable<JsonResult<BarcodeSku>> postBarcode_sku(@FieldMap Map<String, String> map);



}
