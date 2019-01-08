package cn.appsys.service;


import cn.appsys.pojo.DevUser;

public interface DevUserServiceDao {
	//通过用户名userCode获取用户
	public abstract DevUser getDevUserByCode(String devCode);
}
