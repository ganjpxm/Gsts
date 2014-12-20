package org.ganjp.core.web.action;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ganjp.core.Configuration;
import org.ganjp.core.Constants;
import org.ganjp.core.entity.PageUrlEntity;
import org.ganjp.core.util.SqlUtil;
import org.ganjp.core.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseServlet extends HttpServlet {
	protected Logger log = LoggerFactory.getLogger(this.getClass());

	protected PageUrlEntity pageUrlEntity = null;
	protected String ERROR_URL = "/page/error.jsp";
	protected String NO_FOUND_URL = "/page/404.jsp";
	protected String action = null;
	
	/**
	 * 
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		doPost(request, response);
	}
	
	/**
	 * 
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		//request.setCharacterEncoding("utf-8");
		//response.setContentType("text/html; charset=UTF-8");
		try {
			action = request.getParameter("action");
			if (StringUtil.isBlank(action)) {
				response.sendRedirect(request.getContextPath() + NO_FOUND_URL);
			} else {
				pageUrlEntity = new PageUrlEntity();
				execute(request,response);
				if (StringUtil.isNotBlank(pageUrlEntity.getJsonData())) {
					response.setContentType("application/json;charset=UTF-8");   
					PrintWriter out = null;   
					try {   
						out = response.getWriter();   
					    out.print(pageUrlEntity.getJsonData());
					} catch (IOException ex) {   
					    ex.printStackTrace();   
					} finally {   
					    out.close();   
					}
					log.debug("json data: " + pageUrlEntity.getJsonData());
				} else if (StringUtil.isNotBlank(pageUrlEntity.getStringData())) {
					PrintWriter out = null;   
					try {   
						out = response.getWriter();   
						out.print(pageUrlEntity.getStringData());
					} catch (IOException ex) {   
					    ex.printStackTrace();   
					} finally {   
					    out.close();   
					}
					log.debug("string data: " + pageUrlEntity.getStringData());
				} else if (StringUtil.isNotBlank(pageUrlEntity.getImagePath())) {
					try{ 
						FileInputStream hFile = new FileInputStream(pageUrlEntity.getImagePath()); // 以byte流的方式打开文件 
						int i = hFile.available(); //得到文件大小 
						byte[] data=new byte[i]; 
						hFile.read(data); //读数据 
						hFile.close();
						response.setContentType("image/x-jg"); //设置返回的文件类型 
						OutputStream toClient=response.getOutputStream(); //得到向客户端输出二进制数据的对象 
						toClient.write(data); //输出数据 
						toClient.close();
					} catch(IOException e) { //错误处理 
						PrintWriter toClient = response.getWriter(); //得到向客户端输出文本的对象 
						toClient.write("don't open image!"); 
						toClient.close(); 
					}
				} else {
					if (StringUtil.isNotBlank(pageUrlEntity.getMsgContent())) {
						request.getSession().setAttribute(pageUrlEntity.getMsgType(), pageUrlEntity.getMsgContent());
					}
					if (StringUtil.isNotBlank(pageUrlEntity.getServletUrl())) {
						response.sendRedirect(request.getContextPath() + pageUrlEntity.getServletUrl());
					} else if (StringUtil.isNotBlank(pageUrlEntity.getJspUrl())) {
						ServletContext servletContext = getServletContext();
						RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(pageUrlEntity.getJspUrl());
				        requestDispatcher.forward(request, response);
					} else {
						response.sendRedirect(request.getContextPath() + NO_FOUND_URL);
					}
				}
			}
		}  catch (Exception ex) {
			log.error(ex.getMessage());
			saveMessage(request, Constants.MESSAGE_ERROR, ex.getLocalizedMessage());
			try {
				response.sendRedirect(request.getContextPath() + ERROR_URL);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
			
		}
	}
	
	/**
	 * execute business 
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 * @throws Exception
	 */
	public abstract void execute(HttpServletRequest request, HttpServletResponse response);

	
	/**
	 * <p>保存各类信息(错误,提示,成功,警告)到session上,以type值为主键</p>
	 * 
	 * @param request
	 * @param msg 保存到session的值
	 * @param type session的主键值
	 * @see Constants#MESSAGE_ERROR 
	 * @see Constants#MESSAGE_INFO
	 * @see Constants#MESSAGE_SUCCESS
	 * @see Constants#MESSAGE_WARNING
	 * @see Constants#MESSAGE_ERROR_FOOTER
	 * @see Constants#MESSAGE_INFO_FOOTER
	 * @see Constants#MESSAGE_SUCCESS_FOOTER
	 * @see Constants#MESSAGE_WARNING_FOOTER
	 */
	public void saveMessage(HttpServletRequest request, String type, String msg) {
        List messages = (List) request.getSession().getAttribute(type);
        if (messages == null) {
            messages = new ArrayList();
        }
        messages.add(msg);
        request.getSession().setAttribute(type, messages);
	}

	public String getMessage(String msgKey) {
		return Configuration.getValue(msgKey, Configuration.getLanguage());
    }
	
	public String getMessage(String msgKey, Object[] argArr) {
		return Configuration.getValue(msgKey, argArr, Configuration.getLanguage());
    }
	
	public String getMessage(String msgKey, String arg) {
        return getMessage(msgKey, new Object[] { arg });
    }
	
	public String getMessage(String msgKey, String arg1, String arg2) {
        return getMessage(msgKey, new Object[] { arg1, arg2 });
    }
	
	public int getPageNo(HttpServletRequest request) {
		String pageNoStr = request.getParameter("pageNo");
		int pageNo = 1;
		if (StringUtil.isNotBlank(pageNoStr)) pageNo=Integer.parseInt(pageNoStr);
		return pageNo;
	}
	
	public int getPageSize(HttpServletRequest request) {
		String pageSizeStr = request.getParameter("pageSize");
		int pageSize = Configuration.getInt("default.page.size");
		if (StringUtil.isNotBlank(pageSizeStr)) pageSize=Integer.parseInt(pageSizeStr);
		return pageSize;
	}
	
	/**
	 * fieldNamesWithComma:"configKey,configValue",className:"BmConfig"
	 * return Map(SqlUtil.COLUMN_NAMES, new String[]{config_key,config_value});
	 * return Map(SqlUtil.VALUES, new Object[]{'isStartH2','true'});
	 * 
	 * @param request
	 * @param fieldNamesWithComma
	 * @param className
	 * @return
	 */
	public Map getSqlInfoMap(HttpServletRequest request, final String fieldNamesWithComma, final String className, Map replaceMap) {
		Map sqlAndParamValueMap = new HashMap();
		String[] fieldNames = fieldNamesWithComma.split(",");
		Object[] paramObjects = new Object[fieldNames.length];
		String[] columnNames = new String[fieldNames.length];
		for (int i = 0; i < fieldNames.length; i++) {
			String fieldName = fieldNames[i];
			columnNames[i] = Configuration.getColumnName(className, fieldName);
			String value = null;
			if (replaceMap != null && replaceMap.containsKey(fieldName)) {
				value = (String)replaceMap.get(fieldName);
			} else {
				value = request.getParameter(fieldName);
			}
			
			paramObjects[i] = value;
		}
		sqlAndParamValueMap.put(SqlUtil.COLUMN_NAMES, columnNames);
		sqlAndParamValueMap.put(SqlUtil.VALUES, paramObjects);
		return sqlAndParamValueMap;
	}
	
	/**
	 * fieldNamesWithComma:"configKey,configValue",className:"BmConfig"
	 * return Map(SqlUtil.COLUMN_NAMES, new String[]{config_key,config_value});
	 * return Map(SqlUtil.VALUES, new Object[]{'isStartH2','true'});
	 * 
	 * @param request
	 * @param fieldNamesWithComma
	 * @param className
	 * @return
	 */
	public Map getSqlInfoMap(HttpServletRequest request, final String fieldNamesWithComma, final String className) {
		return this.getSqlInfoMap(request, fieldNamesWithComma, className, null);
	}
	
	public String getUrl(String roleIds, HttpServletRequest request) {
		
		if (roleIds.indexOf(Constants.SUPER_ADMIN_ROLE_ID)!=-1) {
			return getBasePath(request) + "/servlet/common?action=toGlobleManage";
		} else {
			return getBasePath(request);
		}
	}
	
	public String getBasePath(HttpServletRequest request) {
		return request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() + request.getContextPath();
	}
	
}
