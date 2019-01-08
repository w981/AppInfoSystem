package cn.appsys.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


import cn.appsys.pojo.BackendUser;
import cn.appsys.tools.Constants;
//自定义拦截器类，继承HandlerInterceptorAdapter(HandlerInterceptor接口的实现类)
public class SysInterceptor extends HandlerInterceptorAdapter {
	private Logger logger = Logger.getLogger(SysInterceptor.class);
	
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,
								Object handler) throws Exception{
		logger.info("进入自定义拦截器------------------------------------------------------");
		//获取session对象
		HttpSession session = request.getSession();
		//通过session对象获取session中的对象
		BackendUser backendUser = (BackendUser) session.getAttribute(Constants.BACKEND_USER_SESSION);
		//判断session中是否有对象
		if(null == backendUser){
			//seesion中没有对象，那就未登录，返回错误页面提示
			response.sendRedirect(request.getContextPath() + "/403.jsp");
			return false;
		}
		//不等于null，有对象存在，说明已登录，继续执行下一步return true。
		return true;
		
	}
}
