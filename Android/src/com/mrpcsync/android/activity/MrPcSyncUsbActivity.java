package com.mrpcsync.android.activity;

import com.mrpcsync.android.data.MrPcSyncAction;
import com.mrpcsync.android.data.message.MrPcSyncMessage;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

public class  MrPcSyncUsbActivity extends Activity {
    
    //private MrPcSyncContact mContacts;
   // private MrPcSyncContact.Contact mContact;
	  private MrPcSyncMessage mMessage;
    
    private CmdBroadcastReceiver mReceiver;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usb_sync);
        
        //mContacts = MrPcSyncContact.Instance(this);
        //mContact = new MrPcSyncContact.Contact();
        mMessage = MrPcSyncMessage.Instance();
    
        IntentFilter filter =  new IntentFilter();
        mReceiver = new CmdBroadcastReceiver();
        filter.addAction(MrPcSyncAction.NEW_ADD_CONTACT);
        filter.addAction(MrPcSyncAction.NEW_ADD_MESSAGE);
        filter.addAction(MrPcSyncAction.CLOSE);
        this.registerReceiver(mReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.unregisterReceiver(mReceiver);
    }
    
    class CmdBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.v("MOURUI", action);
            if (MrPcSyncAction.NEW_ADD_CONTACT.equals(action)){
                //mContact.setName(intent.getStringExtra("name"));
                //mContact.setPhone(intent.getStringExtra("phone"));
                //mContacts.insertContact(mContact);
            }else if(MrPcSyncAction.CLOSE.endsWith(action)){
                finish();
            }else if(MrPcSyncAction.NEW_ADD_MESSAGE.equals(action)){
            	mMessage.sendMessage(intent.getStringExtra("phone"), intent.getStringExtra("message"));
            }
        }
        
    }
}
