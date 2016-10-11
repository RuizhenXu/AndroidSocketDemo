package com.example.zhen.androidseversocketdemo;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Created by zhen on 2016/10/11.
 */
public class SocketThread implements Runnable{
    private Socket socket;
    private String msg;
    public static String TAG = "SocketThread";
    private SocketListener socketListener;

    public SocketThread(Socket socket){
        this.socket = socket;
    }

    public void setSocketListener(SocketListener socketListener){
        this.socketListener = socketListener;
    }

    @Override
    public void run() {
        try {
            InputStream ips = socket.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(ips));

            SocketResponse socketResponse = new SocketResponse(socket);
            socketResponse.response();
            Log.e(TAG, "====");
            if (socketListener != null){
                socketListener.doSomething(in);
            }

            in.close();
            Log.e(TAG,in.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    interface SocketListener {
        void doSomething(BufferedReader in) throws IOException;
    }
}
