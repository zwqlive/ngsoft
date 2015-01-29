package org.ngsoft.core.script;

/**
 * 脚本加载结果
 * 
 * @author will
 * @date 2015年1月25日 下午3:25:11
 *
 */
public class ScriptLoadResult {
	
	private int resultCode;
	
	private String errMsg;
	
	public int getResultCode() {
		return resultCode;
	}
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	
}
