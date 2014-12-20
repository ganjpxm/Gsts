package org.ganjp.cm.model;

import org.ganjp.core.entity.Entity;

import java.sql.Timestamp;

public class CmVocabulary extends Entity{
	// Fields    
	private String vocabularyId; 
	private String foreignLanguage; 
	private String phonogram; 
	private String chinese; 
	private String propertyCatagoryIds; 
	private String vocabularyCatagoryIds; 
	private String levelId; 
	private String recommendState; 
	private Integer displayNo; 
	private String description; 
	private String operatorId; 
	private String operatorName; 
	private Timestamp createDateTime; 
	private Timestamp modifyTimestamp; 
	private String dataState; 
		
	/** default constructor */
    public CmVocabulary() {
    	super();
    }
    
    // Property accessors
public String getVocabularyId() {
        return this.vocabularyId;
    }
    
    public void setVocabularyId(String vocabularyId) {
		this.vocabularyId = vocabularyId;
    }
    
    public String getForeignLanguage() {
        return this.foreignLanguage;
    }
    
    public void setForeignLanguage(String foreignLanguage) {
		this.foreignLanguage = foreignLanguage;
    }
    
    public String getPhonogram() {
        return this.phonogram;
    }
    
    public void setPhonogram(String phonogram) {
		this.phonogram = phonogram;
    }
    
    public String getChinese() {
        return this.chinese;
    }
    
    public void setChinese(String chinese) {
		this.chinese = chinese;
    }
    
    public String getPropertyCatagoryIds() {
        return this.propertyCatagoryIds;
    }
    
    public void setPropertyCatagoryIds(String propertyCatagoryIds) {
		this.propertyCatagoryIds = propertyCatagoryIds;
    }
    
    public String getVocabularyCatagoryIds() {
        return this.vocabularyCatagoryIds;
    }
    
    public void setVocabularyCatagoryIds(String vocabularyCatagoryIds) {
		this.vocabularyCatagoryIds = vocabularyCatagoryIds;
    }
    
    public String getLevelId() {
        return this.levelId;
    }
    
    public void setLevelId(String levelId) {
		this.levelId = levelId;
    }
    
    public String getRecommendState() {
        return this.recommendState;
    }
    
    public void setRecommendState(String recommendState) {
		this.recommendState = recommendState;
    }
    
    public Integer getDisplayNo() {
        return this.displayNo;
    }
    
    public void setDisplayNo(Integer displayNo) {
		this.displayNo = displayNo;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
		this.description = description;
    }
    
    public String getOperatorId() {
        return this.operatorId;
    }
    
    public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
    }
    
    public String getOperatorName() {
        return this.operatorName;
    }
    
    public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
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