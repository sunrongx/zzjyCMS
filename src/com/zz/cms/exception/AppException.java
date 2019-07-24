package com.zz.cms.exception;

//异常信息
public class AppException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//异常类型（自定义）
	private int errCode;
	//异常信息
	private String errMsg;
	/**
	 * 应用异常
	 * @param errCode
	 * @param errMsg
	 */
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
