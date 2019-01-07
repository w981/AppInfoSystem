package cn.appsys.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.appsys.dao.AdPromotionMapper;
import cn.appsys.service.AdPromotionServiceDao;
@Service
@Transactional(propagation=Propagation.REQUIRED)
public class AdPromotionServiceDaoImpl implements AdPromotionServiceDao {
	@Resource
	private AdPromotionMapper adPromotionMapper;
}
