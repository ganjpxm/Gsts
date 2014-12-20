package org.ganjp.core.entity;

import org.ganjp.core.Constants;

public class PageUrlEntity {
	public static final String JSP_URL_PREFIX = "/page/";
	public static final String URL_SUFFIX = ".jsp";
	
	private String servletUrl = "";
	private String jspUrl = "";
	private String jsonData = "";
	private String stringData = "";
	private String msgType = Constants.MESSAGE_INFO;
	private String msgContent = "";
	private String imagePath = "";
	
	public String getServletUrl() {
		return servletUrl;
	}
	public void setServletUrl(String servletUrl) {
		this.servletUrl = servletUrl;
	}
	public String getJspUrl() {
		return jspUrl;
	}
	public void setJspUrl(String jspUrl) {
		this.jspUrl = JSP_URL_PREFIX + jspUrl + URL_SUFFIX;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	public String getJsonData() {
		return jsonData;
	}
	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}
	public String getStringData() {
		return stringData;
	}
	public void setStringData(String stringData) {
		this.stringData = stringData;
	}
	
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	/**
	 * 
	 * 
	 * @param msgType
	 * @param msgContent
	 */
    public void setMsg(String msgType, String msgContent) {
    	this.msgType = msgType;
    	this.msgContent = msgContent;
    }
    
    public void setSevletUrlAndSuccessMsg(String sevletUrl, String msgContent) {
		this.setSevletUrlAndMsg(sevletUrl, Constants.MESSAGE_SUCCESS, msgContent);
	}
    
    public void setSevletUrlAndErrorMsg(String sevletUrl, String msgContent) {
		this.setSevletUrlAndMsg(sevletUrl, Constants.MESSAGE_ERROR, msgContent);
	}
    
    public void setSevletUrlAndMsg(String servletUrl, String msgType, String msgContent) {
		this.setServletUrl(servletUrl);
		this.msgType = msgType;
    	this.msgContent = msgContent;
	}
    
    public void setJspUrlAndSuccessMsg(String jspUrl, String msgContent) {
		this.setJspUrlAndMsg(jspUrl, Constants.MESSAGE_SUCCESS, msgContent);
	}
    
    public void setJspUrlAndErrorMsg(String jspUrl, String msgContent) {
		this.setJspUrlAndMsg(jspUrl, Constants.MESSAGE_ERROR, msgContent);
	}
    
    public void setJspUrlAndMsg(String jspUrl, String msgType, String msgContent) {
		this.setJspUrl(jspUrl);
		this.msgType = msgType;
    	this.msgContent = msgContent;
	}
   
}
