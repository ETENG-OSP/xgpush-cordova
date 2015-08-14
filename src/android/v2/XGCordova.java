package com.eteng.push.xgpush2;

import org.apache.cordova.CallbackContext;

import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushManager;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

public class XGCordova extends XGPushPlugin {

  private static final String TAG = "XGCordovaPlugin";
  private ReceiverManager manager = new ReceiverManager();

  @Override
  protected boolean registerPush(Context context, String alias, CallbackContext callback) {
    Log.d(TAG, "> register: " + alias);
    XGIOperateCallback reply = new XGCordovaOperateCallback(callback);
    if (TextUtils.isEmpty(alias)) {
      Log.d(TAG, "> register public");
      XGPushManager.registerPush(context, reply);
    } else {
      Log.d(TAG, "> register private");
      XGPushManager.registerPush(context, alias, reply);
    }

    return true;
  }

  @Override
  protected boolean unregisterPush(Context context, CallbackContext callback) {
    XGIOperateCallback reply = new XGCordovaOperateCallback(callback);
    XGPushManager.unregisterPush(context, reply);
    return true;
  }

  @Override
  protected boolean addListener(Context context, CallbackContext callback) {
    return manager.registerReceiver(context, callback);
  }

}
