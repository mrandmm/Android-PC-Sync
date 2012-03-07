package com.mr;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class DataMode implements ActionListener, ListSelectionListener,
		ChangeListener {
	private static DataMode _instance = null;
	private Controller mController = Controller.getInstance();
	private DevicesList mDevicesList = DevicesList.getInstance();
	private ScreenShot screen_shot = ScreenShot.getInstance();
	private DeviceInfo mDeviceInfo = DeviceInfo.getInstance();
	private Contact mContact = Contact.getInstance();
	
	private MrSyncClient mClient = new MrSyncClient();

	//
	JTabbedPane tabbedPane;
	JTextField textField_1;
	JTextField textField;
	public void initWelcomePanel() {
		JButton btn_refresh = (JButton) mController
				.getController("btn_refresh");
		btn_refresh.addActionListener(this);

		JButton btn_screenshot = (JButton) mController
				.getController("btn_screenshot");
		btn_screenshot.addActionListener(this);

		JButton btn_refresh_screen = (JButton) mController
				.getController("btn_refresh_screen");
		btn_refresh_screen.addActionListener(this);

		JList list_devices = (JList) mController.getController("list_devices");
		list_devices.addListSelectionListener(this);

		JButton btn_new_contact = (JButton) mController
				.getController("btn_new_contact");
		btn_new_contact.addActionListener(this);

		tabbedPane = (JTabbedPane) mController.getController("tabbedPane");
		tabbedPane.addChangeListener(this);
		
		textField_1 =  (JTextField) mController.getController("textField_1");
		textField =  (JTextField) mController.getController("textField");

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand().equals("刷新设备列表")) {
			mDevicesList.updateDevices();
		} else if (arg0.getActionCommand().equals("刷新")) {
			screen_shot.draw();
		} else if (arg0.getActionCommand().equals("截图")) {
			ScreenDialog dialog = ScreenDialog.getInstance();
			if (dialog.show()) {
				screen_shot.getScreenShot(dialog.getDirectory(),
						dialog.getFile());
			}
		} else if (arg0.getActionCommand().equals("添加")) {
			mContact.addContact(textField.getText(), textField_1.getText());
		}
	}

	public static DataMode getInstance() {
		if (_instance == null) {
			_instance = new DataMode();
		}
		return _instance;
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		int selection = ((JList) arg0.getSource()).getSelectedIndex();
		screen_shot.setDevice(mDevicesList.getDevices()[selection]);

		mDeviceInfo.setDevice(mDevicesList.getDevices()[selection]);
		((JTextPane) mController.getController("text_device_info"))
				.setText(mDeviceInfo.getDeviceInfo());

		mContact.setDevice(mDevicesList.getDevices()[selection]);
		
		mClient.start();
		mClient.client();
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		if (tabbedPane.getSelectedIndex() == 0) {

		} else if (tabbedPane.getSelectedIndex() == 1) {
		} else if (tabbedPane.getSelectedIndex() == 2) {

		} else if (tabbedPane.getSelectedIndex() == 3) {

		}
	}

}
