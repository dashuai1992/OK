package com.very.ok.sys.session;

import org.springframework.stereotype.Service;

import com.very.ok.sys.exception.AuthException;

@Service
public class LoginService {
	
	public void login(SessionType type) {
		getSession(type).login();
	}
	
	private Session getSession(SessionType type) {
		switch (type.getType()) {
		case SessionType.USER_NAME:
			return username(type);
		case SessionType.MAIL:
			return mail(type);
		case SessionType.MOBILE:
			return mobile(type);
		default:
			return def(type);
		}
	}
	
	private Session username(SessionType type) {
		return () -> {
			return type.toString();
		};
	}
	
	private Session mail(SessionType type) {
		return () -> {
			return type.toString();
		};
	}
	
	private Session mobile(SessionType type) {
		return () -> {
			return type.toString();
		};
	}
	
	private Session def(SessionType type) {
		return () -> {
			throw new AuthException(AuthException.LOGIN_TYPE_NOT_SUPORT);
		};
	}

}
