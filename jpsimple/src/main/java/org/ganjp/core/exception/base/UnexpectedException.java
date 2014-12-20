package org.ganjp.core.exception.base;


/**
 * 运行时异常
 * 
 * 继承此类的异常一般都是由程序逻辑引起的，程序应该从逻辑角度尽量避免出现此类异常。
 * 
 * @author zhaoqy
 * 
 */
public class UnexpectedException extends NestedRuntimeException {
	
private static final long serialVersionUID = 1839620751050558396L;
	
	private boolean hasBeanRecorded = false;
	
	protected int errorCode;
	
	/**
	 * 
	 * @param message	异常信息
	 * @param record	是否已经记录
	 */
    public UnexpectedException(String message,boolean record) {
        super(message);
        this.recorded(record);
    }

    /**
     * 
     * @param exception	抛出的异常
     * @param record	是否已经记录
     */
    public UnexpectedException(Throwable exception,boolean record) {
        super("", exception);
        this.recorded(record);
    }
    
    /**
     * 
     * @param message
     * @param cause
     * @param record	是否已经记录
     */
    public UnexpectedException(String message, Throwable cause,boolean record) {
    	super(message, cause);
    	this.recorded(record);
    }
    
    /**
     * 
     * @param message
     */
    public UnexpectedException(String message) {
        super(message);
    }

    public UnexpectedException(Throwable exception) {
        super(exception.getMessage(), exception);
    }
    
    public UnexpectedException(String message, Throwable cause) {
    	super(message, cause);
    }
    
    /**
     * 返回异常编号<p>
     * 如要显示异常编号，子类需要覆盖此方法
     * @return
     */
    public int getErrorCode(){
    	return -1;
    }
    
    /**
     * 返回异常的标题
     * @return
     */
    public String getErrorTitle(){
    	return "运行时错误";
    }

    /**
     * 返回异常的详细信息
     * @return
     */
    public String getErrorDescription(){
    	String message = this.getMessage();
    	if(message==null || "".equals(message)){
    		message = getCause().getMessage();
    	}
    	return message;
    }
    
    /**
     * 异常是否已经记录过，指的是是否被记录到日志文件中了
     * @return
     */
    public boolean hasBeanRecorded(){
    	return hasBeanRecorded;
    }
    
    
    public void recorded(boolean hasBeanRecorded){
    	this.hasBeanRecorded = hasBeanRecorded;
    }
}

