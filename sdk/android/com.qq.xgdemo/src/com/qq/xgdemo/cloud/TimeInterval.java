package com.qq.xgdemo.cloud;

import org.json.JSONException;
import org.json.JSONObject;

public class TimeInterval {
	public TimeInterval(int startHour, int startMin, int endHour, int endMin)
	{
		this.m_startHour = startHour;
		this.m_startMin = startMin;
		this.m_endHour = endHour;
		this.m_endMin = endMin;
	}
	
	public boolean isValid()
	{
		if (this.m_startHour>=0 && this.m_startHour<=23 &&
			this.m_startMin>=0 && this.m_startMin<=59 &&
			this.m_endHour>=0 && this.m_endHour<=23 &&
			this.m_endMin>=0 && this.m_endMin<=59)
			return true;
		else
			return false;
	}
	
	public JSONObject toJsonObject() throws JSONException
	{
		JSONObject json = new JSONObject();
		JSONObject js = new JSONObject();
		JSONObject je = new JSONObject();
		js.put("hour", String.valueOf(m_startHour));
		js.put("min", String.valueOf(m_startMin));
		je.put("hour", String.valueOf(m_endHour));
		je.put("min", String.valueOf(m_endMin));
		json.put("start", js);
		json.put("end", je);
		return json;
	}
	
	private int m_startHour;
	private int m_startMin;
	private int m_endHour;
	private int m_endMin;
}

