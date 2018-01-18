package com.chuanqi56.logistics;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ycw on 2016/8/24.
 */
public class BaseActivity extends AppCompatActivity {
//    protected ProgressDialog progressDialog;
//    protected MaterialDialog materialDialog;

    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = this;
        initDialog();
    }
    @Override
    protected void onResume() {
        super.onResume();
//        TalkingDataUtils.onResume(this);
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
//        TalkingDataUtils.onPause(this);
    }
    private void initDialog(){
//        progressDialog =  new ProgressDialog(this);
//        materialDialog = new MaterialDialog(this);
//        materialDialog.setPositiveButton("确定", v -> {
//            materialDialog.dismiss();
//        });
    }

    protected void showDialog(String msg){
//        materialDialog.setMessage(msg);
//        materialDialog.show();
    }
    protected void dismissDialog(){
//        materialDialog.dismiss();
    }
    /**
     *显示进度条
     */
    protected void showProgressDialog(){
//        if(!progressDialog.isShowing()){
//            progressDialog.show();
//        }
    }
    /**
     * 关闭进度条
     */
    protected void dismissProgressDialog(){
//        progressDialog.dismiss();
    }
}
