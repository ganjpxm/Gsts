package org.ganjp.core.web.filter;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ganjp.core.Configuration;
import org.ganjp.core.Constants;
import org.ganjp.core.util.StringUtil;


/**
 * Filter to wrap request with a request including user preferred locale.
 */
public class LocaleFilter extends BaseFilter {
	private String targetEncoding = "utf-8";
	  
	public void initInternal() {
		String encoding = filterConfig.getInitParameter(Constants.ENCODING);
		if (StringUtil.hasText(encoding)) this.targetEncoding = encoding;
		log.debug("initInternal() set encoding end");
	}
	
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
		request.setCharacterEncoding(targetEncoding);
		
		HttpSession session = request.getSession(false);
        Map config = (Map) getServletContext().getAttribute(Constants.WEB_CONFIG);
        
        String theme = request.getParameter(Constants.CSS_THEME);
        if (StringUtil.hasText(theme)) {
            config.put(Constants.CSS_THEME, theme);
        }
        Locale locale = request.getLocale();
        String language = locale.getLanguage();
        if (StringUtil.hasText(request.getParameter(Constants.I18N_LANGUAGE))) {
        	language = request.getParameter(Constants.I18N_LANGUAGE);
        } else if (null != session && null != session.getAttribute(Constants.I18N_LANGUAGE)) {
    		language = session.getAttribute(Constants.I18N_LANGUAGE).toString();
    	}
        config.put(Constants.I18N_LANGUAGE, language);
        Configuration.setLanguage(language);
        log.debug("doFilter() set csstheme and request or session language, set Configuration ThreadLocal language end");
        chain.doFilter(request, response);
	}

}
