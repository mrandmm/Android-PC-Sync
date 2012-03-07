package com.mr;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.net.Socket;

public class MrSyncClient extends Thread {
	private Socket mSocket;
	private String mAddress = "127.0.0.1";
	private int mPort = 2589;
    private BufferedOutputStream out;
    private BufferedInputStream in;
    private byte[] buffer = new byte[2048+128];
    private MrPcSyncHead mHead = new MrPcSyncHead();	
	public MrSyncClient(){
	}
	
	public void client(){
		try {
			Runtime.getRuntime().exec("adb shell am broadcast -a MrPcSync.NotifyServiceStart");
			Thread.sleep(1000);
			Runtime.getRuntime().exec("adb forward tcp:2589 tcp:2589");
			Thread.sleep(1000);
			
			mSocket = new Socket(mAddress, mPort);
			out = new BufferedOutputStream(mSocket.getOutputStream());
			in = new BufferedInputStream(mSocket.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		} 

	}
	
	
	public void send(byte[] cmd){
		try {
			if (mSocket==null){
				return;
			}
			out.write(cmd);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		try {
			if (mSocket==null){
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
