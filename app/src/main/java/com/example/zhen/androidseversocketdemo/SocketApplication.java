package com.example.zhen.androidseversocketdemo;

import android.app.Application;

/**
 * Created by zhen on 2016/10/13.
 */
public class SocketApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SocketLog.isDebug = true;
    }
}
