package org.ganjp.bm.model;

import org.ganjp.core.entity.Entity;

import java.sql.Timestamp;

public class BmCodeName extends Entity{
	// Fields    
	private String codeNameId; 
	private String codeNamePid; 
	private String code; 
	private String name; 
	private Integer displayNo; 
	private Integer displayLevel; 
	private String endFlag; 
	private Timestamp createDateTime; 
	private Timestamp modifyTimestamp; 
	private String dataState; 
		
	/** default constructor */
    public BmCodeName() {
    	super();
    }
    
    // Property accessors
public String getCodeNameId() {
        return this.codeNameId;
    }
    
    public void setCodeNameId(String codeNameId) {
		this.codeNameId = codeNameId;
    }
    
    public String getCodeNamePid() {
        return this.codeNamePid;
    }
    
    public void setCodeNamePid(String codeNamePid) {
		this.codeNamePid = codeNamePid;
    }
    
    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
		this.code = code;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
		this.name = name;
    }
    
    public Integer getDisplayNo() {
        return this.displayNo;
    }
    
    public void setDisplayNo(Integer displayNo) {
		this.displayNo = displayNo;
    }
    
    public Integer getDisplayLevel() {
        return this.displayLevel;
    }
    
    public void setDisplayLevel(Integer displayLevel) {
		this.displayLevel = displayLevel;
    }
    
    public String getEndFlag() {
        return this.endFlag;
    }
    
    public void setEndFlag(String endFlag) {
		this.endFlag = endFlag;
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
 		if ("BmCodeName".equalsIgnoreCase(className)) {
 	    	if ("codeNameId".equalsIgnoreCase(fieldName)) {
		   		return "code_name_id";
			} else if ("codeNamePid".equalsIgnoreCase(fieldName)) {
		   		return "code_name_pid";
			} else if ("code".equalsIgnoreCase(fieldName)) {
		   		return "code";
			} else if ("name".equalsIgnoreCase(fieldName)) {
		   		return "name";
			} else if ("displayNo".equalsIgnoreCase(fieldName)) {
		   		return "display_no";
			} else if ("displayLevel".equalsIgnoreCase(fieldName)) {
		   		return "display_level";
			} else if ("endFlag".equalsIgnoreCase(fieldName)) {
		   		return "end_flag";
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
    
    public String getTableName(String className) {
    	if ("BmCodeName".equalsIgnoreCase(className)) {
    		return "bm_code_name";
    	}
    	return null;
    }  
}