package cn.appsys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.aop.ThrowsAdvice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.appsys.dao.AppInfoMapper;
import cn.appsys.pojo.AppInfo;
import cn.appsys.service.AppInfoServiceDao;
@Service
@Transactional(propagation=Propagation.REQUIRED)
public class AppInfoServiceDaoImpl implements AppInfoServiceDao {
	@Resource
	private AppInfoMapper appInfoMapper;

	@Override
	public List<AppInfo> getAppInfoList(Integer devid, String softwareName, Integer status, Integer flatformId,
			Integer categoryLevel1, Integer categoryLevel2, Integer categoryLevel3, Integer currentPageNo,
			Integer pageSize) {
		currentPageNo = (currentPageNo - 1) * pageSize;
		return appInfoMapper.getAppInfoList(devid, softwareName, status, flatformId, categoryLevel1, categoryLevel2, categoryLevel3, currentPageNo, pageSize);
	}

	@Override
	public int getAppCount(Integer devId, String softwareName, Integer status, Integer flatformId,
			Integer categoryLevel1, Integer categoryLevel2, Integer categoryLevel3) {
		return appInfoMapper.getAppCount(devId, softwareName, status, flatformId, categoryLevel1, categoryLevel2, categoryLevel3);
	}

	@Override
	public int getCount() {
		return appInfoMapper.getCount();
	}

	@Override
	public int getAppInfoByAPKName(String APKName) {
		return appInfoMapper.getAppInfoByAPKName(APKName);
	}

	@Override
	public int addAppInfo(AppInfo appInfo) {
		return appInfoMapper.addAppInfo(appInfo);
	}

	@Override
	public int update(AppInfo appInfo) {
		return appInfoMapper.update(appInfo);
	}
	
	@Override
	public AppInfo getAppInfoById(Integer id) {
		return appInfoMapper.getAppInfoById(id);
	}

	@Override
	public int delete(Integer appInfoId) {
		return appInfoMapper.delete(appInfoId);
	}
}
