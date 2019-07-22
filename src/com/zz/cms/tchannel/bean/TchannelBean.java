package com.zz.cms.tchannel.bean;

public class TchannelBean {
	private int id;
	private String cname;
	private int pid;
	private int lev;
	private int isleaf;
	private int sort;
	
	private String pname;
	private String strlev;
	private String strIsleaf;
	
	
	public String getStrlev() {
		return strlev;
	}
	public void setStrlev(String strlev) {
		this.strlev = strlev;
	}
	
	public String getStrIsleaf() {
		return strIsleaf;
	}
	public void setStrIsleaf(String strIsleaf) {
		this.strIsleaf = strIsleaf;
	}
	
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getLev() {
		return lev;
	}
	public void setLev(int lev) {
		this.lev = lev;
	}
	public int getIsleaf() {
		return isleaf;
	}
	public void setIsleaf(int isleaf) {
		this.isleaf = isleaf;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}

	
}
