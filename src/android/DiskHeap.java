package com.eteng.push.xgpush;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import android.content.Context;
import android.util.Log;

public class DiskHeap implements FilenameFilter {
	
	private static final String TAG = "DiskHeap";
	private static final String SUFFIX = ".xgpush";
	private Context context;
	
	DiskHeap(Context context) {
		this.context = context;
	}
	
	public void put(String message) {
		Log.d(TAG, "store message");
		String filename = UUID.randomUUID().toString();
		File file;
		try {
			file = File.createTempFile(filename, SUFFIX, context.getCacheDir());
			FileOutputStream outputStream = new FileOutputStream(file);
			outputStream.write(message.getBytes());
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<String> popAll() {
		Log.d(TAG, "retrive message");
		File[] files = context.getCacheDir().listFiles(this);
		List<String> result = new LinkedList<String>();
		for (File file: files) {
			Log.d(TAG, file.getName());
			try {
				FileInputStream inputStream = new FileInputStream(file);
				int length = (int) file.length();
				byte[] buffer = new byte[length];
				inputStream.read(buffer);
				inputStream.close();
				result.add(new String(buffer));
				file.delete();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public boolean accept(File dir, String filename) {
		return filename.endsWith(SUFFIX);
	}

}
