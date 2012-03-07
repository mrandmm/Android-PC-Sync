package com.mr;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;

import com.android.ddmlib.AdbCommandRejectedException;
import com.android.ddmlib.IDevice;
import com.android.ddmlib.RawImage;
import com.android.ddmlib.TimeoutException;
import com.sun.imageio.plugins.common.ImageUtil;

public class ScreenShot extends JPanel implements Runnable {
	private static ScreenShot _instance = null;
	private IDevice mDevice;
	private static Graphics mGraphics;
	private Thread mThread = new Thread(this);
	private Image mImage;
	private static boolean runFlag = false;

	public ScreenShot() {
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		draw(g);
	}
	static int i=30;
	public void draw(Graphics g) {
		if (g != null) {
			g.setColor(Color.BLUE);
			g.drawString("WWwwwwwwwwwwwww", 10, 20);
			g.drawImage(mImage =  getToolkit().createImage("C:\\Users\\MM\\Pictures\\ÎÞ±êÌâ.png"), 0, 0, this);
			g.setColor(Color.RED);
			g.drawString("WWwwwwwwwwwwwww", 10, 30);
		}
	}

	public static ScreenShot getInstance() {
		if (_instance == null) {
			_instance = new ScreenShot();
		}
		return _instance;
	}

	public void setDevice(IDevice device) {
		if (device != null) {
			mDevice = device;
		}
	}
	
	public Image getScreenShot() {
		try {
			if (mDevice == null) {
				return null;
			}
			RawImage mRawimage = mDevice.getScreenshot();
			BufferedImage image;
			if (mRawimage != null) {
				boolean landscape = false;
				int width2 = landscape ? mRawimage.height : mRawimage.width;
				int height2 = landscape ? mRawimage.width : mRawimage.height;
				image = new BufferedImage(width2, height2,
						BufferedImage.TYPE_INT_RGB);
				if (image.getHeight() != height2 || image.getWidth() != width2) {
					image = new BufferedImage(width2, height2,
							BufferedImage.TYPE_INT_RGB);
				}
				int index = 0;
				int indexInc = mRawimage.bpp >> 3;
				for (int y = 0; y < mRawimage.height; y++) {
					for (int x = 0; x < mRawimage.width; x++, index += indexInc) {
						int value = mRawimage.getARGB(index);
						if (landscape)
							image.setRGB(y, mRawimage.width - x - 1, value);
						else
							image.setRGB(x, y, value);
					}
				}
				return getToolkit().createImage(mRawimage.data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void getScreenShot(String path, String file) {
		try {
			if (mDevice == null) {
				return;
			}
			RawImage mRawimage = mDevice.getScreenshot();
			if (mRawimage != null) {
				boolean landscape = false;
				int width2 = landscape ? mRawimage.height : mRawimage.width;
				int height2 = landscape ? mRawimage.width : mRawimage.height;
				BufferedImage image = new BufferedImage(width2, height2,
						BufferedImage.TYPE_INT_RGB);
				if (image.getHeight() != height2 || image.getWidth() != width2) {
					image = new BufferedImage(width2, height2,
							BufferedImage.TYPE_INT_RGB);
				}
				int index = 0;
				int indexInc = mRawimage.bpp >> 3;
				for (int y = 0; y < mRawimage.height; y++) {
					for (int x = 0; x < mRawimage.width; x++, index += indexInc) {
						int value = mRawimage.getARGB(index);
						if (landscape)
							image.setRGB(y, mRawimage.width - x - 1, value);
						else
							image.setRGB(x, y, value);
					}
				}
				ImageIO.write(image, "PNG", new File(path + "/" + file));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void draw() {
		try {
			if (runFlag) {
				mThread.join();
			} else {
				mThread.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		while (true) {
			runFlag = true;
			try {
				if (mDevice == null) {
					break;
				}
				repaint();
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		runFlag = false;
	}

}
