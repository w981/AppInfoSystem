package cn.appsys.pojo;

import java.math.BigDecimal;
import java.util.Date;
//app信息表
public class AppInfo {
    private Long id;	//序号

    private String softwarename;	//app名称

    private String apkname;	//apk名称（唯一，下载下来时的名字）

    private String supportrom;	//支持ROM（版本要求）

    private String interfacelanguage;	//界面语言

    private BigDecimal softwaresize;	//软件大小

    private Date updatedate;	//更新日期

    private Long devid;	//开发者id（来源dev_user表的开发者id）

    private String appinfo;	//应用简介

    private Long status;	//状态（来源data_dictionary表，1 待审核 2 审核通过 
    								//3 审核不通过 4 已上架 5 已下架）

    private Date onsaledate;	//上架时间

    private Date offsaledate;	//下架时间

    private Long flatformid;	//所属平台（来源data_dictionary表，1手机，2平板，3通用）

    private Long categorylevel3;	//所属三级分类（来源app_category，app分类表parentId）

    private Long downloads;	//下载量

    private Long createdby;	//创建者（来源dev_user开发者信息表的用户id）

    private Date creationdate;	//创建时间

    private Long modifyby;	//更新者（来源dev_user开发者信息表的用户id）

    private Date modifydate;	//最新更新时间

    private Long categorylevel1;	//所属一级分类（来源app_category，app分类表parentId）

    private Long categorylevel2;	//所属二级分类（来源app_categoryapp分类表parentId）

    private String logopicpath;	//logo图片url路径

    private String logolocpath;	//logo图片服务器存储路径

    private Long versionid;	//最新的版本id （来源aap_versionapp版本表的id）

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSoftwarename() {
        return softwarename;
    }

    public void setSoftwarename(String softwarename) {
        this.softwarename = softwarename == null ? null : softwarename.trim();
    }

    public String getApkname() {
        return apkname;
    }

    public void setApkname(String apkname) {
        this.apkname = apkname == null ? null : apkname.trim();
    }

    public String getSupportrom() {
        return supportrom;
    }

    public void setSupportrom(String supportrom) {
        this.supportrom = supportrom == null ? null : supportrom.trim();
    }

    public String getInterfacelanguage() {
        return interfacelanguage;
    }

    public void setInterfacelanguage(String interfacelanguage) {
        this.interfacelanguage = interfacelanguage == null ? null : interfacelanguage.trim();
    }

    public BigDecimal getSoftwaresize() {
        return softwaresize;
    }

    public void setSoftwaresize(BigDecimal softwaresize) {
        this.softwaresize = softwaresize;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    public Long getDevid() {
        return devid;
    }

    public void setDevid(Long devid) {
        this.devid = devid;
    }

    public String getAppinfo() {
        return appinfo;
    }

    public void setAppinfo(String appinfo) {
        this.appinfo = appinfo == null ? null : appinfo.trim();
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Date getOnsaledate() {
        return onsaledate;
    }

    public void setOnsaledate(Date onsaledate) {
        this.onsaledate = onsaledate;
    }

    public Date getOffsaledate() {
        return offsaledate;
    }

    public void setOffsaledate(Date offsaledate) {
        this.offsaledate = offsaledate;
    }

    public Long getFlatformid() {
        return flatformid;
    }

    public void setFlatformid(Long flatformid) {
        this.flatformid = flatformid;
    }

    public Long getCategorylevel3() {
        return categorylevel3;
    }

    public void setCategorylevel3(Long categorylevel3) {
        this.categorylevel3 = categorylevel3;
    }

    public Long getDownloads() {
        return downloads;
    }

    public void setDownloads(Long downloads) {
        this.downloads = downloads;
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

    public Long getCategorylevel1() {
        return categorylevel1;
    }

    public void setCategorylevel1(Long categorylevel1) {
        this.categorylevel1 = categorylevel1;
    }

    public Long getCategorylevel2() {
        return categorylevel2;
    }

    public void setCategorylevel2(Long categorylevel2) {
        this.categorylevel2 = categorylevel2;
    }

    public String getLogopicpath() {
        return logopicpath;
    }

    public void setLogopicpath(String logopicpath) {
        this.logopicpath = logopicpath == null ? null : logopicpath.trim();
    }

    public String getLogolocpath() {
        return logolocpath;
    }

    public void setLogolocpath(String logolocpath) {
        this.logolocpath = logolocpath == null ? null : logolocpath.trim();
    }

    public Long getVersionid() {
        return versionid;
    }

    public void setVersionid(Long versionid) {
        this.versionid = versionid;
    }
}