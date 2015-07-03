package com.eteng.push.xgpush2;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.tencent.android.tpush.XGPushBaseReceiver;
import com.tencent.android.tpush.XGPushClickedResult;
import com.tencent.android.tpush.XGPushRegisterResult;
import com.tencent.android.tpush.XGPushShowedResult;
import com.tencent.android.tpush.XGPushTextMessage;

public class XGCordovaPushReceiver extends XGPushBaseReceiver {

  private CallbackContext callback;

  public XGCordovaPushReceiver(CallbackContext callback) {
    this.callback = callback;
  }

  @Override
  public void onDeleteTagResult(Context arg0, int arg1, String arg2) {
  }

  @Override
  public void onNotifactionClickedResult(Context arg0, XGPushClickedResult arg1) {
  }

  @Override
  public void onNotifactionShowedResult(Context arg0, XGPushShowedResult arg1) {
  }

  @Override
  public void onRegisterResult(Context arg0, int arg1, XGPushRegisterResult arg2) {
  }

  @Override
  public void onSetTagResult(Context arg0, int arg1, String arg2) {
  }

  @Override
  public void onTextMessage(Context arg0, XGPushTextMessage message) {
    JSONObject data = new JSONObject();
    try {
      data.put("content", message.getContent());
      data.put("title", message.getTitle());
      data.put("customContent", message.getCustomContent());
    } catch (JSONException e) {
      e.printStackTrace();
    }
    PluginResult results = new PluginResult(PluginResult.Status.OK, data);
    results.setKeepCallback(true);
    this.callback.sendPluginResult(results);
  }

  @Override
  public void onUnregisterResult(Context arg0, int arg1) {
  }

}
