package com.chuanqi56.logistics.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;

import java.util.ArrayList;


public class SharePreferUtils {

	public static final String PUBLIC_KEY = "DHTTG9D14B3413C65991278F09A03896";// 首次握手使用公钥、之后使用返回的私钥
//	public static final String SP_KEY_SETTING_DATA = "setting_data";//sharedPreference本地保存settingData信息的key
	public static final String SP_KEY_COUPON_DATA = "coupons_data";//SharedPreferences本地保存优惠券信息类的key
	public static final String SP_KEY_CNTERINFO_DATA = "center_info_data";//SharedPreferences本地保存个人中心信息类的key
//	public static final String SP_KEY_ORDERLIST_DATA = "orderlistbeans_data";//SharedPreferences本地保存订单列表信息类的key
	public static final String SP_KEY_ADDRESSLIST_DATA = "addrlist_data";//SharedPreferences本地保存地址列表信息类的key
//	public static final String SP_KEY_PTORDERLIST_DATA = "pt_orderlistbeans_data";//SharedPreferences本地保存拼团订单列表信息类的key


	public static void setValue(Context context, String fileName, String key, Object value) {
		SharedPreferencesUtils.setParam(context, fileName, key, value);
	}

	public static void setValue(Context context, String key, Object value) {
		setValue(context, SharedPreferencesUtils.FILE_NAME, key, value);
	}

	public static Object getValue(Context context, String fileName, String key, Object defaultValue) {
		return SharedPreferencesUtils.getParam(context, fileName, key, defaultValue);
	}

	public static Object getValue(Context context, String key, Object defaultValue) {
		return getValue(context, SharedPreferencesUtils.FILE_NAME, key, defaultValue);
	}

	private static String getStringValue(Context context, String key) {
		return (String) getValue(context, key, "");
	}

	private static String getStringValue(Context context, String fileName, String key) {
		return (String) getValue(context, fileName, key, "");
	}

	// 端口地址
	public static void setHostUrl(Context context, String protocol, String host, String port) {
		setValue(context, "Host_Url", protocol + "s://" + host + ":" + port);
	}

	public static String getHostUrl(Context context) {
//		return (String) getValue(context, "Host_Url", ConstantUrl.TEST_URL);
		return (String) getValue(context, "Host_Url", "");
	}

//	public static String getHostUrl() {
//		return getHostUrl(null);
//	}

	//登录session
	public static void setLoginSuccessSession(Context context, String session) {
//		Global.setSession(session);
		setValue(context, "login_session", session);
	}

	public static String getLoginSuccessSession(Context context) {
		return getStringValue(context, "login_session");
	}

	// 保存用户登录的手机号
	public static void setLoginUserPhoneNum(Context context, String user_PhoneNum) {
//		Global.mPhoneNumber = user_PhoneNum;
		setValue(context, "user_phoneNum", user_PhoneNum);
	}

	public static String getLoginUserPhoneNum(Context context) {
		return getStringValue(context, "user_phoneNum");
	}
	// 保存用户登录的用户ID
	public static void setLoginUserId( String user_id) {
		setValue(null, "user_id", user_id);
	}

	public static String getLoginUserId() {
		return getStringValue(null, "user_id");
	}

	/**
	 * 0 weixinzhifu 1 zhifubaozhifu 2 yue
	 */
	public static void setPayTypeFlagSP(Context context, int payTypeFlag) {
		setValue(context, "pay_type_flag", payTypeFlag);
	}

	public static int getPayTypeFlagSP(Context context) {
		return (Integer) getValue(context, "pay_type_flag", 1);
	}

	//私钥
	public static void setPrivate_KeySp(Context context, String private_Key) {
		setValue(context, "private_Key", private_Key);
	}

	public static String getPrivate_KeySp(Context context) {
		return (String) getValue(context, "private_Key", PUBLIC_KEY);
	}

	public static void setApp_IDSp(Context context, String app_id) {
		setValue(context, "app_id", app_id);
	}




	//获得搜索的信息列表
	public static ArrayList<String> getSearchMessage(Context context, int maxSize) {
		ArrayList<String> mList = new ArrayList<String>();

		SharedPreferences preferences = context.getSharedPreferences(SharedPreferencesUtils.FILE_NAME, Context.MODE_PRIVATE);
		String save_message = preferences.getString("search_save_message_str", "");
		if (TextUtils.isEmpty(save_message)) {
			return null;
		} else {
			String[] array = save_message.split(",");
			for (int i = 0; i < array.length && i < maxSize; i++){
				mList.add(array[i]);
			}
			return mList;
		}
	}
	public static void toSaveSearchMessage(Context context, String message, int maxSize) {
		SharedPreferences preferences = context.getSharedPreferences(SharedPreferencesUtils.FILE_NAME, Context.MODE_PRIVATE);
		//获取老数据
		String save_message = preferences.getString("search_save_message_str", "");
		String[] array = save_message.split(",");

		//过度数据
		ArrayList<String> mList = new ArrayList<>();

		//生成新的信息列表可能大于是10偶
		mList.add(message);
		for (int i = 0; i < array.length; i++){
			if (!array[i].equals(message)){
				mList.add(array[i]);
			}
		}

		//取出10个保存在新的数据里边
		save_message = "";
		for (int j = 0; j < mList.size() && j < maxSize; j++){
			if (j == 0){
				save_message = mList.get(j);
			} else {
				save_message = save_message + "," + mList.get(j);
			}
		}

		android.content.SharedPreferences.Editor editor = preferences.edit();
		editor.putString("search_save_message_str", save_message);
		editor.commit();
	}
	//清空搜索的额历史记录
	public static void toSaveSearchMessageDel(Context context) {
		SharedPreferences preferences = context.getSharedPreferences(SharedPreferencesUtils.FILE_NAME, Context.MODE_PRIVATE);
		android.content.SharedPreferences.Editor editor = preferences.edit();
		editor.putString("search_save_message_str", "");
		editor.commit();
	}


	/**
	 * 保存登入的类型
	 * @param context
	 * @param value
	 */
	public static void setLoginType(Context context, int value) {
		setValue(context, "login_type", value);
	}

	public static int getLoginType(Context context) {
		return (int) getValue(context, "login_type", 0x0);
	}


	private static String getJsonStr(Object obj) {
		Gson gson = new Gson();
		String json = gson.toJson(obj);
		DebugLog.e("保存本地的数据json对象：" + json);
		return json;
	}

}
