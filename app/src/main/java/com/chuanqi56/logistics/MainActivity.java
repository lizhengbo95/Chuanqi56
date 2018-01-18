package com.chuanqi56.logistics;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.chuanqi56.logistics.activity.ScanerActivity;
import com.chuanqi56.logistics.utils.ActivityUtils;
import com.chuanqi56.logistics.utils.DebugLog;
import com.chuanqi56.logistics.utils.ToastUtils;

import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;

        if(Build.VERSION.SDK_INT >= 23){//android6.0以上的版本
            DebugLog.e("android 6.0以上");
            if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    &&ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                    &&ContextCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                    ) {
                //拥有权限
                DebugLog.e("以获得所有与权限");
//                appInit = new AppInit();
//                initApp();
            } else {
                DebugLog.e("部分权限未获取");
                ActivityCompat.requestPermissions((Activity) mContext,
                        new String[] {Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.CAMERA}, REQUEST_CODE_REQUEST_PERMISSION);
            }
        }else{
            DebugLog.e("android 6.0以下");
//            appInit = new AppInit();
//            initApp();
        }
    }

    private int REQUEST_CODE_REQUEST_PERMISSION = 100;

    @Override
    public void onRequestPermissionsResult(int requestCode,  String[] permissions,  int[] grantResults) {
        if (requestCode == REQUEST_CODE_REQUEST_PERMISSION) {
            boolean isGrant =  true;//申请的所有权限是否都已授权
            for(int i = 0;i<grantResults.length;i++){
                if(grantResults[0] != PackageManager.PERMISSION_GRANTED){
                    isGrant = false;
                }
            }
            if(isGrant){
                DebugLog.e("返回结果：申请权限已全部授权成功");
//                appInit = new AppInit();
//                initApp();
            }else {
                String message = "返回结果：申请权限未全部授权成功";
                DebugLog.e(message);
//                AndPermission.defaultSettingDialog((Activity) mContext, 100)
//                        .setTitle("权限申请失败")
////                                    .setMessage("定位权限，存储权限被您拒绝或者系统申请失败，请您到设置页面手动授权，否则不能为您提供")
//                        .setMessage("为了您能正常使用U掌柜，需要定位权限、存储权限，请到设置页设置")
//                        .setPositiveButton("好，去设置")
//                        .show();
                ToastUtils.showToast("必须打开权限");
            }
        }
    }

    @OnClick
    public void onScan(View v){
        ActivityUtils.openActivity(this, ScanerActivity.class);
    }
    @OnClick
    public void onH5(View v){
        ActivityUtils.openActivity(this, com.chuanqi56.logistics.vassonic.MainActivity.class);
    }
}
