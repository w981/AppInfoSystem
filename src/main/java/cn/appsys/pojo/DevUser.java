package cn.appsys.pojo;

import java.util.Date;
//开发者信息表
public class DevUser {
    private Long id;	//序号

    private String devcode;	//开发者账号

    private String devname;	//开发者名称
    
    private String devpassword;	//开发者密码

    private String devemail;	//开发者电子邮箱

    private String devinfo;	//开发者简介

    private Long createdby;	//创建者(来源backend_user用户表的用户id)

    private Date creationdate;	//创建时间

    private Long modifyby;	//更新者（来源backend_user用户表的用户id）

    private Date modifydate;	//最新更新时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDevcode() {
        return devcode;
    }

    public void setDevcode(String devcode) {
        this.devcode = devcode == null ? null : devcode.trim();
    }

    public String getDevname() {
        return devname;
    }

    public void setDevname(String devname) {
        this.devname = devname == null ? null : devname.trim();
    }

    public String getDevpassword() {
        return devpassword;
    }

    public void setDevpassword(String devpassword) {
        this.devpassword = devpassword == null ? null : devpassword.trim();
    }

    public String getDevemail() {
        return devemail;
    }

    public void setDevemail(String devemail) {
        this.devemail = devemail == null ? null : devemail.trim();
    }

    public String getDevinfo() {
        return devinfo;
    }

    public void setDevinfo(String devinfo) {
        this.devinfo = devinfo == null ? null : devinfo.trim();
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