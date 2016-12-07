package com.humanheima.blurimgdemo;

import android.app.Application;

import com.wonderkiln.blurkit.BlurKit;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        BlurKit.init(this);
    }
}