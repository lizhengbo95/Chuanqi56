package com.chuanqi56.logistics.rxjavaretrofit.http;

/**
 * 说明：错误结果统一处理
 * 作者：lizhengbo95
 * 时间：2016/7/28 10:20
 */
public class ApiException extends RuntimeException {

    private static final int SIGN_ERROR = -1;//签名错误
    private static final int PARAMETER_ERROR = -2;//参数错误
    private static final int APP_ID_ERROR = -3;//app_id错误
    private static final int SESSION_ERROR = -4;//session无效

    public ApiException(int resultCode) {
        this(getApiExceptionMessage(resultCode));
    }

    private ApiException(String detailMessage) {
        super(detailMessage);
    }

    /**
     * 由于服务器传递过来的错误信息直接给用户看的话，用户未必能够理解
     * 需要根据错误码对错误信息进行一个转换，在显示给用户
     *
     * @param code
     * @return
     */
    private static String getApiExceptionMessage(int code) {
        String message;
        switch (code) {
            case SIGN_ERROR:
            case PARAMETER_ERROR:
            case APP_ID_ERROR:
                message = "数据异常，请反馈给程序猿GG :)" + code;
                break;
            case SESSION_ERROR:
                message = "账号异常，请重新登录" + code;
                break;
            default:
                message = "未知错误" + code;
        }
        return message;
    }
}

