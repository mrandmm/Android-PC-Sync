package com.mrpcsync.pc.event;

public interface IEventDispatcher {
    void addEventListener(String type, OnRecvListener listener);
    void removeListener(String type, OnRecvListener listener);
    void Notify();
	void dispatchEvent(ObjectEvent<?> event);
}
