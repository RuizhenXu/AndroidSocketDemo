package com.example.zhen.androidseversocketdemo;

import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by zhen on 2016/10/10.
 */
public class MySocketClient {
    private Socket socket = null;
    private PrintStream ps = null;
    private static String TAG = "MySocketClient";
    public MySocketClient(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    socket = new Socket("127.0.0.1",8888);
                    ps = new PrintStream(new BufferedOutputStream(new BufferedOutputStream(socket.getOutputStream())),true);
                    Log.e(TAG,"SocketClient Start...");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void sendMsg(String msg){
        if (socket != null && socket.isConnected()){
            if (!socket.isOutputShutdown() && ps != null){
                ps.println(msg);
                Log.e(TAG, "Send Msg : " + msg);
            }
        }
    }

}
