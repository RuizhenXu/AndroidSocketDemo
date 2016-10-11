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
    private boolean isRun = false;
    public MySocketSever(){

    }

    /**
     * 开启socket服务
     */
    public void start(){
        isRun = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                socket = new Socket();
                try {
                    serverSocket = new ServerSocket(8888);
                    Log.e(TAG ,"Server Start...");
                    executorService = Executors.newCachedThreadPool();
                    while (isRun){
                        Log.e(TAG, "Before accept()...");
                        socket = serverSocket.accept();
                        Log.e(TAG, "After accept()...");

                        SocketThread socketThread = new SocketThread(socket);
                        socketThread.setSocketListener(new SocketThread.SocketListener() {
                            @Override
                            public void doSomething(BufferedReader in) throws IOException {
                                while ((msg = in.readLine()) != null){
                                    Log.e(TAG,msg);
                                }
                            }
                        });
                        executorService.execute(socketThread);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 停止socket服务
     */
    public void stop(){
        isRun = false;
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * socket是否开启
     * @return
     */
    public boolean isRun(){
        return  isRun;
    }

}
