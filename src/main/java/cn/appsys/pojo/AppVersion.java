package cn.appsys.pojo;

import java.math.BigDecimal;
import java.util.Date;
//app版本表
public class AppVersion {
    private int id;	//序号

    private int appId;	//appid（来源app_info表的id）

    private String versionNo;	//版本号

    private String versionInfo;	//版本介绍

    private int publishStatus;	//发布状态（来源data_dictionary,1 不发布，2 已发布，3 预发布）

    private String downloadLink;	//下载链接

    private BigDecimal versionSize;	//版本大小（单位M）

    private int createdBy;	//创建者（来源dev_user开发者信息表的用户id）

    private Date creationDate;	//创建时间

    private int modifyBy;	//更新者（来源dev_user开发者信息表的用户id）

    private Date modifyDate;	//最新更新时间

    private String apkLocPath;	//apk文件的服务器存储路径

    private String apkFileName;	//上传的apk文件名称
    
    private String appName;	//app软件名字
    
    private String publishStatusName;	//发布状态
    
	public String getPublishStatusName() {
		return publishStatusName;
	}

	public void setPublishStatusName(String publishStatusName) {
		this.publishStatusName = publishStatusName;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAppId() {
		return appId;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}

	public String getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo;
	}

	public String getVersionInfo() {
		return versionInfo;
	}

	public void setVersionInfo(String versionInfo) {
		this.versionInfo = versionInfo;
	}

	public int getPublishStatus() {
		return publishStatus;
	}

	public void setPublishStatus(int publishStatus) {
		this.publishStatus = publishStatus;
	}

	public String getDownloadLink() {
		return downloadLink;
	}

	public void setDownloadLink(String downloadLink) {
		this.downloadLink = downloadLink;
	}

	public BigDecimal getVersionSize() {
		return versionSize;
	}

	public void setVersionSize(BigDecimal versionSize) {
		this.versionSize = versionSize;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public int getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(int modifyBy) {
		this.modifyBy = modifyBy;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getApkLocPath() {
		return apkLocPath;
	}

	public void setApkLocPath(String apkLocPath) {
		this.apkLocPath = apkLocPath;
	}

	public String getApkFileName() {
		return apkFileName;
	}

	public void setApkFileName(String apkFileName) {
		this.apkFileName = apkFileName;
	}

}