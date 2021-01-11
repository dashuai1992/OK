package com.very.ok.sys.user;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.very.ok.result.Result;
import com.very.ok.sys.entity.SysUser;
import com.very.ok.sys.service.SysUserService;

@Service
public class UserService extends SysUserService {
	
	public Result get(Integer id) {
		return Result.ok(super.getById(id));
	}
	
	public Result list(QueryUserParams queryUserParams) {
		return Result.ok(super.page(new Page<SysUser>(queryUserParams.getCurrent(), queryUserParams.getSize())));
	}

}
