package com.eteng.push.xgpush;

import android.content.Context;
import android.content.Intent;

import com.tencent.android.tpush.XGPushBaseReceiver;
import com.tencent.android.tpush.XGPushClickedResult;
import com.tencent.android.tpush.XGPushRegisterResult;
import com.tencent.android.tpush.XGPushShowedResult;
import com.tencent.android.tpush.XGPushTextMessage;

public class XGReceiver extends XGPushBaseReceiver {
	
	public static final String TAG = "XGReceiver";
	
	@Override
	public void onTextMessage(Context context, XGPushTextMessage message) {
		Intent intent = new Intent();
		intent.putExtra("Title", message.getTitle());
		intent.putExtra("Content", message.getContent());
		
		PushPlugin.handlePushMessage(context, intent);
	}

	@Override
	public void onRegisterResult(Context context, int errorCode, XGPushRegisterResult registerMessage) {
	}

	@Override
	public void onUnregisterResult(Context context, int errorCode) {
	}

	@Override
	public void onSetTagResult(Context context, int errorCode, String tagName) {
	}

	@Override
	public void onDeleteTagResult(Context context, int errorCode, String tagName) {
	}

	@Override
	public void onNotifactionClickedResult(Context context, XGPushClickedResult message) {
	}

	@Override
	public void onNotifactionShowedResult(Context context, XGPushShowedResult notifiShowedRlt) {
	}
}
