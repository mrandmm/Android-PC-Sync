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

import com.android.ddmlib.IDevice;
import com.mrpcsync.android.device.AndroidCmd;
import com.mrpcsync.android.device.AndroidDevice;
import com.mrpcsync.android.device.AndroidDeviceList;
import com.mrpcsync.android.device.StringEvent;
import com.mrpcsync.pc.data.MrPcSyncHead;
import com.mrpcsync.pc.data.MrSyncClient;
import com.mrpcsync.pc.data.control.Controller;
import com.mrpcsync.pc.event.EventDispatcher;
import com.mrpcsync.pc.event.ObjectEvent;
import com.mrpcsync.pc.event.OnRecvListener;
import com.pcsync.pc.handle.MrPcSyncContact;
import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;

public class DataModeEventDispatcher extends EventDispatcher implements
        OnRecvListener {
    private boolean mInit[] = {false, false, false, false, false};
    private static DataModeEventDispatcher _instance = null;
    private MrSyncClient mClient = MrSyncClient.getInstance();
    private Controller mController = Controller.getInstance();
    private MrPcSyncContact mContact = MrPcSyncContact.getInstance();

    private JTabbedPane mTabbedPane;
    private AndroidDeviceList mAndroidDeviceList = AndroidDeviceList.getInstance();
    
    

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
    
    public void registerTabbedPane(){
        mTabbedPane = (JTabbedPane) mController.getController("tabbedPane");
        mTabbedPane.addChangeListener(new mTabbedPaneListener());
        this.initWelcomePanel();
    }

    public DataModeEventDispatcher() {
        addEventListener(ObjectEvent.RECV_SOCKET, this);
        addEventListener(ObjectEvent.RECV_DEVICE, this);
        addEventListener(ObjectEvent.RECV_STRING, this);
    }

    public static DataModeEventDispatcher getInstance() {
        if (_instance == null) {
            _instance = new DataModeEventDispatcher();
        }
        return _instance;
    }

    @Override
    public void onRecv(ObjectEvent<?> event) {
        if (ObjectEvent.RECV_SOCKET.equals(event.getEvent())){
            onRecv(((MrPcSyncHead) event));
        }else if(ObjectEvent.RECV_DEVICE.equals(event.getEvent())){
            onRecv(((AndroidDevice) event));
        }else if(ObjectEvent.RECV_STRING.equals(event.getEvent())){
        	onRecv(((StringEvent) event));
        }
    }
    
    public void onRecv(AndroidDevice device) {
    	IDevice[] devices = device.getDevices();
    }

    public void onRecv(MrPcSyncHead head) {
    }
    
    public void onRecv(StringEvent string) {
    }

    public void initWelcomePanel() {
        if(mInit[0]){
            return;
        }
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
    	System.out.println("CmdPanel");
        mInit[4] = true;
    }
}
