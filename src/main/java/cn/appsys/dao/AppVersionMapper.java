package cn.appsys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import cn.appsys.pojo.AppVersion;

public interface AppVersionMapper {
	//根据id修改发布状态
	public abstract Integer updateStatus(@Param("appVersion")AppVersion appVersion);
	
	//增加
	public abstract Integer add(@Param("appVersion")AppVersion appVersion);
	
	//根据appId获取信息
	public abstract List<AppVersion> getAppVsersionByappId(@Param("appinfoId")Integer appinfoId);

	//根据id获取信息
	public abstract AppVersion getAppVsersionById(@Param("versionId")Integer versionId);
	
	//修改
	public abstract Integer update(@Param("appVersion")AppVersion appVersion);
	
	//删除
	public abstract Integer delete(@Param("appInfoId")Integer appInfoId);
	
	//查询最新id
	public abstract Integer getId();
}
