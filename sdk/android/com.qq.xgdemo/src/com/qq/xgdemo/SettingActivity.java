package com.qq.xgdemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.content.Context;
import android.content.DialogInterface;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.tencent.android.tpush.XGBasicPushNotificationBuilder;
import com.tencent.android.tpush.XGCustomPushNotificationBuilder;
import com.tencent.android.tpush.XGLocalMessage;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;
import com.tencent.android.tpush.horse.Tools;

public class SettingActivity extends Activity {
	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		context = this;
		initComponent();
		// 通知自定义初始化（如果需要自定义通知view）
		// initCustomPushNotificationBuilder(context);
	}

	/**
	 * 设置自定义样式，这样在下发通知时可以指定build_id。编号由开发者自己维护,build_id=0为默认设置
	 * 
	 * @param context
	 */
	@SuppressWarnings("unused")
	private void initNotificationBuilder(Context context) {
		// 新建自定义样式
		XGBasicPushNotificationBuilder build = new XGBasicPushNotificationBuilder();
		// 设置自定义样式属性，该属性对对应的编号生效，指定后不能修改。
		build.setIcon(R.drawable.ic_launcher)
				.setSound(
						RingtoneManager.getActualDefaultRingtoneUri(
								getApplicationContext(),
								RingtoneManager.TYPE_ALARM)) // 设置声音
				.setDefaults(Notification.DEFAULT_VIBRATE) // 振动
				.setFlags(Notification.FLAG_NO_CLEAR); // 是否可清除
		// 设置通知样式，样式编号为2，即build_id为2，可通过后台脚本指定
		// XGPushManager.setPushNotificationBuilder(getApplicationContext(),
		// build_id, build);

		// 下同
		// XGBasicPushNotificationBuilder build11 = new
		// XGBasicPushNotificationBuilder();
		// build11.setIcon(R.drawable.ic_launcher)
		// .setSound(
		// RingtoneManager
		// .getDefaultUri(RingtoneManager.TYPE_ALARM))
		// .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
		// .setFlags(Notification.FLAG_NO_CLEAR);
		// XGPushManager.setPushNotificationBuilder(getApplicationContext(), 5,
		// build11);
	}

	/**
	 * 设置通知自定义View，这样在下发通知时可以指定build_id。编号由开发者自己维护,build_id=0为默认设置
	 * 
	 * @param context
	 */
	@SuppressWarnings("unused")
	private void initCustomPushNotificationBuilder(Context context) {
		XGCustomPushNotificationBuilder build = new XGCustomPushNotificationBuilder();
		build.setSound(
				RingtoneManager.getActualDefaultRingtoneUri(
						getApplicationContext(), RingtoneManager.TYPE_ALARM)) // 设置声音
				// setSound(
				// Uri.parse("android.resource://" + getPackageName()
				// + "/" + R.raw.wind)) 设定Raw下指定声音文件
				.setDefaults(Notification.DEFAULT_VIBRATE) // 振动
				.setFlags(Notification.FLAG_NO_CLEAR); // 是否可清除
		// 设置自定义通知layout,通知背景等可以在layout里设置
		build.setLayoutId(R.layout.notification);
		// 设置自定义通知内容id
		build.setLayoutTextId(R.id.content);
		// 设置自定义通知标题id
		build.setLayoutTitleId(R.id.title);
		// 设置自定义通知图片id
		build.setLayoutIconId(R.id.icon);
		// 设置自定义通知图片资源
		build.setLayoutIconDrawableId(R.drawable.logo);
		// 设置状态栏的通知小图标
		build.setIcon(R.drawable.right);
		// 设置时间id
		build.setLayoutTimeId(R.id.time);
		// 若不设定以上自定义layout，又想简单指定通知栏图片资源（注：自定义layout和setNotificationLargeIcon两种方式指定图片资源只能选其一，不能同时使用）
		// build.setNotificationLargeIcon(R.drawable.logo);
		// 客户端保存build_id
		// XGPushManager.setPushNotificationBuilder(this, build_id, build);
	}

	// 设置本地消息
	@SuppressWarnings("unused")
	private void initLocalMessage() {
		XGLocalMessage local_msg = new XGLocalMessage();
		// 设置本地消息类型，1:通知，2:消息
		local_msg.setType(1);
		// 设置消息标题
		local_msg.setTitle("qq");
		// 设置消息内容
		local_msg.setContent("ww");
		// 设置消息日期，格式为：20140502
		local_msg.setDate("20140930");
		// 设置消息触发的小时(24小时制)，例如：22代表晚上10点
		local_msg.setHour("14");
		// 获取消息触发的分钟，例如：05代表05分
		local_msg.setMin("16");
		// 设置消息样式，默认为0或不设置
		// local_msg.setBuilderId(6);
		// 设置拉起应用页面
		// local_msg.setActivity("com.qq.xgdemo.SettingActivity");
		// 设置动作类型：1打开activity或app本身，2打开浏览器，3打开Intent ，4通过包名打开应用
		// local_msg.setAction_type(1);
		// 设置URL
		// local_msg.setUrl("http://www.baidu.com");
		// 设置Intent
		// local_msg.setIntent("intent:10086#Intent;scheme=tel;action=android.intent.action.DIAL;S.key=value;end");
		// 自定义本地通知样式
		// local_msg.setIcon_type(0);
		// local_msg.setIcon_res("right");
		// 是否覆盖原先build_id的保存设置。1覆盖，0不覆盖
		// local_msg.setStyle_id(1);
		// 设置音频资源
		// local_msg.setRing_raw("mm");
		// 设置key,value
		// HashMap<String, Object> map = new HashMap<String, Object>();
		// map.put("key", "v1");
		// map.put("key2", "v2");
		// local_msg.setCustomContent(map);
		// 设置下载应用URL
		// local_msg.setPackageDownloadUrl("http://softfile.3g.qq.com:8080/msoft/179/1105/10753/MobileQQ1.0(Android)_Build0198.apk");
		// 设置要打开的应用包名
		// local_msg.setPackageName("com.example.com.qq.feedback");
		XGPushManager.addLocalNotification(context, local_msg);
	}

	private void initComponent() {
		// initCustomPushNotificationBuilder(getApplicationContext());
		// initNotificationBuilder(getApplicationContext());
		findViewById(R.id.Button_register).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						// 注册应用（必须调用本接口，否则APP将无法接收到通知和消息）
						// registerPush有2个版本的接口：带账号绑定和不带
						// registerPush可以在APP启动时或用户登陆后调用
						XGPushManager.registerPush(getApplicationContext());
					}
				});
		findViewById(R.id.Button_registerAccount).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {

						Context ctx = context;
						if (ctx != null) {
							LinearLayout layout = new LinearLayout(ctx);
							layout.setOrientation(LinearLayout.VERTICAL);
							final EditText textviewGid = new EditText(ctx);
							textviewGid.setHint("请输入需要绑定的账号");
							layout.addView(textviewGid);

							AlertDialog.Builder builder = new AlertDialog.Builder(
									ctx);
							builder.setView(layout);
							builder.setPositiveButton("账号注册",
									new DialogInterface.OnClickListener() {

										public void onClick(
												DialogInterface dialog,
												int which) {

											String text = textviewGid.getText()
													.toString();
											if (text != null
													&& text.length() != 0) {
												// 注册应用（必须调用本接口，否则APP将无法接收到通知和消息）
												// 使用绑定账号的注册接口（可针对账号下发通知和消息）
												// 可以重复注册，以最后一次注册为准
												XGPushManager
														.registerPush(
																getApplicationContext(),
																text);
											}
										}
									});
							builder.show();
						}
					}
				});

		findViewById(R.id.Button_unregister).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						// 反注册，调用本接口后，APP将停止接收通知和消息
						XGPushManager.unregisterPush(getApplicationContext());
					}
				});

		findViewById(R.id.Button_setTag).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						Context ctx = context;
						if (ctx != null) {
							LinearLayout layout = new LinearLayout(ctx);
							layout.setOrientation(LinearLayout.VERTICAL);
							final EditText textviewGid = new EditText(ctx);
							textviewGid.setHint("请输入标签名称");
							layout.addView(textviewGid);

							AlertDialog.Builder builder = new AlertDialog.Builder(
									ctx);
							builder.setView(layout);
							builder.setPositiveButton("设置标签",
									new DialogInterface.OnClickListener() {

										public void onClick(
												DialogInterface dialog,
												int which) {

											String text = textviewGid.getText()
													.toString();
											if (text != null
													&& text.length() != 0) {
												XGPushManager
														.setTag(getApplicationContext(),
																text);
											}
										}
									});
							builder.show();
						}
					}
				});
		findViewById(R.id.Button_delTag).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						Context ctx = context;
						if (ctx != null) {
							LinearLayout layout = new LinearLayout(ctx);
							layout.setOrientation(LinearLayout.VERTICAL);
							final EditText textviewGid = new EditText(ctx);
							textviewGid.setHint("请输入标签名称");
							layout.addView(textviewGid);

							AlertDialog.Builder builder = new AlertDialog.Builder(
									ctx);
							builder.setView(layout);
							builder.setPositiveButton("删除标签",
									new DialogInterface.OnClickListener() {

										public void onClick(
												DialogInterface dialog,
												int which) {

											String text = textviewGid.getText()
													.toString();
											if (text != null
													&& text.length() != 0) {
												XGPushManager.deleteTag(
														SettingActivity.this,
														text);
											}
										}
									});
							builder.show();
						}
					}
				});

		findViewById(R.id.Button_clearCache).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						Tools.clearCacheServerItems(getApplicationContext());
						Tools.clearOptStrategyItem(getApplicationContext());
					}
				});
		findViewById(R.id.Button_copyToken).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						String token = XGPushConfig
								.getToken(getApplicationContext());
						if (token != null && !"".equals(token)) {
							ClipboardManager copy = (ClipboardManager) SettingActivity.this
									.getSystemService(Context.CLIPBOARD_SERVICE);
							copy.setText(token);
						} else {
							Toast.makeText(SettingActivity.this,
									"请先注册，获取token！", Toast.LENGTH_SHORT).show();
						}
					}
				});
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
