package cn.appsys.dao;

import org.apache.ibatis.annotations.Param;

import cn.appsys.pojo.DevUser;
//发开者接口
public interface DevUserMapper {
	//通过用户名userCode获取用户
	public abstract DevUser getDevUserByCode(@Param("devCode") String devCode);
}
