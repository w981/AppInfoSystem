package cn.appsys.pojo;

import java.util.Date;
//广告信息表
public class AdPromotion {
    private Long id;	//序号
    
    private Long appid;	//appId（app_info表的id）

    private String adpicpath;	//广告图片存储路径

    private Long adpv;	//广告点击量
    
    private Integer carouselposition;	//轮播位

    private Date starttime;	//起效时间

    private Date endtime;	//失效时间

    private Long createdby;	//创建者（backend_user用户表的id）

    private Date creationdate;	//创建时间

    private Long modifyby;	//更新者（backend_user用户表的id）

    private Date modifydate;	//最新更新时间	

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAppid() {
        return appid;
    }

    public void setAppid(Long appid) {
        this.appid = appid;
    }

    public String getAdpicpath() {
        return adpicpath;
    }

    public void setAdpicpath(String adpicpath) {
        this.adpicpath = adpicpath == null ? null : adpicpath.trim();
    }

    public Long getAdpv() {
        return adpv;
    }

    public void setAdpv(Long adpv) {
        this.adpv = adpv;
    }

    public Integer getCarouselposition() {
        return carouselposition;
    }

    public void setCarouselposition(Integer carouselposition) {
        this.carouselposition = carouselposition;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Long getCreatedby() {
        return createdby;
    }

    public void setCreatedby(Long createdby) {
        this.createdby = createdby;
    }

    public Date getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(Date creationdate) {
        this.creationdate = creationdate;
    }

    public Long getModifyby() {
        return modifyby;
    }

    public void setModifyby(Long modifyby) {
        this.modifyby = modifyby;
    }

    public Date getModifydate() {
        return modifydate;
    }

    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }
}