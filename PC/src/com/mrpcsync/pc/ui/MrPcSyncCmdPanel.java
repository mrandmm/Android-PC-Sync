package com.mrpcsync.pc.ui;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.UnsupportedEncodingException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;

import com.android.ddmlib.IDevice;
import com.android.ddmlib.IShellOutputReceiver;


public class MrPcSyncCmdPanel extends JPanel implements KeyListener, IShellOutputReceiver{
    private static MrPcSyncCmdPanel _instance = null;
    private JScrollPane mScrollPane = new JScrollPane();
    private JTextPane mTextPane = new JTextPane();
	private IDevice mDevice;

	public IDevice getDevice() {
		return mDevice;
	}

	public void setDevice(IDevice mDevice) {
		this.mDevice = mDevice;
	}
    
    public MrPcSyncCmdPanel(){
    	mScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
    	mScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    	mScrollPane.setBounds(0, 0, 793, 493);
		this.add(mScrollPane);
		
		mTextPane.setBounds(1, 1, 775, 475);
		mTextPane.setForeground(Color.GREEN);
		mTextPane.setBackground(Color.BLACK);
		mTextPane.setCaretColor(Color.WHITE);
		mTextPane.addKeyListener(this);
		mTextPane.requestFocus();
		mScrollPane.add(mTextPane);
    }

    public static MrPcSyncCmdPanel getInstance() {
        if (_instance == null) {
            _instance = new MrPcSyncCmdPanel();
        }
        return _instance;
    }
    
    int old = 0;

	@Override
	public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode()==KeyEvent.VK_ENTER){
			int length = mTextPane.getCaretPosition();
			try {
				String cmd = mTextPane.getText(old, length-old);
				if (mDevice!=null && (length-old)>0){
					mDevice.executeShellCommand(cmd, this);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			old = length+1;
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
			builder.append(mTextPane.getText());
			builder.append(new String(arg0, "utf-8"));
			mTextPane.setText(builder.toString());
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
