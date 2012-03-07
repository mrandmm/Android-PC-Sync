package com.mrpcsync.pc.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EventDispatcher implements IEventDispatcher{
	private static ObjectEvent<?> evt;
    private static List<HashMap<String, OnSocketListener>> onList = new ArrayList<HashMap<String, OnSocketListener>>();
    
	@Override
	public void addEventListener(String type, OnSocketListener listener) {
		HashMap<String, OnSocketListener> map = new HashMap<String, OnSocketListener>();
        map.put(type, listener);
        onList.add(map);
	}

	@Override
	public void removeListener(String type, OnSocketListener listener) {
		HashMap<String, OnSocketListener> map = new HashMap<String, OnSocketListener>();
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

