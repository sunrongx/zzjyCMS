package com.zs.zscms.tarticle.bean;

public class TarticleBean {
	private int id;
	private String title;
	private String content;
	private String auther;
	private String crtime;
	private int channel;
	private int isremod;
	private int ishot;
	
	private String strChannel;
	private String strIsremod;
	private String strIshot;
	
	public String getStrChannel() {
		return strChannel;
	}
	public void setStrChannel(String strChannel) {
		this.strChannel = strChannel;
	}
	public String getStrIsremod() {
		return strIsremod;
	}
	public void setStrIsremod(String strIsremod) {
		this.strIsremod = strIsremod;
	}
	public String getStrIshot() {
		return strIshot;
	}
	public void setStrIshot(String strIshot) {
		this.strIshot = strIshot;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAuther() {
		return auther;
	}
	public void setAuther(String auther) {
		this.auther = auther;
	}
	public String getCrtime() {
		return crtime;
	}
	public void setCrtime(String crtime) {
		this.crtime = crtime;
	}
	public int getChannel() {
		return channel;
	}
	public void setChannel(int channel) {
		this.channel = channel;
	}
	public int getIsremod() {
		return isremod;
	}
	public void setIsremod(int isremod) {
		this.isremod = isremod;
	}
	public int getIshot() {
		return ishot;
	}
	public void setIshot(int ishot) {
		this.ishot = ishot;
	}
}
