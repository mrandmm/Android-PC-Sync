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

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
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
import javax.swing.JScrollBar;
import javax.swing.DropMode;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;

public class MrPcSyncUI {

	private JFrame frame;
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

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 800, 600);
		frame.getContentPane().add(tabbedPane);
		Controller.getInstance().addController("tabbedPane", tabbedPane);

		MrPcSyncWelcomePanel panel_welcome = new MrPcSyncWelcomePanel();
		tabbedPane.addTab("\u6B22\u8FCE", new ImageIcon("C:\\Users\\MM\\workspace\\MrPcSync\\res\\menu_icon_about.png"), panel_welcome, null);
		panel_welcome.setLayout(null);

		MrPcSyncContactPanel panel_contact = new MrPcSyncContactPanel();
		tabbedPane.addTab("\u901A\u8BAF\u5F55", new ImageIcon("C:\\Users\\MM\\workspace\\MrPcSync\\res\\menu_icon_account.png"), panel_contact, null);
		panel_contact.setLayout(null);

		MrPcSyncMessagePanel panel_message = new MrPcSyncMessagePanel();
		tabbedPane.addTab("\u77ED\u4FE1", new ImageIcon("C:\\Users\\MM\\workspace\\MrPcSync\\res\\menu_icon_feedback.png"), panel_message, null);
		panel_message.setLayout(null);

		MrPcSyncAppliactionPanel panel_application = new MrPcSyncAppliactionPanel();
		tabbedPane.addTab("\u5E94\u7528", new ImageIcon("C:\\Users\\MM\\workspace\\MrPcSync\\res\\menu_icon_task.png"), panel_application, null);
		panel_application.setLayout(null);

		MrPcSyncCmdPanel panel_cmd = new MrPcSyncCmdPanel();
		tabbedPane.addTab("\u7EC8\u7AEF", new ImageIcon("C:\\Users\\MM\\Desktop\\Android-PC-Sync\\PC\\res\\menu_icon_feedback.png"), panel_cmd, null);
		panel_cmd.setLayout(null);
	}

	private void initializeData() {
		datamode.registerTabbedPane();
	}
}
