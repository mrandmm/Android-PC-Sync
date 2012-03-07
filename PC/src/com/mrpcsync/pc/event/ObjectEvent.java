package com.mrpcsync.pc.event;

public class ObjectEvent<T> {
	
	public static String RECV_SOCKET = "RECV_SOCKET";
	
	private String object;
	
	public ObjectEvent(String object){
		this.object = object;
	}

	public String getEvent() {
		return object;
	}
}
