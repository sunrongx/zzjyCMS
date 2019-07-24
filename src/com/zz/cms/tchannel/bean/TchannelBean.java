package com.zz.cms.tchannel.bean;

/**
 * 栏目bean
 * @author Administrator
 *
 */
public class TchannelBean {
	//栏目id
	private int id;
	//栏目名称
	private String cname;
	//上级栏目
	private int pid;
	//栏目级别
	private int lev;
	//是否叶子
	private int isleaf;
	//栏目顺序
	private int sort;
	
	//上级栏目名称
	private String pname;
	//栏目级别文字
	private String strlev;
	//是否叶子文字
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
