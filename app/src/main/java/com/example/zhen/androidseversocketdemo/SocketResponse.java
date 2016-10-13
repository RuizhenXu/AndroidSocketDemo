package com.example.zhen.androidseversocketdemo;

import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by zhen on 2016/10/11.
 */
public class SocketResponse {
    private Socket socket = null;
    private PrintWriter printWriter = null;
    private static String TAG = "SocketResponse";
    private Map<String, String> httpHeaders = null;
    private String httpVersion;
    private int statusCode;
    private String reasonPhrase;

    {
        httpVersion = "HTTP/1.1";
        statusCode = 200;
        reasonPhrase = "OK";
        httpHeaders = new HashMap<>();
        httpHeaders.put("Cache-Control","private");
        httpHeaders.put("Connection","keep-alive");
//        httpHeaders.put("Content-Encoding","gzip");
        httpHeaders.put("Content-type","text/html; charset=utf-8");
        httpHeaders.put("Date",getRFC822());
    }

    public SocketResponse(Socket socket){
        this.socket = socket;
        try {
            printWriter = new PrintWriter(new BufferedOutputStream(this.socket.getOutputStream()),true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void response(){
        if (socket != null && socket.isConnected()){
            if (!socket.isOutputShutdown() && printWriter != null){
                printWriter.print(buildResponseHeader());
                printWriter.println();
                printWriter.println(buildResponseBody());
                printWriter.flush();
                printWriter.close();
            }
        }
    }

    public String buildResponseHeader(){
        StringBuilder sb = new StringBuilder();
        sb.append(httpVersion + " " + statusCode + " " + reasonPhrase + "\r\n");
        for (Map.Entry<String,String> httpHeader : httpHeaders.entrySet()){
            sb.append(httpHeader.getKey() + ": "+ httpHeader.getValue() + "\r\n");
        }
        if (SocketLog.isDebug){
            String logMsg = sb.toString().replaceAll("\r\n","\n");
            SocketLog.e(TAG,logMsg);
        }
        return sb.toString();
    }

    public String buildResponseBody() {
        return "<h1>哈哈哈</h1>";
    }

    public String getHttpHeaderValue(String httpHeaderName) {
        return httpHeaders.get(httpHeaderName);
    }

    public SocketResponse setHttpHeaderValue(String httpHeaderName, String httpHeaderValue) {
        httpHeaders.put(httpHeaderName,httpHeaderValue);
        return this;
    }

    public Map<String, String> getHttpHeaders() {
        return httpHeaders;
    }

    public void setHttpHeaders(Map<String, String> httpHeaders) {
        this.httpHeaders = httpHeaders;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }

    public void setReasonPhrase(String reasonPhrase) {
        this.reasonPhrase = reasonPhrase;
    }

    private String getRFC822(){
        DateFormat df = new SimpleDateFormat("EEE,d MMM yyyy hh:mm:ss Z", Locale.ENGLISH);
        String time = df.format(new Date());
        return time + " GMT";
    }
}
