package cn.appsys.service;

import java.util.List;

import cn.appsys.pojo.DataDictionary;

public interface DataDictionaryServiceDao {
	//获取所有app状态
	public List<DataDictionary> getAppStatusList();

	//获取app状态，通过typeCode
	public List<DataDictionary> getStatusBytypeCode(String typeCode);
}
