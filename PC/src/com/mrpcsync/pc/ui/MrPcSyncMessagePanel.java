package com.mrpcsync.pc.ui;

import javax.swing.JPanel;

import com.android.ddmlib.IDevice;
import com.mrpcsync.pc.data.MrSyncClient;

public class MrPcSyncMessagePanel extends JPanel {
	private static MrPcSyncCmdPanel _instance = null;
	private MrSyncClient mClient = MrSyncClient.getInstance();
	public MrPcSyncMessagePanel() {
	}
}
