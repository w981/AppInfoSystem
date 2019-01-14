package cn.appsys.service;


import java.util.List;


import cn.appsys.pojo.AppVersion;

public interface AppVersionServiceDao {
	//根据appinfo的id修改发布状态
	public abstract Integer updateStatus(AppVersion appVersion);
	
	//增加
	public abstract Integer add(AppVersion appVersion);
	
	//根据appId获取信息
	public abstract List<AppVersion> getAppVsersionByappId(Integer appinfoId);
	
	//根据id获取信息
	public abstract AppVersion getAppVsersionById(Integer versionId);
	
	//删除
	public abstract Integer delete(Integer appInfoId);
}
