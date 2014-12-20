/*
 * Copyright (c) 2010 ChinaSoft International, Ltd. All rights reserved
 * ResourceOne BizFoundation Project
 *
 */
package org.ganjp.extend.xml;

import java.io.InputStream;
import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

/**
 * 
 * <p>对hibernate的hbm文件对应的DTD文件进行指定</p>
 * 
 * @author BizFoundation Team: ganjp
 * 
 * @version 1.0
 * @since 4.3
 */
public class HibernateDtdEntityResolver implements EntityResolver, Serializable {
	private static final long serialVersionUID = 8520015288878114314L;

	private static final Logger log = LoggerFactory.getLogger(HibernateDtdEntityResolver.class);

	private static final String URL = "http://hibernate.sourceforge.net/";
	private transient ClassLoader resourceLoader;

	/**
	 * Default constructor using DTDEntityResolver classloader for resource loading.
	 */
	public HibernateDtdEntityResolver() {
		// backward compatibility
		resourceLoader = this.getClass().getClassLoader();
	}

	/**
	 * Set the class loader used to load resouces
	 * 
	 * @param resourceLoader class loader to use
	 */
	public HibernateDtdEntityResolver(ClassLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	/**
	 * 使用本地的DTD去验证hbm文件
	 */
	public InputSource resolveEntity(String publicId, String systemId) {
		if (systemId != null && systemId.startsWith(URL)) {
			log.debug("trying to locate " + systemId + " in classpath under org/hibernate/");
			// Search for DTD
			String path = "org/ganjp/extend/xml/resource/" + systemId.substring(URL.length());
			InputStream dtdStream = resourceLoader == null ? getClass().getResourceAsStream(path) : resourceLoader.getResourceAsStream(path);
			if (dtdStream == null) {
				log.debug(systemId + " not found in classpath");
				return null;
			} else {
				log.debug("found " + systemId + " in classpath");
				InputSource source = new InputSource(dtdStream);
				source.setPublicId(publicId);
				source.setSystemId(systemId);
				return source;
			}
		} else {
			log.debug(" use the default behaviour ");
			return null;
		}
	}

}
