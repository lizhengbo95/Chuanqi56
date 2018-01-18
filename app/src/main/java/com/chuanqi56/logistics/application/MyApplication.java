package com.chuanqi56.logistics.application;

import com.chuanqi56.logistics.BuildConfig;
import com.chuanqi56.logistics.utils.DebugLog;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.beta.Beta;


public class MyApplication extends BaseApplication {

	/**
	 * 打包后自动设置为false关闭
	 * 
	 * @return
	 */
	public static boolean isDebuggable() {
		return BuildConfig.DEBUG;
	}

	public static MyApplication myApplication;

	public static MyApplication getApplication(){
		return myApplication;
	}

	/**
	 *
	 */
	@Override
	public void onCreate() {
		super.onCreate();
		myApplication = this;
		init();
//		TalkingDataUtils.init(this);
//		LeakCanary.install(this);
		initBugly();

		// 极光推送
//		JPushInterface.setDebugMode(isDebuggable()); // 设置开启日志,发布时请关闭日志
//		JPushInterface.init(this); // 初始化 JPush
	}

	@Override
	protected String getAppNameFromSub() {
		return null;
	}

	@Override
	protected void onAppExit() {
		appExit();
	}

	private void initBugly(){
		DebugLog.e("腾讯Bugly");
		Beta.autoCheckUpgrade = true;
		BuglyStrategy buglyStrategy = new BuglyStrategy();
		if(BuildConfig.LOG_DEBUG){
			Bugly.init(getApplicationContext(), "a95ff7b2c0", true ,buglyStrategy);//测试环境，初始化统一接口
		}else{
			Bugly.init(getApplicationContext(), "a95ff7b2c0", true ,buglyStrategy);//测试环境，初始化统一接口
		}
	}
}
