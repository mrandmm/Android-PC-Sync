package com.mrpcsync.pc.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.List;
import java.awt.Panel;
import java.awt.Label;
import javax.swing.JList;
import java.awt.Canvas;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import com.mr.ScreenDialog;
import com.mr.ScreenShot;
import com.mrpcsync.pc.data.control.Controller;
import com.mrpcsync.pc.ui.control.DataModeEventDispatcher;

public class MrPcSyncUI {

	private JFrame frame;
	private Controller controller = Controller.getInstance();
	private DataModeEventDispatcher datamode = DataModeEventDispatcher.getInstance();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				    MrPcSyncUI window = new MrPcSyncUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MrPcSyncUI() {
		initializeUI();
		initializeData();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initializeUI() {
		frame = new JFrame("MrPcSync");
		frame.setResizable(false);
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		//ScreenDialog.getInstance(frame);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 800, 600);
		frame.getContentPane().add(tabbedPane);
		controller.addController("tabbedPane", tabbedPane);
		
		JPanel panel_welcome = new JPanel();
		tabbedPane.addTab("\u6B22\u8FCE", new ImageIcon("C:\\Users\\MM\\workspace\\MrPcSync\\res\\menu_icon_about.png"), panel_welcome, null);
		panel_welcome.setLayout(null);
		
		JPanel panel_devices = new JPanel();
		panel_devices.setBackground(Color.WHITE);
		panel_devices.setBounds(75, 40, 192, 336);
		panel_welcome.add(panel_devices);
		panel_devices.setLayout(null);
		
		JList list_devices = new JList();
		list_devices.setBounds(0, 25, 192, 311);
		panel_devices.add(list_devices);
		controller.addController("list_devices", list_devices);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setToolTipText("");
		toolBar.setBounds(0, 0, 192, 23);
		panel_devices.add(toolBar);
		
		JButton btn_refresh = new JButton("\u5237\u65B0\u8BBE\u5907\u5217\u8868");
		btn_refresh.setHorizontalAlignment(SwingConstants.RIGHT);
		toolBar.add(btn_refresh);
		controller.addController("btn_refresh", btn_refresh);
		
		JPanel panel_devices_info = new JPanel();
		panel_devices_info.setBackground(Color.WHITE);
		panel_devices_info.setBounds(345, 40, 192, 336);
		panel_welcome.add(panel_devices_info);
		panel_devices_info.setLayout(null);
		
		JToolBar toolBar_1 = new JToolBar();
		toolBar_1.setBounds(0, 0, 192, 23);
		panel_devices_info.add(toolBar_1);
		
		JLabel label_1 = new JLabel("\u8BBE\u5907\u4FE1\u606F");
		toolBar_1.add(label_1);
		
		JTextPane text_device_info = new JTextPane();
		text_device_info.setBounds(10, 28, 172, 298);
		panel_devices_info.add(text_device_info);
		controller.addController("text_device_info", text_device_info);
		
		JPanel panel_contact = new JPanel();
		tabbedPane.addTab("\u901A\u8BAF\u5F55", new ImageIcon("C:\\Users\\MM\\workspace\\MrPcSync\\res\\menu_icon_account.png"), panel_contact, null);
		panel_contact.setLayout(null);
		controller.addController("panel_contact", panel_contact);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane_1.setBounds(21, 24, 753, 452);
		panel_contact.add(tabbedPane_1);
		
		JPanel panel_new_contact = new JPanel();
		tabbedPane_1.addTab("\u65B0\u5EFA\u8054\u7CFB\u4EBA", new ImageIcon("C:\\Users\\MM\\workspace\\MrPcSync\\res\\umeng_analyse_write_feedback_pressed.png"), panel_new_contact, null);
		panel_new_contact.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(39, 22, 291, 220);
		panel_new_contact.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u59D3\u540D");
		lblNewLabel.setBounds(61, 54, 54, 15);
		panel.add(lblNewLabel);
		
		JTextField textField = new JTextField();
		textField.setBounds(95, 51, 97, 21);
		panel.add(textField);
		textField.setColumns(10);
		controller.addController("textField", textField);
		
		JTextField textField_1 = new JTextField();
		textField_1.setBounds(95, 87, 97, 21);
		panel.add(textField_1);
		textField_1.setColumns(10);
		controller.addController("textField_1", textField_1);
		
		JLabel label = new JLabel("\u624B\u673A");
		label.setBounds(61, 90, 54, 15);
		panel.add(label);
		
		JButton btn_new_contact = new JButton("\u6DFB\u52A0");
		btn_new_contact.setBounds(95, 151, 93, 23);
		panel.add(btn_new_contact);
		controller.addController("btn_new_contact", btn_new_contact);
		
		JPanel panel_all_contact = new JPanel();
		tabbedPane_1.addTab("\u6240\u6709\u8054\u7CFB\u4EBA", new ImageIcon("C:\\Users\\MM\\workspace\\MrPcSync\\res\\umeng_analyse_see_list_pressed.png"), panel_all_contact, null);
		panel_all_contact.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(22, 28, 574, 387);
		panel_all_contact.add(panel_1);
		panel_1.setLayout(null);
		
		JList list = new JList();
		list.setBounds(10, 388, 564, -377);
		panel_1.add(list);
		
		JPanel panel_message = new JPanel();
		tabbedPane.addTab("\u77ED\u4FE1", new ImageIcon("C:\\Users\\MM\\workspace\\MrPcSync\\res\\menu_icon_feedback.png"), panel_message, null);
		panel_message.setLayout(null);
		panel_message.setEnabled(false);
		controller.addController("panel_message", panel_message);
		
		JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane_2.setBounds(21, 24, 753, 452);
		panel_message.add(tabbedPane_2);
		
		JPanel panel_new_message = new JPanel();
		tabbedPane_2.addTab("\u65B0\u4FE1\u606F", new ImageIcon("C:\\Users\\MM\\workspace\\MrPcSync\\res\\umeng_analyse_write_feedback_pressed.png"), panel_new_message, null);
		
		JPanel panel_draft_message = new JPanel();
		tabbedPane_2.addTab("\u8349\u7A3F\u7BB1", new ImageIcon("C:\\Users\\MM\\workspace\\MrPcSync\\res\\umeng_analyse_write_feedback_pressed.png"), panel_draft_message, null);
		
		JPanel panel_all_message = new JPanel();
		tabbedPane_2.addTab("\u6536\u4EF6\u7BB1", new ImageIcon("C:\\Users\\MM\\workspace\\MrPcSync\\res\\umeng_analyse_write_feedback_pressed.png"), panel_all_message, null);
		
		
		JPanel panel_application = new JPanel();
		tabbedPane.addTab("\u5E94\u7528", new ImageIcon("C:\\Users\\MM\\workspace\\MrPcSync\\res\\menu_icon_task.png"), panel_application, null);
		panel_application.setLayout(null);
		panel_application.setEnabled(false);
		controller.addController("panel_application", panel_application);
		
		JTabbedPane tabbedPane_3 = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane_3.setBounds(21, 24, 753, 452);
		panel_application.add(tabbedPane_3);
		
		JPanel panel_ins_app = new JPanel();
		tabbedPane_3.addTab("\u5B89\u88C5\u5E94\u7528", new ImageIcon("C:\\Users\\MM\\workspace\\MrPcSync\\res\\umeng_analyse_write_feedback_pressed.png"), panel_ins_app, null);
		
		JPanel panel_sys_app = new JPanel();
		tabbedPane_3.addTab("\u7CFB\u7EDF\u5E94\u7528", new ImageIcon("C:\\Users\\MM\\workspace\\MrPcSync\\res\\umeng_analyse_write_feedback_pressed.png"), panel_sys_app, null);
	}
	
	private void initializeData() {
	    datamode.registerTabbedPane();
	}
}
