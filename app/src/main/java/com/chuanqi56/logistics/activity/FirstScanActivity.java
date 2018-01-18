package com.chuanqi56.logistics.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.chuanqi56.logistics.utils.ActivityUtils;

import com.chuanqi56.logistics.R;

public class FirstScanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
    }

    public void onScan(View view) {
    }

    public void onHomPage(View view) {
        ActivityUtils.openActivity(this, com.chuanqi56.logistics.vassonic.MainActivity.class);
    }

    public void onScanBarCode(View view) {
        ActivityUtils.openActivity(this, ScanerActivity.class);
    }

    public void onScanFace(View view) {
        ActivityUtils.openActivity(this, MainActivity.class);
    }
}
