package cn.appsys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.appsys.dao.AppInfoMapper;
import cn.appsys.dao.AppVersionMapper;
import cn.appsys.pojo.AppVersion;
import cn.appsys.service.AppVersionServiceDao;
@Service
@Transactional(propagation=Propagation.REQUIRED)
public class AppVersionServiceDaoImpl implements AppVersionServiceDao {
	@Resource
	private AppVersionMapper appVersionMapper;
	@Resource
	private AppInfoMapper appInfoMapper;

	@Override
	public Integer updateStatus(AppVersion appVersion) {
		return appVersionMapper.updateStatus(appVersion);
	}

	@Override
	/*public Integer add(AppVersion appVersion) {
		Integer count = 0;
		Integer versionId = null;
		if(appVersionMapper.add(appVersion) > 0){
			count = 1;
			versionId = appVersionMapper.getId();
			System.err.println("versionId" + versionId + "appId" + appVersion.getAppId() + "--------------------------------------------");
		}
		if(appInfoMapper.updateVersionId(appVersion.getAppId(), versionId) > 0){
			return count;
		}else{
			count = 0;
		}
		return count;
	}*/
	public Integer add(AppVersion appVersion) {
		Integer count = 0;
		Integer versionId = null;
		if(appVersionMapper.add(appVersion) > 0){
			versionId = appVersion.getId();
			count = 1;
			System.err.println("versionId---------------" + versionId + "appId---------------------" + appVersion.getAppId() + "--------------------------------------------");
		}
		if(appInfoMapper.updateVersionId(versionId, appVersion.getAppId()) > 0){
			return count;
		}else{
			count = 0;
		}
		return count;
	}

	@Override
	public List<AppVersion> getAppVsersionByappId(Integer appinfoId) {
		return appVersionMapper.getAppVsersionByappId(appinfoId);
	}

	@Override
	public AppVersion getAppVsersionById(Integer versionId) {
		return appVersionMapper.getAppVsersionById(versionId);
	}

	@Override
	public Integer delete(Integer appInfoId) {
		return appVersionMapper.delete(appInfoId);
	}
}
