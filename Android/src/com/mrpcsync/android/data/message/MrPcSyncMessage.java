package com.mrpcsync.android.data.message;

import java.util.Iterator;
import java.util.List;

import android.telephony.SmsManager;

public class MrPcSyncMessage {
	private static MrPcSyncMessage _instance = null;
	private SmsManager mSms;

	private MrPcSyncMessage() {
		mSms = SmsManager.getDefault();
	}

	public static MrPcSyncMessage Instance() {
		if (_instance == null) {
			_instance = new MrPcSyncMessage();
		}
		return _instance;
	}

	public void sendMessage(Message message) {
		List<String> msg = mSms.divideMessage(message.getMessage());
		Iterator<String> itor = msg.iterator();
		while (itor.hasNext()) {
			mSms.sendTextMessage(message.getPhone(), null, itor.next(), null,
					null);
		}
	}

	public static class Message {
		private String id;
		private String phone;
		private String message;
		
		public Message(){
			
		}

		public Message(String phone, String message) {
			setPhone(phone);
			setMessage(message);
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
	}
}
