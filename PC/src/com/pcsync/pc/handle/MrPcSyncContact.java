package com.pcsync.pc.handle;

import com.mrpcsync.pc.data.MrPcSyncAction;
import com.mrpcsync.pc.data.MrPcSyncHead;
import com.mrpcsync.pc.data.MrSyncClient;

public class MrPcSyncContact {
	private static MrPcSyncContact _instance = null;
	private MrSyncClient mClient = new MrSyncClient();
	private MrPcSyncHead mHead = new MrPcSyncHead();

	public static MrPcSyncContact getInstance() {
		if (_instance == null) {
			_instance = new MrPcSyncContact();
		}
		return _instance;
	}

	public void addContact(String name, String phone) {
		try {
			mHead.setHead(MrPcSyncAction.NEW_ADD_CONTACT);
			mHead.setMsg(name + "#" + phone);
			mClient.send(mHead);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteContact(String name, String phone) {
		try {
			mHead.setHead(MrPcSyncAction.DEL_ONE_CONTACT);
			mHead.setMsg(name + "#" + phone);
			mClient.send(mHead);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getContact() {
		try {
			mHead.setHead(MrPcSyncAction.GET_ALL_CONTACT);
			mClient.send(mHead);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
