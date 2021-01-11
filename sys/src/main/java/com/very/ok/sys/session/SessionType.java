package com.very.ok.sys.session;

import javax.validation.constraints.NotNull;

/**
 * @author yds
 *
 */
public class SessionType {
	public static final int USER_NAME = 1;
	public static final int MAIL = 2;
	public static final int MOBILE = 3;
	
	
	/**
	 * 登录类型
	 */
	@NotNull
	private int type;
	
	/**
	 * public
	 * 登录用到的标识，不敏感的数据
	 * 如：用户名，邮箱验证码，手机验证码等
	 */
	@NotNull
	private String pub;
	
	
	/**
	 * private
	 * 登录时的密钥，敏感的数据 
	 */
	@NotNull
	private String pri;


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}


	public String getPub() {
		return pub;
	}


	public void setPub(String pub) {
		this.pub = pub;
	}


	public String getPri() {
		return pri;
	}


	public void setPri(String pri) {
		this.pri = pri;
	}


	@Override
	public String toString() {
		return "SessionType [type=" + type + ", pub=" + pub + ", pri=" + pri + "]";
	}
	
	

	
}
