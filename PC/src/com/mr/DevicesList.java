package com.mr;

import javax.swing.JList;

import com.android.ddmlib.AndroidDebugBridge;
import com.android.ddmlib.IDevice;
import com.mrpcsync.pc.data.control.Controller;

public class DevicesList implements Runnable{
	private static DevicesList _instance = null;
	private IDevice[] mDevices;
	private Thread mThread = new Thread(this);
	private DevicesList(){
		AndroidDebugBridge.init(false);
	}
	
	public void updateDevices(){
		try {
			mThread = null;
			if (mThread==null){
				mThread = new Thread(this);
			}
			mThread.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public IDevice[] getDevices(){
		return mDevices;
	}
	
	public static DevicesList getInstance(){
		if (_instance==null){
			_instance = new DevicesList();
		}
		return _instance;
	}

	@Override
	public void run() {
		AndroidDebugBridge bridge = AndroidDebugBridge.createBridge("adb", true);
		int count = 0;
		while (bridge.hasInitialDeviceList()==false) {
			try{
				Thread.sleep(500);
				count++;
			}catch (Exception e){
				e.printStackTrace();
			}
			if (count>60){
				break;
			}
		}
		mDevices = bridge.getDevices();
		((JList)(Controller.getInstance().getController("list_devices"))).setListData(mDevices);
	}
	
	
}
