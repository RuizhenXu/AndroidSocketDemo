package com.example.zhen.androidseversocketdemo;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * Created by zhen on 2016/10/11.
 */
public class SocketThread implements Runnable{
    private Socket socket;
    private RequestListener requestListener = null;
    public static String TAG = "SocketThread";

    public SocketThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream ips = socket.getInputStream();
//            if (ips.available() == 0){
//                return;
//            }

            //获取http request
            SocketRequest socketRequest = new SocketRequest();
            Socket socket1 = socketRequest.resolveRequest(socket);
            if (requestListener != null){
                requestListener.getRequest(socketRequest);
            }
            //响应
            SocketResponse socketResponse = new SocketResponse(socket1);
            socketResponse.response();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setRequestListener(RequestListener requestListener){
        this.requestListener = requestListener;
    }
    interface RequestListener{
        void getRequest(SocketRequest socketRequest);
    }
}
