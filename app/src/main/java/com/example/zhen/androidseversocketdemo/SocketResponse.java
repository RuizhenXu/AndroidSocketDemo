package com.example.zhen.androidseversocketdemo;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by zhen on 2016/10/11.
 */
public class SocketResponse {
    private Socket socket = null;
    private PrintWriter printWriter = null;

    public SocketResponse(Socket socket){
        this.socket = socket;
        try {
            printWriter = new PrintWriter(new BufferedOutputStream(new BufferedOutputStream(this.socket.getOutputStream())),true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void response(){
        if (socket != null && socket.isConnected()){
            if (!socket.isOutputShutdown() && printWriter != null){
                printWriter.println("HTTP/1.1 200 OK");
                printWriter.println("Content-Type: text/html");
                printWriter.println("Content-Type:text/html;charset:GBK");
                printWriter.println();
                printWriter.println("aaaa");
                printWriter.flush();
                printWriter.close();
            }
        }
    }
}
