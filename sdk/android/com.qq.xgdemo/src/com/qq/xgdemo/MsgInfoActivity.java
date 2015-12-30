package com.qq.xgdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.qq.xgdemo.R;
import com.tencent.android.tpush.XGPushManager;

public class MsgInfoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_msginfo);

		Bundle xgnotification = this.getIntent().getExtras();
		TextView title_id = (TextView) this.findViewById(R.id.title_id);
		title_id.setText("ID:" + xgnotification.getLong("msg_id"));
		TextView textView = (TextView) this.findViewById(R.id.title);
		textView.setText(xgnotification.getString("title"));
		textView = (TextView) this.findViewById(R.id.content);
		textView.setText(xgnotification.getString("content"));
		textView = (TextView) this.findViewById(R.id.update_time);
		textView.setText("到达时间：" + xgnotification.getString("update_time"));
		textView = (TextView) this.findViewById(R.id.activityType);
		TextView textViewContent = (TextView) this
				.findViewById(R.id.activityContent);
		if (xgnotification.getInt("notificationActionType", 0) == 1) {
			textView.setText("特定页面：");
		} else if (xgnotification.getInt("notificationActionType", 0) == 2) {
			textView.setText(" URL：");
		} else if (xgnotification.getInt("notificationActionType", 0) == 3) {
			textView.setText("Intent:");
		}
		textViewContent.setText(xgnotification.getString("activity"));
		findViewById(R.id.arrow).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

	}

	@Override
	protected void onResume() {
		super.onResume();
		XGPushManager.onActivityStarted(this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		XGPushManager.onActivityStoped(this);
	}

}
