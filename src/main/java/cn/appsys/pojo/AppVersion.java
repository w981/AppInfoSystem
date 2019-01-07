package cn.appsys.pojo;

import java.math.BigDecimal;
import java.util.Date;
//app版本表
public class AppVersion {
    private Long id;	//序号

    private Long appid;	//appid（来源app_info表的id）

    private String versionno;	//版本号

    private String versioninfo;	//版本介绍

    private Long publishstatus;	//发布状态（来源data_dictionary,1 不发布，2 已发布，3 预发布）

    private String downloadlink;	//下载链接

    private BigDecimal versionsize;	//版本大小（单位M）

    private Long createdby;	//创建者（来源dev_user开发者信息表的用户id）

    private Date creationdate;	//创建时间

    private Long modifyby;	//更新者（来源dev_user开发者信息表的用户id）

    private Date modifydate;	//最新更新时间

    private String apklocpath;	//apk文件的服务器存储路径

    private String apkfilename;	//上传的apk文件名称

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

    public String getVersionno() {
        return versionno;
    }

    public void setVersionno(String versionno) {
        this.versionno = versionno == null ? null : versionno.trim();
    }

    public String getVersioninfo() {
        return versioninfo;
    }

    public void setVersioninfo(String versioninfo) {
        this.versioninfo = versioninfo == null ? null : versioninfo.trim();
    }

    public Long getPublishstatus() {
        return publishstatus;
    }

    public void setPublishstatus(Long publishstatus) {
        this.publishstatus = publishstatus;
    }

    public String getDownloadlink() {
        return downloadlink;
    }

    public void setDownloadlink(String downloadlink) {
        this.downloadlink = downloadlink == null ? null : downloadlink.trim();
    }

    public BigDecimal getVersionsize() {
        return versionsize;
    }

    public void setVersionsize(BigDecimal versionsize) {
        this.versionsize = versionsize;
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

    public String getApklocpath() {
        return apklocpath;
    }

    public void setApklocpath(String apklocpath) {
        this.apklocpath = apklocpath == null ? null : apklocpath.trim();
    }

    public String getApkfilename() {
        return apkfilename;
    }

    public void setApkfilename(String apkfilename) {
        this.apkfilename = apkfilename == null ? null : apkfilename.trim();
    }
}