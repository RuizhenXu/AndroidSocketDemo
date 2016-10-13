package com.example.zhen.androidseversocketdemo;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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

    /**
     * 获取rfc822格式的当前时间
     * @return
     */
    public static String getRFC822(){
        DateFormat df = new SimpleDateFormat("EEE,d MMM yyyy hh:mm:ss Z", Locale.ENGLISH);
        String time = df.format(new Date());
        return time + " GMT";
    }
}
