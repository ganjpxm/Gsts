package org.ganjp.am.model;

import org.ganjp.core.entity.Entity;

import java.sql.Timestamp;

public class AmUser extends Entity{
	// Fields    
	private String userId; 
	private String userCd; 
	private String userName; 
	private String userAlias; 
	private Integer userLevel; 
	private Integer userScore; 
	private Integer loginTimes; 
	private String password; 
	private String gender; 
	private String qq; 
	private String email; 
	private String birthYear; 
	private String birthMonth; 
	private String birthDay; 
	private Timestamp createDateTime; 
	private Timestamp modifyTimestamp; 
	private String dataState; 
	private String roleIds;
	
	/** default constructor */
    public AmUser() {
    	super();
    }
    
    // Property accessors
public String getUserId() {
        return this.userId;
    }
    
    public void setUserId(String userId) {
		this.userId = userId;
    }
    
    public String getUserCd() {
        return this.userCd;
    }
    
    public void setUserCd(String userCd) {
		this.userCd = userCd;
    }
    
    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
		this.userName = userName;
    }
    
    public String getUserAlias() {
        return this.userAlias;
    }
    
    public void setUserAlias(String userAlias) {
		this.userAlias = userAlias;
    }
    
    public Integer getUserLevel() {
        return this.userLevel;
    }
    
    public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
    }
    
    public Integer getUserScore() {
        return this.userScore;
    }
    
    public void setUserScore(Integer userScore) {
		this.userScore = userScore;
    }
    
    public Integer getLoginTimes() {
        return this.loginTimes;
    }
    
    public void setLoginTimes(Integer loginTimes) {
		this.loginTimes = loginTimes;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
		this.password = password;
    }
    
    public String getGender() {
        return this.gender;
    }
    
    public void setGender(String gender) {
		this.gender = gender;
    }
    
    public String getQq() {
        return this.qq;
    }
    
    public void setQq(String qq) {
		this.qq = qq;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
		this.email = email;
    }
    
    public String getBirthYear() {
        return this.birthYear;
    }
    
    public void setBirthYear(String birthYear) {
		this.birthYear = birthYear;
    }
    
    public String getBirthMonth() {
        return this.birthMonth;
    }
    
    public void setBirthMonth(String birthMonth) {
		this.birthMonth = birthMonth;
    }
    
    public String getBirthDay() {
        return this.birthDay;
    }
    
    public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
    }
    
    public Timestamp getCreateDateTime() {
        return this.createDateTime;
    }
    
    public void setCreateDateTime(Timestamp createDateTime) {
		this.createDateTime = createDateTime;
    }
    
    public Timestamp getModifyTimestamp() {
        return this.modifyTimestamp;
    }
    
    public void setModifyTimestamp(Timestamp modifyTimestamp) {
		this.modifyTimestamp = modifyTimestamp;
    }
    
    public String getDataState() {
        return this.dataState;
    }
    
    public void setDataState(String dataState) {
		this.dataState = dataState;
    }

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}
    
}