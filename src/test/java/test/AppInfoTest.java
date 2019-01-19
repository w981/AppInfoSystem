package test;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.appsys.dao.AppVersionMapper;
import cn.appsys.pojo.AppCategory;
import cn.appsys.service.AppCategoryServiceDao;
import cn.appsys.service.AppInfoServiceDao;
import cn.appsys.service.AppVersionServiceDao;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext-jdbc.xml"})
public class AppInfoTest {
	@Resource
	private AppCategoryServiceDao appCategoryService;
	@Resource
	private AppInfoServiceDao appInfoService;
	@Resource
	private AppVersionServiceDao appVersionService;
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void test2(){
		List<AppCategory> list = appCategoryService.getAppCategoryListByParentId(null);
		for (AppCategory appCategory : list) {
			System.out.println(appCategory.getCategoryName());
		}
	}
	
	@Test
	public void test3(){
		int count = appInfoService.getAppCount(1,null,null,null,null,null,null);
		System.out.println(count);
	}
	
	@Test
	public void test4(){
		int count = appInfoService.getCount();
		System.out.println(count);
	}
	
	@Test
	public void test5(){
		int count = appVersionService.updateStatus(null);
		System.out.println(count);
	}


}
