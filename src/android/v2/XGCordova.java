package com.eteng.push.xgpush2;

import org.apache.cordova.CallbackContext;

import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushManager;

import android.content.Context;

public class XGCordova extends XGPushPlugin {

  @Override
  protected boolean registerPush(Context context, String alias, CallbackContext callback) {
    // Log.d(TAG, "> register: " + alias);
    XGIOperateCallback reply = new XGCordovaOperateCallback(callback);
    if (alias != null && !alias.trim().isEmpty()) {
      XGPushManager.registerPush(context, reply);
    } else {
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

}
