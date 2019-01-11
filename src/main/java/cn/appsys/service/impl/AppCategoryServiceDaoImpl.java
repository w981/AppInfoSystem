package cn.appsys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.appsys.dao.AppCategoryMapper;
import cn.appsys.pojo.AppCategory;
import cn.appsys.service.AppCategoryServiceDao;
@Service
@Transactional(propagation=Propagation.REQUIRED)
public class AppCategoryServiceDaoImpl implements AppCategoryServiceDao {
	@Resource
	private AppCategoryMapper appCategoryMapper;

	@Override
	public List<AppCategory> getAppCategoryListByParentId(Integer parentId) {
		return appCategoryMapper.getAppCategoryListByParentId(parentId);
	}
}
