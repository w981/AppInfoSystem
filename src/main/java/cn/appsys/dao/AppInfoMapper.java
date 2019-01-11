package cn.appsys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.appsys.pojo.AppInfo;

public interface AppInfoMapper {
	// 根据开发者id，软件名，软件状态，软件平台，软件分类，获取app信息数量
	public abstract int getAppCount(@Param("devId") Integer devId, @Param("softwareName") String softwareName,
			@Param("status") Integer status, @Param("flatformId") Integer flatformId,
			@Param("categoryLevel1") Integer categoryLevel1,
			@Param("categoryLevel2") Integer categoryLevel2,
			@Param("categoryLevel3") Integer categoryLevel3);

	// 根据开发者id，软件名，软件状态，软件平台，软件分类，获取app信息
	public abstract List<AppInfo> getAppInfoList(@Param("devId") Integer devid,
			@Param("softwareName") String softwareName, @Param("status") Integer status,
			@Param("flatformId") Integer flatformId, @Param("categoryLevel1") Integer categoryLevel1,
		 	@Param("categoryLevel2") Integer categoryLevel2,@Param("categoryLevel3") Integer categoryLevel3,
			@Param("currentPageNo") Integer currentPageNo,@Param("pageSize") Integer pageSize);
	
	//测试获取所有信息总数量
	public abstract int getCount();
	
	//根据APKName获取信息
	public abstract int getAppInfoByAPKName(@Param("APKName")String APKName);
	
	//增加appInfo信息
	public abstract int addAppInfo(@Param("appInfo")AppInfo appInfo);
}
