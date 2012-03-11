package com.mrpcsync.pc.ui;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.UnsupportedEncodingException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;

import com.android.ddmlib.IDevice;
import com.android.ddmlib.IShellOutputReceiver;


public class MrPcSyncCmdPanel extends JPanel implements KeyListener, IShellOutputReceiver{
    private static MrPcSyncCmdPanel _instance = null;
    private JTextPane mTextPane = new JTextPane();
    private JTextField mTextField = new JTextField();
	private static IDevice mDevice;

	public IDevice getDevice() {
		return mDevice;
	}

	public void setDevice(IDevice Device) {
		if (Device!=null){
			this.mDevice = Device;
		}
	}
    
    public MrPcSyncCmdPanel(){
		mTextPane.setBounds(0, 0, 793, 470);
		mTextPane.setForeground(Color.GREEN);
		mTextPane.setBackground(Color.BLACK);
		mTextPane.setCaretColor(Color.WHITE);
		mTextPane.setEditable(false);
		this.add(mTextPane);
		
		mTextField = new JTextField();
		mTextField.setBounds(0, 471, 793, 21);
		mTextField.addKeyListener(this);
		this.add(mTextField);
    }

    public static MrPcSyncCmdPanel getInstance() {
        if (_instance == null) {
            _instance = new MrPcSyncCmdPanel();
        }
        return _instance;
    }
    
	@Override
	public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode()==KeyEvent.VK_ENTER){
			try {
				String cmd = mTextField.getText();
				if (mDevice!=null){
					mDevice.executeShellCommand(cmd, this);
				}
				mTextField.setText("");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	@Override
	public void addOutput(byte[] arg0, int arg1, int arg2) {
		try {
			mTextPane.setText("");
			StringBuilder builder = new StringBuilder();
			builder.append(new String(arg0, "utf-8"));
			mTextPane.setText(builder.toString());
			mTextPane.requestFocus();
			mTextPane.setCaretPosition(mTextPane.getCaretPosition());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void flush() {
	}

	@Override
	public boolean isCancelled() {
		return false;
	}
}
