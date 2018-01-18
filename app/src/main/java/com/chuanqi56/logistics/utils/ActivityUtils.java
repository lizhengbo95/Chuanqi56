package com.chuanqi56.logistics.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.chuanqi56.logistics.application.MyApplication;

public class ActivityUtils {

	public static void openActivity(Context context, Class<?> pClass) {
		openActivity(context, pClass, null);
	}

	public static void openActivity(Context context, Class<?> pClass, Bundle pBundle) {
		Intent intent = new Intent(context, pClass);
		if (pBundle != null) {
			intent.putExtras(pBundle);
		}
		context.startActivity(intent);
	}

	public static void openActivity(Context context, String pAction) {
		openActivity(context, pAction, null);
	}


	public static void openActivity(Class<?> pClass) {
		Intent intent = new Intent();
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setClass(MyApplication.getContext(), pClass);
		MyApplication.getApplication().startActivity(intent);
	}



	public static void openActivity(Context context, String pAction, Bundle pBundle) {
		Intent intent = new Intent(pAction);
		if (pBundle != null) {
			intent.putExtras(pBundle);
		}
		context.startActivity(intent);
	}

	public static void openActivityForResult(Context context, Class<?> pClass, int requestCode) {
		openActivityForResult(context, pClass, requestCode, null);
	}

	public static void openActivityForResult(Context context, Class<?> pClass, int requestCode, Bundle pBundle) {
		Intent intent = new Intent(context, pClass);
		if (pBundle != null) {
			intent.putExtras(pBundle);
		}
		((Activity) context).startActivityForResult(intent, requestCode);
	}

	/**
	 * 将fragment添加到指定id的容器中
	 */
	public static void addFragmentToActivity (@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, int frameId){
//		FragmentTransaction transaction = fragmentManager.beginTransaction();
//		transaction.add(frameId, fragment);
//		transaction.commit();
		if (fragment.isAdded()) {
			fragmentManager.beginTransaction().show(fragment).commit();
		} else {
			FragmentTransaction transaction = fragmentManager.beginTransaction();
			transaction.add(frameId, fragment);
			transaction.commit();
		}
	}

}
