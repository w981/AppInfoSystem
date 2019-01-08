package cn.appsys.service;


import cn.appsys.pojo.BackendUser;

public interface BackendUserServiceDao {
	
	//通过用户名userCode获取用户
	public abstract BackendUser getBackendUserByCode(String userCode);
}
