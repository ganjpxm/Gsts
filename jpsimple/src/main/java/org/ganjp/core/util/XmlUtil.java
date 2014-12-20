package org.ganjp.core.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * 
 * @author ganjp
 * @date 2010-7-7
 * 
 */
public abstract class XmlUtil {

	protected static Logger logger = LoggerFactory.getLogger(XmlUtil.class);

	public static String encode(String src) {
		if (src == null) {
			return "";
		}

		char[] chars = src.toString().toCharArray();
		StringBuffer out = new StringBuffer();

		for (int i = 0; i < chars.length; i++) {
			switch (chars[i]) {
			case '&':
				out.append("&amp;");
				break;
			case '<':
				out.append("&lt;");
				break;
			case '>':
				out.append("&gt;");
				break;
			case '\"':
				out.append("&quot;");
				break;
			default:
				out.append(chars[i]);
			}
		}

		return out.toString();
	}

	public static Element getChildElement(Element parent, String childName) {
		NodeList children = parent.getChildNodes();
		int size = children.getLength();

		for (int i = 0; i < size; i++) {
			Node node = children.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				if (childName.equals(element.getNodeName())) {
					return element;
				}
			}
		}
		return null;
	}

	public static List getChildElements(Element parent, String childName) {
		NodeList children = parent.getChildNodes();
		List list = new ArrayList();
		int size = children.getLength();

		for (int i = 0; i < size; i++) {
			Node node = children.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				if (childName.equals(element.getNodeName())) {
					list.add(element);
				}
			}
		}

		return list;
	}

	public static String getText(Element element) {
		StringBuffer sb = new StringBuffer();
		NodeList list = element.getChildNodes();

		for (int i = 0; i < list.getLength(); i++) {
			Node child = list.item(i);
			switch (child.getNodeType()) {
			case Node.CDATA_SECTION_NODE:
			case Node.TEXT_NODE:
				sb.append(child.getNodeValue());
			}
		}

		return sb.toString().trim();
	}

	public static String getAttribute(Element element, String attributeName) {
		return element.getAttribute(attributeName).trim();
	}

	public static class DocumentErrorHandler implements ErrorHandler {
		public DocumentErrorHandler() {
		}

		public void warning(SAXParseException exception) throws SAXException {
		}

		public void error(SAXParseException exception) throws SAXException {
			throw new SAXException(getMessage(exception));
		}

		public void fatalError(SAXParseException exception) throws SAXException {
			throw new SAXException(getMessage(exception));
		}

		private String getMessage(SAXParseException exception) {
			return exception.getMessage() + " (" + "line:" + exception.getLineNumber()
					+ ((exception.getColumnNumber() > -1) ? ("col:" + exception.getColumnNumber()) : "") + ") ";
		}
	}

	/**
	 * 
	 * 
	 * @Creator paladin.xiehai
	 * @CreateTime 2008-10-17 $Author: ganjp $ ${date} $Revision: 1.1 $ $Date: 2010/07/07 10:01:25 $
	 */
	public static class AllExceptionsErrorHandler implements ErrorHandler {

		private final List exceptions = new ArrayList();

		public AllExceptionsErrorHandler() {

		}

		public List getExceptions() {
			return exceptions;
		}

		public void warning(SAXParseException exception) throws SAXException {

		}

		public void error(SAXParseException exception) throws SAXException {
			addMessage(exception);
		}

		public void fatalError(SAXParseException exception) throws SAXException {
			addMessage(exception);
		}

		private void addMessage(SAXParseException exception) {
			exceptions.add(exception.getMessage() + " (" + "line:" + exception.getLineNumber()
					+ ((exception.getColumnNumber() > -1) ? ("col:" + exception.getColumnNumber()) : "") + ") ");
		}
	}

	/**
	 * 
	 * @param is
	 * @param rootName
	 * @param valid
	 * @param entityResolver
	 * @return
	 * @throws Exception
	 */
	public static Element loadAsElement(InputStream is, String rootName, boolean valid, EntityResolver entityResolver)
			throws Exception {
		Document doc = loadAsDocument(is, valid, entityResolver);

		NodeList nodes = (NodeList) doc.getElementsByTagName(rootName);

		if ((nodes == null) || (nodes.getLength() == 0)) {
			return null;
		}

		return (Element) nodes.item(0);
	}

	public static Element loadAsElement(InputStream is, String rootName)
			throws Exception {
		return loadAsElement(is,rootName,false,null);
	}

	/**
	 * 
	 * @param is
	 * @param rootName
	 * @param valid
	 * @param entityResolver
	 * @return
	 * @throws Exception
	 */
	public static List loadAsElements(InputStream is, String rootName, boolean valid, EntityResolver entityResolver)
			throws Exception {
		try {
			List r = new ArrayList();

			Document doc = loadAsDocument(is, valid, entityResolver);
			NodeList nodes = (NodeList) doc.getElementsByTagName(rootName);

			if (nodes != null) {
				for (int i = 0; i < nodes.getLength(); i++) {
					Element item = (Element) nodes.item(i);
					r.add(item);
				}
			}
			return r;
		} finally {
			if (is != null) {
				is.close();
			}
		}
	}

	/**
	 * 
	 * @param is
	 * @param rootNames
	 * @param valid
	 * @param entityResolver
	 * @return
	 * @throws Exception
	 */
	public static Element[] loadAsElements(InputStream is, String[] rootNames, boolean valid,
			EntityResolver entityResolver) throws Exception {
		try {
			if ((rootNames == null) || (rootNames.length == 0)) {
				return new Element[] {};
			}
			Element[] elements = new Element[rootNames.length];

			Document doc = loadAsDocument(is, valid, entityResolver);

			for (int i = 0; i < elements.length; i++) {
				NodeList nodes = doc.getElementsByTagName(rootNames[i]);
				if ((nodes != null) && (nodes.getLength() > 0)) {
					elements[i] = (Element) nodes.item(0);
				} else {
					elements[i] = null;
				}
			}

			return elements;
		} finally {
			if (is != null) {
				is.close();
			}
		}
	}

	/**
	 * 
	 * @param is
	 * @param valid
	 * @param entityResolver
	 * @return
	 * @throws Exception
	 */
	public static Document loadAsDocument(InputStream is, boolean valid, EntityResolver entityResolver)
			throws Exception {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = null;
			try {
				db = dbf.newDocumentBuilder();
				if (valid && (null != entityResolver)) {
					dbf.setValidating(valid);
					db.setEntityResolver(entityResolver);
				}
			} catch (ParserConfigurationException e) {
				throw new SAXException("Cannot creating document builder!!!", e);
			}

			db.setErrorHandler(new DocumentErrorHandler());
			Document doc = null;

			try {
				doc = db.parse(is);
			} catch (Exception e) {
				throw e;
			}
			return doc;
		} finally {
			if (is != null) {
				is.close();
			}
		}
	}

	public static Document loadAsDocument(InputStream is) throws Exception {
		return loadAsDocument(is, false, null);

	}

}
