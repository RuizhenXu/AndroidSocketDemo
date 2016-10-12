package com.example.zhen.androidseversocketdemo;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhen on 2016/10/12.
 */
public class SocketRequest {
    private Map<String,String> httpHead;
    private String httpBody;
    private String method;      //请求方法
    private String httpVersion; //http版本
    private String httpParams;  //请求参数


    private Socket mSocket;
    private InputStream is = null;
    private boolean isFirst = true;
    private String line;

    private static String TAG = "SocketRequset";

    public SocketRequest(){
    }

    public void setSocket(Socket mSocket) {
        this.mSocket = mSocket;
        try {
            resolveRequest(mSocket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket resolveRequest(Socket mSocket) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
        while ((line = br.readLine()) != null){
            //读到""时候，为http头的结束,跳出while，防止阻塞
            if ("".equals(line)){
                break;
            }
            if (isFirst){
                //http请求第一行为请求的方法和http版本
                String[] attr = line.split(" ");
                setMethod(attr[0]);
                setHttpParams(attr[1]);
                setHttpVersion(attr[2]);
            }else {
                String[] attr = line.split(": ");
                addHttpHead(attr[0], attr[1]);
            }
            isFirst = false;
            Log.e(TAG, line);
        }
//        br.close();
        mSocket.shutdownInput();
        return mSocket;
    }

    public void addHttpHead(String headName,String headValue){
        if (httpHead == null){
            this.httpHead = new HashMap<>();
        }
        httpHead.put(headName,headValue);
    }

    public String getHttpHeadValue(String headName) throws Exception {
        if (httpHead == null){
            throw new Exception();
        }else {
            return httpHead.get(headName);
        }
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getHttpParams() {
        return httpParams;
    }

    public void setHttpParams(String httpParams) {
        this.httpParams = httpParams;
    }
}
