package com.mrpcsync.pc.event;

public interface IEventDispatcher {
    void addEventListener(String type, OnSocketListener listener);
    void removeListener(String type, OnSocketListener listener);
    void Notify();
	void dispatchEvent(ObjectEvent<?> event);
}
