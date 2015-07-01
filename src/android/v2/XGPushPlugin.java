package com.eteng.push.xgpush2;

import java.util.HashMap;
import java.util.Map;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

import com.tencent.android.tpush.XGPushConstants;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

public abstract class XGPushPlugin extends CordovaPlugin {

  private static final String TAG = "XGCordovaPlugin";
  private static final String ACTION_REGISTER_PUSH = "registerpush";
  private static final String ACTION_UNREGISTER_PUSH = "unregisterpush";
  private Integer idGenerator = 0;
  protected Map<Integer, BroadcastReceiver> receivers = new HashMap<Integer, BroadcastReceiver>();

  abstract protected boolean registerPush(Context context, String alias, CallbackContext callback);
  abstract protected boolean unregisterPush(Context context, CallbackContext callback);

  @Override
  public void initialize(CordovaInterface cordova, CordovaWebView webView) {
    super.initialize(cordova, webView);
    // initialize
    Context context = cordova.getActivity().getApplicationContext();
    // register(context);
  }

  protected Integer registerReceiver(Context context, CallbackContext callback) {
    int id = idGenerator++;
    BroadcastReceiver receiver = new XGCordovaPushReceiver(callback);
    IntentFilter filter = new IntentFilter();
    filter.addAction(XGPushConstants.ACTION_PUSH_MESSAGE);
    filter.addAction(XGPushConstants.ACTION_FEEDBACK);
    context.registerReceiver(receiver, filter);
    receivers.put(id, receiver);
    return id;
  }

  protected void unregisterReceiver(Context context, Integer id) {
    BroadcastReceiver receiver = receivers.remove(id);
    context.unregisterReceiver(receiver);
  }

  @Override
  public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
    Log.d(TAG, "> plugin invoke");
    Context context = cordova.getActivity().getApplicationContext();
    if (ACTION_REGISTER_PUSH.equals(action)) {
      String alias = args.getString(0);
      return registerPush(context, alias, callbackContext);

    } else if (ACTION_UNREGISTER_PUSH.equals(action)) {
      return unregisterPush(context, callbackContext);

    }

    return false;
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
