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
//            BufferedReader in = new BufferedReader(new InputStreamReader(ips));
            SocketRequest socketRequest = new SocketRequest();
            Socket socket1 = socketRequest.resolveRequest(socket);
            if (requestListener != null){
                requestListener.getRequest(socketRequest);
            }
//            boolean isFirst = true;
//            while ((line = in.readLine()) != null){
//                //读到""时候，为http头的结束,跳出while，防止阻塞
//                if ("".equals(line)){
//                    break;
//                }
//                if (isFirst){
//                    //http请求第一行为请求的方法和http版本
//                    String[] attr = line.split(" ");
//                    socketRequset.setMethod(attr[0]);
//                    socketRequset.setHttpParams(attr[1]);
//                    socketRequset.setHttpVersion(attr[2]);
//                }else {
//                    String[] attr = line.split(": ");
//                    socketRequset.addHttpHead(attr[0], attr[1]);
//                }
//                isFirst = false;
//                Log.e(TAG,line);
//            }
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
