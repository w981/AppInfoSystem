package cn.appsys.pojo;

import java.util.Date;
//超级管理员表
public class BackendUser {
    private Long id;	//序号

    private String userCode;	//用户编码

    private String userName;	//用户名称

    private Long userType;	//用户角色类型（来源于数据字典表，分为：超管、财务、市场、运营、销售）

    private Long createdBy;	//创建者（来源本表的id）

    private Date creationDate;	//创建时间

    private Long modifyBy;	//更新者（来源本表的id）

    private Date modifyDate;	//最新更新时间

    private String userPassword;	//用户密码
    
    private String userTypeName;	//用户角色类型名称
    
    public String getUserTypeName() {
		return userTypeName;
	}

	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getUserType() {
		return userType;
	}

	public void setUserType(Long userType) {
		this.userType = userType;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Long getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(Long modifyBy) {
		this.modifyBy = modifyBy;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

}