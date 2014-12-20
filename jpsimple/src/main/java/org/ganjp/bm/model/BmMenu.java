package org.ganjp.bm.model;

import java.sql.Timestamp;

import org.ganjp.core.Constants;
import org.ganjp.core.entity.Entity;
import org.ganjp.core.util.StringUtil;

public class BmMenu extends Entity{
	// Fields    
	private String menuId; 
	private String menuPid;
	private String menuPids;
	private String menuCd; 
	private String menuName; 
	private String menuType; 
	private String url; 
	private String belongUserId; 
	private Integer displayLevel; 
	private Integer displayNo; 
	private String endFlag; 
	private Timestamp modifyTimestamp; 
	private Timestamp createDateTime; 
	private String dataState; 
		
	/** default constructor */
    public BmMenu() {
    	super();
    }
    
    // Property accessors
public String getMenuId() {
        return this.menuId;
    }
    
    public void setMenuId(String menuId) {
		this.menuId = menuId;
    }
    
    public String getMenuPid() {
        return this.menuPid;
    }
    
    public void setMenuPid(String menuPid) {
		this.menuPid = menuPid;
    }
    
    public String getMenuCd() {
        return this.menuCd;
    }
    
    public void setMenuCd(String menuCd) {
		this.menuCd = menuCd;
    }
    
    public String getMenuName() {
        return this.menuName;
    }
    
    public void setMenuName(String menuName) {
		this.menuName = menuName;
    }
    
    public String getMenuType() {
        return this.menuType;
    }
    
    public void setMenuType(String menuType) {
		this.menuType = menuType;
    }
    
    public String getUrl() {
    	if (this.url==null) {
    		return "";
    	}
        return this.url;
    }
    
    public void setUrl(String url) {
		this.url = url;
    }
    
    public String getBelongUserId() {
        return this.belongUserId;
    }
    
    public void setBelongUserId(String belongUserId) {
		this.belongUserId = belongUserId;
    }
    
    public Integer getDisplayLevel() {
        return this.displayLevel;
    }
    
    public void setDisplayLevel(Integer displayLevel) {
		this.displayLevel = displayLevel;
    }
    
    public Integer getDisplayNo() {
        return this.displayNo;
    }
    
    public void setDisplayNo(Integer displayNo) {
		this.displayNo = displayNo;
    }
    
    public String getEndFlag() {
        return this.endFlag;
    }
    
    public void setEndFlag(String endFlag) {
		this.endFlag = endFlag;
    }
    
    public Timestamp getModifyTimestamp() {
        return this.modifyTimestamp;
    }
    
    public void setModifyTimestamp(Timestamp modifyTimestamp) {
		this.modifyTimestamp = modifyTimestamp;
    }
    
    public Timestamp getCreateDateTime() {
        return this.createDateTime;
    }
    
    public void setCreateDateTime(Timestamp createDateTime) {
		this.createDateTime = createDateTime;
    }
    
    public String getDataState() {
        return this.dataState;
    }
    
    public void setDataState(String dataState) {
		this.dataState = dataState;
    }
    
    public String getMenuPids() {
		return menuPids;
	}

	public void setMenuPids(String menuPids) {
		this.menuPids = menuPids;
	}

	/**
     * 判断是否是末节点
     * @return boolean 如果是末节点返回true,否则返回false
     */
    public boolean isEnd() {
    	if (StringUtil.isNotBlank(this.endFlag) && Constants.END_FLAG_YES.equalsIgnoreCase(this.endFlag)){
    		return true;
    	} else {
    		return false;
    	}
    }
}