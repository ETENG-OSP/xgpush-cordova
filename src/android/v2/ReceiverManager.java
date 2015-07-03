package com.eteng.push.xgpush2;

import org.apache.cordova.CallbackContext;

import com.tencent.android.tpush.XGPushConstants;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;

public class ReceiverManager {

  protected boolean registerReceiver(Context context, CallbackContext callback) {
    BroadcastReceiver receiver = new XGCordovaPushReceiver(callback);
    IntentFilter filter = new IntentFilter();
    filter.addAction(XGPushConstants.ACTION_PUSH_MESSAGE);
    filter.addAction(XGPushConstants.ACTION_FEEDBACK);
    context.registerReceiver(receiver, filter);
    return true;
  }

}
