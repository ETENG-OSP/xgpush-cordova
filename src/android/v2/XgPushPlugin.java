package com.eteng.push.xgpush2;

import java.util.HashMap;
import java.util.Map;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.android.tpush.common.Constants;

public class XgPushPlugin extends CordovaPlugin {
	
    private static final String ACTION_REGISTER_PUSH = "register_push";
	private static final String ACTION_UNREGISTER_PUSH = "unregister_push";
	
	Map<String, BroadcastReceiver> receivers = new HashMap<String, BroadcastReceiver>();

	@Override
	public void initialize(CordovaInterface cordova, CordovaWebView webView) {
		super.initialize(cordova, webView);
		// log
		XGPushConfig.enableDebug(cordova.getActivity(), true);
		
		// initialize
		Context context = cordova.getActivity().getApplicationContext();
		XGPushManager.registerPush(context);
	}

	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		if (ACTION_REGISTER_PUSH.equals(action)) {
			String account = args.getString(0);
			registerPush(account, callbackContext);
			return true;
		} else if (ACTION_UNREGISTER_PUSH.equals(action)) {
			unregisterPush();
			return true;
		}
		
		return false;
	}

	private void unregisterPush() {
		Context context = cordova.getActivity().getApplicationContext();
		XGPushManager.unregisterPush(context);
		for (Map.Entry<String, BroadcastReceiver> entry: receivers.entrySet()) {
			unregisterReceiver(entry.getValue());
		}
	}

	private void registerPush(String account, CallbackContext callback) {
		Context context = cordova.getActivity().getApplicationContext();
		XGPushManager.registerPush(context, account);
		receivers.put(account, registerReceiver(callback));
	}
	
	private BroadcastReceiver registerReceiver(CallbackContext callback) {
		BroadcastReceiver receiver = new XgPushReceiver(callback);
		IntentFilter filter = new IntentFilter();
		filter.addAction(Constants.ACTION_FEEDBACK);
		filter.addAction(Constants.ACTION_PUSH_MESSAGE);
		cordova.getActivity().registerReceiver(receiver, filter);
		return receiver;
	}
	
	private void unregisterReceiver(BroadcastReceiver receiver) {
		cordova.getActivity().unregisterReceiver(receiver);
	}

	/**
	 * fix for singletop
	 */
	@Override
	public void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		this.cordova.getActivity().setIntent(intent);
	}

}
