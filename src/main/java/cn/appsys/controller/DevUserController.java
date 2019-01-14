package cn.appsys.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.Logger;
import org.springframework.expression.spel.standard.SpelExpression;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
import cn.appsys.pojo.AppVersion;
import cn.appsys.pojo.DataDictionary;
import cn.appsys.pojo.DevUser;
import cn.appsys.service.AppCategoryServiceDao;
import cn.appsys.service.AppInfoServiceDao;
import cn.appsys.service.AppVersionServiceDao;
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
	@Resource
	private AppVersionServiceDao appVserSionServiceDao;

	// 获取app列表页
	@RequestMapping("/list")
	public String appList(Model model, HttpSession session,
			@RequestParam(value = "querySoftwareName", required = false) String querySoftwareName,
			@RequestParam(value = "queryStatus", required = false) String _queryStatus,
			@RequestParam(value = "queryFlatformId", required = false) String _queryFlatformId,
			@RequestParam(value = "queryCategoryLevel1", required = false) String _queryCategoryLevel1,
			@RequestParam(value = "queryCategoryLevel2", required = false) String _queryCategoryLevel2,
			@RequestParam(value = "queryCategoryLevel3", required = false) String _queryCategoryLevel3,
			@RequestParam(value = "pageIndex", required = false) String pageIndex) {
		// 日志输出获取的参数，参数都是可有可无，多条件查询，根据条件查询结果
		logger.info("getAppInfoList------------------------------" + querySoftwareName);
		logger.info("getAppInfoList------------------------------" + _queryStatus);
		logger.info("getAppInfoList------------------------------" + _queryFlatformId);
		logger.info("getAppInfoList------------------------------" + _queryCategoryLevel1);
		logger.info("getAppInfoList------------------------------" + _queryCategoryLevel2);
		logger.info("getAppInfoList------------------------------" + _queryCategoryLevel3);
		logger.info("getAppInfoList------------------------------" + pageIndex);

		// 获取当前登录的开发者的id（这个是只能看到自己开发的app信息，所以查询条件要有开发者id）
		DevUser devUserSession = ((DevUser) session.getAttribute(Constants.DEV_USER_SESSION));
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
		int totalCount = appinfoServiceDao.getAppCount(devUserSession.getId(), querySoftwareName, queryStatus, queryFlatformId,
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
		appInfoList = appinfoServiceDao.getAppInfoList(devUserSession.getId(), querySoftwareName, queryStatus, queryFlatformId,
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
		model.addAttribute("devUserSession", devUserSession);

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
	public String appInfoAdd(Model model,HttpSession session) {
		AppInfo appInfo = new AppInfo();
		DevUser devUser = (DevUser)session.getAttribute(Constants.DEV_USER_SESSION);
		model.addAttribute("appInfo", appInfo);
		model.addAttribute("devUserSession", devUser);
		return "developer/appinfoadd";
	}
	
	//增加appInfo
	@RequestMapping("/appinfoaddsave")
	public String addAppInfo(AppInfo appInfo,HttpSession session,
								HttpServletRequest request,
								@RequestParam(value="a_logoPicPath",required=false) MultipartFile attach){
		//图片url路径
		String logopicPath = null;
		
		//图片的服务器存储路径
		String logoLocPath = null;
		
		//定义图片上传到服务器的路径
		String serverPath = request.getSession().getServletContext().getRealPath("statics" + File.separator + "uploadfiles");
		
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
				String newFileName = appInfo.getAPKName() + suffix;
				
				//通过服务器路径和文件服务器名字构造文件对象
				File newFile = new File(serverPath,newFileName);
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
					return "developer/appinfoadd";
				}
				
				//设置图片url路径(服务器地址+服务器中文件的名字，也就是在服务器中的完全路径)
				logopicPath = request.getContextPath() + "/statics/uploadfiles/" + newFileName;
				//设置图片的服务器路径
				logoLocPath = serverPath + File.separator + newFileName;
				
			}else {
				request.setAttribute("error", "上传文件格式不正确！");
				return "developer/appinfoadd";
			}
		}
		
		//获取当前用户的id
		DevUser devUser = ((DevUser)session.getAttribute(Constants.DEV_USER_SESSION));
		logger.info("开发者---------------------------------------------------------------" + devUser.getId());
		//设置开发者
		appInfo.setDevId(devUser.getId());
		//设置创建者
		appInfo.setCreatedBy(devUser.getId());
		//设置创建时间
		appInfo.setCreationDate(new Date());
		//设置服务器路径
		appInfo.setLogoLocPath(logoLocPath);
		//设置appinfo的图片路径属性
		appInfo.setLogoPicPath(logopicPath);
		//调用添加appinfo的方法，成功跳转到appinfo显示页面
		if(appinfoServiceDao.addAppInfo(appInfo) > 0){
			return "redirect:/dev/flatform/app/list";
		}
		//失败跳转到appinfo添加页面
		return "developer/appinfoadd";
		
	}

	//进入appInfo修改页面
	@RequestMapping("/appinfomodify")
	public String modifyAppInfo(Model model,@RequestParam("id")Integer appInfoId,
									HttpSession session){
		//获取要修改的appInfo对象
		AppInfo appInfo = appinfoServiceDao.getAppInfoById(appInfoId);
		//获取当前登录对象
		DevUser devUserSession = (DevUser)session.getAttribute(Constants.DEV_USER_SESSION);
		model.addAttribute("appInfo", appInfo);
		model.addAttribute("devUserSession",devUserSession);
		return "developer/appinfomodify";
		
	}
	
	//appInfo修改页面
	@RequestMapping("/appinfomodifysave")
	public String doAppInfoModify(AppInfo appInfo,HttpSession session,
			HttpServletRequest request,
			@RequestParam(value="attach",required=false) MultipartFile attach){
		//通过appInfo的id获取要修改的对象
		AppInfo appInfo2 = appinfoServiceDao.getAppInfoById(appInfo.getId());
		
		//图片url路径
		String logopicPath = appInfo2.getLogoPicPath();
		
		//图片的服务器存储路径
		String logoLocPath = appInfo2.getLogoLocPath();
		
		//定义图片上传到服务器的路径
		String serverPath = request.getSession().getServletContext().getRealPath("statics" + File.separator + "uploadfiles");
		
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
				return "developer/appinfomodify";
			}else if(suffix.equals("jpg")
					|| suffix.equals("jpeg")
					|| suffix.equals("png")
					|| suffix.equals("pneg")){
				
				//大小和格式都正确
				
				//定义新文件名上到到服务器的名字
				String newFileName = appInfo.getAPKName() + suffix;
				
				//通过服务器路径和文件服务器名字构造文件对象
				File newFile = new File(serverPath,newFileName);
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
					return "developer/appinfomodify";
				}
				
				//设置图片url路径(服务器地址+服务器中文件的名字，也就是在服务器中的完全路径)
				logopicPath = request.getContextPath() + "/statics/uploadfiles/" + newFileName;
				//设置图片的服务器路径
				logoLocPath = serverPath + File.separator + newFileName;
				
			}else {
				request.setAttribute("error", "上传文件格式不正确！");
				return "developer/appinfomodify";
			}
		}
		
		//获取当前用户的id
		DevUser devUser = ((DevUser)session.getAttribute(Constants.DEV_USER_SESSION));
		logger.info("开发者---------------------------------------------------------------" + devUser.getId());
		//设置软件名
		appInfo2.setSoftwareName(appInfo.getSoftwareName());
		//设置apk名称
		appInfo2.setAPKName(appInfo.getAPKName());
		//设置支持ROM
		appInfo2.setSupportROM(appInfo.getSupportROM());
		//设置界面语言
		appInfo2.setInterfaceLanguage(appInfo.getInterfaceLanguage());
		//设置软件大小
		appInfo2.setSoftwareSize(appInfo.getSoftwareSize());
		//设置下载次数
		appInfo2.setDownloads(appInfo.getDownloads());
		//设置所属平台
		appInfo2.setFlatformId(appInfo.getFlatformId());
		//设置一级分类
		appInfo2.setCategoryLevel1(appInfo.getCategoryLevel1());
		appInfo2.setCategoryLevel2(appInfo.getCategoryLevel2());
		appInfo2.setCategoryLevel3(appInfo.getCategoryLevel3());
		//设置app简介
		appInfo2.setAppInfo(appInfo.getAppInfo());
		//设置开发者
		appInfo2.setDevId(devUser.getId());
		//设置修改者
		appInfo2.setModifyBy(devUser.getId());
		//设置创建时间
		appInfo2.setModifyDate(new Date());
		//设置服务器路径
		appInfo2.setLogoLocPath(logoLocPath);
		//设置appinfo的图片路径属性
		appInfo2.setLogoPicPath(logopicPath);
		//调用添加appinfo的方法，成功跳转到appinfo显示页面
		if(appinfoServiceDao.update(appInfo2) > 0){
			return "redirect:/dev/flatform/app/list";
		}
		//失败跳转到appinfo添加页面
		return "developer/appinfomodify";
				
	}

	//删除appInfo中的图片和apk文件
	@RequestMapping("/delfile")
	@ResponseBody
	public Object delPic(@RequestParam("id")Integer appInfoId,
							@RequestParam("flag")String flag){
		HashMap<String,String> resultMap = new HashMap<String,String>();
		
		if(appInfoId == 0 || flag.equals("") || flag == null){
			resultMap.put("result", "failed");
		}else if(flag.equals("logo")){
			AppInfo appInfo = appinfoServiceDao.getAppInfoById(appInfoId);
			appInfo.setLogoPicPath(null);
			appInfo.setLogoPicPath(null);
			File file = new File(appInfo.getLogoLocPath());
			if(file.exists()){
				if(file.delete()){
					if(appinfoServiceDao.update(appInfo)> 0){
						resultMap.put("result", "success");
					}
				}
			}
		}else if(flag.equals("apk")){
			AppInfo appInfo = appinfoServiceDao.getAppInfoById(appInfoId);
			appInfo.setLogoLocPath(null);
			appInfo.setLogoPicPath(null);
			appInfo.setAPKName(null);
			File file = new File(appInfo.getLogoLocPath());
			if(file.exists()){
				if(file.delete()){
					if(appinfoServiceDao.update(appInfo)> 0){
						resultMap.put("result", "success");
					}
				}
			}
		}
		return JSONArray.toJSONString(resultMap);
	}
	
	//上下架操作
	@RequestMapping("/{appId}/sale")
	@ResponseBody
	public Object appCloseAndOpen(@PathVariable String appId,HttpSession session){
		HashMap<String,String> resultMap = new HashMap<String,String>();
		Integer appIdInteger = 0;
		try {
			appIdInteger = Integer.parseInt(appId);
		} catch (Exception e) {
			appIdInteger = 0;
		}
		resultMap.put("errorCode", "0");
		
		if(appIdInteger > 0){
			DevUser devUser = (DevUser)session.getAttribute(Constants.DEV_USER_SESSION);
			AppInfo appInfo = appinfoServiceDao.getAppInfoById(appIdInteger);
			appInfo.setModifyBy(devUser.getId());
			//状态2，审核通过，上架需要把版本信息表的状态更改为已发布 5，已下架，都是可以直接上架的
			if(appInfo.getStatus() == 2){
				AppVersion appVersion = new AppVersion();
				appVersion.setId(appInfo.getVersionId());
				appVersion.setPublishStatus(appInfo.getStatus());;
				appVersion.setCreatedBy(appInfo.getDevId());
				appVersion.setCreationDate(new Date());
				appVserSionServiceDao.updateStatus(appVersion);
				
				appInfo.setOnSaleDate(new Date());
				appInfo.setStatus(4);
				
			}else if(appInfo.getStatus() == 4){
				appInfo.setOffSaleDate(new Date());
				appInfo.setStatus(5);
			}else{
				if (appInfo.getStatus() == 5) {
					appInfo.setOnSaleDate(new Date());
					appInfo.setStatus(4);
				}
			}
			appInfo.setCreationDate(new Date());
			if(appinfoServiceDao.update(appInfo) > 0){
				resultMap.put("resultMsg", "success");
			}else{
				resultMap.put("resultMsg", "failed");
			}
		}else{
			resultMap.put("resultMsg", "failed");
		}
		
		return resultMap;
	}

	//增加appVersion页面
	@RequestMapping("/appversionadd")
	public String addVersion(Model model,AppVersion appVersion,
								HttpSession session,HttpServletRequest request,
								@RequestParam("id")Integer appinfoId){
		logger.info("appinfoId------------------------------------------------" + appinfoId);
		//获取app的所有版本信息
		List<AppVersion> appVersionList = appVserSionServiceDao.getAppVsersionByappId(appinfoId);
		//获取当前登录的开发者用户
		DevUser devUserSession = (DevUser)session.getAttribute(Constants.DEV_USER_SESSION);
		model.addAttribute("appVersionList", appVersionList);
		appVersion.setAppId(appinfoId);
		model.addAttribute("appVersion", appVersion);
		model.addAttribute("devUserSession", devUserSession);
		return "developer/appversionadd";
	}
	
	//增加appVersion
	@RequestMapping(value="/addversionsave",method=RequestMethod.POST)
	public String doAddVsersion(HttpServletRequest request,
								HttpSession session,AppVersion appVersion,
								@RequestParam(value="a_downloadLink",required=false)MultipartFile a_downloadLink
								){
		//通过appId获取appinfo的对象
		AppInfo appInfo = appinfoServiceDao.getAppInfoById(appVersion.getAppId());
		
		//定义上传的apk名字
		String apkFileName = null;
		
		//定义apk文件的服务器存储路径
		String apkLocPath = null;
		
		//服务器路径
		String serverPath = session.getServletContext().getRealPath("statics" + File.separator + "uploadfiles");
		
		//判断是否有上传文件
		if(!a_downloadLink.isEmpty()){//不为空有上传文件
			
			//获取文件名
			String fileName = a_downloadLink.getOriginalFilename();
			//获取扩展名
			String suffix = FilenameUtils.getExtension(fileName);
			//设置大小不超过10000MB
			int fileSize = 1024000;
			
			//判断文件大小
			if(a_downloadLink.getSize() > fileSize){
				//超过上传上限
				request.setAttribute("fileUploadError", "上传失败，文件大小超过上限！");
				return "developer/appversionadd";
			}else if(suffix.equals("apk")){
				//定义上传到服务器的名字
				apkFileName = appInfo.getAPKName() + "-" + appVersion.getVersionNo() + "." + suffix;
				
				//通过服务器路径和文件名创建此文件
				File file = new File(serverPath,apkFileName);
				if(!file.exists()){
					file.mkdirs();
				}
				
				//将上传的文件流数据保存到服务器新建的文件中
				try {
					a_downloadLink.transferTo(file);
				} catch (Exception e) {
					e.printStackTrace();
					request.setAttribute("fileUploadError", "上传文件失败！");
					return "developer/appversionadd";
				}
				//定义apk文件的服务器存储路径
				apkLocPath = serverPath + File.separator + apkFileName;
			}else{
				request.setAttribute("fileUploadError", "上传失败，文件格式不正确！");
				return "developer/appversionadd";
			}
		}
		
		//设置apk文件的服务器路径
		appVersion.setApkLocPath(apkLocPath);
		//设置apk文件的名字
		appVersion.setApkFileName(apkFileName);
		//获取当前登录用户
		DevUser devUser = (DevUser)session.getAttribute(Constants.DEV_USER_SESSION);
		//设置创建者，为当前登录用户
		appVersion.setCreatedBy(devUser.getId());
		//创建时间
		appVersion.setCreationDate(new Date());
		//设置appId
		appVersion.setAppId(appInfo.getId());
		//下载路径
		String downloadLink = request.getContextPath() + "/statics/uploadfiles/" + apkFileName;
		appVersion.setDownloadLink(downloadLink);
		if(appVserSionServiceDao.add(appVersion) > 0){
				return "redirect:/dev/flatform/app/list";
			}
		return "developer/appversionadd";
	}

	//进入修改appVersion页面
	@RequestMapping("/appversionmodify")
	public String modify(Model model,@RequestParam("aid")Integer appId,
							@RequestParam("vid")Integer versionId,
							HttpSession session){
		//根据appId获取所有此app的版本信息
		List<AppVersion> appVersionList = appVserSionServiceDao.getAppVsersionByappId(appId);
		//根据appinfo表的versionId，是最新版本的id，获取最新版本的信息
		AppVersion appVersion = appVserSionServiceDao.getAppVsersionById(versionId);
		DevUser devUserSession = (DevUser)session.getAttribute(Constants.DEV_USER_SESSION);
		model.addAttribute("appVersionList", appVersionList);
		model.addAttribute("appVersion", appVersion);
		model.addAttribute("devUserSession", devUserSession);
		return "developer/appversionmodify";
	}
	
	//修改appVersion页面
	@RequestMapping("/appversionmodifysave")
	public String doModify(HttpServletRequest request,
							HttpSession session,AppVersion appVersion,
							@RequestParam(value="attach",required=false)MultipartFile attach
							){
		logger.info(appVersion.getApkFileName());
		//通过id获取AppVersion对象，因为参数AppVersion对象只有表单上有的属性，
		//创建者和创建时间没有，所以先通过id获取到AppVersion对象的所有信息,再所有信息的基础上改
		AppVersion appVersion2 = appVserSionServiceDao.getAppVsersionById(appVersion.getId());
		
		//通过appId获取appinfo的对象
		AppInfo appInfo = appinfoServiceDao.getAppInfoById(appVersion.getAppId());
		
		//定义上传的apk名字
		String apkFileName = appVersion2.getApkFileName();
		
		//定义apk文件的服务器存储路径
		String apkLocPath = appVersion2.getApkLocPath();
		
		//服务器路径
		String serverPath = session.getServletContext().getRealPath("statics" + File.separator + "uploadfiles");
		
		//判断是否有上传文件
		if(!attach.isEmpty()){//不为空有上传文件
			
			//获取文件名
			String fileName = attach.getOriginalFilename();
			//获取扩展名
			String suffix = FilenameUtils.getExtension(fileName);
			//设置大小不超过10000MB
			int fileSize = 1024000;
			
			//判断文件大小
			if(attach.getSize() > fileSize){
				//超过上传上限
				request.setAttribute("fileUploadError", "上传失败，文件大小超过上限！");
				return "developer/appversionmodify";
			}else if(suffix.equals("apk")){
				//定义上传到服务器的名字
				apkFileName = appInfo.getAPKName() + "-" + appVersion.getVersionNo() + "." + suffix;
				
				//通过服务器路径和文件名创建此文件
				File file = new File(serverPath,apkFileName);
				if(!file.exists()){
					file.mkdirs();
				}
				
				//将上传的文件流数据保存到服务器新建的文件中
				try {
					attach.transferTo(file);
				} catch (Exception e) {
					e.printStackTrace();
					request.setAttribute("fileUploadError", "上传文件失败！");
					return "developer/appversionmodify";
				}
				//定义apk文件的服务器存储路径
				apkLocPath = serverPath + File.separator + apkFileName;
			}else{
				request.setAttribute("fileUploadError", "上传失败，文件格式不正确！");
				return "developer/appversionmodify";
			}
		}
		
		//设置创建者
		appVersion.setCreatedBy(appVersion2.getCreatedBy());
		//设置创建时间
		appVersion.setCreationDate(appVersion2.getCreationDate());
		//设置apk文件的服务器路径
		appVersion.setApkLocPath(apkLocPath);
		//设置apk文件的名字
		appVersion.setApkFileName(apkFileName);
		//获取当前登录用户
		DevUser devUser = (DevUser)session.getAttribute(Constants.DEV_USER_SESSION);
		//设置创建者，为当前登录用户
		appVersion.setModifyBy(devUser.getId());
		//创建时间
		appVersion.setModifyDate(new Date());
		//设置appId
		appVersion.setAppId(appInfo.getId());
		//下载路径
		String downloadLink = request.getContextPath() + "/statics/uploadfiles/" + apkFileName;
		appVersion.setDownloadLink(downloadLink);
		
		if(appVserSionServiceDao.updateStatus(appVersion) > 0){
			return "redirect:/dev/flatform/app/list";
		}
		return "developer/appversionmodify";
	}

	//查看
	@RequestMapping("/appview/{id}")
	public String view(@PathVariable Integer id,Model model,HttpSession session){
		DevUser devUserSession = (DevUser)session.getAttribute(Constants.DEV_USER_SESSION);
		AppInfo appInfo = appinfoServiceDao.getAppInfoById(id);
		List<AppVersion> appVersionList = appVserSionServiceDao.getAppVsersionByappId(id);
		model.addAttribute("devUserSession", devUserSession);
		model.addAttribute("appInfo", appInfo);
		model.addAttribute("appVersionList", appVersionList);
		return "developer/appinfoview";
	}

	//删除
	@RequestMapping("/delapp")
	@ResponseBody
	public Object del(@RequestParam("id")Integer appInfoId){
		HashMap<String , String> resultMap = new HashMap<String,String>();
		int count = appinfoServiceDao.delete(appInfoId);
		int count2 = appVserSionServiceDao.delete(appInfoId);
		if(count > 0 && count2 > 0){
			resultMap.put("delResult","true");
		}else{
			resultMap.put("delResult","false");
		}
		return JSONArray.toJSONString(resultMap);
	}

}
