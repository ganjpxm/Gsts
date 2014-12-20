package org.ganjp.cm.model;

import org.ganjp.core.entity.Entity;

import java.sql.Timestamp;

public class CmArticle extends Entity{
	// Fields    
	private String articleId; 
	private String articleCategoryIds; 
	private String articleCd; 
	private String articleTitle; 
	private String articleContent; 
	private String commentAuthorId; 
	private String commentAuthorName; 
	private String articleRecommendLevelId; 
	private Integer displayNo; 
	private String remark; 
	private String auditFlag; 
	private String operatorId; 
	private String operatorName; 
	private Timestamp createDateTime; 
	private Timestamp modifyTimestamp; 
	private String dataState; 
		
	/** default constructor */
    public CmArticle() {
    	super();
    }
    
    // Property accessors
public String getArticleId() {
        return this.articleId;
    }
    
    public void setArticleId(String articleId) {
		this.articleId = articleId;
    }
    
    public String getArticleCategoryIds() {
        return this.articleCategoryIds;
    }
    
    public void setArticleCategoryIds(String articleCategoryIds) {
		this.articleCategoryIds = articleCategoryIds;
    }
    
    public String getArticleCd() {
        return this.articleCd;
    }
    
    public void setArticleCd(String articleCd) {
		this.articleCd = articleCd;
    }
    
    public String getArticleTitle() {
        return this.articleTitle;
    }
    
    public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
    }
    
    public String getArticleContent() {
        return this.articleContent;
    }
    
    public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
    }
    
    public String getCommentAuthorId() {
        return this.commentAuthorId;
    }
    
    public void setCommentAuthorId(String commentAuthorId) {
		this.commentAuthorId = commentAuthorId;
    }
    
    public String getCommentAuthorName() {
        return this.commentAuthorName;
    }
    
    public void setCommentAuthorName(String commentAuthorName) {
		this.commentAuthorName = commentAuthorName;
    }
    
    public String getArticleRecommendLevelId() {
        return this.articleRecommendLevelId;
    }
    
    public void setArticleRecommendLevelId(String articleRecommendLevelId) {
		this.articleRecommendLevelId = articleRecommendLevelId;
    }
    
    public Integer getDisplayNo() {
        return this.displayNo;
    }
    
    public void setDisplayNo(Integer displayNo) {
		this.displayNo = displayNo;
    }
    
    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
		this.remark = remark;
    }
    
    public String getAuditFlag() {
        return this.auditFlag;
    }
    
    public void setAuditFlag(String auditFlag) {
		this.auditFlag = auditFlag;
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