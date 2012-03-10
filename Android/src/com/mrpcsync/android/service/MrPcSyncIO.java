package com.mrpcsync.android.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.mrpcsync.android.data.MrPcSyncAction;
import com.mrpcsync.android.data.MrPcSyncHead;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

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
                if (!mSocket.isConnected()) {
                    break;
                }
                if (-1 == in.read(buffer)) {
                    break;
                }
                mHead.setReadBuffer(buffer);
                doAction(mHead.getHeadString());
            }
            in.close();
            out.close();
            mSocket.close();
            doClose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doClose() {
        Intent intent = new Intent();
        intent.setAction(MrPcSyncAction.CLOSE);
        mContext.sendBroadcast(intent);
    }

    private void doAction(String cmd) {
        if (MrPcSyncAction.NEW_ADD_CONTACT.equals(cmd)) {
            Intent intent = new Intent();
            intent.setAction(MrPcSyncAction.NEW_ADD_CONTACT);
            intent.putExtra("name", mHead.getMsgCmd()[0]);
            intent.putExtra("phone", mHead.getMsgCmd()[1]);
            mContext.sendBroadcast(intent);
        } else if (MrPcSyncAction.NEW_ADD_MESSAGE.equals(cmd)) {
            Intent intent = new Intent();
            intent.setAction(MrPcSyncAction.NEW_ADD_CONTACT);
            intent.putExtra("phone", mHead.getMsgCmd()[0]);
            intent.putExtra("message", mHead.getMsgCmd()[1]);
            mContext.sendBroadcast(intent);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        mHead = null;
    }
    
    class recvMessgae extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (MrPcSyncAction.RECV_NEW_MESSAGE.equals(action)){
				
				Bundle bundle = intent.getExtras();
				if (bundle!=null){
					StringBuilder builder = new StringBuilder();
					Object[] _pdus = (Object[])bundle.get("pdus");
					SmsMessage[] message = new SmsMessage[_pdus.length];
					
					for (int i=0; i<_pdus.length; i++){
						message[i] = SmsMessage.createFromPdu((byte[])_pdus[i]);
					}
					
					for (SmsMessage curmessage : message){
						builder.append(curmessage.getDisplayOriginatingAddress());
						builder.append("#");
						builder.append(curmessage.getDisplayMessageBody());
					}
					
					mHead.setHead(MrPcSyncAction.RECV_NEW_MESSAGE);
					mHead.setMsg(builder.toString());
					
					try {
						out.write(mHead.getBuffer());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
		}
    	
    }

}
