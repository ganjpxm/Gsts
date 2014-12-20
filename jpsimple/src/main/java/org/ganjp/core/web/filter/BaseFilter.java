package org.ganjp.core.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ganjp.core.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Filter to wrap request with a request including user preferred locale.
 */
public abstract class BaseFilter implements Filter {
	
	protected final Logger log = LoggerFactory.getLogger(this.getClass());//Logger available to subclasses
    protected FilterConfig filterConfig;
    
    public final void init(FilterConfig filterConfig) throws ServletException {
    	log.debug("init() start");
		Assert.notNull(filterConfig, "FilterConfig must not be null");
		this.filterConfig = filterConfig;
		this.initInternal();
    }
    
    public void doFilter(final ServletRequest request, final ServletResponse response, FilterChain filterChain) 
    	throws IOException,ServletException {
    	log.debug("doFilter() start");
    	if (!(request instanceof HttpServletRequest) || !(response instanceof HttpServletResponse)) {
			throw new ServletException("BaseFilter just supports HTTP requests");
		}
    	HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		//String uri = httpRequest.getRequestURI();
		doFilterInternal(httpRequest, httpResponse, filterChain);
	}
    
    /**
	 * Same contract as for <code>doFilter</code>, but guaranteed to be
	 * just invoked base request. Provides HttpServletRequest and
	 * HttpServletResponse arguments instead of the default ServletRequest
	 * and ServletResponse ones.
	 */
	protected abstract void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException;
	
	protected abstract void initInternal();
	
	/**
	 * Make the ServletContext of this filter available to subclasses.
	 * Analogous to GenericServlet's <code>getServletContext()</code>.
	 * <p>Takes the FilterConfig's ServletContext by default.
	 * If initialized as bean in a Spring application context,
	 * it falls back to the ServletContext that the bean factory runs in.
	 * @return the ServletContext instance, or <code>null</code> if none available
	 * @see javax.servlet.GenericServlet#getServletContext()
	 * @see javax.servlet.FilterConfig#getServletContext()
	 * @see #setServletContext
	 */
	protected final ServletContext getServletContext() {
		return (this.filterConfig != null ? this.filterConfig.getServletContext() : this.getServletContext());
	}
	
    public void destroy() {
    	this.filterConfig=null;
    }
    
    
    
}
