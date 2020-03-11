package com.probie.video.application;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * @author ：probie
 * @date ：Created in 2020/3/10 15:56
 * @description：
 * @modified By：
 * @version: $
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
