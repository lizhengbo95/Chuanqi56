package com.chuanqi56.logistics.utils;

import android.widget.Toast;

import com.chuanqi56.logistics.application.MyApplication;

public class ToastUtils {
	public static boolean isDebuggable() {
		// return BuildConfig.DEBUG;
		// 调试、测试时，给开发、测试人员的提示信息；正式发布上线时，改为false
		return false;
	}

//	@Deprecated
//	public static void debugToast(Context context, int ret, String message) {
//		if (isDebuggable()) {
//			Toast.makeText(context, "测试提示：[" + ret + "]" + message, Toast.LENGTH_SHORT).show();
//		}
//	}

	private static String oldMsg;
	protected static Toast toast = null;
	private static long oneTime = 0;
	private static long twoTime = 0;

	public static void showToast(String s) {
		if (toast == null) {
			toast = Toast.makeText(MyApplication.getApplication(), s, Toast.LENGTH_SHORT);
			toast.show();
			oneTime = System.currentTimeMillis();
		} else {
			twoTime = System.currentTimeMillis();
			if (s.equals(oldMsg)) {
				if (twoTime - oneTime > Toast.LENGTH_SHORT) {
					toast.show();
				}
			} else {
				oldMsg = s;
				toast.setText(s);
				toast.show();
			}
		}
		oneTime = twoTime;
	}

	public static void showToast(int resId) {
		showToast(MyApplication.getApplication().getString(resId));
	}

}
