package org.ganjp.core.web.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;

import org.ganjp.core.web.tag.base.AbstractSelectTag;
import org.ganjp.core.web.tag.base.ExpressionEvaluationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @jsp.tag name="yearmonth" bodycontent="empty"
 */
public class YearMonthTag extends AbstractSelectTag {

	private static final long serialVersionUID = 1L;
	protected final transient Logger log = LoggerFactory.getLogger(getClass());
	public static final int monthSize = 12;
	public static final String fillCode = "0";
	private String businessYear;
	
	public int doStartTag() throws JspException {
		StringBuffer html = new StringBuffer();
		prefix(html);
		
    	for (int i = 1; i <= monthSize; i++) {
    		String value = i < 10 ? businessYear + fillCode + String.valueOf(i) : businessYear + String.valueOf(i);
			if(value.equals(selected)) {
				html.append("<option value=\"").append(value).append("\" selected=\"true\">").append(value).append("</option>");
			} else {
				html.append("<option value=\"").append(value).append("\">").append(value).append("</option>");
			}
		}
    	suffix(html);

    	try {
			pageContext.getOut().write(html.toString());
		}
		catch (IOException io) {
			io.printStackTrace();
			throw new JspException("Error writing label: " + io.getMessage());
		}
		return (SKIP_BODY);
	}

	/**
	 * @jsp.attribute required="true" rtexprvalue="false"
	 */
	public void setBusinessYear(String businessYear) {
		try {
			this.businessYear = ExpressionEvaluationUtil.evaluateString("businessYear", businessYear, pageContext);;
		} catch (Exception e) {			
			log.error(e.getMessage());
		}
	}
}
