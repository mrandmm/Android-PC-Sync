package com.mr;

import java.net.ServerSocket;
import java.net.Socket;

import android.content.Context;
import android.util.Log;

public class MrPcSyncTCPServer extends Thread{
    private ServerSocket mSocket;
    private int mServerPort = 2589;
    public Context mContext;
    
    public MrPcSyncTCPServer(Context context){
        mContext = context;
    }
    @Override
    public void run() {
        mSocket = null;
        try{
            mSocket = new ServerSocket(mServerPort);
            while (true){
                Socket mClient = mSocket.accept();
                if (mClient!=null){
                    new MrPcSyncIO(mContext, mClient).start();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
