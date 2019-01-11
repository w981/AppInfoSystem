package cn.appsys.pojo;

import java.util.Date;
//广告信息表
public class AdPromotion {
    private int id;	//序号
    
    private int appId;	//appId（app_info表的id）

    private String adPicPath;	//广告图片存储路径

    private int adPV;	//广告点击量
    
    private int carouselPosition;	//轮播位

    private Date startTime;	//起效时间

    private Date endTime;	//失效时间

    private int createdBy;	//创建者（backend_user用户表的id）

    private Date creationDate;	//创建时间

    private int modifyBy;	//更新者（backend_user用户表的id）

    private Date modifyDate;	//最新更新时间	

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

	public String getAdPicPath() {
		return adPicPath;
	}

	public void setAdPicPath(String adPicPath) {
		this.adPicPath = adPicPath;
	}

	public int getAdPV() {
		return adPV;
	}

	public void setAdPV(int adPV) {
		this.adPV = adPV;
	}

	public int getCarouselPosition() {
		return carouselPosition;
	}

	public void setCarouselPosition(int carouselPosition) {
		this.carouselPosition = carouselPosition;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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


}