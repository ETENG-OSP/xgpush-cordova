package com.eteng.push.xgpush2;

import java.util.HashMap;
import java.util.Map;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

import com.tencent.android.tpush.XGPushManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class XGPushPlugin extends CordovaPlugin {

  private static final String ACTION_REGISTER_PUSH = "register_push";
  private static final String ACTION_REGISTER_ACCOUNT = "register_account";
  private static final String ACTION_UNREGISTER_PUSH = "unregister";

  Map<String, BroadcastReceiver> receivers = new HashMap<String, BroadcastReceiver>();

  @Override
  public void initialize(CordovaInterface cordova, CordovaWebView webView) {
    super.initialize(cordova, webView);
    // log
    // XGPushConfig.enableDebug(cordova.getActivity(), true);

    // initialize
    Context context = cordova.getActivity().getApplicationContext();
    XGPushManager.registerPush(context);
  }

  @Override
  public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
    if (ACTION_REGISTER_PUSH.equals(action)) {
      return registerPush(callbackContext);

    } else if (ACTION_REGISTER_ACCOUNT.equals(action)) {
      String alias = args.getString(0);
      return registerPush(alias, callbackContext);

    } else if (ACTION_UNREGISTER_PUSH.equals(action)) {
      unregisterPush();
      return true;

    }

    return false;
  }

  private boolean unregisterPush() {
    Context context = cordova.getActivity().getApplicationContext();
    XGPushManager.unregisterPush(context);
    return true;
  }

  private boolean registerPush(CallbackContext callback) {
    Context context = cordova.getActivity().getApplicationContext();
    XGPushManager.registerPush(context, new XGCordovaOperateCallback(callback));
    return true;
  }

  private boolean registerPush(String account, CallbackContext callback) {
    Context context = cordova.getActivity().getApplicationContext();
    XGPushManager.registerPush(context, account, new XGCordovaOperateCallback(callback));
    return true;
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
