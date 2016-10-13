package com.example.zhen.androidseversocketdemo;

import android.util.Log;

/**
 * Created by zhen on 2016/10/13.
 */
public final class SocketLog{
    public static boolean isDebug = false;

    private SocketLog(){

    }

    public static void e(String tag, String msg){
        if (isDebug) Log.e(tag,msg);
    }

    public static void d(String tag, String msg){
        if (isDebug) Log.d(tag, msg);
    }

    public static void v(String tag, String msg){
        if (isDebug) Log.v(tag, msg);
    }

    public static void i(String tag, String msg){
        if (isDebug) Log.i(tag, msg);
    }

    public static void w(String tag, String msg){
        if (isDebug) Log.w(tag, msg);
    }
}
