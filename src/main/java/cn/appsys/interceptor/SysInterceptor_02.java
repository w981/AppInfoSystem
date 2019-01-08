package cn.appsys.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.appsys.pojo.DevUser;
import cn.appsys.tools.Constants;

public class SysInterceptor_02 extends HandlerInterceptorAdapter {
	private Logger logger = Logger.getLogger(SysInterceptor_02.class);
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception{
		logger.info("进入devUser拦截器-------------------------------------------------------------------------");
		HttpSession session = request.getSession();
		DevUser devUser = (DevUser)session.getAttribute(Constants.DEV_USER_SESSION);
		if (devUser == null) {
			response.sendRedirect(request.getContextPath() + "/403.jsp");
			return false;
		}
		return true;
	}
}
