/**
 * $Id: AmUser.java,v 1.0 2012/08/19 00:16:55 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.gmsw.am.model;

import org.ganjp.gmsw.common.model.BaseModel;
import org.ganjp.gcore.uuid.UUIDHexGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * <p>AmUser</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Entity
@Table(name="am_user")
public class AmUser extends BaseModel{
	@Id
	@Column(name="user_id")
	private String userId = UUIDHexGenerator.getUuid();
	
	@Column(name="user_cd")
	private String userCd;
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	@Column(name="gender")
	private String gender;
	@Column(name="birthday")
	private Date birthday;
	@Column(name="mobile_number")
	private String mobileNumber;
	@Column(name="email")
	private String email;
	@Column(name="password")
	private String password;
	@Column(name="photo_url")
	private String photoUrl;
	@Column(name="login_count")
	private Integer loginCount;
	@Column(name="description")
	private String description;
	@Column(name="lang")
	private String lang;
	@Column(name="operator_id")
	private String operatorId;
	@Column(name="operator_name")
	private String operatorName;
	@Column(name="create_date_time")
	private Timestamp createDateTime;
	@Column(name="modify_timestamp")
	private Timestamp modifyTimestamp;
	@Column(name="data_status")
	private String dataStatus;
		
	//----------------------------------------------- default constructor --------------------------
    public AmUser() {
    	super();
    }
    
    //------------------------------------------------ Property accessors --------------------------
/**
	 * @return String
	 */
	public String getUserId() {
        return this.userId;
    }
    
    /**
	 * @param String userId
	 */
    public void setUserId(String userId) {
		this.userId = userId;
    }
    /**
	 * @return String
	 */
	public String getUserCd() {
        return this.userCd;
    }
    
    /**
	 * @param String userCd
	 */
    public void setUserCd(String userCd) {
		this.userCd = userCd;
    }
    /**
	 * @return String
	 */
	public String getFirstName() {
        return this.firstName;
    }
    
    /**
	 * @param String firstName
	 */
    public void setFirstName(String firstName) {
		this.firstName = firstName;
    }
    /**
	 * @return String
	 */
	public String getLastName() {
        return this.lastName;
    }
    
    /**
	 * @param String lastName
	 */
    public void setLastName(String lastName) {
		this.lastName = lastName;
    }
    /**
	 * @return String
	 */
	public String getGender() {
        return this.gender;
    }
    
    /**
	 * @param String gender
	 */
    public void setGender(String gender) {
		this.gender = gender;
    }
    /**
	 * @return Date
	 */
	public Date getBirthday() {
        return this.birthday;
    }
    
    /**
	 * @param Date birthday
	 */
    public void setBirthday(Date birthday) {
		this.birthday = birthday;
    }
    /**
	 * @return String
	 */
	public String getMobileNumber() {
        return this.mobileNumber;
    }
    
    /**
	 * @param String mobileNumber
	 */
    public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
    }
    /**
	 * @return String
	 */
	public String getEmail() {
        return this.email;
    }
    
    /**
	 * @param String email
	 */
    public void setEmail(String email) {
		this.email = email;
    }
    /**
	 * @return String
	 */
	public String getPassword() {
        return this.password;
    }
    
    /**
	 * @param String password
	 */
    public void setPassword(String password) {
		this.password = password;
    }
    /**
	 * @return String
	 */
	public String getPhotoUrl() {
        return this.photoUrl;
    }
    
    /**
	 * @param String photoUrl
	 */
    public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
    }
    /**
	 * @return Integer
	 */
	public Integer getLoginCount() {
        return this.loginCount;
    }
    
    /**
	 * @param Integer loginCount
	 */
    public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
    }
    /**
	 * @return String
	 */
	public String getDescription() {
        return this.description;
    }
    
    /**
	 * @param String description
	 */
    public void setDescription(String description) {
		this.description = description;
    }
    /**
	 * @return String
	 */
	public String getLang() {
        return this.lang;
    }
    
    /**
	 * @param String lang
	 */
    public void setLang(String lang) {
		this.lang = lang;
    }
    /**
	 * @return String
	 */
	public String getOperatorId() {
        return this.operatorId;
    }
    
    /**
	 * @param String operatorId
	 */
    public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
    }
    /**
	 * @return String
	 */
	public String getOperatorName() {
        return this.operatorName;
    }
    
    /**
	 * @param String operatorName
	 */
    public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
    }
    /**
	 * @return Timestamp
	 */
	public Timestamp getCreateDateTime() {
        return this.createDateTime;
    }
    
    /**
	 * @param Timestamp createDateTime
	 */
    public void setCreateDateTime(Timestamp createDateTime) {
		this.createDateTime = createDateTime;
    }
    /**
	 * @return Timestamp
	 */
	public Timestamp getModifyTimestamp() {
        return this.modifyTimestamp;
    }
    
    /**
	 * @param Timestamp modifyTimestamp
	 */
    public void setModifyTimestamp(Timestamp modifyTimestamp) {
		this.modifyTimestamp = modifyTimestamp;
    }
    /**
	 * @return String
	 */
	public String getDataStatus() {
        return this.dataStatus;
    }
    
    /**
	 * @param String dataStatus
	 */
    public void setDataStatus(String dataStatus) {
		this.dataStatus = dataStatus;
    }
     
}