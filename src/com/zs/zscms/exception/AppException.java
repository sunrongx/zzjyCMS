package com.zs.zscms.exception;

//异常信息
public class AppException extends Exception{
	//异常类型（自定义）
	private int errCode;
	//异常信息
	private String errMsg;
	
	public AppException(int errCode, String errMsg) {
		this.errCode = errCode;
		this.errMsg = errMsg;
	}
	
	public int getErrCode() {
		return errCode;
	}
	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	
}
