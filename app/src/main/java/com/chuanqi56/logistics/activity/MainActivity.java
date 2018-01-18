package com.chuanqi56.logistics.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.chuanqi56.logistics.R;

//import com.chuanqi56.logistics.baidu.activities.FaceActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_scan);
    }

//    public void onScan(View view) {
//        ActivityUtils.openActivity(this, FaceActivity.class);
//    }
}
