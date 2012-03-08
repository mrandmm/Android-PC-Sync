package com.mrpcsync.pc.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EventDispatcher implements IEventDispatcher{
	private static ObjectEvent<?> evt;
    private static List<HashMap<String, OnRecvListener>> onList = new ArrayList<HashMap<String, OnRecvListener>>();
    
	@Override
	public void addEventListener(String type, OnRecvListener listener) {
		HashMap<String, OnRecvListener> map = new HashMap<String, OnRecvListener>();
        map.put(type, listener);
        onList.add(map);
	}

	@Override
	public void removeListener(String type, OnRecvListener listener) {
		HashMap<String, OnRecvListener> map = new HashMap<String, OnRecvListener>();
        map.put(type, listener);
        onList.remove(map);
	}

	@Override
	public void dispatchEvent(ObjectEvent<?> event) {
		evt = event;
        Notify();
	}

	@Override
	public void Notify() {
        for (int i=0; i<onList.size(); i++){
            if (onList.get(i).containsKey(evt.getEvent())){
            	onList.get(i).get(evt.getEvent()).onRecv(evt);
            }
        }
	}

}

