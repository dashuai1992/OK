package com.very.ok.sys.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.very.ok.sys.exception.AuthException;
import com.very.ok.utils.RequestUtil;

@Configuration
public class RequestConfiguration implements WebMvcConfigurer{
	
	private static Logger logger = LoggerFactory.getLogger(RequestConfiguration.class);
	
    private static final String SEND_TIME = "send_time";
    private static final String OPTION_REQUEST = "OPTIONS";
    private static final String LOGGER_FORMAT_STR = "\n========================================\n{}, {}\nfrom:{}\n========================================\n";
	
	@Autowired
	private RequestProp requestProp;
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TokenInterceptor()).addPathPatterns("/**");
    }
    
    class TokenInterceptor implements HandlerInterceptor {
        public TokenInterceptor() {}

        /**
         * 请求前
         * 2020-06-15 18:56
         * yds
         **/
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            request.setAttribute(SEND_TIME, System.currentTimeMillis());
            String uri = request.getRequestURI();
            String ip = RequestUtil.getIp(request);
            String method = request.getMethod();
            
            if (OPTION_REQUEST.equalsIgnoreCase(method)) {
                return true;
            }
            
            logger.info(LOGGER_FORMAT_STR, uri, method, ip);
            
            if(!requestProp.getEnable())
            	return true;
            
            validAgeingFromHeader(request);
            
            for (String whiteUri : requestProp.getWhiteList())
                if (whiteUri.equals(uri))
                    return true;
            
            validTokenFormHeader(request);
            return true;
        }

        /**
         * 请求完成
         * 2020-06-15 18:56
         * yds
         **/
        @Override
        public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
            Long requestTime = Long.valueOf(request.getAttribute(SEND_TIME).toString());
            long time = System.currentTimeMillis();
            //ThreadContext.release();
            logger.info("请求耗时：{}毫秒", Long.valueOf(time - requestTime).toString());
        }

        private void validTokenFormHeader(HttpServletRequest request) {
            String token = request.getHeader(requestProp.getTokenHeader());
            if (StringUtils.isBlank(token)) {
                throw new AuthException(AuthException.HAVE_NOT_LOGINED);
            }
            //使用threadLocal传递线程参数
            //ThreadContext.set(redisService.get(token).toString());
        }

        private void validAgeingFromHeader(HttpServletRequest request) {
            String ageing = request.getHeader(requestProp.getAgeingHeader());
            if (StringUtils.isBlank(ageing) || System.currentTimeMillis() - Long.parseLong(ageing) > requestProp.getTimeOut()) {
                throw new AuthException(AuthException.BAD_REQUEST);
            }
        }
    }
}
