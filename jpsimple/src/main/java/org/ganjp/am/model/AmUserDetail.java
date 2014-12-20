package org.ganjp.am.model;

import org.ganjp.core.entity.Entity;

import java.math.BigDecimal;
import java.io.InputStream;
import java.sql.Timestamp;

public class AmUserDetail extends Entity{
	// Fields    
	private String userDetailId; 
	private String userId; 
	private String mobilePhone; 
	private String homePhone; 
	private String countryId; 
	private String provinceId; 
	private String cityId; 
	private String birthPlace; 
	private String livePlace; 
	private BigDecimal annualIncome; 
	private String jobCategoryPid; 
	private String jobCategoryId; 
	private String jobPositionId; 
	private String companyName; 
	private String shoolName; 
	private String educationId; 
	private String interestIds; 
	private String passwordTipCustom; 
	private String passwordTipId; 
	private String passwordTipAnswer; 
	private String signature; 
	private InputStream attach; 
	private String attachName; 
	private String remark; 
	private Timestamp createDateTime; 
	private Timestamp modifyTimestamp; 
	private String dataState; 
		
	/** default constructor */
    public AmUserDetail() {
    	super();
    }
    
    // Property accessors
public String getUserDetailId() {
        return this.userDetailId;
    }
    
    public void setUserDetailId(String userDetailId) {
		this.userDetailId = userDetailId;
    }
    
    public String getUserId() {
        return this.userId;
    }
    
    public void setUserId(String userId) {
		this.userId = userId;
    }
    
    public String getMobilePhone() {
        return this.mobilePhone;
    }
    
    public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
    }
    
    public String getHomePhone() {
        return this.homePhone;
    }
    
    public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
    }
    
    public String getCountryId() {
        return this.countryId;
    }
    
    public void setCountryId(String countryId) {
		this.countryId = countryId;
    }
    
    public String getProvinceId() {
        return this.provinceId;
    }
    
    public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
    }
    
    public String getCityId() {
        return this.cityId;
    }
    
    public void setCityId(String cityId) {
		this.cityId = cityId;
    }
    
    public String getBirthPlace() {
        return this.birthPlace;
    }
    
    public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
    }
    
    public String getLivePlace() {
        return this.livePlace;
    }
    
    public void setLivePlace(String livePlace) {
		this.livePlace = livePlace;
    }
    
    public BigDecimal getAnnualIncome() {
        return this.annualIncome;
    }
    
    public void setAnnualIncome(BigDecimal annualIncome) {
		this.annualIncome = annualIncome;
    }
    
    public String getJobCategoryPid() {
        return this.jobCategoryPid;
    }
    
    public void setJobCategoryPid(String jobCategoryPid) {
		this.jobCategoryPid = jobCategoryPid;
    }
    
    public String getJobCategoryId() {
        return this.jobCategoryId;
    }
    
    public void setJobCategoryId(String jobCategoryId) {
		this.jobCategoryId = jobCategoryId;
    }
    
    public String getJobPositionId() {
        return this.jobPositionId;
    }
    
    public void setJobPositionId(String jobPositionId) {
		this.jobPositionId = jobPositionId;
    }
    
    public String getCompanyName() {
        return this.companyName;
    }
    
    public void setCompanyName(String companyName) {
		this.companyName = companyName;
    }
    
    public String getShoolName() {
        return this.shoolName;
    }
    
    public void setShoolName(String shoolName) {
		this.shoolName = shoolName;
    }
    
    public String getEducationId() {
        return this.educationId;
    }
    
    public void setEducationId(String educationId) {
		this.educationId = educationId;
    }
    
    public String getInterestIds() {
        return this.interestIds;
    }
    
    public void setInterestIds(String interestIds) {
		this.interestIds = interestIds;
    }
    
    public String getPasswordTipCustom() {
        return this.passwordTipCustom;
    }
    
    public void setPasswordTipCustom(String passwordTipCustom) {
		this.passwordTipCustom = passwordTipCustom;
    }
    
    public String getPasswordTipId() {
        return this.passwordTipId;
    }
    
    public void setPasswordTipId(String passwordTipId) {
		this.passwordTipId = passwordTipId;
    }
    
    public String getPasswordTipAnswer() {
        return this.passwordTipAnswer;
    }
    
    public void setPasswordTipAnswer(String passwordTipAnswer) {
		this.passwordTipAnswer = passwordTipAnswer;
    }
    
    public String getSignature() {
        return this.signature;
    }
    
    public void setSignature(String signature) {
		this.signature = signature;
    }
    
    public InputStream getAttach() {
        return this.attach;
    }
    
    public void setAttach(InputStream attach) {
		this.attach = attach;
    }
    
    public String getAttachName() {
        return this.attachName;
    }
    
    public void setAttachName(String attachName) {
		this.attachName = attachName;
    }
    
    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
		this.remark = remark;
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
    
     
 	public String getColumnName(String className,String fieldName) {
 		if ("AmUserDetail".equalsIgnoreCase(className)) {
 	    	if ("userDetailId".equalsIgnoreCase(fieldName)) {
		   		return "user_detail_id";
			} else if ("userId".equalsIgnoreCase(fieldName)) {
		   		return "user_id";
			} else if ("mobilePhone".equalsIgnoreCase(fieldName)) {
		   		return "mobile_phone";
			} else if ("homePhone".equalsIgnoreCase(fieldName)) {
		   		return "home_phone";
			} else if ("countryId".equalsIgnoreCase(fieldName)) {
		   		return "country_id";
			} else if ("provinceId".equalsIgnoreCase(fieldName)) {
		   		return "province_id";
			} else if ("cityId".equalsIgnoreCase(fieldName)) {
		   		return "city_id";
			} else if ("birthPlace".equalsIgnoreCase(fieldName)) {
		   		return "birth_place";
			} else if ("livePlace".equalsIgnoreCase(fieldName)) {
		   		return "live_place";
			} else if ("annualIncome".equalsIgnoreCase(fieldName)) {
		   		return "annual_income";
			} else if ("jobCategoryPid".equalsIgnoreCase(fieldName)) {
		   		return "job_category_pid";
			} else if ("jobCategoryId".equalsIgnoreCase(fieldName)) {
		   		return "job_category_id";
			} else if ("jobPositionId".equalsIgnoreCase(fieldName)) {
		   		return "job_position_id";
			} else if ("companyName".equalsIgnoreCase(fieldName)) {
		   		return "company_name";
			} else if ("shoolName".equalsIgnoreCase(fieldName)) {
		   		return "shool_name";
			} else if ("educationId".equalsIgnoreCase(fieldName)) {
		   		return "education_id";
			} else if ("interestIds".equalsIgnoreCase(fieldName)) {
		   		return "interest_ids";
			} else if ("passwordTipCustom".equalsIgnoreCase(fieldName)) {
		   		return "password_tip_custom";
			} else if ("passwordTipId".equalsIgnoreCase(fieldName)) {
		   		return "password_tip_id";
			} else if ("passwordTipAnswer".equalsIgnoreCase(fieldName)) {
		   		return "password_tip_answer";
			} else if ("signature".equalsIgnoreCase(fieldName)) {
		   		return "signature";
			} else if ("attach".equalsIgnoreCase(fieldName)) {
		   		return "attach";
			} else if ("attachName".equalsIgnoreCase(fieldName)) {
		   		return "attach_name";
			} else if ("remark".equalsIgnoreCase(fieldName)) {
		   		return "remark";
			} else if ("createDateTime".equalsIgnoreCase(fieldName)) {
		   		return "create_date_time";
			} else if ("modifyTimestamp".equalsIgnoreCase(fieldName)) {
		   		return "modify_timestamp";
			} else if ("dataState".equalsIgnoreCase(fieldName)) {
		   		return "data_state";
			}
		}
		return null;
		
    }   
    
}