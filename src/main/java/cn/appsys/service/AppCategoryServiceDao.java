package cn.appsys.service;

import java.util.List;

import cn.appsys.pojo.AppCategory;

public interface AppCategoryServiceDao {
	//通过父类id(parentId)获取分类
	public abstract List<AppCategory> getAppCategoryListByParentId(Integer parentId);
}
