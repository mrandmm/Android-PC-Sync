package com.mrpcsync.android.data.message;


import java.util.Iterator;
import java.util.List;

import android.telephony.SmsManager;
public class MrPcSyncMessage {
    private static MrPcSyncMessage _instance = null;
    private SmsManager mSms;

    private MrPcSyncMessage() {
    	mSms = SmsManager.getDefault();
    }

    public static MrPcSyncMessage Instance() {
        if (_instance == null) {
            _instance = new MrPcSyncMessage();
        }
        return _instance;
    }
    
    public void sendMessage(String phone, String message){
    	List<String> msg = mSms.divideMessage(message);
    	Iterator<String> itor = msg.iterator();
    	while (itor.hasNext()){
    		mSms.sendTextMessage(phone, null, itor.next(), null, null);
    	}
    }
}
