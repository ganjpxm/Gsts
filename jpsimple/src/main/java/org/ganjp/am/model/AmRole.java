package org.ganjp.am.model;

import org.ganjp.core.entity.Entity;

import java.sql.Timestamp;

public class AmRole extends Entity{
	// Fields    
	private String roleId; 
	private String roleCd; 
	private String roleName; 
	private String description; 
	private Timestamp createDateTime; 
	private Timestamp modifyTimestamp; 
	private String dataState; 
		
	/** default constructor */
    public AmRole() {
    	super();
    }
    
    // Property accessors
public String getRoleId() {
        return this.roleId;
    }
    
    public void setRoleId(String roleId) {
		this.roleId = roleId;
    }
    
    public String getRoleCd() {
        return this.roleCd;
    }
    
    public void setRoleCd(String roleCd) {
		this.roleCd = roleCd;
    }
    
    public String getRoleName() {
        return this.roleName;
    }
    
    public void setRoleName(String roleName) {
		this.roleName = roleName;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
		this.description = description;
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