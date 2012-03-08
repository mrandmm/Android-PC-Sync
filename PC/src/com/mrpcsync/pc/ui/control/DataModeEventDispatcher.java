package com.mrpcsync.pc.ui.control;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.mrpcsync.pc.data.MrPcSyncHead;
import com.mrpcsync.pc.data.MrSyncClient;
import com.mrpcsync.pc.data.control.Controller;
import com.mrpcsync.pc.event.EventDispatcher;
import com.mrpcsync.pc.event.ObjectEvent;
import com.mrpcsync.pc.event.OnSocketListener;

public class DataModeEventDispatcher extends EventDispatcher implements
        OnSocketListener {
    private static DataModeEventDispatcher _instance = null;
    private MrSyncClient mClient = MrSyncClient.getInstance();
    private Controller mController = Controller.getInstance();

    private JTabbedPane mTabbedPane;

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
    }

    public static DataModeEventDispatcher getInstance() {
        if (_instance == null) {
            _instance = new DataModeEventDispatcher();
        }
        return _instance;
    }

    @Override
    public void onRecv(ObjectEvent<?> event) {
        onRecv(((MrPcSyncHead) event));
    }

    public void onRecv(MrPcSyncHead head) {

    }

    public void initWelcomePanel() {
        System.out.println("WelcomePanel");
    }

    public void initContactPanel() {
        System.out.println("ContactPanel");
    }

    public void initMessagePanel() {
        System.out.println("MessagePanel");
    }

    public void initApplicationPanel() {
        System.out.println("ApplicationPanel");
    }

}
