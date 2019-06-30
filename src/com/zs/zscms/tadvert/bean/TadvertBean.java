package com.zs.zscms.tadvert.bean;

public class TadvertBean {
	//主键ID
	private int id;
	//广告名
	private String title;
	//广告内容
	private String content;
	//广告日期
	private String crtime;
	//创建人
	private String crman;
	
	
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
	public String getCrtime() {
		return crtime;
	}
	public void setCrtime(String crtime) {
		this.crtime = crtime;
	}
	public String getCrman() {
		return crman;
	}
	public void setCrman(String crman) {
		this.crman = crman;
	}
	
}
