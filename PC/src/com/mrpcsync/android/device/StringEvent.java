package com.mrpcsync.android.device;

import com.mrpcsync.pc.event.ObjectEvent;

public class StringEvent extends ObjectEvent<StringEvent> {

	private String buffer;

	public StringEvent() {
		super(ObjectEvent.RECV_STRING);
	}

	public String getBuffer() {
		return buffer;
	}

	public void setBuffer(byte[] buff) {
		try {
			buffer = new String(buff, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
