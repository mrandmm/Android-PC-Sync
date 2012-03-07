package com.mr;

import java.awt.FileDialog;
import java.io.File;
import java.io.FilenameFilter;

import javax.swing.JFrame;

public class ScreenDialog {
	private static ScreenDialog _instance = null;
	private FileDialog mDialog;

	public ScreenDialog(JFrame parent) {
		mDialog = new FileDialog(parent, "±£´æ½ØÍ¼", FileDialog.LOAD);
		mDialog.setFilenameFilter(png);
	}

	public static ScreenDialog getInstance(JFrame dialog) {
		if (_instance == null) {
			_instance = new ScreenDialog(dialog);
		}
		return _instance;
	}

	public static ScreenDialog getInstance() {
		return _instance;
	}

	public boolean show() {
		mDialog.show();
		if (mDialog.getFile() != null) {
			return true;
		}
		return false;
	}

	public String getFile() {
		return mDialog.getFile();
	}

	public String getDirectory() {
		return mDialog.getDirectory();
	}

	private FilenameFilter png = new FilenameFilter() {

		@Override
		public boolean accept(File arg0, String arg1) {
			if (arg1.toLowerCase().endsWith(".png")) {
				return true;
			} else {
				return false;
			}
		}

	};

}
