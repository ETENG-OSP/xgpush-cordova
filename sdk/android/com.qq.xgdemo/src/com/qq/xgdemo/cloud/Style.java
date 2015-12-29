package com.qq.xgdemo.cloud;

public class Style {
	public Style(int builderId)
	{
		this(builderId, 0, 0, 1, 0, 1, 0, 1);
	}
	public Style(int builderId, int ring, int vibrate, int clearable, int nId)
	{
		this.m_builderId = builderId;
		this.m_ring = ring;
		this.m_vibrate = vibrate;
		this.m_clearable = clearable;
		this.m_nId = nId;
	}
	public Style(int builderId, int ring, int vibrate, int clearable, 
			int nId, int lights, int iconType, int styleId)
	{
		this.m_builderId = builderId;
		this.m_ring = ring;
		this.m_vibrate = vibrate;
		this.m_clearable = clearable;
		this.m_nId = nId;
		this.m_lights = lights;
		this.m_iconType = iconType;
		this.m_styleId = styleId;
	}
	public int getBuilderId()
	{
		return m_builderId;
	}
	public int getRing()
	{
		return m_ring;
	}
	public int getVibrate()
	{
		return m_vibrate;
	}
	public int getClearable()
	{
		return m_clearable;
	}
	public int getNId()
	{
		return m_nId;
	}
	public int getLights() 
	{
		return m_lights;
	}
	public int getIconType()
	{
		return m_iconType;
	}
	public int getStyleId()
	{
		return m_styleId;
	}
	public void setRingRaw(String ringRaw) 
	{
		this.m_ringRaw = ringRaw;
	}
	public String getRingRaw()
	{
		return m_ringRaw;
	}
	public void setIconRes(String iconRes)
	{
		this.m_iconRes = iconRes;
	}
	public String getIconRes()
	{
		return m_iconRes;
	}
	public void setSmallIcon(String smallIcon)
	{
		this.m_smallIcon = smallIcon;
	}
	public String getSmallIcon()
	{
		return m_smallIcon;
	}
	
	public boolean isValid()
	{
		if (m_ring<0 || m_ring>1) return false;
		if (m_vibrate<0 || m_vibrate>1) return false;
		if (m_clearable<0 || m_clearable>1) return false;
		if (m_lights<0 || m_lights>1) return false;
		if (m_iconType<0 || m_iconType>1) return false;
		if (m_styleId<0 || m_styleId>1) return false;
		
		return true;
	}
	
	private int m_builderId;
	private int m_ring;
	private int m_vibrate;
	private int m_clearable;
	private int m_nId;
	private String m_ringRaw;
	private int m_lights;
	private int m_iconType;
	private String m_iconRes;
	private int m_styleId;
	private String m_smallIcon;
}
