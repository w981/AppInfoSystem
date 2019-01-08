package cn.appsys.controller;




import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.appsys.pojo.BackendUser;
import cn.appsys.service.BackendUserServiceDao;
import cn.appsys.tools.Constants;

@Controller
@RequestMapping("/manager")
public class BackendLoginController {
	//定义日志
	private Logger logger = Logger.getLogger(BackendLoginController.class);
	//定义BackendUserMapper对象
	@Resource
	private BackendUserServiceDao backendUserServiceDao;

	//进入登录页面
	@RequestMapping("/login")
	public String LoginContext(){
		return "backendlogin";
	}
	
	//登录页面
	@RequestMapping(value="/dologin",method=RequestMethod.POST)
	public String doLogin(@RequestParam("userCode") String userCode,
							@RequestParam("userPassword") String userPassword,
							HttpSession session,HttpServletRequest request){
		logger.info(userCode + "---------------------------------------------------------------------" + userPassword);
		//新建一个BackendUser对象
		BackendUser backendUser = new BackendUser();
		
		//通过用户名获取对象
		backendUser = backendUserServiceDao.getBackendUserByCode(userCode);
		if(backendUser == null){
			//没有此用户，返回登录页面
			request.setAttribute("error", "用户名错误，请重新登录！");
			//return "redirect:/manager/login";   这样写错误，重定向后页面获取不到request的错误提醒
			//所以直接return 视图名，使用服务器页面跳转，还在一次请求范围，传递回去提示
			return "backendlogin";
		}
		//用户存在，判断密码是否一致
		if(!backendUser.getUserPassword().equals(userPassword)){
			//密码不正确，返回登录页面
			request.setAttribute("error", "密码错误，请重新登录！");
			//return "redirect:/manager/login";	 这样写错误，重定向后页面获取不到request的错误提醒
			//所以直接return 视图名，使用服务器页面跳转，还在一次请求范围，传递回去提示
			return "backendlogin";
		}
		
		//此用户存在跳转到超级管理员主页面,并把次用户set在session中
		session.setAttribute(Constants.BACKEND_USER_SESSION, backendUser);
		return "redirect:/manager/sys/main";
	}
	
	//进入超级管理员主页面
	@RequestMapping("/sys/main")
	public String main(HttpSession session,HttpServletRequest request){
		//判断session中是否有用户
		BackendUser backendUser = (BackendUser)session.getAttribute(Constants.BACKEND_USER_SESSION);
		//未登录不用写了，用了拦截器，未登录拦截器跳转
		/*if(backendUser == null){
			//没有用户，跳转到登录页面
			return "redirect:/manager/login";
		}*/
		//有用户存在，将用户返回到页面
		request.setAttribute("userSession", backendUser);
		//有用户登录，跳转到主页面
		return "backend/main";
	}
	
	//注销登录
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		//将session中的用户清除，跳转到登录页面
		session.removeAttribute(Constants.BACKEND_USER_SESSION);
		return "backendlogin";
	}
}
