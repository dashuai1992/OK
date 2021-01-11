package com.very.ok.sys.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.very.ok.result.Result;

/**
 * 接口规范
 * 
 * 
 * 
 * */
@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/{userCode}")
	public Result get(@PathVariable String userCode) {
		return userService.get(1);
	}

	@GetMapping()
	public Result list(QueryUserParams queryUserParams) {
		return userService.list(queryUserParams);
	}

}
