package org.ganjp.core.web.tag.base;

import javax.servlet.jsp.tagext.TagSupport;

import org.ganjp.core.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class AbstractTag extends TagSupport {
	protected final transient Logger log = LoggerFactory.getLogger(getClass());

	protected String getMessage(String key) {
		String message = null;
		try {
			message = Configuration.getValue(key, Configuration.getLanguage());
		} catch (Exception ex) {
		    message = "???" + key + "???";
		}
		return message;
	}
}
