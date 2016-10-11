package com.example.zhen.androidseversocketdemo;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

/**
 * Created by zhen on 2016/10/11.
 */
public class Utils {





    /**
     * 获取手机IP地址
     * @return
     */
    public static String getIP(Context context){
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();

        return intToIP(wifiInfo.getIpAddress());
    }

    /**
     * int的ip转换成ip形式
     * @param i
     * @return
     */
    private static String intToIP(int i){
        return (i & 0xFF ) + "." + ((i >> 8 ) & 0xFF) + "." + ((i >> 16 ) & 0xFF) + "." + ( i >> 24 & 0xFF) ;
    }
}
