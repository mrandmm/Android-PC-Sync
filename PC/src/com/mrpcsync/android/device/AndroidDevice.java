package com.mrpcsync.android.device;

import com.android.ddmlib.IDevice;
import com.mrpcsync.pc.event.ObjectEvent;

public class AndroidDevice extends ObjectEvent<AndroidDevice>{
    private IDevice[] mDevices;
    
    public AndroidDevice() {
        super(RECV_DEVICE);
    }
    
    public AndroidDevice(IDevice[] devices) {
    	super(RECV_DEVICE);
    	setDevices(devices);
	}

	public void setDevices(IDevice[] device){
        mDevices = device;
    }
    
    public IDevice[] getDevices(){
        return mDevices;
    }

}
