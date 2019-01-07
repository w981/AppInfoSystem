package cn.appsys.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.appsys.dao.BackendUserMapper;
import cn.appsys.service.BackendUserServiceDao;
@Service
@Transactional(propagation=Propagation.REQUIRED)
public class BackendUserServiceDaoImpl implements BackendUserServiceDao {
	@Resource
	private BackendUserMapper backendUserMapper;
}
