package com.mrpcsync.pc.event;

public class ObjectEvent<T> {
	
	public static String RECV_SOCKET = "RECV_SOCKET";
	public static String RECV_DEVICE = "RECV_DEVICE";
	public static String RECV_STRING = "RECV_STRING";
	
	private String object;
	
	public ObjectEvent(String object){
		this.object = object;
	}

	public String getEvent() {
		return object;
	}
}
