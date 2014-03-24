package com.eteng.push.xgpush;

import java.util.List;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public abstract class PushPlugin extends CordovaPlugin {

	public static final String TAG = "PushPlugin";
	public static final String ACTION_ENABLE_DEBUG = "enable_debug";
	public static final String ACTION_REGISTER_PUSH = "register_push";
	public static final String ACTION_REGISTER_ACCOUNT = "register_action";
	public static final String ACTION_UNREGISTER_PUSH = "unregister_push";
	public static final String ACTION_SET_TAG = "set_tag";
	public static final String ACTION_DELETE_TAG = "delete_tag";
	public static final String ACTION_CLEAR_CACHE = "clear_cache";
	public static final String ACTION_SET_ID_AND_KEY = "set_id_and_key";
	
	public static final String ACTION_READY = "ready";
	
	private static CordovaWebView appView;
	private static DiskHeap messageCache;
	private static boolean safeToFireMessages = false;
	
	@Override
	public void initialize(CordovaInterface cordova, CordovaWebView webView) {
		Context context = cordova.getActivity().getApplicationContext();
		safeToFireMessages = false;
		appView = webView;
		messageCache = new DiskHeap(context);
		onInitialize(context);
		
		super.initialize(cordova, webView);
	}

	@Override
	public void onPause(boolean multitasking) {
		safeToFireMessages = false;
		this.onActivityPause(this.cordova.getActivity());
		super.onPause(multitasking);
	}

	@Override
	public void onResume(boolean multitasking) {
		this.onActivityResume(this.cordova.getActivity());
		super.onResume(multitasking);
	}

	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		Log.d(TAG, "receive action " + action);
		Context context = this.cordova.getActivity();
		
		if (action.equals(ACTION_REGISTER_PUSH)) {
			this.onRegisterPush(context);
			
		} else if (action.equals(ACTION_REGISTER_ACCOUNT)) {
			String account = args.getString(0);
			this.onRegisterAccount(context, account);
			
		} else if (action.equals(ACTION_SET_TAG)) {
			String tag = args.getString(0);
			this.onSetTag(context, tag);
			
		} else if (action.equals(ACTION_DELETE_TAG)) {
			String tag = args.getString(0);
			this.onDeleteTag(context, tag);
			
		} else if (action.equals(ACTION_UNREGISTER_PUSH)) {
			this.onUnregisterPush(context);
			
		} else if (action.equals(ACTION_CLEAR_CACHE)) {
			this.onClearCache(context);
			
		} else if (action.equals(ACTION_ENABLE_DEBUG)) {
			boolean enable = args.getBoolean(0);
			this.onEnableDebug(context, enable);
			
		} else if (ACTION_SET_ID_AND_KEY.equals(action)) {
			long id = args.getLong(0);
			String key = args.getString(1);
			this.onSetIdAndKey(context, id, key);
			
		} else if (ACTION_READY.equals(action)) {
			this.onReady();
		}
		
		return false;
	}

	private void onReady() {
		safeToFireMessages = true;
		List<String> pending = messageCache.popAll();
		for (String message: pending) {
			fireMessage(message);
		}
	}
	
	public static void handlePushMessage(Context context, Intent intent) {
		JSONObject payload = new JSONObject();
		try {
			for (String key : intent.getExtras().keySet()) {
				payload.put(key, intent.getStringExtra(key));
			}
		} catch (Exception e) {
			Log.e(TAG, "Error construction push message payload: " + e);
			return;
		}
		String payloadString = payload.toString();
		
		if (!safeToFireMessages || appView == null) {
			messageCache.put(payloadString);
			return;
		}
		
		fireMessage(payloadString);
	}
	
	private static void fireMessage(String message) {
		appView.sendJavascript(message);
	}
	
	protected abstract void onActivityPause(Activity context);
	protected abstract void onActivityResume(Activity context);
	
	protected abstract void onEnableDebug(Context context, boolean enable);
	protected abstract void onRegisterPush(Context context);
	protected abstract void onRegisterAccount(Context context, String account);
	protected abstract void onUnregisterPush(Context context);
	protected abstract void onSetTag(Context context, String tag);
	protected abstract void onDeleteTag(Context context, String tag);
	protected abstract void onClearCache(Context context);
	protected abstract void onSetIdAndKey(Context context, long id, String key);
	protected abstract void onInitialize(Context applicationContext);

}
