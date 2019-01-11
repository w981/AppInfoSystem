package cn.appsys.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.appsys.pojo.DevUser;
import cn.appsys.service.DevUserServiceDao;
import cn.appsys.tools.Constants;

@Controller
@RequestMapping("/dev")
public class DevUserLoginController {
	private Logger logger = Logger.getLogger(DevUserLoginController.class);
	@Resource
	private DevUserServiceDao devUserServiceDao;
	
	//进入开发者登录页面
	@RequestMapping("/login")
	public String login(){
		return "devlogin";
	}
	
	//开发者登录页面
	@RequestMapping(value="/dologin",method=RequestMethod.POST)
	public String doLogin(@RequestParam("devCode") String devCode,
							@RequestParam("devPassword") String devPassword,HttpServletRequest request,
							HttpSession session){
		logger.info(devCode + "-------------------------------------" + devPassword);
		//创建DevUser对象
		DevUser devUser = new DevUser();
		//通过devCode用户名获取对象
		devUser = devUserServiceDao.getDevUserByCode(devCode);
		//判断用户是否存在
		if(devUser == null){
			//用户不存在
			request.setAttribute("error", "用户名错误，请重新登录！");
			return "devlogin";
		}
		//用户存在
		if(!devUser.getDevPassword().equals(devPassword)){
			//密码不正确
			request.setAttribute("error", "密码不正确，请重新登录！");
			return "devlogin";
		}
		//用户，密码都正确，将对象保存到sesion中
		session.setAttribute(Constants.DEV_USER_SESSION, devUser);
		//跳转到开发者主页面
		return "redirect:/dev/flatform/app/main";
	}
	
	//开发者主页面
	@RequestMapping("/flatform/app/main")
	public String main(HttpSession session,HttpServletRequest request){
		//从session获取登录保存的对象
		DevUser devUser = (DevUser)session.getAttribute(Constants.DEV_USER_SESSION);
		//将对象保存到request中，页面要获取信息
		request.setAttribute("devUserSession", devUser);
		return "developer/main";
	}
	
	//注销登录
	@RequestMapping("/logout")
	public String logout(HttpSession session){
		session.removeAttribute(Constants.DEV_USER_SESSION);
		return "devlogin";
	}
	
	
	
	
	
	
}
