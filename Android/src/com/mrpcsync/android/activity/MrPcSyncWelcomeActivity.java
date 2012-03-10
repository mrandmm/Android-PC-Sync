package com.mrpcsync.android.activity;

import com.mrpcsync.android.data.message.MrPcSyncMessage;

import android.app.Activity;
import android.os.Bundle;

public class MrPcSyncWelcomeActivity extends Activity{
	MrPcSyncMessage msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.main);
        msg = MrPcSyncMessage.Instance();
        
        
        msg.sendMessage("10086", "hf");
    }
    
}