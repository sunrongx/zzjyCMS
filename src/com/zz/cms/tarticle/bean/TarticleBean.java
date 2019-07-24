package com.zz.cms.tarticle.bean;

public class TarticleBean  {
	//文章id
	private int id;
	//文章标题
	private String title;
	//文章内容
	private String content;
	//文章作者
	private String auther;
	//文章创建时间
	private String ctime;
	//文章所属栏目
	private int channel;
	//文章是否推荐
	private int isremod;
	//文章热度
	private int ishot;
	//文章所属栏目名称
	private String strChannel;
	//文章推荐文字
	private String strIsremod;
	//文章热度文字
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
	public String getCtime() {
		return ctime;
	}
	public void setCtime(String ctime) {
		this.ctime = ctime;
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
