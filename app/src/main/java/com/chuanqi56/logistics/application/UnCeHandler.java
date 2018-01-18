package com.chuanqi56.logistics.application;

import android.os.Looper;
import android.util.Log;

import com.chuanqi56.logistics.utils.DebugLog;
import com.chuanqi56.logistics.utils.ToastUtils;

/**
 * Created by ycw on 2016/11/1.
 */

public class UnCeHandler implements Thread.UncaughtExceptionHandler {

    private Thread.UncaughtExceptionHandler mDefaultHandler;
    public static  final String TAG = "CatchExcep";
    BaseApplication application;

    public UnCeHandler(BaseApplication application) {
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        this.application = application;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if(!handleException(ex)&&mDefaultHandler!=null){
            //如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread,ex);
        }else{
            try{
                Thread.sleep(2000);
            }catch (InterruptedException e){
                Log.e(TAG,"error:",e);
            }
//            Intent intent = new Intent(application.getApplicationContext(), SplashScreenActivity.class);
//            PendingIntent restartIntent = PendingIntent.getActivity(application.getApplicationContext(),0,intent, PendingIntent.FLAG_UPDATE_CURRENT);
//            AlarmManager mgr = (AlarmManager) application.getSystemService(Context.ALARM_SERVICE);
//            mgr.set(AlarmManager.RTC, System.currentTimeMillis()+1000,restartIntent);
            mDefaultHandler.uncaughtException(thread,ex);
            application.appExit();
        }


    }

    /**
     * 自定义错误处理。收集错误信息 发送错误报告等操作均在此完成
     * @param ex
     * @return
     */
    private boolean handleException(Throwable ex){
        if(ex == null){
            DebugLog.e("Throwable == null");
            return false;
        }
        DebugLog.e(ex.getMessage());
        //使用Toast来显示异常信息
        new Thread(){
            @Override
            public void run() {
                super.run();
                Looper.prepare();
                ToastUtils.showToast("很抱歉程序出现异常，即将退出");
                Looper.loop();
            }
        }.start();
        return true;
    }
}
