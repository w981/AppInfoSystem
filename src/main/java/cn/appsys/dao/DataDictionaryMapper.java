package cn.appsys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.appsys.pojo.DataDictionary;

//数据字典
public interface DataDictionaryMapper {
	//获取所有app状态
	public List<DataDictionary> getAppStatusList();
	
	//获取app状态，通过typeCode
	public List<DataDictionary> getStatusBytypeCode(@Param("typeCode")String typeCode);
}
