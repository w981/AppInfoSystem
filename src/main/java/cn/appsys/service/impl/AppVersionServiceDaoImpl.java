package cn.appsys.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.appsys.dao.AppVersionMapper;
import cn.appsys.service.AppVersionServiceDao;
@Service
@Transactional(propagation=Propagation.REQUIRED)
public class AppVersionServiceDaoImpl implements AppVersionServiceDao {
	@Resource
	private AppVersionMapper appVersionMapper;
}
