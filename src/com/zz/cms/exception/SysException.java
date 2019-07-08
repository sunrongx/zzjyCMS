package com.zz.cms.exception;

public class SysException extends Exception {
	/**
	 * 自定义异常
	 */
	private static final long serialVersionUID = -6121774509153153319L;
		//异常类型（自定义）
		private int errCode;
		//异常信息
		private String errMsg;
		
		public SysException(int errCode, String errMsg) {
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
