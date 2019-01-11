package cn.appsys.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.mysql.jdbc.StringUtils;

import cn.appsys.pojo.AppCategory;
import cn.appsys.pojo.AppInfo;
import cn.appsys.pojo.DataDictionary;
import cn.appsys.pojo.DevUser;
import cn.appsys.service.AppCategoryServiceDao;
import cn.appsys.service.AppInfoServiceDao;
import cn.appsys.service.DataDictionaryServiceDao;
import cn.appsys.tools.Constants;
import cn.appsys.tools.PageSupport;

@Controller
@RequestMapping("/dev/flatform/app")
public class DevUserController {
	private Logger logger = Logger.getLogger(DevUserController.class);
	@Resource
	private DataDictionaryServiceDao dataDictionaryServiceDao;
	@Resource
	private AppInfoServiceDao appinfoServiceDao;
	@Resource
	private AppCategoryServiceDao appCategoryServiceDao;

	// 获取app列表页
	@RequestMapping("/list")
	public String appList(Model model, HttpSession session,
			@RequestParam(value = "querySoftwareName", required = false) String querySoftwareName,
			@RequestParam(value = "queryStatus", required = false) String _queryStatus,
			@RequestParam(value = "queryFlatformId", required = false) String _queryFlatformId,
			@RequestParam(value = "queryCategoryLevel1", required = false) String _queryCategoryLevel1,
			@RequestParam(value = "queryCategoryLevel2", required = false) String _queryCategoryLevel2,
			@RequestParam(value = "queryCategoryLevel3", required = false) String _queryCategoryLevel3,
			@RequestParam(value = "pegeIndex", required = false) String pageIndex) {
		// 日志输出获取的参数，参数都是可有可无，多条件查询，根据条件查询结果
		logger.info("getAppInfoList------------------------------" + querySoftwareName);
		logger.info("getAppInfoList------------------------------" + _queryStatus);
		logger.info("getAppInfoList------------------------------" + _queryFlatformId);
		logger.info("getAppInfoList------------------------------" + _queryCategoryLevel1);
		logger.info("getAppInfoList------------------------------" + _queryCategoryLevel2);
		logger.info("getAppInfoList------------------------------" + _queryCategoryLevel3);
		logger.info("getAppInfoList------------------------------" + pageIndex);

		// 获取当前登录的开发者的id（这个是只能看到自己开发的app信息，所以查询条件要有开发者id）
		Integer devId = ((DevUser) session.getAttribute(Constants.DEV_USER_SESSION)).getId();
		// 根据条件查询到的app信息
		List<AppInfo> appInfoList = null;
		// app状态(DataDictionary表)
		List<DataDictionary> statusList = null;
		// app所属平台(DataDictionary表)
		List<DataDictionary> flatFormList = null;
		// 一级分类，注：二级和三级分类通过ajax异常获取
		List<AppCategory> categoryLevel1List = null;
		List<AppCategory> categoryLevel2List = null;
		List<AppCategory> categoryLevel3List = null;

		// 条件不为空，将参数转为为Integer类型(数据库和实体类就是int，
		// 页面获取过来都是String,所以需要转换)
		Integer queryStatus = null;
		if (_queryStatus != null && !("").equals(_queryStatus)) {
			queryStatus = Integer.parseInt(_queryStatus);
		}
		Integer queryFlatformId = null;
		if (_queryFlatformId != null && !("").equals(_queryFlatformId)) {
			queryFlatformId = Integer.parseInt(_queryFlatformId);
		}
		Integer queryCategoryLevel1 = null;
		if (_queryCategoryLevel1 != null && !("").equals(_queryCategoryLevel1)) {
			queryCategoryLevel1 = Integer.parseInt(_queryCategoryLevel1);
		}
		Integer queryCategoryLevel2 = null;
		if (_queryCategoryLevel2 != null && !("").equals(_queryCategoryLevel2)) {
			queryCategoryLevel2 = Integer.parseInt(_queryCategoryLevel2);
		}
		Integer queryCategoryLevel3 = null;
		if (_queryCategoryLevel3 != null && !("").equals(_queryCategoryLevel3)) {
			queryCategoryLevel3 = Integer.parseInt(_queryCategoryLevel3);
		}

		// 页面容量(自定义的常量，数值5)
		int pageSize = Constants.pageSize;
		// 当前页码
		Integer currentPageNo = 1;
		if (pageIndex != null) {
			try {
				currentPageNo = Integer.valueOf(pageIndex);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 页面的总数量（符合查询条件的列表信息总数）
		int totalCount = appinfoServiceDao.getAppCount(devId, querySoftwareName, queryStatus, queryFlatformId,
				queryCategoryLevel1, queryCategoryLevel2, queryCategoryLevel3);
		// 创建分页工具类对象
		PageSupport pages = new PageSupport();
		// 设置当前页码
		pages.setCurrentPageNo(currentPageNo);
		// 设置页码容量，自定义的默认5
		pages.setPageSize(pageSize);
		// 设置总数量，总数量的方法中会调用设置总页数的方法
		pages.setTotalCount(totalCount);
		// 获取总页数
		int totalPageCount = pages.getTotalPageCount();
		// 控制首页和尾页
		if (currentPageNo < 1) {
			currentPageNo = 1;
		} else if (currentPageNo > totalPageCount) {
			currentPageNo = totalPageCount;
		}

		// 按条件分页获取app信息
		appInfoList = appinfoServiceDao.getAppInfoList(devId, querySoftwareName, queryStatus, queryFlatformId,
				queryCategoryLevel1, queryCategoryLevel2, queryCategoryLevel3, currentPageNo, pageSize);

		// app状态(DataDictionary表)
		statusList = dataDictionaryServiceDao.getStatusBytypeCode("APP_STATUS");

		// app所属平台(DataDictionary表)
		flatFormList = dataDictionaryServiceDao.getStatusBytypeCode("APP_FLATFORM");

		// 获取一级分类
		categoryLevel1List = appCategoryServiceDao.getAppCategoryListByParentId(null);

		// 返回数据到页面
		model.addAttribute("appInfoList", appInfoList);
		model.addAttribute("statusList", statusList);
		model.addAttribute("flatFormList", flatFormList);
		model.addAttribute("categoryLevel1List", categoryLevel1List);
		model.addAttribute("pages", pages);
		model.addAttribute("queryStatus", queryStatus);
		model.addAttribute("queryFlatformId", queryFlatformId);
		model.addAttribute("queryCategoryLevel1", queryCategoryLevel1);
		model.addAttribute("queryCategoryLevel2", queryCategoryLevel2);
		model.addAttribute("queryCategoryLevel3", queryCategoryLevel3);
		model.addAttribute("querySoftwareName", querySoftwareName);

		return "developer/appinfolist";
	}
	/*
	 * public List<DataDictionary> getDataDictionaryList(String typeCode){
	 * List<DataDictionary> dataDictionaries =
	 * dataDictionaryServiceDao.getStatusBytypeCode(typeCode); return
	 * dataDictionaries; }
	 */

	// 获取二级，三级分类。一个方法，使用通过父类id获取分类对象
	@RequestMapping(value = "/categorylevellist", method = RequestMethod.GET)
	@ResponseBody
	public List<AppCategory> getCategorylevellist(@RequestParam("pid") Integer parentId) {
		List<AppCategory> categoryLevel2List = null;
		if (("").equals(parentId)) {
			parentId = null;
		}
		categoryLevel2List = appCategoryServiceDao.getAppCategoryListByParentId(parentId);
		return categoryLevel2List;
	}

	// 获取aap所属平台，通过typeCode
	@RequestMapping("/datadictionarylist")
	@ResponseBody
	public List<DataDictionary> getFlatform(@RequestParam("tcode") String typeCode) {
		List<DataDictionary> flatFormList = dataDictionaryServiceDao.getStatusBytypeCode(typeCode);
		return flatFormList;
	}

	// 通过APKName验证用户对象是否存在
	@RequestMapping("/apkexist")
	@ResponseBody
	public Object getAppInfoByAKPName(@RequestParam("APKName") String APKName) {
		HashMap<String,String> resultMap = new HashMap<String,String>();
		if(StringUtils.isNullOrEmpty(APKName)){
			resultMap.put("APKName", "empty");
		}else{
			int count = appinfoServiceDao.getAppInfoByAPKName(APKName);
			if(count > 0){
				resultMap.put("APKName", "exist");
			}else{
				resultMap.put("APKName", "noexist");
			}
		}
		return JSONArray.toJSON(resultMap); 
		
		
	}

	// app信息新增页面
	@RequestMapping("/appinfoadd")
	public String appInfoAdd(@ModelAttribute("appInfo")AppInfo appInfo) {
		return "developer/appinfoadd";
	}
	
	//增加appInfo
	@RequestMapping("appinfoaddsave")
	public String addAppInfo(AppInfo appInfo,HttpSession session,
								HttpServletRequest request,
								@RequestParam(value="a_logoPicPath",required=false) MultipartFile attach){
		//图片url路径
		String logopicPath = null;
		
		//定义图片上传到服务器的路径
		String logoLocPath = request.getSession().getServletContext().getRealPath("statics" + File.separator + "uploadfiles");
		
		//判断文件是否为空，有没有上传
		if(!attach.isEmpty()){
			
			//获取上传文件的名字
			String oldFileName = attach.getOriginalFilename();
			
			//获取上传文件的扩展名
			String suffix = FilenameUtils.getExtension(oldFileName);
			
			//定义文件上限
			int fileSize = 500000;
			
			//判断文件大小和扩展名
			if(attach.getSize() > fileSize){
				request.setAttribute("error", "上传文件不得超过500KB！");
				return "developer/appinfoadd";
			}else if(suffix.equals("jpg")
					|| suffix.equals("jpeg")
					|| suffix.equals("png")
					|| suffix.equals("pneg")){
				
				//大小和格式都正确
				
				//定义新文件名上到到服务器的名字
				String newFileName = System.currentTimeMillis() + RandomUtils.nextInt(1000000) + "_Personal." + suffix;
				
				//通过服务器路径和文件服务器名字构造文件对象
				File newFile = new File(logoLocPath,newFileName);
				//判断文件是否存在，不存在就自动创建
				if(!newFile.exists()){
					newFile.mkdirs();
				}
				
				try {
					//保存上传的文件到服务器中
					attach.transferTo(newFile);
				} catch (Exception e) {
					e.printStackTrace();
					request.setAttribute("error", "文件上传失败!");
				}
				
				//设置图片url路径(服务器地址+服务器中文件的名字，也就是在服务器中的完全路径)
				logopicPath = logoLocPath + File.separator + newFileName;
				
			}else {
				request.setAttribute("error", "上传文件格式不正确！");
			}
		}
		
		//获取当前用户的id
		Integer devId = ((DevUser)session.getAttribute(Constants.DEV_USER_SESSION)).getId();
		appInfo.setDevId(devId);
		//设置服务器路径
		appInfo.setLogolocPath(logoLocPath);
		//设置appinfo的图片路径属性
		appInfo.setLogopicPath(logopicPath);
		//调用添加appinfo的方法，成功跳转到appinfo显示页面
		if(appinfoServiceDao.addAppInfo(appInfo) > 0){
			return "redirect:/dev/flatform/app/list";
		}
		//失败跳转到appinfo添加页面
		return "developer/appinfoadd";
		
	}
}
