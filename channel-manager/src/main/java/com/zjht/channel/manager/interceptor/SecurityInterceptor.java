package com.zjht.channel.manager.interceptor;

import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zjht.channel.helper.common.ObjectHelper;
import com.zjht.channel.helper.common.StringHelper;
import com.zjht.channel.manager.common.constant.SessionConstant;

/**
 * ClassName: SecurityInterceptor <br/>
 * Function: 安全性拦截器. <br/>
 * date: Sep 14, 2015 3:56:25 PM <br/>
 * 
 * @author jun yangwenjun@chinaexpresscard.com
 * @version v0.1
 * @since JDK 1.8
 */
public class SecurityInterceptor implements HandlerInterceptor {
	private final static Logger logger = LoggerFactory.getLogger(SecurityInterceptor.class);
	
	private List<String> excludeURIs;
	

	/** 
	 * 
	 * @param   excludeURIs    the excludeURIs to set 
	 * @since   JDK 1.8
	 */
	public void setExcludeURIs(List<String> excludeURIs) {
		this.excludeURIs = excludeURIs;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String reqURI = request.getRequestURI().replaceFirst(request.getContextPath(), "");
		logger.debug("拦截Request URI=[{}]",reqURI);
		
		if (isExcludeURI(reqURI)) {
			logger.debug("排除URI列表存在，放行!");
			return true;
		}
		
		if(isLogin(request)){
			logger.debug("已登录，放行!");
			return true;
		}
		logger.debug("未登录或者session失效，跳转到登陆页面");
		response.sendRedirect("login");
		return false;
	}

	/** 
	 * 判断是否排除的URI. <br/> 
	 * 
	 * @author jun yangwenjun@chinaexpresscard.com
	 * @param uri
	 * @return 
	 * @since JDK 1.8
	 */  
	private boolean isExcludeURI(String uri) {
		for (int i = 0; i < excludeURIs.size(); i++) {
			String excludeURI =  excludeURIs.get(i);
			Pattern pattern = Pattern.compile(excludeURI.replace("*", ".*"));
			boolean isMatch = pattern.matcher(uri).matches();
			logger.debug("判断是否匹配URI过滤列表{}，结果{}",excludeURI,isMatch);
			if (isMatch) {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		
	}

	/** 
	 * 判断是否有登陆系统（基于session）. <br/> 
	 * 
	 * @author jun yangwenjun@chinaexpresscard.com
	 * @param request
	 * @return 
	 * @since JDK 1.8
	 */  
	private boolean isLogin(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String username = ObjectHelper.trans(session.getAttribute(SessionConstant.USER.getName()));
		logger.debug("用户名:{}",username);
		if (StringHelper.isEmpty(username)) {
			/*
			//如果是ajax请求响应头会有，x-requested-with；
			if (request.getHeader("x-requested-with") != null  
                    && request.getHeader("x-requested-with")  
                            .equalsIgnoreCase("XMLHttpRequest"))  
            {  
				LOGGER.debug("ajax请求，session超时");
				//在响应头设置session状态  
                response.setHeader("sessionstatus", "timeout");
                PrintWriter wirter =  response.getWriter();
                wirter.write("timeout");
                wirter.flush();
//                return true;
            }  */
			
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
}
