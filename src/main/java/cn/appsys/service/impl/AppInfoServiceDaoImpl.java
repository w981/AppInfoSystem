package cn.appsys.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.appsys.dao.AppInfoMapper;
import cn.appsys.service.AppInfoServiceDao;
@Service
@Transactional(propagation=Propagation.REQUIRED)
public class AppInfoServiceDaoImpl implements AppInfoServiceDao {
	@Resource
	private AppInfoMapper appInfoMapper;
}
