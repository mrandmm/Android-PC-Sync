package com.mrpcsync.pc.ui.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.mrpcsync.android.device.AndroidDevice;
import com.mrpcsync.android.device.AndroidDeviceList;
import com.mrpcsync.android.device.AndroidInstall;
import com.mrpcsync.pc.data.MrPcSyncHead;
import com.mrpcsync.pc.data.MrSyncClient;
import com.mrpcsync.pc.data.control.Controller;
import com.mrpcsync.pc.event.EventDispatcher;
import com.mrpcsync.pc.event.ObjectEvent;
import com.mrpcsync.pc.event.OnRecvListener;

public class DataModeEventDispatcher extends EventDispatcher implements
        OnRecvListener {
    private boolean mInit[] = {false, false, false, false};
    private static DataModeEventDispatcher _instance = null;
    private MrSyncClient mClient = MrSyncClient.getInstance();
    private Controller mController = Controller.getInstance();

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
    }

    public static DataModeEventDispatcher getInstance() {
        if (_instance == null) {
            _instance = new DataModeEventDispatcher();
        }
        return _instance;
    }

    @Override
    public void onRecv(ObjectEvent<?> event) {
        if (event.getEvent()==ObjectEvent.RECV_SOCKET){
            onRecv(((MrPcSyncHead) event));
        }else if(event.getEvent()==ObjectEvent.RECV_DEVICE){
            onRecv(((AndroidDevice) event));
        }
    }
    
    public void onRecv(AndroidDevice device) {
        AndroidInstall.getInstance().setDevices(device.getDevices()[0]);
    }

    public void onRecv(MrPcSyncHead head) {
    }

    public void initWelcomePanel() {
        if(mInit[0]){
            return;
        }
        mAndroidDeviceList.start();
        ((JButton)mController.getController("btn_refresh")).addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                mAndroidDeviceList.start();
            }
        });
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

}
