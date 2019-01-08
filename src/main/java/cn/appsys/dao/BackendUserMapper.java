package cn.appsys.dao;

import org.apache.ibatis.annotations.Param;

import cn.appsys.pojo.BackendUser;

//超级管理员接口
public interface BackendUserMapper {
	//通过用户名userCode获取用户
	public abstract BackendUser getBackendUserByCode(@Param("userCode") String userCode);
}
