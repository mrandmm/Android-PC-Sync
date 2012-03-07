package com.mrpcsync.pc.ui.control;


import com.mrpcsync.pc.data.MrSyncClient;
import com.mrpcsync.pc.event.EventDispatcher;
import com.mrpcsync.pc.event.ObjectEvent;
import com.mrpcsync.pc.event.OnSocketListener;

public class DataModeEventDispatcher extends EventDispatcher implements OnSocketListener {
	private static DataModeEventDispatcher _instance = null;
	private MrSyncClient mClient = MrSyncClient.getInstance();

	public DataModeEventDispatcher(){
		addEventListener(ObjectEvent.RECV_SOCKET, this);
	}

	public static DataModeEventDispatcher getInstance() {
		if (_instance==null){
			_instance = new DataModeEventDispatcher();
		}
		return _instance;
	}


	@Override
	public void onRecv(ObjectEvent<?> event) {
	}

}
