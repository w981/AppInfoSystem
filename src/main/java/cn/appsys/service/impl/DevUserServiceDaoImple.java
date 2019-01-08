package cn.appsys.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.appsys.dao.DevUserMapper;
import cn.appsys.pojo.DevUser;
import cn.appsys.service.DevUserServiceDao;
@Service
@Transactional(propagation=Propagation.REQUIRED)
public class DevUserServiceDaoImple implements DevUserServiceDao {
	@Resource
	private DevUserMapper devUserMapper;

	@Override
	public DevUser getDevUserByCode(String devCode) {
		return devUserMapper.getDevUserByCode(devCode);
	}
}
