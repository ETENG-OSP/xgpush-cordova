package com.qq.xgdemo.cloud;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;

public class XingeApp {

	public static final String RESTAPI_PUSHSINGLEDEVICE = "http://openapi.xg.qq.com/v2/push/single_device";
	public static final String RESTAPI_PUSHSINGLEACCOUNT = "http://openapi.xg.qq.com/v2/push/single_account";
	public static final String RESTAPI_PUSHACCOUNTLIST = "http://openapi.xg.qq.com/v2/push/account_list";
	public static final String RESTAPI_PUSHALLDEVICE = "http://openapi.xg.qq.com/v2/push/all_device";
	public static final String RESTAPI_PUSHTAGS = "http://openapi.xg.qq.com/v2/push/tags_device";
	public static final String RESTAPI_QUERYPUSHSTATUS = "http://openapi.xg.qq.com/v2/push/get_msg_status";
	public static final String RESTAPI_QUERYDEVICECOUNT = "http://openapi.xg.qq.com/v2/application/get_app_device_num";
	public static final String RESTAPI_QUERYTAGS = "http://openapi.xg.qq.com/v2/tags/query_app_tags";
	public static final String RESTAPI_CANCELTIMINGPUSH = "http://openapi.xg.qq.com/v2/push/cancel_timing_task";
	public static final String RESTAPI_BATCHSETTAG = "http://openapi.xg.qq.com/v2/tags/batch_set";
	public static final String RESTAPI_BATCHDELTAG = "http://openapi.xg.qq.com/v2/tags/batch_del";
	public static final String RESTAPI_QUERYTOKENTAGS = "http://openapi.xg.qq.com/v2/tags/query_token_tags";
	public static final String RESTAPI_QUERYTAGTOKENNUM = "http://openapi.xg.qq.com/v2/tags/query_tag_token_num";

	public static final String HTTP_POST = "POST";
	public static final String HTTP_GET = "GET";

	public static final int DEVICE_ALL = 0;
	public static final int DEVICE_BROWSER = 1;
	public static final int DEVICE_PC = 2;
	public static final int DEVICE_ANDROID = 3;
	public static final int DEVICE_IOS = 4;
	public static final int DEVICE_WINPHONE = 5;

	public static final int IOSENV_PROD = 1;
	public static final int IOSENV_DEV = 2;

	public static void main(String[] args) {
		System.out.println("Hello xg!");
	}

	public JSONObject pushSingleDevice(String deviceToken, Message message) throws JSONException
	{
		if (!message.isValid())
		{
			return new JSONObject("{'ret_code':-1,'err_msg':'message invalid!'}");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("access_id", this.m_accessId);
		params.put("expire_time", message.getExpireTime());
		params.put("send_time", message.getSendTime());
		params.put("multi_pkg", message.getMultiPkg());
		params.put("device_token", deviceToken);
		params.put("message_type", message.getType());
		params.put("message", message.toJson());
		params.put("timestamp", System.currentTimeMillis() / 1000);

		return callRestful(XingeApp.RESTAPI_PUSHSINGLEDEVICE, params);
	}

	public JSONObject pushSingleDevice(String deviceToken, MessageIOS message, int environment) throws JSONException
	{
		if (!message.isValid())
		{
			return new JSONObject("{'ret_code':-1,'err_msg':'message invalid!'}");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("access_id", this.m_accessId);
		params.put("expire_time", message.getExpireTime());
		params.put("send_time", message.getSendTime());
		params.put("device_token", deviceToken);
		params.put("message_type", message.getType());
		params.put("message", message.toJson());
		params.put("timestamp", System.currentTimeMillis() / 1000);
		params.put("environment", environment);

		if (message.getLoopInterval() > 0 && message.getLoopTimes() > 0)
		{
			params.put("loop_interval", message.getLoopInterval());
			params.put("loop_times", message.getLoopTimes());
		}

		return callRestful(XingeApp.RESTAPI_PUSHSINGLEDEVICE, params);
	}

	public JSONObject pushSingleAccount(int deviceType, String account, Message message) throws JSONException
	{
		if (!message.isValid())
		{
			return new JSONObject("{'ret_code':-1,'err_msg':'message invalid!'}");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("access_id", this.m_accessId);
		params.put("expire_time", message.getExpireTime());
		params.put("send_time", message.getSendTime());
		params.put("multi_pkg", message.getMultiPkg());
		params.put("device_type", deviceType);
		params.put("account", account);
		params.put("message_type", message.getType());
		params.put("message", message.toJson());
		params.put("timestamp", System.currentTimeMillis() / 1000);

		return callRestful(XingeApp.RESTAPI_PUSHSINGLEACCOUNT, params);
	}

	public JSONObject pushAccountList(int deviceType, List<String> accountList, Message message) throws JSONException
	{
		if (!message.isValid())
		{
			return new JSONObject("{'ret_code':-1,'err_msg':'message invalid!'}");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("access_id", this.m_accessId);
		params.put("expire_time", message.getExpireTime());
		params.put("multi_pkg", message.getMultiPkg());
		params.put("device_type", deviceType);
		params.put("account_list", new JSONArray(accountList).toString());
		params.put("message_type", message.getType());
		params.put("message", message.toJson());
		params.put("timestamp", System.currentTimeMillis() / 1000);

		return callRestful(XingeApp.RESTAPI_PUSHACCOUNTLIST, params);
	}

	public JSONObject pushSingleAccount(int deviceType, String account, MessageIOS message, int environment) throws JSONException
	{
		if (!message.isValid())
		{
			return new JSONObject("{'ret_code':-1,'err_msg':'message invalid!'}");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("access_id", this.m_accessId);
		params.put("expire_time", message.getExpireTime());
		params.put("send_time", message.getSendTime());
		params.put("device_type", deviceType);
		params.put("account", account);
		params.put("message_type", message.getType());
		params.put("message", message.toJson());
		params.put("timestamp", System.currentTimeMillis() / 1000);
		params.put("environment", environment);

		return callRestful(XingeApp.RESTAPI_PUSHSINGLEACCOUNT, params);
	}

	public JSONObject pushAccountList(int deviceType, List<String> accountList, MessageIOS message, int environment) throws JSONException
	{
		if (!message.isValid())
		{
			return new JSONObject("{'ret_code':-1,'err_msg':'message invalid!'}");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("access_id", this.m_accessId);
		params.put("expire_time", message.getExpireTime());
		params.put("device_type", deviceType);
		params.put("account_list", new JSONArray(accountList).toString());
		params.put("message_type", message.getType());
		params.put("message", message.toJson());
		params.put("timestamp", System.currentTimeMillis() / 1000);
		params.put("environment", environment);

		return callRestful(XingeApp.RESTAPI_PUSHACCOUNTLIST, params);
	}

	public JSONObject pushAllDevice(int deviceType, Message message) throws JSONException
	{
		if (!message.isValid())
		{
			return new JSONObject("{'ret_code':-1,'err_msg':'message invalid!'}");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("access_id", this.m_accessId);
		params.put("expire_time", message.getExpireTime());
		params.put("send_time", message.getSendTime());
		params.put("multi_pkg", message.getMultiPkg());
		params.put("device_type", deviceType);
		params.put("message_type", message.getType());
		params.put("message", message.toJson());
		params.put("timestamp", System.currentTimeMillis() / 1000);

		if (message.getLoopInterval() > 0 && message.getLoopTimes() > 0)
		{
			params.put("loop_interval", message.getLoopInterval());
			params.put("loop_times", message.getLoopTimes());
		}

		return callRestful(XingeApp.RESTAPI_PUSHALLDEVICE, params);
	}

	public JSONObject pushAllDevice(int deviceType, MessageIOS message, int environment) throws JSONException
	{
		if (!message.isValid())
		{
			return new JSONObject("{'ret_code':-1,'err_msg':'message invalid!'}");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("access_id", this.m_accessId);
		params.put("expire_time", message.getExpireTime());
		params.put("send_time", message.getSendTime());
		params.put("device_type", deviceType);
		params.put("message_type", message.getType());
		params.put("message", message.toJson());
		params.put("timestamp", System.currentTimeMillis() / 1000);
		params.put("environment", environment);

		if (message.getLoopInterval() > 0 && message.getLoopTimes() > 0)
		{
			params.put("loop_interval", message.getLoopInterval());
			params.put("loop_times", message.getLoopTimes());
		}

		return callRestful(XingeApp.RESTAPI_PUSHALLDEVICE, params);
	}

	public JSONObject pushTags(int deviceType, List<String> tagList, String tagOp, Message message) throws JSONException
	{
		if (!message.isValid() || tagList.size() == 0 || (!tagOp.equals("AND") && !tagOp.equals("OR")))
		{
			return new JSONObject("{'ret_code':-1,'err_msg':'param invalid!'}");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("access_id", this.m_accessId);
		params.put("expire_time", message.getExpireTime());
		params.put("send_time", message.getSendTime());
		params.put("multi_pkg", message.getMultiPkg());
		params.put("device_type", deviceType);
		params.put("message_type", message.getType());
		params.put("tags_list", new JSONArray(tagList).toString());
		params.put("tags_op", tagOp);
		params.put("message", message.toJson());
		params.put("timestamp", System.currentTimeMillis() / 1000);

		if (message.getLoopInterval() > 0 && message.getLoopTimes() > 0)
		{
			params.put("loop_interval", message.getLoopInterval());
			params.put("loop_times", message.getLoopTimes());
		}

		return callRestful(XingeApp.RESTAPI_PUSHTAGS, params);
	}

	public JSONObject pushTags(int deviceType, List<String> tagList, String tagOp, MessageIOS message, int environment) throws JSONException
	{
		if (!message.isValid() || tagList.size() == 0 || (!tagOp.equals("AND") && !tagOp.equals("OR")))
		{
			return new JSONObject("{'ret_code':-1,'err_msg':'param invalid!'}");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("access_id", this.m_accessId);
		params.put("expire_time", message.getExpireTime());
		params.put("send_time", message.getSendTime());
		params.put("device_type", deviceType);
		params.put("message_type", message.getType());
		params.put("tags_list", new JSONArray(tagList).toString());
		params.put("tags_op", tagOp);
		params.put("message", message.toJson());
		params.put("timestamp", System.currentTimeMillis() / 1000);
		params.put("environment", environment);

		if (message.getLoopInterval() > 0 && message.getLoopTimes() > 0)
		{
			params.put("loop_interval", message.getLoopInterval());
			params.put("loop_times", message.getLoopTimes());
		}

		return callRestful(XingeApp.RESTAPI_PUSHTAGS, params);
	}

	public JSONObject queryPushStatus(List<String> pushIdList) throws JSONException
	{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("access_id", this.m_accessId);
		params.put("timestamp", System.currentTimeMillis() / 1000);
		JSONArray jArray = new JSONArray();
		for (String pushId : pushIdList) {
			JSONObject js = new JSONObject();
			js.put("push_id", pushId);
			jArray.put(js);
		}
		params.put("push_ids", jArray.toString());

		return callRestful(XingeApp.RESTAPI_QUERYPUSHSTATUS, params);
	}

	public JSONObject queryDeviceCount() throws JSONException
	{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("access_id", this.m_accessId);
		params.put("timestamp", System.currentTimeMillis() / 1000);

		return callRestful(XingeApp.RESTAPI_QUERYDEVICECOUNT, params);
	}

	public JSONObject queryTags(int start, int limit) throws JSONException
	{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("access_id", this.m_accessId);
		params.put("start", start);
		params.put("limt", limit);
		params.put("timestamp", System.currentTimeMillis() / 1000);

		return callRestful(XingeApp.RESTAPI_QUERYTAGS, params);
	}

	public JSONObject queryTags() throws JSONException
	{
		return queryTags(0, 100);
	}

	public JSONObject queryTagTokenNum(String tag) throws JSONException
	{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("access_id", this.m_accessId);
		params.put("tag", tag);
		params.put("timestamp", System.currentTimeMillis() / 1000);

		return callRestful(XingeApp.RESTAPI_QUERYTAGTOKENNUM, params);
	}

	public JSONObject queryTokenTags(String deviceToken) throws JSONException
	{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("access_id", this.m_accessId);
		params.put("device_token", deviceToken);
		params.put("timestamp", System.currentTimeMillis() / 1000);

		return callRestful(XingeApp.RESTAPI_QUERYTOKENTAGS, params);
	}

	public JSONObject cancelTimingPush(String pushId) throws JSONException
	{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("access_id", this.m_accessId);
		params.put("push_id", pushId);
		params.put("timestamp", System.currentTimeMillis() / 1000);

		return callRestful(XingeApp.RESTAPI_CANCELTIMINGPUSH, params);
	}

	public XingeApp(long accessId, String secretKey)
	{
		this.m_accessId = accessId;
		this.m_secretKey = secretKey;
	}

	protected String generateSign(String method, String url, Map<String, Object> params)
	{
		List<Map.Entry<String, Object>> paramList = new ArrayList<Map.Entry<String, Object>>(params.entrySet());
		Collections.sort(paramList, new Comparator<Map.Entry<String, Object>>() {
			public int compare(Map.Entry<String, Object> o1, Map.Entry<String, Object> o2) {
				return (o1.getKey()).toString().compareTo(o2.getKey());
			}
		});
		String paramStr = "";
		String md5Str = "";
		for (Map.Entry<String, Object> entry : paramList) {
			paramStr += entry.getKey() + "=" + entry.getValue().toString();
		}
		try
		{
			URL u = new URL(url);
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			String s = method + u.getHost() + u.getPath() + paramStr + this.m_secretKey;
			byte[] bArr = s.getBytes("UTF-8");
			byte[] md5Value = md5.digest(bArr);
			BigInteger bigInt = new BigInteger(1, md5Value);
			md5Str = bigInt.toString(16);
			while (md5Str.length() < 32) {
				md5Str = "0" + md5Str;
			}
			// System.out.println(s);
		} catch (Exception e)
		{
			// e.printStackTrace();
			return "";
		}
		return md5Str;
	}

	protected JSONObject callRestful(String url, Map<String, Object> params) throws JSONException
	{
		String temp;
		String ret = "";
		JSONObject jsonRet = null;
		String sign = generateSign("POST", url, params);
		if (TextUtils.isEmpty(sign))
			return new JSONObject("{\"ret_code\":-1,\"err_msg\":\"generateSign error\"}");
		params.put("sign", sign);
		try {
			URL u = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();
			conn.setRequestMethod("POST");
			conn.setConnectTimeout(10000);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			StringBuffer param = new StringBuffer();
			for (String key : params.keySet())
			{
				param.append(key).append("=").append(URLEncoder.encode(params.get(key).toString(), "UTF-8")).append("&");
			}
			conn.getOutputStream().write(param.toString().getBytes("UTF-8"));
			// System.out.println(param);
			conn.getOutputStream().flush();
			conn.getOutputStream().close();
			InputStreamReader isr = new InputStreamReader(conn.getInputStream());
			BufferedReader br = new BufferedReader(isr);
			while ((temp = br.readLine()) != null) {
				ret += temp;
			}
			br.close();
			isr.close();
			conn.disconnect();
			// System.out.println(ret);
			jsonRet = new JSONObject(ret);

		} catch (java.net.SocketTimeoutException e) {
			// e.printStackTrace();
			jsonRet = new JSONObject("{\"ret_code\":-1,\"err_msg\":\"call restful timeout\"}");
		} catch (Exception e) {
			// e.printStackTrace();
			jsonRet = new JSONObject("{\"ret_code\":-1,\"err_msg\":\"call restful error\"}");
		}
		return jsonRet;
	}

	protected boolean ValidateToken(String token) {
		if (this.m_accessId >= 2200000000L) {
			return token.length() == 64;
		} else {
			return (token.length() == 40 || token.length() == 64);
		}
	}

	protected Map<String, Object> InitParams() {
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("access_id", this.m_accessId);
		params.put("timestamp", System.currentTimeMillis() / 1000);

		return params;
	}

	public JSONObject BatchSetTag(List<TagTokenPair> tagTokenPairs) throws JSONException
	{

		for (TagTokenPair pair : tagTokenPairs) {
			if (!this.ValidateToken(pair.token)) {
				String returnMsgJsonStr = String.format("{\"ret_code\":-1,\"err_msg\":\"invalid token %s\"}", pair.token);
				return new JSONObject(returnMsgJsonStr);
			}
		}

		Map<String, Object> params = this.InitParams();

		List<List> tag_token_list = new ArrayList<List>();

		for (TagTokenPair pair : tagTokenPairs) {
			List<String> singleTagToken = new ArrayList<String>();
			singleTagToken.add(pair.tag);
			singleTagToken.add(pair.token);

			tag_token_list.add(singleTagToken);
		}

		params.put("tag_token_list", new JSONArray(tag_token_list).toString());

		return callRestful(XingeApp.RESTAPI_BATCHSETTAG, params);
	}

	public JSONObject BatchDelTag(List<TagTokenPair> tagTokenPairs) throws JSONException
	{

		for (TagTokenPair pair : tagTokenPairs) {
			if (!this.ValidateToken(pair.token)) {
				String returnMsgJsonStr = String.format("{\"ret_code\":-1,\"err_msg\":\"invalid token %s\"}", pair.token);
				return new JSONObject(returnMsgJsonStr);
			}
		}

		Map<String, Object> params = this.InitParams();

		List<List> tag_token_list = new ArrayList<List>();

		for (TagTokenPair pair : tagTokenPairs) {
			List<String> singleTagToken = new ArrayList<String>();
			singleTagToken.add(pair.tag);
			singleTagToken.add(pair.token);

			tag_token_list.add(singleTagToken);
		}

		params.put("tag_token_list", new JSONArray(tag_token_list).toString());

		return callRestful(XingeApp.RESTAPI_BATCHDELTAG, params);
	}

	private long m_accessId;
	private String m_secretKey;
}
