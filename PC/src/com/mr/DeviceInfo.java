package com.mr;

import java.io.IOException;

import com.android.ddmlib.AdbCommandRejectedException;
import com.android.ddmlib.IDevice;
import com.android.ddmlib.ShellCommandUnresponsiveException;
import com.android.ddmlib.SyncException;
import com.android.ddmlib.SyncService.ISyncProgressMonitor;
import com.android.ddmlib.TimeoutException;

public class DeviceInfo implements ISyncProgressMonitor{
	private static DeviceInfo _instance = null;
	private IDevice mDevice;
	
	public static DeviceInfo getInstance() {
		if (_instance==null){
			_instance = new DeviceInfo();
		}
		return _instance;
	}
	
	public String getDeviceInfo(){
		if (mDevice==null){
			return null;
		}
		String info = "";
		try {
			info+="Device:"+(mDevice.isEmulator()?"模拟器":"手机设备"+"\n");
			info+="AvdName:"+mDevice.getAvdName()+"\n";
			info+="Battery:"+mDevice.getBatteryLevel()+"\n";
			info+="State:"+mDevice.getState()+"\n";
			info+="Serial:"+mDevice.getSerialNumber()+"\n";
			//pull();
		} catch (Exception e) {
			return null;
		}
		return info;
	}
	
	public void pull(String source, String target){
		if (mDevice!=null){
			try {
				mDevice.getSyncService().pullFile(source, target, this);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}
	
	public void push(String source, String target){
		if (mDevice!=null){
			try {
				mDevice.getSyncService().pushFile(source, target, this);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}
	
	public void setDevice(IDevice device){
		if (device!=null){
			mDevice = device;
		}
	}

	@Override
	public void advance(int arg0) {
	}

	@Override
	public boolean isCanceled() {
		return false;
	}

	@Override
	public void start(int arg0) {
		
	}

	@Override
	public void startSubTask(String arg0) {
		
	}

	@Override
	public void stop() {
	}
}
