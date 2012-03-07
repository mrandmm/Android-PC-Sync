package com.mr;

import com.android.ddmlib.IDevice;
import com.android.ddmlib.SyncService;
import com.mrpcsync.pc.data.MrPcSyncHead;
import com.mrpcsync.pc.data.MrSyncClient;

public class Contact {
	private static Contact _instance = null;
	private IDevice mDevice;
	private MrSyncClient mClient = new MrSyncClient();
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
		mHead.setHead(MrPcCMD.NEW_ADD_CONTACT);
		mHead.setMsg(name+"#"+phone);
		//mClient.send(mHead.getBuffer());
	}
}
