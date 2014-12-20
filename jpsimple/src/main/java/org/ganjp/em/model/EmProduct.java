package org.ganjp.em.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.ganjp.core.entity.Entity;

public class EmProduct extends Entity{
	// Fields    
	private String productId; 
	private String productCd; 
	private String productNameCn; 
	private String productNameEn; 
	private BigDecimal price; 
	private String imagepath; 
	private String description; 
	private Timestamp createDateTime; 
	private Timestamp modifyTimestamp; 
	private String dataState; 
		
	/** default constructor */
    public EmProduct() {
    	super();
    }
    
    // Property accessors
    public String getProductId() {
        return this.productId;
    }
    
    public void setProductId(String productId) {
		this.productId = productId;
    }
    
    public String getProductCd() {
        return this.productCd;
    }
    
    public void setProductCd(String productCd) {
		this.productCd = productCd;
    }
    
    public String getProductNameCn() {
        return this.productNameCn;
    }
    
    public void setProductNameCn(String productNameCn) {
		this.productNameCn = productNameCn;
    }
    
    public String getProductNameEn() {
        return this.productNameEn;
    }
    
    public void setProductNameEn(String productNameEn) {
		this.productNameEn = productNameEn;
    }
    
    public BigDecimal getPrice() {
        return this.price;
    }
    
    public void setPrice(BigDecimal price) {
		this.price = price;
    }
    
    public String getImagepath() {
        return this.imagepath;
    }
    
    public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
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