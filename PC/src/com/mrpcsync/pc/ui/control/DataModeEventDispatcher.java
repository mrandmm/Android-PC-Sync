package com.mrpcsync.pc.ui.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.BadLocationException;

import com.android.ddmlib.AndroidDebugBridge;
import com.android.ddmlib.IDevice;
import com.mrpcsync.pc.data.MrPcSyncHead;
import com.mrpcsync.pc.data.MrSyncClient;
import com.mrpcsync.pc.data.control.Controller;
import com.mrpcsync.pc.event.EventDispatcher;
import com.mrpcsync.pc.event.ObjectEvent;
import com.mrpcsync.pc.event.OnRecvListener;
import com.mrpcsync.pc.ui.MrPcSyncCmdPanel;
import com.pcsync.pc.handle.MrPcSyncContact;
import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;

public class DataModeEventDispatcher extends Thread {
    private boolean mInit[] = {false, false, false, false, false};
    private static DataModeEventDispatcher _instance = null;
    private MrPcSyncCmdPanel mCmd = MrPcSyncCmdPanel.getInstance();
    private IDevice mDevice;
    private IDevice[] mDevices;
    private AndroidDebugBridge mBridge = null;
    
    private class mTabbedPaneListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            switch (mTabbedPane.getSelectedIndex()){
            case 0:
                initWelcomePanel();
                break;
            case 1:
                initContactPanel();
                break;
            case 2:
                initMessagePanel();
                break;
            case 3:
                initApplicationPanel();
                break;
            case 4:
            	initCmdPanel();
            	break;
            }
        }

    };
    
    private JTabbedPane mTabbedPane;
    public void registerTabbedPane(){
        mTabbedPane = (JTabbedPane) Controller.getInstance().getController("tabbedPane");
        mTabbedPane.addChangeListener(new mTabbedPaneListener());
        mTabbedPane.setEnabledAt(1, false);
        mTabbedPane.setEnabledAt(2, false);
        mTabbedPane.setEnabledAt(3, false);
        mTabbedPane.setEnabledAt(4, false);
        this.initWelcomePanel();
    }

    public DataModeEventDispatcher() {
    	AndroidDebugBridge.init(false);
        mBridge = AndroidDebugBridge.createBridge("adb", true);
    }

    public static DataModeEventDispatcher getInstance() {
        if (_instance == null) {
            _instance = new DataModeEventDispatcher();
        }
        return _instance;
    }

    public void initWelcomePanel() {
        if(mInit[0]){
            return;
        }
        //
        start();
        System.out.println("WelcomePanel");
        mInit[0] = true;
    }

    public void initContactPanel() {
        if(mInit[1]){
            return;
        }
        System.out.println("ContactPanel");
        mInit[1] = true;
    }

    public void initMessagePanel() {
        if(mInit[2]){
            return;
        }
        System.out.println("MessagePanel");
        mInit[2] = true;
    }

    public void initApplicationPanel() {
        if(mInit[3]){
            return;
        }
        System.out.println("ApplicationPanel");
        mInit[3] = true;
    }
    
    public void initCmdPanel(){
    	if(mInit[4]){
            return;
        }
    	mCmd.setDevice(mDevice);
    	System.out.println("CmdPanel");
        mInit[4] = true;
    }
    
    public void refersh(){
    	start();
    }

	@Override
	public void run() {
        int count = 0;
        while (!mBridge.hasInitialDeviceList()) {
            try {
                Thread.sleep(500);
                count++;
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (count > 30) {
                break;
            }
        }
        mDevices = mBridge.getDevices();
        if (mDevices.length>0){
        	mDevice = mDevices[0];
            mTabbedPane.setEnabledAt(1, true);
            mTabbedPane.setEnabledAt(2, true);
            mTabbedPane.setEnabledAt(3, true);
            mTabbedPane.setEnabledAt(4, true);
        }

	}
}
