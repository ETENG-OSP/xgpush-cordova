package com.qq.xgdemo.common;

import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;

public class CommonWorkingThread {

	private static HandlerThread thread = null;

	private static Handler handler = null;

	private CommonWorkingThread() {
	}

	public static class CommonWorkingThreadHolder {
		public static CommonWorkingThread instance = new CommonWorkingThread();
	}

	public static CommonWorkingThread getInstance() {
		initHandler();
		return CommonWorkingThreadHolder.instance;
	}

	public boolean execute(Runnable r) {
		if (handler != null) {
			Log.i("CommonWorkingThread", ">>> working thread execute ");
			return handler.post(r);
		}
		return false;

	}

	public boolean execute(Runnable r, long delayMillis) {
		if (handler != null) {
			Log.i("CommonWorkingThread",
					">>> working thread execute delayMillis " + delayMillis);
			return handler.postDelayed(r, delayMillis);
		}
		return false;
	}

	public Handler getHandler() {
		return handler;
	}

	private static void initHandler() {
		if (thread == null || !thread.isAlive() || thread.isInterrupted()
				|| thread.getState() == Thread.State.TERMINATED) {
			thread = new HandlerThread("tpush.working.thread");
			thread.start();
			handler = new Handler(thread.getLooper());
			Log.i("CommonWorkingThread", ">>> Create new working thread."
					+ thread.getId());
		}
	}

}
