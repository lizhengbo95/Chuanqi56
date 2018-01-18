package com.chuanqi56.logistics.rxjavaretrofit.http;


import com.chuanqi56.logistics.utils.DebugLog;
import com.chuanqi56.logistics.entity.JsonResult;

import io.reactivex.functions.Function;

/**
 * Created by Administrator on 2017/6/7.
 */
public class JsonResultFunc<T> implements Function<JsonResult<T>, T> {


    @Override
    public T apply(JsonResult<T> jsonResult) {
        int ret = jsonResult.ret;
        DebugLog.e("--JsonResult--" + jsonResult);
        if (ret == -1 || ret == -2 || ret == -4) {
            if (ret == -4) {//session失效，跳转打登陆页
                DebugLog.e("--session失效----" + ret);
                //注释--使用获取context的时候会报异常使之不能跳转登录页面--
//            CommonUtils.showLongToast(MyApplication.getApplication(), "账号异常，请重新登录");
//                Intent intent = new Intent(MyApplication.getApplication(), V3_LoginActivity.class);
//                //由于功能性防止force close,故加入此标志
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                MyApplication.getApplication().startActivity(intent);

                // （版本：V3.2.0；作者：lizhengbo95；时间：2017/6/20 10:05 ）说明：
//                SharePreferUtils.clean(null);
                DebugLog.e("--session失效--4--" + ret);
            }
            //负数错误统一处理
//            throw new ApiException(ret);
        }
        DebugLog.e("----JsonResultFunc--" + jsonResult.ret + "---" + jsonResult);
        return ((T) jsonResult);
    }


}