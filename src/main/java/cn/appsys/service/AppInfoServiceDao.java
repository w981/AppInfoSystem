package cn.appsys.service;

import java.util.List;


import cn.appsys.pojo.AppInfo;

public interface AppInfoServiceDao {
	// 根据开发者id，软件名，软件状态，软件平台，软件分类，获取app信息数量
	public abstract int getAppCount(Integer devId,String softwareName,
			Integer status,Integer flatformId,
			Integer categoryLevel1,
			Integer categoryLevel2,
			Integer categoryLevel3);

	// 根据开发者id，软件名，软件状态，软件平台，软件分类，获取app信息
	public abstract List<AppInfo> getAppInfoList(Integer devid,
			String softwareName,Integer status,
			Integer flatformId,Integer categoryLevel1,
		 	Integer categoryLevel2,Integer categoryLevel3,
			Integer currentPageNo,Integer pageSize);
	
	public abstract int getCount();
	
	//根据APKName获取信息
	public abstract int getAppInfoByAPKName(String APKName);
	
	//增加appInfo信息
	public abstract int addAppInfo(AppInfo appInfo);
}
