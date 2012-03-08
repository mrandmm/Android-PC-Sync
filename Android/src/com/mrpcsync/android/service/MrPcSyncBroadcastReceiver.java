package com.mrpcsync.android.service;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MrPcSyncBroadcastReceiver extends BroadcastReceiver {
    private static String START_ACTION = "MrPcSync.NotifyServiceStart";
    private static String STOP_ACTION = "MrPcSync.NotifyServiceStop";
    
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (START_ACTION.equals(action)){
            context.startService(new Intent(context, MrPcSyncService.class));
            Log.v(START_ACTION, START_ACTION);
        }else if(STOP_ACTION.equals(action)){
            context.stopService(new Intent(context, MrPcSyncService.class));
            Log.v(STOP_ACTION, STOP_ACTION);
        }
    }

}
