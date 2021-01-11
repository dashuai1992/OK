package com.very.ok.sys.session;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.very.ok.result.Result;

@RestController
@RequestMapping(value="/session", produces = MediaType.APPLICATION_JSON_VALUE)
public class SessionController {
	
	private static final String MEDIA_LOGIN = "application/com.ds.login+json;charset=utf-8";

	@PostMapping(value = "/login",consumes = MEDIA_LOGIN)
	public Result login(@RequestBody @Validated SessionType loginSession) {
		return Result.ok();
	}
}
