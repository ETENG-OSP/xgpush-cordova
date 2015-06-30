package com.eteng.push.xgpush2;

import org.apache.cordova.CallbackContext;

import com.tencent.android.tpush.XGIOperateCallback;

public class XGCordovaOperateCallback implements XGIOperateCallback {

  private CallbackContext callback;

  XGCordovaOperateCallback(CallbackContext callback) {
    this.callback = callback;
  }

  @Override
  public void onFail(Object data, int errCode, String msg) {
    this.callback.error(errCode);
  }

  @Override
  public void onSuccess(Object data, int flag) {
    this.callback.success();
  }

}
