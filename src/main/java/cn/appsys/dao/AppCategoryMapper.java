package cn.appsys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.appsys.pojo.AppCategory;

public interface AppCategoryMapper {
	//通过父类id(parentId)获取分类
	public abstract List<AppCategory> getAppCategoryListByParentId(@Param("parentId")Integer parentId);
}
