package com.example.zhen.androidseversocketdemo;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhen on 2016/10/10.
 */
public class MySocketSever {

    public static String TAG = "MySeverSocket";
    public ServerSocket serverSocket = null;
    public Socket socket = null;
    public ExecutorService executorService;
    public String msg;

    public MySocketSever(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                socket = new Socket();
                try {
                    serverSocket = new ServerSocket(8888);
                    Log.e(TAG ,"Server Start...");
                    executorService = Executors.newCachedThreadPool();
                    while (true){
                        Log.e(TAG ,"Before accept()...");
                        socket = serverSocket.accept();
                        Log.e(TAG ,"After accept()...");
                        executorService.execute(new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    InputStream ips = socket.getInputStream();
                                    BufferedReader in = new BufferedReader(new InputStreamReader(ips));
                                    while ((msg = in.readLine()) != null){
                                        Log.e(TAG,msg);
                                    }
                                    in.close();
                                    Log.e(TAG,in.toString());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

    public void start(){

    }

    public void stop(){

    }
}
