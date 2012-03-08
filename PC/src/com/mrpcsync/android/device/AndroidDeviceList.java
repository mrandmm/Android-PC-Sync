package com.mrpcsync.android.device;

import com.android.ddmlib.AndroidDebugBridge;
import com.mrpcsync.pc.event.EventDispatcher;

public class AndroidDeviceList extends EventDispatcher implements Runnable {
    private static AndroidDeviceList _instance = null;
    private AndroidDebugBridge mBridge = null;

    public static AndroidDeviceList getInstance() {
        if (_instance == null) {
            _instance = new AndroidDeviceList();
        }
        return _instance;
    }

    private AndroidDeviceList() {
        AndroidDebugBridge.init(false);
        mBridge = AndroidDebugBridge.createBridge("adb", true);
    }
    
    public void start(){
        new Thread(this).start();
    }

    @Override
    public void run() {
        int count = 0;
        while (!mBridge.hasInitialDeviceList()) {
            try {
                Thread.sleep(500);
                count++;
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (count > 30) {
                break;
            }
        }
        AndroidDevice devices = new AndroidDevice();
        devices.setDevices(mBridge.getDevices());
        this.dispatchEvent(devices);
        AndroidDebugBridge.disconnectBridge();
    }

}