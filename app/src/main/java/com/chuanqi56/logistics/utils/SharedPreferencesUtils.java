package com.chuanqi56.logistics.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.chuanqi56.logistics.application.MyApplication;


/**
 * 
 * @Description: SharedPreferences工具类，将少量数据以键值对形式存储到xml文件中
 * @author lizhengbo95
 * @date 2015-9-14 下午3:20:45
 * 
 */
public class SharedPreferencesUtils {
	/**
	 * 保存在手机里面的文件名
	 */
	public static final String FILE_NAME = "app_uboss_share_pref";
//	public static final String FILE_NAME_BANNER_IMG = "app_uboss_share_pref_banner_img";//图片xml文件单独存一个文件
	private static Context mContext = MyApplication.getApplication().getApplicationContext();//获取应用中的Context

	/**
	 * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
	 * 
	 * @param context
	 * @param fileName
	 * @param key
	 * @param object
	 */
	public static void setParam(Context context, String fileName, String key, Object object) {
		if (object == null) {
			return;
		}
		String type = object.getClass().getSimpleName();
//		if (mContext == null){
//			TalkingDataUtils.onEvent(context,"获取应用中的Context==null");
//		}
		SharedPreferences sp = mContext.getSharedPreferences(fileName, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();

		if ("String".equals(type)) {
			editor.putString(key, (String) object);
		} else if ("Integer".equals(type)) {
			editor.putInt(key, (Integer) object);
		} else if ("Boolean".equals(type)) {
			editor.putBoolean(key, (Boolean) object);
		} else if ("Float".equals(type)) {
			editor.putFloat(key, (Float) object);
		} else if ("Long".equals(type)) {
			editor.putLong(key, (Long) object);
		} else if ("Double".equals(type)) {
			editor.putFloat(key, Float.parseFloat(object.toString()));
		}

		editor.commit();
	}

	/**
	 * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
	 * 
	 * @param context
	 * @param key
	 * @param object
	 */
	public static void setParam(Context context, String key, Object object) {
		setParam(context, FILE_NAME, key, object);
	}

	/**
	 * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
	 * 
	 * @param context
	 * @param fileName
	 * @param key
	 * @param defaultObject
	 * @return
	 */
	public static Object getParam(Context context, String fileName, String key, Object defaultObject) {
		String type = defaultObject.getClass().getSimpleName();
//		if (mContext == null){
//			TalkingDataUtils.onEvent(context,"获取应用中的Context==null");
//		}
		SharedPreferences sp = mContext.getSharedPreferences(fileName, Context.MODE_PRIVATE);

		if ("String".equals(type)) {
			try {
				// TODO: 2016/11/14 内存溢出优化
				return sp.getString(key, (String) defaultObject);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if ("Integer".equals(type)) {
			return sp.getInt(key, (Integer) defaultObject);
		} else if ("Boolean".equals(type)) {
			return sp.getBoolean(key, (Boolean) defaultObject);
		} else if ("Float".equals(type)) {
			return sp.getFloat(key, (Float) defaultObject);
		} else if ("Long".equals(type)) {
			return sp.getLong(key, (Long) defaultObject);
		}

		return defaultObject;
	}

	/**
	 * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
	 * 
	 * @param context
	 * @param key
	 * @param defaultObject
	 * @return
	 */
	public static Object getParam(Context context, String key, Object defaultObject) {
		return getParam(context, FILE_NAME, key, defaultObject);
	}

	/**
	 * 清除全部数据
	 */
	public static void clearAllData(String fileName) {
		SharedPreferences sp = mContext.getSharedPreferences(fileName, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		editor.clear();
		editor.commit();
	}
}
