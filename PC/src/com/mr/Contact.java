package com.mr;

import com.android.ddmlib.IDevice;
import com.android.ddmlib.SyncService;

public class Contact {
	private static Contact _instance = null;
	private IDevice mDevice;
	private MrSyncClient mClient = new MrSyncClient();
	private String cmd = "MrSync:add_new_contact";
	private MrPcSyncHead mHead = new MrPcSyncHead();
	
	public static Contact getInstance() {
		if (_instance==null){
			_instance = new Contact();
		}
		return _instance;
	}
	
	public void setDevice(IDevice device){
		if (device!=null){
			mDevice = device;
		}
	}
	
	public void addContact(String name, String phone){
		mHead.setHead(cmd);
		mHead.setMsg(name+"/"+phone);
		mClient.send(mHead.getBuffer());
	}
	
	public void getContact(){
		try {
			if (mDevice!=null){
				mClient.start();
				mClient.client();
			}
		} catch (Exception e) {
			return;
		} 
	}
	
}
