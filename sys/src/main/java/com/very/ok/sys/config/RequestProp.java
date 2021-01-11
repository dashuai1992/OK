package com.very.ok.sys.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "sysconf.requestprop")
@Component
public class RequestProp {
    /**
     * 启用标识
     */
	private Boolean enable;
    /**
     * 白名单
     */
	private String[] whiteList;
    /**
     * 请求带的时间戳如果和主机暗相差超过该值，视为非法请求
     */
	private Long timeOut;
    /**
     * 请求头上的token参数名
     */
	private String tokenHeader;
    /**
     * 请求头上的ageing参数名
     */
	private String ageingHeader;
	public Boolean getEnable() {
		return enable;
	}
	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
	public String[] getWhiteList() {
		return whiteList;
	}
	public void setWhiteList(String[] whiteList) {
		this.whiteList = whiteList;
	}
	public Long getTimeOut() {
		return timeOut;
	}
	public void setTimeOut(Long timeOut) {
		this.timeOut = timeOut;
	}
	public String getTokenHeader() {
		return tokenHeader;
	}
	public void setTokenHeader(String tokenHeader) {
		this.tokenHeader = tokenHeader;
	}
	public String getAgeingHeader() {
		return ageingHeader;
	}
	public void setAgeingHeader(String ageingHeader) {
		this.ageingHeader = ageingHeader;
	}
	
	

}
