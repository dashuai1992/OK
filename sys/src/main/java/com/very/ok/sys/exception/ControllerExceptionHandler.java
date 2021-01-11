package com.very.ok.sys.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.very.ok.exception.CommonException;
import com.very.ok.result.Result;

@RestControllerAdvice
public class ControllerExceptionHandler {
	
	private static Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);
	
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handler(Exception e) {
        if (CommonException.class.isAssignableFrom(e.getClass())) {
            String code = ((CommonException) e).getCode();
            return Result.fail(code, e.getMessage());
        } else {
            logger.error("请求异常：", e);
            return Result.fail(e.getClass().getSimpleName());
        }
    }

}
