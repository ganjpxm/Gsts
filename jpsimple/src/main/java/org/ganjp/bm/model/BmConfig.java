package org.ganjp.bm.model;

import org.ganjp.core.entity.Entity;

import java.sql.Timestamp;

public class BmConfig extends Entity{
	// Fields    
	private String configId; 
	private String configKey; 
	private String configKeyDisplayName; 
	private String configValue; 
	private String description; 
	private String dataState; 
	private Timestamp createDateTime; 
	private Timestamp modifyTimestamp; 
		
	/** default constructor */
    public BmConfig() {
    	super();
    }
    
    // Property accessors
public String getConfigId() {
        return this.configId;
    }
    
    public void setConfigId(String configId) {
		this.configId = configId;
    }
    
    public String getConfigKey() {
        return this.configKey;
    }
    
    public void setConfigKey(String configKey) {
		this.configKey = configKey;
    }
    
    public String getConfigKeyDisplayName() {
        return this.configKeyDisplayName;
    }
    
    public void setConfigKeyDisplayName(String configKeyDisplayName) {
		this.configKeyDisplayName = configKeyDisplayName;
    }
    
    public String getConfigValue() {
        return this.configValue;
    }
    
    public void setConfigValue(String configValue) {
		this.configValue = configValue;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
		this.description = description;
    }
    
    public String getDataState() {
        return this.dataState;
    }
    
    public void setDataState(String dataState) {
		this.dataState = dataState;
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
    
     
 	public String getColumnName(String className,String fieldName) {
 		if ("BmConfig".equalsIgnoreCase(className)) {
 	    	if ("configId".equalsIgnoreCase(fieldName)) {
		   		return "config_id";
			} else if ("configKey".equalsIgnoreCase(fieldName)) {
		   		return "config_key";
			} else if ("configKeyDisplayName".equalsIgnoreCase(fieldName)) {
		   		return "config_key_display_name";
			} else if ("configValue".equalsIgnoreCase(fieldName)) {
		   		return "config_value";
			} else if ("description".equalsIgnoreCase(fieldName)) {
		   		return "description";
			} else if ("dataState".equalsIgnoreCase(fieldName)) {
		   		return "data_state";
			} else if ("createDateTime".equalsIgnoreCase(fieldName)) {
		   		return "create_date_time";
			} else if ("modifyTimestamp".equalsIgnoreCase(fieldName)) {
		   		return "modify_timestamp";
			}
		}
		return null;
		
    }   
    
    public String getTableName(String className) {
    	if ("BmConfig".equalsIgnoreCase(className)) {
    		return "bm_config";
    	}
    	return null;
    }  
}