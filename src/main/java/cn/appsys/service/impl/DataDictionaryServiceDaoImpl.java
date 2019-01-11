package cn.appsys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.appsys.dao.DataDictionaryMapper;
import cn.appsys.pojo.DataDictionary;
import cn.appsys.service.DataDictionaryServiceDao;
@Service
@Transactional(propagation=Propagation.REQUIRED)
public class DataDictionaryServiceDaoImpl implements DataDictionaryServiceDao{
	@Resource
	private DataDictionaryMapper dataDictionaryMapper;

	@Override
	public List<DataDictionary> getAppStatusList() {
		return dataDictionaryMapper.getAppStatusList();
	}

	@Override
	public List<DataDictionary> getStatusBytypeCode(String typeCode) {
		return dataDictionaryMapper.getStatusBytypeCode(typeCode);
	}
	
	
}
