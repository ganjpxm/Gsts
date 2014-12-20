package org.ganjp.bm.model;
// Generated 2012-3-13 16:59:29 by Hibernate Tools 3.1.0.beta4

import java.sql.Timestamp;


/**
 *        @hibernate.class
 *         table="bm_record"
 *     
 */

public class BmRecord  implements java.io.Serializable {


    // Fields    

    private String recordId;
    private String tableName;
    private String operateTypeId;
    private String operatorId;
    private String operatorName;
    private String remark;
    private Timestamp createTime;
    private String dataState;


    // Constructors

    /** default constructor */
    public BmRecord() {
    }

	/** minimal constructor */
    public BmRecord(Timestamp createTime) {
        this.createTime = createTime;
    }
    
    /** full constructor */
    public BmRecord(String tableName, String operateTypeId, String operatorId, String operatorName, String remark, Timestamp createTime, String dataState) {
        this.tableName = tableName;
        this.operateTypeId = operateTypeId;
        this.operatorId = operatorId;
        this.operatorName = operatorName;
        this.remark = remark;
        this.createTime = createTime;
        this.dataState = dataState;
    }
    

   
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="uuid"
     *             type="java.lang.String"
     *             column="record_id"
     *             unsaved-value=""
     *         
     */

    public String getRecordId() {
        return this.recordId;
    }
    
    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }
    /**       
     *      *            @hibernate.property
     *             column="table_name"
     *             length="64"
     *         
     */

    public String getTableName() {
        return this.tableName;
    }
    
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    /**       
     *      *            @hibernate.property
     *             column="operate_type_id"
     *             length="32"
     *         
     */

    public String getOperateTypeId() {
        return this.operateTypeId;
    }
    
    public void setOperateTypeId(String operateTypeId) {
        this.operateTypeId = operateTypeId;
    }
    /**       
     *      *            @hibernate.property
     *             column="operator_id"
     *             length="32"
     *         
     */

    public String getOperatorId() {
        return this.operatorId;
    }
    
    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }
    /**       
     *      *            @hibernate.property
     *             column="operator_name"
     *             length="30"
     *         
     */

    public String getOperatorName() {
        return this.operatorName;
    }
    
    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }
    /**       
     *      *            @hibernate.property
     *             column="remark"
     *             length="255"
     *         
     */

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    /**       
     *      *            @hibernate.property
     *             column="create_time"
     *             length="19"
     *             not-null="true"
     *         
     */

    public Timestamp getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
    /**       
     *      *            @hibernate.property
     *             column="data_state"
     *             length="1"
     *         
     */

    public String getDataState() {
        return this.dataState;
    }
    
    public void setDataState(String dataState) {
        this.dataState = dataState;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("tableName").append("='").append(getTableName()).append("' ");			
      buffer.append("operateTypeId").append("='").append(getOperateTypeId()).append("' ");			
      buffer.append("operatorId").append("='").append(getOperatorId()).append("' ");			
      buffer.append("operatorName").append("='").append(getOperatorName()).append("' ");			
      buffer.append("remark").append("='").append(getRemark()).append("' ");			
      buffer.append("createTime").append("='").append(getCreateTime()).append("' ");			
      buffer.append("dataState").append("='").append(getDataState()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof BmRecord) ) return false;
		 BmRecord castOther = ( BmRecord ) other; 
         
		 return ( (this.getRecordId()==castOther.getRecordId()) || ( this.getRecordId()!=null && castOther.getRecordId()!=null && this.getRecordId().equals(castOther.getRecordId()) ) )
 && ( (this.getTableName()==castOther.getTableName()) || ( this.getTableName()!=null && castOther.getTableName()!=null && this.getTableName().equals(castOther.getTableName()) ) )
 && ( (this.getOperateTypeId()==castOther.getOperateTypeId()) || ( this.getOperateTypeId()!=null && castOther.getOperateTypeId()!=null && this.getOperateTypeId().equals(castOther.getOperateTypeId()) ) )
 && ( (this.getOperatorId()==castOther.getOperatorId()) || ( this.getOperatorId()!=null && castOther.getOperatorId()!=null && this.getOperatorId().equals(castOther.getOperatorId()) ) )
 && ( (this.getOperatorName()==castOther.getOperatorName()) || ( this.getOperatorName()!=null && castOther.getOperatorName()!=null && this.getOperatorName().equals(castOther.getOperatorName()) ) )
 && ( (this.getRemark()==castOther.getRemark()) || ( this.getRemark()!=null && castOther.getRemark()!=null && this.getRemark().equals(castOther.getRemark()) ) )
 && ( (this.getCreateTime()==castOther.getCreateTime()) || ( this.getCreateTime()!=null && castOther.getCreateTime()!=null && this.getCreateTime().equals(castOther.getCreateTime()) ) )
 && ( (this.getDataState()==castOther.getDataState()) || ( this.getDataState()!=null && castOther.getDataState()!=null && this.getDataState().equals(castOther.getDataState()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getRecordId() == null ? 0 : this.getRecordId().hashCode() );
         result = 37 * result + ( getTableName() == null ? 0 : this.getTableName().hashCode() );
         result = 37 * result + ( getOperateTypeId() == null ? 0 : this.getOperateTypeId().hashCode() );
         result = 37 * result + ( getOperatorId() == null ? 0 : this.getOperatorId().hashCode() );
         result = 37 * result + ( getOperatorName() == null ? 0 : this.getOperatorName().hashCode() );
         result = 37 * result + ( getRemark() == null ? 0 : this.getRemark().hashCode() );
         result = 37 * result + ( getCreateTime() == null ? 0 : this.getCreateTime().hashCode() );
         result = 37 * result + ( getDataState() == null ? 0 : this.getDataState().hashCode() );
         return result;
   }   





}
