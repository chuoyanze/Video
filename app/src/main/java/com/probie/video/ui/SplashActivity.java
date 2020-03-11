package com.probie.video.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

import com.probie.video.R;
import com.probie.video.base.BaseActivity;
import com.probie.video.util.Log;

public class SplashActivity extends BaseActivity {

    private String TAG = "SplashActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e(TAG,"SplashActivity");
        super.onCreate(savedInstanceState);

    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_spalsh);
        Log.e(TAG,"initView");
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                startActivity(HomeActivity.createIntent(SplashActivity.this));
                finish();
            }
        }, 500);

    }

    @Override
    public void initData() {
        Log.e(TAG,"initData");

    }

    @Override
    public void initEvent() {
        Log.e(TAG,"initEvent");

    }

    @Override
    public void finish() {
        super.finish();
    }
}
