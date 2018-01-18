package com.chuanqi56.logistics.parameters;

import android.content.Context;

import com.chuanqi56.logistics.application.MyApplication;
import com.chuanqi56.logistics.rxjavaretrofit.ConstantUrl;
import com.chuanqi56.logistics.utils.MD5;

import java.util.HashMap;
import java.util.Map;


/**
 * 
 * @Description: 参数基类
 * @author lizhengbo95
 * @date 2015-9-23 上午10:13:16
 * 
 */
public class BaseParameters {
	protected Context mContext;
	protected static String mSession;
	protected static String mPrivate_Key;
	protected static String mApp_ID;

	public String mHost_Url;

	public BaseParameters() {
		this.mContext = MyApplication.getApplication();
//		mSession = Global.getSession();
//		mPrivate_Key = SharePreferUtils.getPrivate_KeySp(mContext);
//		mApp_ID = SharePreferUtils.getApp_IDSp(mContext);
	}

	/**
	 * 参数父类中公共的MD5加密字段
	 *
	 * @param userSession Session是否需要参与加密
	 * @return
	 */
	protected StringBuilder getStringBuilder(boolean userSession) {
		StringBuilder sb = new StringBuilder();
		sb.append(mPrivate_Key);
		sb.append(mApp_ID);
		if (userSession) {
			sb.append(mSession);
		}
		return sb;
	}

	/**
	 * 参数父类中公共的字段
	 *
	 * @param userSession Session是否需要参与加密
	 * @param sb          拼接好需要加密的字符串
	 * @return
	 */
	protected Map getParametersMap(boolean userSession, StringBuilder sb) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("app_id", mApp_ID);
		if (userSession) {
			map.put("session", mSession);
		}
		map.put("sign", MD5.getSign(sb.toString()));
		map.put("control_app_version", ConstantUrl.CONTROL_APP_VERSION);
		return map;
	}
}
