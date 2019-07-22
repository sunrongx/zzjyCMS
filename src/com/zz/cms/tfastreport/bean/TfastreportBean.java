package com.zz.cms.tfastreport.bean;

public class TfastreportBean {
	//主键ID
	private int id;
	//广告名
	private String title;
	//广告内容
	private String content;
	//广告日期
	private String ctime;
	
	
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
	public String getCtime() {
		return ctime;
	}
	public void setCtime(String ctime) {
		this.ctime = ctime;
	}
	
	
}
