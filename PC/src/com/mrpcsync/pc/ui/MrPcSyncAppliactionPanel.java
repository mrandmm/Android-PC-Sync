package com.mrpcsync.pc.ui;

import javax.swing.JPanel;

import com.android.ddmlib.IDevice;

public class MrPcSyncAppliactionPanel extends JPanel {
	private static MrPcSyncCmdPanel _instance = null;
	private IDevice mDevice;

	public IDevice getDevice() {
		return mDevice;
	}

	public void setDevice(IDevice mDevice) {
		this.mDevice = mDevice;
	}

	public MrPcSyncAppliactionPanel() {
	}
}
