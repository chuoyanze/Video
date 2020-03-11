package com.probie.video.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.probie.video.R;
import com.probie.video.base.BaseActivity;

public class PlayActivity extends BaseActivity {


    /**启动这个Activity的Intent
     * @param context
     * @return
     */
    public static Intent createIntent(Context context) {
        return new Intent(context, PlayActivity.class);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_play);

    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }
}
