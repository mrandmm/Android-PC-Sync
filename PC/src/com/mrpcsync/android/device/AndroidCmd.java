package com.mrpcsync.android.device;

import com.android.ddmlib.IDevice;
import com.android.ddmlib.IShellOutputReceiver;
import com.mrpcsync.pc.event.EventDispatcher;

public class AndroidCmd extends EventDispatcher implements IShellOutputReceiver {
	private IDevice mDevice;

	public IDevice getDevice() {
		return mDevice;
	}

	public void setDevice(IDevice mDevice) {
		this.mDevice = mDevice;
	}

	private static AndroidCmd _instance = null;

	public static AndroidCmd getInstance() {
		if (_instance == null) {
			_instance = new AndroidCmd();
		}
		return _instance;
	}

	public void sendCmd(String cmd) {
		try {
			if (mDevice != null) {
				mDevice.executeShellCommand(cmd, this);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addOutput(byte[] arg0, int arg1, int arg2) {
		StringEvent string = new StringEvent();
		string.setBuffer(arg0);
		this.dispatchEvent(string);
	}

	@Override
	public void flush() {
	}

	@Override
	public boolean isCancelled() {
		return false;
	}
}
