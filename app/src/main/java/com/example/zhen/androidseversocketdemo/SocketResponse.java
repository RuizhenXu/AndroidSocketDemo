package com.example.zhen.androidseversocketdemo;

import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by zhen on 2016/10/11.
 */
public class SocketResponse {
    private Socket socket = null;
    private PrintWriter printWriter = null;
    private static String TAG = "SocketResponse";
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
                Log.e(TAG,"printWriter----start");
                printWriter.println("HTTP/1.1 200 OK");
                printWriter.println("Content-Type: text/html");
                printWriter.println();
                printWriter.println("aaaa");
                printWriter.flush();
                printWriter.close();
                Log.e(TAG,"printWriter----end");
            }
        }
    }
}
