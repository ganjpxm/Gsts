package org.ganjp.core.web.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ganjp.core.Constants;
import org.ganjp.core.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AddHeaderFilter extends BaseFilter {
	protected Logger log = LoggerFactory.getLogger(this.getClass());
	
	protected FilterConfig filterConfig;
	private static Integer expires = new Integer(1);
	protected Map headers = new HashMap();
	
	public void initInternal() {
		String headersStr = super.filterConfig.getInitParameter(Constants.HEAD_FILTER);
		String[] headers = StringUtil.split(headersStr, ",");
		for(int i = 0; i < headers.length; i++) {
			String[] temp = StringUtil.split(headers[i], "=");
			this.headers.put(temp[0].trim(), temp[1].trim());
		}
	   if(expires.intValue()==1 && super.filterConfig.getInitParameter("expires")!=null){
	       expires = new Integer(super.filterConfig.getInitParameter("expires"));
	   }
	   log.debug("initInternal() set head's gzip end");
	}
	
	/**
	 * set head's gzip
	 */
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		String header = request.getHeader("Accept-Encoding");
		
		for(Iterator it = headers.entrySet().iterator();it.hasNext();) {
			Map.Entry entry = (Map.Entry)it.next();
			response.addHeader((String)entry.getKey(),(String)entry.getValue());
		}
		response.addHeader("Cache-Control","max-age="+expires.intValue());
		log.debug("doFilterInternal() set head's gzip end");
		chain.doFilter(request, response);
	}
	
}