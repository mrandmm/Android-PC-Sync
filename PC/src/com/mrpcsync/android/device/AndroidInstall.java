package com.mrpcsync.android.device;

import com.android.ddmlib.IDevice;

public class AndroidInstall {
    private IDevice mDevice;
    private static AndroidInstall _instance = null;
    
    public static AndroidInstall getInstance() {
        if (_instance == null) {
            _instance = new AndroidInstall();
        }
        return _instance;
    }

    public void setDevices(IDevice device) {
        mDevice = device;
    }

    public String install(String pack) {
        String buf = "Fail Device";
        try {
            if (mDevice!=null){
                buf = mDevice.installPackage(pack, true, "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buf;
    }
    
    public String unstall(String pack){
        String buf = "";
        try {
            buf = mDevice.uninstallPackage(pack);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buf;
    }
}
