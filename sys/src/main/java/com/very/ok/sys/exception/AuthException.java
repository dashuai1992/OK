package com.very.ok.sys.exception;

import com.very.ok.exception.CommonException;

public class AuthException extends CommonException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String ERROR_CODE = "AUTH_ERROR";
    public static final String LOGIN_ERROR_MSG = "用户名，密码不正确";
    public static final String LOGIN_TYPE_NOT_SUPORT = "不支持的登录方式";
    public static final String HAVE_NOT_LOGINED = "用户没有登录";
    public static final String PERMISSION_DENIED = "用户没有权限";
    public static final String BAD_REQUEST = "非法请求";

    public AuthException(String msg) {
        super(ERROR_CODE, msg);
    }
}
