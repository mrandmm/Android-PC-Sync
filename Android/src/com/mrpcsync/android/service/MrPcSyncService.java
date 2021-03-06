package com.mrpcsync.android.service;

import com.mrpcsync.android.activity.MrPcSyncUsbActivity;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MrPcSyncService extends Service{
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        
        Intent intt = new Intent();
        intt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intt.setClass(this, MrPcSyncUsbActivity.class);
        startActivity(intt);

        new MrPcSyncTCPServer(this).start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
