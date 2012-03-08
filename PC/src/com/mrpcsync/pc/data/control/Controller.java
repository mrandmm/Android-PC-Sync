package com.mrpcsync.pc.data.control;

import java.util.HashMap;

public class Controller {
	private static Controller _instance = null;
	private HashMap<String, Object> mController;
	
	private Controller(){
		mController = new HashMap<String, Object>();
	}
	
	public static Controller getInstance() {
		if (_instance==null){
			_instance = new Controller();
		}
		return _instance;
	}
	
	public void addController(String key, Object control){
		mController.put(key, control);
	}
	
	public Object getController(String key){
		return mController.get(key);
	}
}
