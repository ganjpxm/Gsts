package org.ganjp.am.model;

import org.ganjp.core.entity.Entity;

import java.sql.Timestamp;

public class AmUserRole extends Entity{
	// Fields    
	private String userRoleId; 
	private String roleId; 
	private String userId; 
	private Timestamp createDateTime; 
	private Timestamp modifyTimestamp; 
	private String dataState; 
		
	/** default constructor */
    public AmUserRole() {
    	super();
    }
    
    // Property accessors
public String getUserRoleId() {
        return this.userRoleId;
    }
    
    public void setUserRoleId(String userRoleId) {
		this.userRoleId = userRoleId;
    }
    
    public String getRoleId() {
        return this.roleId;
    }
    
    public void setRoleId(String roleId) {
		this.roleId = roleId;
    }
    
    public String getUserId() {
        return this.userId;
    }
    
    public void setUserId(String userId) {
		this.userId = userId;
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
    
}