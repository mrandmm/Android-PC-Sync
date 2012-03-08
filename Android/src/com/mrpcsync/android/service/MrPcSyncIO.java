package com.mrpcsync.android.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.net.Socket;

import com.mrpcsync.android.data.MrPcSyncAction;
import com.mrpcsync.android.data.MrPcSyncHead;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MrPcSyncIO extends Thread {
    private Socket mSocket;
    private BufferedOutputStream out;
    private BufferedInputStream in;
    private MrPcSyncHead mHead;
    public Context mContext;

    public MrPcSyncIO(Context context, Socket socket) {
        mContext = context;
        mSocket = socket;
        mHead = new MrPcSyncHead();
    }


    @Override
    public void run() {
        try {
            out = new BufferedOutputStream(mSocket.getOutputStream());
            in = new BufferedInputStream(mSocket.getInputStream());
            byte[] buffer = new byte[MrPcSyncHead.getLength()];
            while (true) {
                if (!mSocket.isConnected()){
                    break;
                }
                if (-1==in.read(buffer)){
                    break;
                }
                mHead.setReadBuffer(buffer);
                doAction(mHead.getHeadString());
            }
            in.close();
            out.close();
            mSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void doAction(String cmd){
        Log.v("MOURUI", cmd);
        if ("NEW_ADD_CONTACT".equals(cmd)){
            Intent intent = new Intent();
            intent.setAction(MrPcSyncAction.NEW_ADD_CONTACT);
            intent.putExtra("name", mHead.getMsgCmd()[0]);
            intent.putExtra("phone", mHead.getMsgCmd()[1]);
            mContext.sendBroadcast(intent);
            Log.v("MOURUI", "sendBroadcast="+mHead.getMsgCmd()[0]+"+"+mHead.getMsgCmd()[1]);
        }
    }


    @Override
    public void destroy() {
        super.destroy();
        mHead = null;
    }

}
