package com.qq.xgdemo.cloud;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Vector;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.annotation.SuppressLint;
import android.text.TextUtils;

public class MessageIOS {
	public MessageIOS()
	{
		this.m_sendTime = "2014-03-13 16:13:00";
		this.m_acceptTimes = new Vector<TimeInterval>();
		this.m_raw = "";
		this.m_alertStr = "";
		this.m_alertJo = new JSONObject();
		this.m_badge = 0;
		this.m_sound = "";
		this.m_category = "";
		this.m_loopInterval = -1;
		this.m_loopTimes = -1;
	}

	public void setExpireTime(int expireTime)
	{
		this.m_expireTime = expireTime;
	}

	public int getExpireTime()
	{
		return this.m_expireTime;
	}

	public void setSendTime(String sendTime)
	{
		this.m_sendTime = sendTime;
	}

	public String getSendTime()
	{
		return this.m_sendTime;
	}

	public void addAcceptTime(TimeInterval acceptTime)
	{
		this.m_acceptTimes.add(acceptTime);
	}

	public String acceptTimeToJson() throws JSONException
	{
		JSONArray json_arr = new JSONArray();
		for (TimeInterval ti : m_acceptTimes)
		{
			JSONObject jtmp = ti.toJsonObject();
			json_arr.put(jtmp);
		}
		return json_arr.toString();
	}

	public JSONArray acceptTimeToJsonArray() throws JSONException
	{
		JSONArray json_arr = new JSONArray();
		for (TimeInterval ti : m_acceptTimes)
		{
			JSONObject jtmp = ti.toJsonObject();
			json_arr.put(jtmp);
		}
		return json_arr;
	}

	public int getType()
	{
		return 0;
	}

	public void setCustom(Map<String, Object> custom)
	{
		this.m_custom = custom;
	}

	public void setRaw(String raw)
	{
		this.m_raw = raw;
	}

	public void setAlert(String alert)
	{
		m_alertStr = alert;
	}

	public void setAlert(JSONObject alert)
	{
		m_alertJo = alert;
	}

	public void setBadge(int badge)
	{
		m_badge = badge;
	}

	public void setSound(String sound)
	{
		m_sound = sound;
	}

	public void setCategory(String category) {
		m_category = category;
	}

	public int getLoopInterval()
	{
		return m_loopInterval;
	}

	public void setLoopInterval(int loopInterval)
	{
		m_loopInterval = loopInterval;
	}

	public int getLoopTimes()
	{
		return m_loopTimes;
	}

	public void setLoopTimes(int loopTimes)
	{
		m_loopTimes = loopTimes;
	}

	@SuppressLint("SimpleDateFormat")
	public boolean isValid()
	{
		if (!TextUtils.isEmpty(m_raw))
			return true;
		if (m_expireTime < 0 || m_expireTime > 3 * 24 * 60 * 60)
			return false;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try
		{
			sdf.parse(m_sendTime);
		} catch (ParseException e) {
			return false;
		}
		for (TimeInterval ti : m_acceptTimes) {
			if (!ti.isValid())
				return false;
		}
		if (TextUtils.isEmpty(m_alertStr) && m_alertJo.length() == 0)
			return false;

		return true;
	}

	public String toJson() throws JSONException
	{
		if (!TextUtils.isEmpty(m_raw))
			return m_raw;
		JSONObject json = new JSONObject(m_custom);
		json.put("accept_time", acceptTimeToJsonArray());
		JSONObject aps = new JSONObject();
		if (m_alertJo.length() != 0)
			aps.put("alert", m_alertJo);
		else
			aps.put("alert", m_alertStr);
		if (m_badge != 0)
			aps.put("badge", m_badge);
		if (!TextUtils.isEmpty(m_sound))
			aps.put("sound", m_sound);
		if (!TextUtils.isEmpty(m_category))
			aps.put("category", m_category);
		json.put("aps", aps);

		return json.toString();
	}

	private int m_expireTime;
	private String m_sendTime;
	private Vector<TimeInterval> m_acceptTimes;
	private Map<String, Object> m_custom;
	private String m_raw;
	private String m_alertStr;
	private JSONObject m_alertJo;
	private int m_badge;
	private String m_sound;
	private String m_category;
	private int m_loopInterval;
	private int m_loopTimes;
}
