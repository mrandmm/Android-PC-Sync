package com.mrpcsync.pc.data;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.net.Socket;

import com.mrpcsync.pc.event.EventDispatcher;

// 客户端连接类
public class MrSyncClient extends EventDispatcher implements Runnable {
	private static MrSyncClient _instance = null;
	private Socket mSocket;
	private final String mAddress = "127.0.0.1";
	private final int mPort = 2589;

	public static MrSyncClient getInstance() {
		if (_instance == null) {
			_instance = new MrSyncClient();
		}
		return _instance;
	}

	public MrSyncClient() {
	}

	private BufferedOutputStream out;
	private BufferedInputStream in;

	public void connect() throws Exception {
		Runtime.getRuntime().exec(
				"adb shell am broadcast -a MrPcSync.NotifyServiceStart");
		// 打开Android 客户端
		Thread.sleep(1000);
		// 端口映射
		Runtime.getRuntime().exec("adb forward tcp:2589 tcp:2589");
		Thread.sleep(1000);

		mSocket = new Socket(mAddress, mPort);
		out = new BufferedOutputStream(mSocket.getOutputStream());
		in = new BufferedInputStream(mSocket.getInputStream());
		
		new Thread(this).start();
	}

	public void send(MrPcSyncHead head) throws Exception {
		if (mSocket == null) {
			return;
		}
		out.write(head.getBuffer());
		out.flush();
	}
	
	private byte[] mRecvBuffer = new byte[MrPcSyncHead.getLength()];
	private MrPcSyncHead mHead = new MrPcSyncHead();

	@Override
	public void run() {
		try {
			while (true) {
				if (mSocket == null) {
					break;
				}
				if (-1 == in.read(mRecvBuffer)) {
					break;
				}
				mHead.setReadBuffer(mRecvBuffer);
				this.dispatchEvent(mHead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
