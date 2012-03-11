package com.mrpcsync.pc.ui;

import javax.swing.JPanel;

import com.android.ddmlib.IDevice;

public class MrPcSyncWelcomePanel extends JPanel {
	private static MrPcSyncWelcomePanel _instance = null;
	private static IDevice mDevice;

	public IDevice getDevice() {
		return mDevice;
	}

	public void setDevice(IDevice mDevice) {
		this.mDevice = mDevice;
	}

	public MrPcSyncWelcomePanel() {
	}

	public static MrPcSyncWelcomePanel getInstance() {
		if (_instance == null) {
			_instance = new MrPcSyncWelcomePanel();
		}
		return _instance;
	}
}
