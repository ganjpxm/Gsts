/*
 * $Id: StringUtil.java,v 1.1 2011/05/12 09:58:19 ganjp Exp $
 * 
 * Copyright 2011 the original author or authors.
 * This software is core of developing project and written by ganjianping. 
 */
package org.ganjp.core.util;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>字符串工具类</p>
 *
 * @author ganjp
 * @since 1.0
 */
public class StringUtil {
	private static Logger log = LoggerFactory.getLogger(StringUtil.class);
	
	public static final String EMPTY_STRING = "";
	public static final char DOT = '.';
	public static final char UNDERSCORE = '_';
	public static final String COMMA_SPACE = ", ";
	public static final String COMMA = ",";
	public static final String OPEN_PAREN = "(";
	public static final String CLOSE_PAREN = ")";
	public static final String EMPTY = "";
	
	public StringUtil() {
		super();
	}
	/**
	 * <p>格式化字符串</p>
	 * <pre>
	 *  String str = "Hello %s";
	 * 	format(str,"ganjp"); =>Hello ganjp
	 * </pre>
	 * 
	 * @param str 要格式化的字符串
	 * @param arg 占位符的值
	 * @return
	 */
	
	public static String format(String str, String arg){
		return format(str,new String[]{arg});
	}
	
	/**
	 * <p>格式化字符串</p>
	 * <pre>
	 * 	String str = "Welcome %s and %s";
	 * 	format(str,"ganjp","xiaoping"); =>Welcome ganjp and xiaoping
	 * </pre>
	 * 
	 * @param str  要格式化的字符串
	 * @param arg1   占位符1的值
	 * @param arg2   占位符2的值
	 * @return
	 */
	public static String format(String str, String arg1, String arg2){
		return format(str,new String[]{arg1,arg2});
	}
	
	/**
	 * <p>格式化字符串</p>
	 * <pre>
	 * 	String str = "Welcome %s, %s and %s";
	 * 	format(str,"ganjp","xiaogan","xiaoping"); =>Welcome ganjp, xiaogan and xiaoping
	 * </pre>
	 * 
	 * @param str  要格式化的字符串
	 * @param arg1   占位符1的值
	 * @param arg2   占位符2的值
	 * @param arg3   占位符3的值
	 * @return
	 */
	public static String format(String str, String arg1, String arg2, String arg3){
		return format(str,new String[]{arg1,arg2,arg3});
	}
	
	/**
	 * <p>格式化字符串</p>
	 * <pre>
	 * 	String str = "Hello %s, welcome to %s";
	 * 	format(str, new Object[]{"Ganjp","China"}); => Hello Ganjp, welcome to China
	 * </pre>
	 * 
	 * @param str    要格式化的字符串
	 * @param argArr 占位符的值
	 * @return
	 */
	public static String format(String str, String[] argArr){
		if (argArr == null)
			return str;
		for (int i = 0; i < argArr.length; i++) {
			if(argArr[i]!=null){
				if (str.indexOf("%s") != -1) {
					str = str.replaceFirst("\\%s", argArr[i]);
				}
			}
		}
		return str;
	}

	/**
	 * <p>判断字符串是否为null或者""</p>
	 * <pre>
	 *  StringUtils.isEmpty(null)      = true
	 *  StringUtils.isEmpty(&quot;&quot;)        = true
	 *  StringUtils.isEmpty(&quot; &quot;)       = false
	 *  StringUtils.isEmpty(&quot;bob&quot;)     = false
	 *  StringUtils.isEmpty(&quot;  bob  &quot;) = false
	 * </pre>
	 * <p>注意：该方法不会去掉字符串的空格</p>
	 * 
	 * @param str 要判断的字符串
	 * @return 如果字符串为null或者""，返回<code>true</code>
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

	/**
	 * <p>判断字符串不为空或者null</p>
	 * <pre>
	 *  StringUtils.isNotEmpty(null)      = false
	 *  StringUtils.isNotEmpty(&quot;&quot;)        = false
	 *  StringUtils.isNotEmpty(&quot; &quot;)       = true
	 *  StringUtils.isNotEmpty(&quot;bob&quot;)     = true
	 *  StringUtils.isNotEmpty(&quot;  bob  &quot;) = true
	 * </pre>
	 * <p>注意：该方法不会去掉字符串的空格</p>
	 * 
	 * @param str 要判断的字符串 
	 * @return    如果字符串不为null或者""，返回<code>true</code>
	 */
	public static boolean isNotEmpty(String str) {
		return !StringUtil.isEmpty(str);
	}

	/**
	 * <p>判断字符串是否为null或者""，首先trim然后再进行判断</p>
	 * <pre>
	 *  StringUtils.isBlank(null)      = true
	 *  StringUtils.isBlank(&quot;&quot;)        = true
	 *  StringUtils.isBlank(&quot; &quot;)       = true
	 *  StringUtils.isBlank(&quot;bob&quot;)     = false
	 *  StringUtils.isBlank(&quot;  bob  &quot;) = false
	 * </pre>
	 * 
	 * @param  str 要判断的字符串
	 * @return 如果字符串为null或者""，返回<code>true</code>
	 */
	public static boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((!Character.isWhitespace(str.charAt(i)))) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * <p>判断字符串是不为null或者""，首先trim然后再进行判断</p>
	 * <pre>
	 *  StringUtils.isNotBlank(null)      = false
	 *  StringUtils.isNotBlank(&quot;&quot;)        = false
	 *  StringUtils.isNotBlank(&quot; &quot;)       = false
	 *  StringUtils.isNotBlank(&quot;bob&quot;)     = true
	 *  StringUtils.isNotBlank(&quot;  bob  &quot;) = true
	 * </pre>
	 *
	 * @param  str 要判断的字符串
	 * @return 如果字符串不为null或者""，返回<code>true</code>
	 */
	public static boolean isNotBlank(String str) {
		return !StringUtil.isBlank(str);
	}
	
	/**
	 * <p>判断给定的str是否为null，如果为null返回defalutStr</p>
	 * 
	 * @param str
	 * @param defalutStr
	 * @return
	 */
	public static String blankStr(String str,String defalutStr){
		if(defalutStr == null)
			defalutStr = "";
		
		if(str == null)
			return defalutStr;
		
		return str.trim();
	}
	
	/**
	 * <p>判断给定的str是否为null，如果为null返回""</p>
	 * 
	 * @param str
	 * @return
	 */
	public static String blankStr(String str){
		return blankStr(str,null);
	}

	/**
	 * <p>转义数据库通配字符：'%','_','\'</p>
	 * <pre>
	 * 	convertCastChar(&quot;abc%bcd_&quot;) =&gt; abc\%bcd\_
	 * </pre>
	 *
	 * @param src 要转义的字符串
	 * @return    转义后的字符串
	 */
	public static String convertCastChar(String src) {
		if (isBlank(src)) {
			return src;
		}
		int length = src.length();
		StringBuffer tmpStrBuf = new StringBuffer();
		for (int i = 0; i < length; i++) {
			switch (src.charAt(i)) {
				case '%':
				case '_':
				case '\\':
					tmpStrBuf.append("\\");
					break;
			}
			tmpStrBuf.append(src.charAt(i));
		}
		return tmpStrBuf.toString();
	}

	/**
	 * <p>转换常见的Html符号 </p>
	 * <pre>
	 * 	convertForHtml(&quot;&lt;input type='text'&gt;&quot;) =&gt; &lt;input type='text'&gt;
	 * </pre>
	 *
	 * @param src 要转义的字符串
	 * @return 转义后的字符串
	 */
	public static String convertForHtml(String src) {
		if (isEmpty(src)) {
			return src;
		}
		int length = src.length();
		StringBuffer tmp = new StringBuffer();
		for (int i = 0; i < length; i++) {
			switch (src.charAt(i)) {
				case '<':
					tmp.append("&lt;");
					break;
				case '>':
					tmp.append("&gt;");
					break;
				case '"':
					tmp.append("&quot;");
					break;
				case ' ': {
					int spaceCount = 0;
					for (; src.charAt(i) == ' '; i++, spaceCount++)
						;
					for (int j = 0; j < spaceCount / 2; j++) {
						tmp.append(" ");
					}
					if (spaceCount % 2 != 0) {
						tmp.append("&#160;");
					}
					--i;
					break;
				}
				case '\n':
					tmp.append("<br/>");
					break;
				case '&':
					tmp.append("&amp;");
					break;
				case '\r':
					break;
				default:
					tmp.append(src.charAt(i));
					break;
			}
		}
		return tmp.toString();
	}

	/**
	 * <p>转换常见的XML符号 </p>
	 * <pre>
	 *  convertForXml(&quot;&lt;name type='a' /&gt;&quot;) =&gt; &lt;name type='a' /&gt;
	 * </pre>
	 *
	 * @param src 要转义的字符串
	 * @return    转义后的字符串
	 */
	public static String convertForXml(String src) {
		if (isEmpty(src)) {
			return src;
		}
		int length = src.length();
		StringBuffer tmp = new StringBuffer();
		for (int i = 0; i < length; i++) {
			switch (src.charAt(i)) {
				case '<':
					tmp.append("&lt;");
					break;
				case '>':
					tmp.append("&gt;");
					break;
				case '"':
					tmp.append("&quot;");
					break;
				case ' ': {
					int spaceCount = 0;
					for (; src.charAt(i) == ' '; i++, spaceCount++)
						;
					for (int j = 0; j < spaceCount / 2; j++) {
						tmp.append(" ");
					}
					if (spaceCount % 2 != 0) {
						tmp.append("&#160;");
					}
					--i;
					break;
				}
				case '&':
					tmp.append("&amp;");
					break;
				case '\r':
					break;
				default:
					tmp.append(src.charAt(i));
					break;
			}
		}
		return tmp.toString();
	}

	/**
	 * <p>转义单引号</p>
	 * <pre>
	 *  convertSingleQuot(&quot;insert into table ('abc')&quot;) =&gt; insert into table (''abc'')
	 * </pre>
	 *
	 * @param src 要转义的字符串
	 * @return    转义后的字符串
	 */
	public static String convertSingleQuot(String src) {
		if (isEmpty(src)) {
			return src;
		}
		int length = src.length();
		StringBuffer tmp = new StringBuffer();
		for (int i = 0; i < length; i++) {
			if (src.charAt(i) == '\'') {
				tmp.append("\'\'");
			} else {
				tmp.append(src.charAt(i));
			}
		}
		return tmp.toString();
	}

	/**
	 * <p>字符串替换 Creation date: (2000-12-23 15:32:25)</p>
	 * <pre>
	 * 	String str = &quot;abc_d&quot;;
	 *  str = replace(str,&quot;_&quot;,&quot;#&quot;); =&gt; abc#d
	 * </pre>
	 *
	 * @param srcStr  源字符串
	 * @param oldStr  替换目标
	 * @param newStr  替换字符串
	 * @return  替换后的字符串
	 */
	public static String replace(String srcStr, String oldStr, String newStr) {
		if (isEmpty(srcStr)) {
			return srcStr;
		}
		if (isEmpty(oldStr)) {
			return srcStr;
		}
		if (srcStr == null) {
			srcStr = "";
		}
		StringBuffer strBuf = new StringBuffer();
		int idx1 = 0;
		int idx2 = 0;
		while ((idx2 = srcStr.indexOf(oldStr, idx1)) != -1) {
			strBuf.append(srcStr.substring(idx1, idx2)).append(newStr);
			idx1 = idx2 + oldStr.length();
		}
		strBuf.append(srcStr.substring(idx1));
		return strBuf.toString();
	}

	/**
	 * <p>字符串替换 </p>
	 * <pre>
	 *  int[] count = new int[1];
	 * 	String str = &quot;abc_defee&quot;;
	 *  StringUtil.replace(str,&quot;e&quot;,&quot;#&quot;,count); =&gt; abc_d#f##
	 *  System.out.println(count[0]) =&gt; 3
	 * </pre>
	 *
	 * @param srcStr   源字符串
	 * @param oldStr   替换目标
	 * @param newStr   替换字符串
	 * @param countArr 替换个数
	 * @return 替换后的字符串
	 */
	public static final String replace(String srcStr, String oldStr, String newStr, int countArr[]) {
		if (srcStr == null)
			return null;
		int i = 0;
		if ((i = srcStr.indexOf(oldStr, i)) >= 0) {
			int counter = 0;
			counter++;
			char line2[] = srcStr.toCharArray();
			char newString2[] = newStr.toCharArray();
			int oLength = oldStr.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;
			int j;
			for (j = i; (i = srcStr.indexOf(oldStr, i)) > 0; j = i) {
				counter++;
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
			}

			buf.append(line2, j, line2.length - j);
			countArr[0] = counter;
			return buf.toString();
		} else {
			return srcStr;
		}
	}

	/**
	 * <p>字符串替换，忽略大小写 </p>
	 * <pre>
	 * 	String str = &quot;abc_def&quot;;
	 *  StringUtil.replaceIgnoreCase(str,&quot;E&quot;,&quot;#&quot;); =&gt; abc_d#f
	 * </pre>
	 *
	 * @param srcStr 源字符串
	 * @param oldStr 替换目标
	 * @param newStr 替换字符串
	 * @return 替换后的字符串
	 */
	public static final String replaceIgnoreCase(String srcStr, String oldStr, String newStr) {
		if (srcStr == null)
			return null;
		String lcLine = srcStr.toLowerCase();
		String lcOldString = oldStr.toLowerCase();
		int i = 0;
		if ((i = lcLine.indexOf(lcOldString, i)) >= 0) {
			char line2[] = srcStr.toCharArray();
			char newString2[] = newStr.toCharArray();
			int oLength = oldStr.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;
			int j;
			for (j = i; (i = lcLine.indexOf(lcOldString, i)) > 0; j = i) {
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
			}

			buf.append(line2, j, line2.length - j);
			return buf.toString();
		} else {
			return srcStr;
		}
	}

	/**
	 * <p>字符串替换，忽略大小写,并记录替换的个数 </p>
	 * <pre>
	 *  int[] count = new int[1];
	 * 	String str = &quot;abc_defee&quot;;
	 *  StringUtil.replaceIgnoreCase(str,&quot;E&quot;,&quot;#&quot;,count); =&gt; abc_d#f##
	 *  System.out.println(count[0]) =&gt; 3
	 * </pre>
	 *
	 * @param srcStr 源字符串
	 * @param oldStr 替换目标
	 * @param newStr 替换字符串
	 * @param count  替换个数
	 * @return 替换后的字符串
	 */
	public static final String replaceIgnoreCase(String srcStr, String oldStr, String newStr, int count[]) {
		if (srcStr == null)
			return null;
		String lcLine = srcStr.toLowerCase();
		String lcOldString = oldStr.toLowerCase();
		int i = 0;
		if ((i = lcLine.indexOf(lcOldString, i)) >= 0) {
			int counter = 0;
			char line2[] = srcStr.toCharArray();
			char newString2[] = newStr.toCharArray();
			int oLength = oldStr.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;
			int j;
			for (j = i; (i = lcLine.indexOf(lcOldString, i)) > 0; j = i) {
				counter++;
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
			}

			buf.append(line2, j, line2.length - j);
			count[0] = counter + 1;
			return buf.toString();
		} else {
			return srcStr;
		}
	}

	/**
	 * <p>字符串替换，只替换第一个匹配</p>
	 * <pre>
	 * 	String str = &quot;abc_defee&quot;;
	 *  StringUtil.replaceOnce(str,&quot;e&quot;,&quot;#&quot;); =&gt; abc_d#fee
	 * </pre>
	 *
	 * @param srcStr 源字符串
	 * @param oldStr 替换目标
	 * @param newStr 替换字符串
	 * @return 替换后的字符串
	 */
	public static String replaceOnce(String srcStr, String oldStr, String newStr) {
		int loc = srcStr.indexOf(oldStr);
		if (loc < 0) {
			return srcStr;
		} else {
			return new StringBuffer(srcStr.substring(0, loc)).append(newStr).append(srcStr.substring(loc + oldStr.length())).toString();
		}
	}

	/**
	 * <p>对html中的&lt;和&gt进行替换 </p>
	 * <pre>
	 * 	String str = &quot;&lt;input type='text' value='d' /&gt;&quot;;
	 *  StringUtil.escapeHTMLTags(str); =&gt; &lt;input type='text' value='d' /&gt;
	 * </pre>
	 *
	 * @param htmlStr
	 * @return
	 */
	public static final String escapeHTMLTags(String htmlStr) {
		if (htmlStr == null)
			return null;
		int i = 0;
		int last = 0;
		char input[] = htmlStr.toCharArray();
		int len = input.length;
		StringBuffer out = new StringBuffer((int) ((double) len * 1.3D));
		for (; i < len; i++) {
			char ch = input[i];
			if (ch <= '>')
				if (ch == '<') {
					if (i > last)
						out.append(input, last, i - last);
					last = i + 1;
					out.append(LT_ENCODE);
				} else if (ch == '>') {
					if (i > last)
						out.append(input, last, i - last);
					last = i + 1;
					out.append(GT_ENCODE);
				}
		}

		if (last == 0)
			return htmlStr;
		if (i > last)
			out.append(input, last, i - last);
		return out.toString();
	}

	/**
	 * <p>替换>为&lt;</p>
	 *
	 * @param string
	 * @return
	 */
	public static final String escapeForXML(String string) {
		if (string == null)
			return null;
		int i = 0;
		int last = 0;
		char input[] = string.toCharArray();
		int len = input.length;
		StringBuffer out = new StringBuffer((int) ((double) len * 1.3D));
		for (; i < len; i++) {
			char ch = input[i];
			if (ch <= '>')
				if (ch == '<') {
					if (i > last)
						out.append(input, last, i - last);
					last = i + 1;
					out.append(LT_ENCODE);
				} else if (ch == '&') {
					if (i > last)
						out.append(input, last, i - last);
					last = i + 1;
					out.append(AMP_ENCODE);
				} else if (ch == '"') {
					if (i > last)
						out.append(input, last, i - last);
					last = i + 1;
					out.append(QUOTE_ENCODE);
				}
		}

		if (last == 0)
			return string;
		if (i > last)
			out.append(input, last, i - last);
		return out.toString();
	}

	/**
	 * <p>将&lt;还原为<</p>
	 * <p>将&&gt;还原为></p>
	 * <p>将&quot;还原为"</p>
	 * <p>将&&amp;还原为&</p>
	 *
	 * @param str
	 * @return
	 */
	public static final String unescapeFromXML(String str) {
		str = replace(str, "&lt;", "<");
		str = replace(str, "&gt;", ">");
		str = replace(str, "&quot;", "\"");
		return replace(str, "&amp;", "&");
	}

	/**
	 * <p>对字符串进行分割</p>
	 * <p/>
	 * <pre>
	 *  StringUtil.split(null, &quot;&quot;)                  = null
	 *  StringUtil.split(&quot;&quot;, &quot;&quot;)          = []
	 *  StringUtil.split(&quot;a.b.c&quot;, &quot;.&quot;)    = [&quot;a&quot;, &quot;b&quot;, &quot;c&quot;]
	 *  StringUtil.split(&quot;a..b.c&quot;, &quot;.&quot;)   = [&quot;a&quot;, &quot;b&quot;, &quot;c&quot;]
	 *  StringUtil.split(&quot;a:b:c&quot;, &quot;.&quot;)    = [&quot;a:b:c&quot;]
	 *  StringUtil.split(&quot;a b c&quot;, &quot; &quot;)    = [&quot;a&quot;, &quot;b&quot;, &quot;c&quot;]
	 * </pre>
	 *
	 * @param srcStr   要split的字符串
	 * @param delimStr 分隔符号
	 * @return
	 */
	public static String[] split(String srcStr, String delimStr) {
		if (srcStr == null || delimStr == null) {
			return null;
		}
		StringTokenizer st = new StringTokenizer(srcStr, delimStr);
		Vector vct = new Vector();
		while (st.hasMoreTokens()) {
			vct.add(st.nextToken());
		}
		Object[] tks = vct.toArray();
		String rt[] = new String[vct.size()];
		System.arraycopy(tks, 0, rt, 0, vct.size());
		return rt;
	}

	/**
	 * <p>提供基于MD5的信息摘要算法，并返回固定长度的哈希值</p>
	 *
	 * @param dataStr
	 * @return
	 */
	public static final synchronized String hash(String dataStr) {
		if (digest == null)
			try {
				digest = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException nsae) {
				System.err.println("Failed to load the MD5 MessageDigest. Jive will be unable to function normally.");
				nsae.printStackTrace();
			}
		digest.update(dataStr.getBytes());
		return encodeHex(digest.digest());
	}

	/**
	 * <p>将字符串进行Hex编码</p>
	 * <pre>
	 * 	encodeHex(&quot;a&quot;.getBytes());
	 * </pre>
	 *
	 * @param byteArr 字符串对应的byte[]
	 * @return
	 */
	public static final String encodeHex(byte byteArr[]) {
		StringBuffer buf = new StringBuffer(byteArr.length * 2);
		for (int i = 0; i < byteArr.length; i++) {
			if ((byteArr[i] & 0xff) < 16)
				buf.append("0");
			buf.append(Long.toString(byteArr[i] & 0xff, 16));
		}
		return buf.toString();
	}

	/**
	 * <p>将字符串进行Hex解码</p>
	 *
	 * @param hexStr
	 * @return
	 */
	public static final byte[] decodeHex(String hexStr) {
		char chars[] = hexStr.toCharArray();
		byte bytes[] = new byte[chars.length / 2];
		int byteCount = 0;
		for (int i = 0; i < chars.length; i += 2) {
			byte newByte = 0;
			newByte |= hexCharToByte(chars[i]);
			newByte <<= 4;
			newByte |= hexCharToByte(chars[i + 1]);
			bytes[byteCount] = newByte;
			byteCount++;
		}
		return bytes;
	}

	/**
	 * <p>将字符串进行Base64编码</p>
	 *
	 * @param dataStr
	 * @return 返回编码后的字符串
	 */
	public static String encodeBase64(String dataStr) {
		return encodeBase64(dataStr.getBytes());
	}

	/**
	 * <p>将字符串进行Base64编码 </p>
	 * <pre>
	 *  encodeBase64(&quot;a&quot;.getBytes());
	 * </pre>
	 *
	 * @param dataArr
	 * @return 以字符串形式返回编码后的结果
	 */
	public static String encodeBase64(byte dataArr[]) {
		int len = dataArr.length;
		StringBuffer ret = new StringBuffer((len / 3 + 1) * 4);
		for (int i = 0; i < len; i++) {
			int c = dataArr[i] >> 2 & 0x3f;
			ret.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(c));
			c = dataArr[i] << 4 & 0x3f;
			if (++i < len)
				c |= dataArr[i] >> 4 & 0xf;
			ret.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(c));
			if (i < len) {
				c = dataArr[i] << 2 & 0x3f;
				if (++i < len)
					c |= dataArr[i] >> 6 & 3;
				ret.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(c));
			} else {
				i++;
				ret.append('=');
			}
			if (i < len) {
				c = dataArr[i] & 0x3f;
				ret.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".charAt(c));
			} else {
				ret.append('=');
			}
		}

		return ret.toString();
	}

	/**
	 * <p>将字符串进行Base64解码</p>
	 *
	 * @param  dataStr
	 * @return 返回解码后的字符串
	 */
	public static String decodeBase64(String dataStr) {
		return decodeBase64(dataStr.getBytes());
	}

	/**
	 * <p>将字符串进行Base64解码</p>
	 * <pre>
	 *  decodeBase64(&quot;a&quot;.getBytes());
	 * </pre>
	 *
	 * @param dataArr
	 * @return 以字符串形式返回解码后的结果
	 */
	public static String decodeBase64(byte dataArr[]) {
		int len = dataArr.length;
		StringBuffer ret = new StringBuffer((len * 3) / 4);
		for (int i = 0; i < len; i++) {
			int c = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf(dataArr[i]);
			i++;
			int c1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf(dataArr[i]);
			c = c << 2 | c1 >> 4 & 3;
			ret.append((char) c);
			if (++i < len) {
				c = dataArr[i];
				if (61 == c)
					break;
				c = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf((char) c);
				c1 = c1 << 4 & 0xf0 | c >> 2 & 0xf;
				ret.append((char) c1);
			}
			if (++i >= len)
				continue;
			c1 = dataArr[i];
			if (61 == c1)
				break;
			c1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".indexOf((char) c1);
			c = c << 6 & 0xc0 | c1;
			ret.append((char) c);
		}

		return ret.toString();
	}

	/**
	 * <p>使用BreakIterator.wordInstance()进行分词转换</p>
	 * <p>
	 * 支持的分词标识：+,/,\\,#,*,),(,&
	 * </p>
	 * <pre>
	 * 	String str = &quot;a+b&quot;;
	 *  toLowerCaseWordArray(str) = &gt; [a,b]
	 *  String str = &quot;a*b&quot;;
	 *  toLowerCaseWordArray(str) = &gt; [a,b]
	 * </pre>
	 *
	 * @param text
	 * @return
	 */
	public static final String[] toLowerCaseWordArray(String text) {
		if (text == null || text.length() == 0)
			return new String[0];
		ArrayList wordList = new ArrayList();
		BreakIterator boundary = BreakIterator.getWordInstance();
		boundary.setText(text);
		int start = 0;
		for (int end = boundary.next(); end != -1; end = boundary.next()) {
			String tmp = text.substring(start, end).trim();
			tmp = replace(tmp, "+", "");
			tmp = replace(tmp, "/", "");
			tmp = replace(tmp, "\\", "");
			tmp = replace(tmp, "#", "");
			tmp = replace(tmp, "*", "");
			tmp = replace(tmp, ")", "");
			tmp = replace(tmp, "(", "");
			tmp = replace(tmp, "&", "");
			if (tmp.length() > 0)
				wordList.add(tmp);
			start = end;
		}

		return (String[]) wordList.toArray(new String[wordList.size()]);
	}

	/**
	 * <p>返回给定长度的随机字符串 </p>
	 *
	 * @param length 随机串的长度
	 * @return
	 */
	public static final String randomString(int length) {
		if (length < 1)
			return null;
		char randBuffer[] = new char[length];
		for (int i = 0; i < randBuffer.length; i++)
			randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];

		return new String(randBuffer);
	}

	/**
	 * <p>返回一定长度的字符串 </p>
	 * <pre>
	 *   StringUtil.chopAtWord(&quot;This is a nice String&quot;, 10)
	 *   返回的结果是：This is a
	 * </pre>
	 *
	 * @param string
	 * @param length
	 * @return a substring of <code>string</code> whose length is less than or equal to
	 *         <code>length</code>, and that is chopped at whitespace.
	 */
	public static final String chopAtWord(String string, int length) {
		if (string == null)
			return string;
		char charArray[] = string.toCharArray();
		int sLength = string.length();
		if (length < sLength)
			sLength = length;
		for (int i = 0; i < sLength - 1; i++) {
			if (charArray[i] == '\r' && charArray[i + 1] == '\n')
				return string.substring(0, i + 1);
			if (charArray[i] == '\n')
				return string.substring(0, i);
		}

		if (charArray[sLength - 1] == '\n')
			return string.substring(0, sLength - 1);
		if (string.length() < length)
			return string;
		for (int i = length - 1; i > 0; i--)
			if (charArray[i] == ' ')
				return string.substring(0, i).trim();

		return string.substring(0, length);
	}

	/**
	 * <p>得到指定长度的字符串，不够以0补齐</p>
	 * <pre>
	 *  StringUtil.zeroPadString(&quot;This&quot;, 10) = 000000This
	 * </pre>
	 *
	 * @param string
	 * @param length
	 * @return
	 */
	public static final String zeroPadString(String string, int length) {
		if (string == null || string.length() > length) {
			return string;
		} else {
			StringBuffer buf = new StringBuffer(length);
			buf.append(zeroArray, 0, length - string.length()).append(string);
			return buf.toString();
		}
	}

	/**
	 * <p>格式化以下划线'_'或减号'-'隔开的字符串</p>
	 * <pre>
	 *  formatJavaName("user-name") => "userName"
	 * </pre>
	 *
	 * @param name               java.lang.String
	 * @param firstCharUpperCase boolean 如果为true，那么返回的首字母大写，反之，小写
	 * @return java.lang.String
	 */
	public static final String formatJavaName(String name, boolean firstCharUpperCase) {
		if (name == null || name.length() <= 1)
			return name;
		StringTokenizer tokenizer = new StringTokenizer(name, "-_");
		StringBuffer tmp = new StringBuffer();
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			tmp.append(Character.toUpperCase(token.charAt(0))).append(token.substring(1));
		}
		if (!firstCharUpperCase) {
			String ch = String.valueOf(Character.toLowerCase(tmp.charAt(0)));
			tmp.replace(0, 1, ch);
		}
		return tmp.toString();
	}

	/**
	 * <p>将首字母转换为大写</p>
	 * <pre>
	 *  StringUtil.upperFirst(&quot;name&quot;) =&gt; Name
	 * </pre>
	 *
	 * @param name
	 * @return
	 */
	public static final String upperFirst(String name) {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append(Character.toUpperCase(name.charAt(0))).append(name.substring(1));
		return strBuf.toString();
	}

	/**
	 * <p>将首字母转换为大写,格式化以下划线'_'或减号'-'隔开的字符串 </p>
	 * <pre>
	 *  upperFirstFormatJavaName("user-name") => "UserName"
	 * </pre>
	 *
	 * @param name
	 * @return
	 */
	public static final String upperFirstFormatJavaName(String name) {
		return formatJavaName(name, true);
	}
	
	/**
	 * <p>将首字母转换为小写</p>
	 * <pre>
	 *  StringUtil.lowerFirst(&quot;Name&quot;) =&gt; name
	 * </pre>
	 *
	 * @param name
	 * @return
	 */
	public static final String lowerFirst(String name) {
		StringBuffer sb = new StringBuffer();
		sb.append(Character.toLowerCase(name.charAt(0))).append(name.substring(1));
		return sb.toString();
	}

	/**
	 * <p>将首字母转换为小写,格式化以下划线'_'或减号'-'隔开的字符串 </p>
	 * <pre>
	 * 	lowerFirstFormatJavaName("user-name") => "UserName"
	 * </pre>
	 *
	 * @param name
	 * @return
	 */
	public static final String lowerFirstFormatJavaName(String name) {
		return formatJavaName(name, true);
	}
	
	/**
	 * <p>得到标准javaBean get方法的名称</p>
	 * <pre>
	 * 	getGetMethodName(&quot;name&quot;) =&gt; getName
	 * </pre>
	 *
	 * @param name
	 * @return
	 */
	public static final String getGetMethodName(String name) {
		return "get" + formatJavaName(name, true);
	}

	/**
	 * <p>得到标准javaBean set方法的名称 </p>
	 * <pre>
	 * 	getSetMethodName(&quot;name&quot;) =&gt; setName
	 * </pre>
	 *
	 * @param name
	 * @return
	 */
	public static final String getSetMethodName(String name) {
		return "set" + formatJavaName(name, true);
	}

	/**
	 * <p>格式化以下划线'_'或减号'-'隔开的字符串，首字母小写</p>
	 * <pre>
	 * 	formatJavaName("user-name")=>"userName"
	 * </pre>
	 *
	 * @param name
	 * @return java.lang.String
	 */
	public static final String formatJavaName(String name) {
		return formatJavaName(name, false);
	}

	/**
	 * <p>将字符数组转换成字符串，以给定的符号分隔 </p>
	 * <pre>
	 *  String[] strings = {&quot;a&quot;,&quot;b&quot;,&quot;c&quot;,&quot;d&quot;};
	 *  StringUtil.join(&quot;_&quot;, strings) =&gt; a_b_c_d
	 * </pre>
	 *
	 * @param seperator 连接符
	 * @param strings
	 * @return java.lang.String
	 */
	public static String join(String seperator, String[] strings) {
		int length = strings.length;
		if (length == 0)
			return EMPTY_STRING;
		StringBuffer buf = new StringBuffer(length * strings[0].length()).append(strings[0]);
		for (int i = 1; i < length; i++) {
			buf.append(seperator).append(strings[i]);
		}
		return buf.toString();
	}

	/**
	 * <p>将Iterator转换成字符串，以给定的符号分隔</p>
	 * <pre>
	 * 	List list = new ArrayList();
	 *  list.add(&quot;a&quot;);
	 *  list.add(&quot;b&quot;);
	 *  list.add(&quot;c&quot;);
	 *  StringUtil.join(&quot;_&quot;, list.iterator()) =&gt; a_b_c
	 * </pre>
	 *
	 * @param seperator
	 * @param iter
	 * @return
	 */
	public static String join(String seperator, java.util.Iterator iter) {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append(iter.next());
		while (iter.hasNext()) {
			strBuf.append(seperator).append(iter.next());
		}
		return strBuf.toString();
	}

	/**
	 * <p>将字符数组转换成字符串，以","分隔</p>
	 * <pre>
	 * 	String[] strings = {&quot;a&quot;,&quot;b&quot;,&quot;c&quot;,&quot;d&quot;};
	 *  StringUtil.Array2String(strings);=&gt;a,b,c,d
	 * </pre>
	 *
	 * @param valueArr
	 * @return
	 */
	public static String Array2String(String[] valueArr) {
		return StringUtil.join(",", valueArr);
	}


	/**
	 * <p>把包名转化为路径</p>
	 * <pre>
	 * 	packageName(&quot;a.b.c&quot;) => a/b/c
	 * </pre>
	 * 
	 * @param packageName
	 * @return
	 */
	public static String package2Path(String packageName) {
		return packageName.replaceAll("\\.", "/");
	}

	/**
	 * <p>获得文件名称</p>
	 * <pre>
	 * 	getFileName(&quot;User.java&quot;) => User
	 * </pre>
	 * 
	 * @param fileFullName
	 * @return
	 */
	public static String getFileName(String fileFullName) {
		if (StringUtil.isBlank(fileFullName)) {
			return fileFullName;
		}
		return fileFullName.substring(0, fileFullName.indexOf("."));
	}

	/**
	 * <p>将给定的值转换为小写</p>
	 * <pre>
	 * 	toLowerCase(&quot;UserFile&quot;) => userfile
	 * </pre>
	 * 
	 * @param str
	 * @return
	 */
	public static String toLowerCase(String str) {
		if (isBlank(str))
			return str;
		return str.trim().toLowerCase();
	}

	/**
	 * <p>将给定的值转换为小写</p>
	 * <pre>
	 * 	toUpperCase(&quot;UserFile&quot;) => USERFILE
	 * </pre>
	 * 
	 * @param value
	 * @return
	 */
	public static String toUpperCase(String value) {
		if (isBlank(value))
			return value;
		return value.trim().toUpperCase();
	}
	
	/**
	 * 通过包名和目录路径生成对应的文件路径
	 * <pre>
	 * 	getDirPath(&quot;com.csi.samppleapp.base.model&quot;, &quot;d:/tmp&quot;)=>
	 *  &quot;d:/tmp/com/csi/samppleapp/base/model&quot;
	 * </pre>
	 * 
	 * @param packageName 包名
	 * @param baseDirPath 基本目录路径
	 * @return
	 */
	public static String getDirPath(String packageName, String baseDirPath) {
		String filePath = ""; 
		if (StringUtil.isNotBlank(packageName)) {
			filePath = package2Path(packageName);
		}
		if (StringUtil.isBlank(baseDirPath)) {
			return filePath; 
		} 
		return  baseDirPath + "/" + filePath; 
	}
	
	/**
	 * <p>通过文件路径（包括文件名）,获得目录和文件名数组</p>
	 * <pre>
	 *  getDirPathAndFileName("d:/tmp/ganjp.txt")=>输出：String[]{"d:/tmp","ganjp.txt"}
	 * </pre>
	 * 
	 * @param filePath
	 * @return
	 */
	public static String [] getDirPathAndFileNameArr(String filePath) {
		String tmpFilePath = filePath.replace('/',File.separatorChar);
		if (tmpFilePath.indexOf(File.separatorChar)==-1) {
			log.error("filePath不是合法的路径,请输入路径如：d:/tmp/ganjp.txt");
			throw new RuntimeException("filePath不是合法的路径,请输入路径如：d:/tmp/ganjp.txt");
		}
    	String dirPath = filePath.substring(0,tmpFilePath.lastIndexOf(File.separatorChar));
    	String fileName = filePath.substring(tmpFilePath.lastIndexOf(File.separatorChar)+1, filePath.length());
    	return new String[]{dirPath, fileName};
	}
	
	/**
	 * <p>把hexchar转成byte</p>
	 * 
	 * @param ch
	 * @return
	 */
	private static final byte hexCharToByte(char ch) {
		switch (ch) {
			case 48: // '0'
				return 0;

			case 49: // '1'
				return 1;

			case 50: // '2'
				return 2;

			case 51: // '3'
				return 3;

			case 52: // '4'
				return 4;

			case 53: // '5'
				return 5;

			case 54: // '6'
				return 6;

			case 55: // '7'
				return 7;

			case 56: // '8'
				return 8;

			case 57: // '9'
				return 9;

			case 97: // 'a'
				return 10;

			case 98: // 'b'
				return 11;

			case 99: // 'c'
				return 12;

			case 100: // 'd'
				return 13;

			case 101: // 'e'
				return 14;

			case 102: // 'f'
				return 15;

			case 58: // ':'
			case 59: // ';'
			case 60: // '<'
			case 61: // '='
			case 62: // '>'
			case 63: // '?'
			case 64: // '@'
			case 65: // 'A'
			case 66: // 'B'
			case 67: // 'C'
			case 68: // 'D'
			case 69: // 'E'
			case 70: // 'F'
			case 71: // 'G'
			case 72: // 'H'
			case 73: // 'I'
			case 74: // 'J'
			case 75: // 'K'
			case 76: // 'L'
			case 77: // 'M'
			case 78: // 'N'
			case 79: // 'O'
			case 80: // 'P'
			case 81: // 'Q'
			case 82: // 'R'
			case 83: // 'S'
			case 84: // 'T'
			case 85: // 'U'
			case 86: // 'V'
			case 87: // 'W'
			case 88: // 'X'
			case 89: // 'Y'
			case 90: // 'Z'
			case 91: // '['
			case 92: // '\\'
			case 93: // ']'
			case 94: // '^'
			case 95: // '_'
			case 96: // '`'
			default:
				return 0;
		}
	}

	/**
	 * 对字符串加上引号并对特殊字符进行处理
	 * 输入：chinasofti\n\r\"beijing\"
	 * 输出：\"chinasofti\\n\\r\\"beijing\\"\"
	 * @param str
	 * @return
	 */
	public static String quoteAndReplaceTransferChar(String str) {
		StringBuffer sb = new StringBuffer(str.length() + 2);
		sb.append('\"');
		sb.append(replaceTransferChar(str));
		sb.append('\"');
		return sb.toString();
	}

	/**
	 * 对字符串加强转义
	 * @param str
	 * @return
	 */
	public static String replaceTransferChar(Object obj) {
		if (obj == null){
			return null;
		}
		String str = obj.toString();
		if (isBlank(str)) {
			return "";
		}
		StringBuffer sb = new StringBuffer(str.length() + 20);
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			switch (c) {
			case '\"':
				sb.append("\\\"");
				break;
			case '\\':
				sb.append("\\\\");
				break;
			case '/':
				sb.append("\\/");
				break;
			case '\b':
				sb.append("\\b");
				break;
			case '\f':
				sb.append("\\f");
				break;
			case '\n':
				sb.append("\\n");
				break;
			case '\r':
				sb.append("\\r");
				break;
			case '\t':
				sb.append("\\t");
				break;
			default:
				sb.append(c);
			}
		}
		return sb.toString();
	}
	
	/**
	 * 该方法拷贝自net.sf.json.util.JSONUtils 
	 * @param string
	 * @return
	 */
	public static String quoteJsonLib(String string) {  
        char b;  
        char c = 0;  
        int i;  
        int len = string.length();  
        StringBuffer sb = new StringBuffer(len * 2);  
        String t;  
        char[] chars = string.toCharArray();  
        char[] buffer = new char[1030];  
        int bufferIndex = 0;  
        sb.append('"');  
        for (i = 0; i < len; i += 1) {  
            if (bufferIndex > 1024) {  
                sb.append(buffer, 0, bufferIndex);  
                bufferIndex = 0;  
            }  
            b = c;  
            c = chars[i];  
            switch (c) {  
            case '\\':  
            case '"':  
                buffer[bufferIndex++] = '\\';  
                buffer[bufferIndex++] = c;  
                break;  
            case '/':  
                if (b == '<') {  
                    buffer[bufferIndex++] = '\\';  
                }  
                buffer[bufferIndex++] = c;  
                break;  
            default:  
                if (c < ' ') {  
                    switch (c) {  
                    case '\b':  
                        buffer[bufferIndex++] = '\\';  
                        buffer[bufferIndex++] = 'b';  
                        break;  
                    case '\t':  
                        buffer[bufferIndex++] = '\\';  
                        buffer[bufferIndex++] = 't';  
                        break;  
                    case '\n':  
                        buffer[bufferIndex++] = '\\';  
                        buffer[bufferIndex++] = 'n';  
                        break;  
                    case '\f':  
                        buffer[bufferIndex++] = '\\';  
                        buffer[bufferIndex++] = 'f';  
                        break;  
                    case '\r':  
                        buffer[bufferIndex++] = '\\';  
                        buffer[bufferIndex++] = 'r';  
                        break;  
                    default:  
                        t = "000" + Integer.toHexString(c);  
                        int tLength = t.length();  
                        buffer[bufferIndex++] = '\\';  
                        buffer[bufferIndex++] = 'u';  
                        buffer[bufferIndex++] = t.charAt(tLength - 4);  
                        buffer[bufferIndex++] = t.charAt(tLength - 3);  
                        buffer[bufferIndex++] = t.charAt(tLength - 2);  
                        buffer[bufferIndex++] = t.charAt(tLength - 1);  
                    }  
                } else {  
                    buffer[bufferIndex++] = c;  
                }  
            }  
        }  
        sb.append(buffer, 0, bufferIndex);  
        sb.append('"');  
        return sb.toString();  
    }
	
	/**
	 * 通过冒号前面的名称获取冒号后面的值
	 * 输入：fullKeyValue="name:ganjp,id:12", key="id"
	 * 输出：12
	 * @param fullKeyValue
	 * @return
	 */
	public static String getAfterColonValue(String fullKeyValue, String key) {
		int index = fullKeyValue.indexOf(key);
		if (index!=-1) {
			int nextIndex = index + key.length();
			if (nextIndex!=fullKeyValue.length() && fullKeyValue.charAt(nextIndex)==':') {
				if (fullKeyValue.indexOf(",")==-1 || fullKeyValue.lastIndexOf(",") < nextIndex) {
					return fullKeyValue.substring(nextIndex+1, fullKeyValue.length());
				} else {
					return fullKeyValue.substring(nextIndex+1, fullKeyValue.indexOf(",", nextIndex));
				}
			}
		}
		return null;
	}
	
	/**
	 * Check that the given CharSequence is neither <code>null</code> nor of length 0.
	 * Note: Will return <code>true</code> for a CharSequence that purely consists of whitespace.
	 * <p><pre>
	 * StringUtils.hasLength(null) = false
	 * StringUtils.hasLength("") = false
	 * StringUtils.hasLength(" ") = true
	 * StringUtils.hasLength("Hello") = true
	 * </pre>
	 * @param str the CharSequence to check (may be <code>null</code>)
	 * @return <code>true</code> if the CharSequence is not null and has length
	 * @see #hasText(String)
	 */
	public static boolean hasLength(CharSequence str) {
		return (str != null && str.length() > 0);
	}
	
	/**
	 * Check that the given String is neither <code>null</code> nor of length 0.
	 * Note: Will return <code>true</code> for a String that purely consists of whitespace.
	 * @param str the String to check (may be <code>null</code>)
	 * @return <code>true</code> if the String is not null and has length
	 * @see #hasLength(CharSequence)
	 */
	public static boolean hasLength(String str) {
		return hasLength((CharSequence) str);
	}
	
	/**
	 * Check whether the given CharSequence has actual text.
	 * More specifically, returns <code>true</code> if the string not <code>null</code>,
	 * its length is greater than 0, and it contains at least one non-whitespace character.
	 * <p><pre>
	 * StringUtils.hasText(null) = false
	 * StringUtils.hasText("") = false
	 * StringUtils.hasText(" ") = false
	 * StringUtils.hasText("12345") = true
	 * StringUtils.hasText(" 12345 ") = true
	 * </pre>
	 * @param str the CharSequence to check (may be <code>null</code>)
	 * @return <code>true</code> if the CharSequence is not <code>null</code>,
	 * its length is greater than 0, and it does not contain whitespace only
	 * @see java.lang.Character#isWhitespace
	 */
	public static boolean hasText(CharSequence str) {
		if (!hasLength(str)) {
			return false;
		}
		int strLen = str.length();
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Check whether the given String has actual text.
	 * More specifically, returns <code>true</code> if the string not <code>null</code>,
	 * its length is greater than 0, and it contains at least one non-whitespace character.
	 * @param str the String to check (may be <code>null</code>)
	 * @return <code>true</code> if the String is not <code>null</code>, its length is
	 * greater than 0, and it does not contain whitespace only
	 * @see #hasText(CharSequence)
	 */
	public static boolean hasText(String str) {
		return hasText((CharSequence) str);
	}
	
	/**
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean containsWithComma(String str1, String str2) {
		if (!hasText(str1) && !hasText(str2)) {
			if (str2.indexOf(",")!=-1) {
				return str1.indexOf(str2) != -1;
			} else {
				String[] str2arr = str2.split(",");
				for (int i = 0; i < str2arr.length; i++) {
					String subStr2 = str2arr[i];
					if (str1.indexOf(subStr2.trim())!=-1) return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Convenience method to return a String array as a delimited (e.g. CSV)
	 * String. E.g. useful for <code>toString()</code> implementations.
	 * @param arr the array to display
	 * @param delim the delimiter to use (probably a ",")
	 * @return the delimited String
	 */
	public static String arrayToDelimitedString(Object[] arr, String delim) {
		if (ObjectUtil.isEmpty(arr)) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			if (i > 0) {
				sb.append(delim);
			}
			sb.append(arr[i]);
		}
		return sb.toString();
	}
	
	/**
	 * Convenience method to return a String array as a CSV String.
	 * E.g. useful for <code>toString()</code> implementations.
	 * @param arr the array to display
	 * @return the delimited String
	 */
	public static String arrayToCommaDelimitedString(Object[] arr) {
		return arrayToDelimitedString(arr, ",");
	}
	
	/**
	 * Normalize the path by suppressing sequences like "path/.." and
	 * inner simple dots.
	 * <p>The result is convenient for path comparison. For other uses,
	 * notice that Windows separators ("\") are replaced by simple slashes.
	 * @param path the original path
	 * @return the normalized path
	 */
	public static String cleanPath(String path) {
		String pathToUse = replace(path, WINDOWS_FOLDER_SEPARATOR, FOLDER_SEPARATOR);

		// Strip prefix from path to analyze, to not treat it as part of the
		// first path element. This is necessary to correctly parse paths like
		// "file:core/../core/io/Resource.class", where the ".." should just
		// strip the first "core" directory while keeping the "file:" prefix.
		int prefixIndex = pathToUse.indexOf(":");
		String prefix = "";
		if (prefixIndex != -1) {
			prefix = pathToUse.substring(0, prefixIndex + 1);
			pathToUse = pathToUse.substring(prefixIndex + 1);
		}

		String[] pathArray = delimitedListToStringArray(pathToUse, FOLDER_SEPARATOR);
		List pathElements = new LinkedList();
		int tops = 0;

		for (int i = pathArray.length - 1; i >= 0; i--) {
			if (CURRENT_PATH.equals(pathArray[i])) {
				// Points to current directory - drop it.
			}
			else if (TOP_PATH.equals(pathArray[i])) {
				// Registering top path found.
				tops++;
			}
			else {
				if (tops > 0) {
					// Merging path element with corresponding to top path.
					tops--;
				}
				else {
					// Normal path element found.
					pathElements.add(0, pathArray[i]);
				}
			}
		}

		// Remaining top paths need to be retained.
		for (int i = 0; i < tops; i++) {
			pathElements.add(0, TOP_PATH);
		}

		return prefix + collectionToDelimitedString(pathElements, FOLDER_SEPARATOR);
	}
	
	/**
	 * Take a String which is a delimited list and convert it to a String array.
	 * <p>A single delimiter can consists of more than one character: It will still
	 * be considered as single delimiter string, rather than as bunch of potential
	 * delimiter characters - in contrast to <code>tokenizeToStringArray</code>.
	 * @param str the input String
	 * @param delimiter the delimiter between elements (this is a single delimiter,
	 * rather than a bunch individual delimiter characters)
	 * @return an array of the tokens in the list
	 * @see #tokenizeToStringArray
	 */
	public static String[] delimitedListToStringArray(String str, String delimiter) {
		return delimitedListToStringArray(str, delimiter, null);
	}

	/**
	 * Take a String which is a delimited list and convert it to a String array.
	 * <p>A single delimiter can consists of more than one character: It will still
	 * be considered as single delimiter string, rather than as bunch of potential
	 * delimiter characters - in contrast to <code>tokenizeToStringArray</code>.
	 * @param str the input String
	 * @param delimiter the delimiter between elements (this is a single delimiter,
	 * rather than a bunch individual delimiter characters)
	 * @param charsToDelete a set of characters to delete. Useful for deleting unwanted
	 * line breaks: e.g. "\r\n\f" will delete all new lines and line feeds in a String.
	 * @return an array of the tokens in the list
	 * @see #tokenizeToStringArray
	 */
	public static String[] delimitedListToStringArray(String str, String delimiter, String charsToDelete) {
		if (str == null) {
			return new String[0];
		}
		if (delimiter == null) {
			return new String[] {str};
		}
		List result = new ArrayList();
		if ("".equals(delimiter)) {
			for (int i = 0; i < str.length(); i++) {
				result.add(deleteAny(str.substring(i, i + 1), charsToDelete));
			}
		}
		else {
			int pos = 0;
			int delPos = 0;
			while ((delPos = str.indexOf(delimiter, pos)) != -1) {
				result.add(deleteAny(str.substring(pos, delPos), charsToDelete));
				pos = delPos + delimiter.length();
			}
			if (str.length() > 0 && pos <= str.length()) {
				// Add rest of String, but not in case of empty input.
				result.add(deleteAny(str.substring(pos), charsToDelete));
			}
		}
		return toStringArray(result);
	}
	
	/**
	 * Copy the given Collection into a String array.
	 * The Collection must contain String elements only.
	 * @param collection the Collection to copy
	 * @return the String array (<code>null</code> if the passed-in
	 * Collection was <code>null</code>)
	 */
	public static String[] toStringArray(Collection collection) {
		if (collection == null) {
			return null;
		}
		return (String[]) collection.toArray(new String[collection.size()]);
	}
	
	/**
	 * Delete any character in a given String.
	 * @param inString the original String
	 * @param charsToDelete a set of characters to delete.
	 * E.g. "az\n" will delete 'a's, 'z's and new lines.
	 * @return the resulting String
	 */
	public static String deleteAny(String inString, String charsToDelete) {
		if (!hasLength(inString) || !hasLength(charsToDelete)) {
			return inString;
		}
		StringBuffer out = new StringBuffer();
		for (int i = 0; i < inString.length(); i++) {
			char c = inString.charAt(i);
			if (charsToDelete.indexOf(c) == -1) {
				out.append(c);
			}
		}
		return out.toString();
	}

	/**
	 * Convenience method to return a Collection as a delimited (e.g. CSV)
	 * String. E.g. useful for <code>toString()</code> implementations.
	 * @param coll the Collection to display
	 * @param delim the delimiter to use (probably a ",")
	 * @param prefix the String to start each element with
	 * @param suffix the String to end each element with
	 * @return the delimited String
	 */
	public static String collectionToDelimitedString(Collection coll, String delim, String prefix, String suffix) {
		if (CollectionUtil.isEmpty(coll)) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		Iterator it = coll.iterator();
		while (it.hasNext()) {
			sb.append(prefix).append(it.next()).append(suffix);
			if (it.hasNext()) {
				sb.append(delim);
			}
		}
		return sb.toString();
	}
	
	/**
	 * Convenience method to return a Collection as a delimited (e.g. CSV)
	 * String. E.g. useful for <code>toString()</code> implementations.
	 * @param coll the Collection to display
	 * @param delim the delimiter to use (probably a ",")
	 * @return the delimited String
	 */
	public static String collectionToDelimitedString(Collection coll, String delim) {
		return collectionToDelimitedString(coll, delim, "", "");
	}
	
	/**
	 * Apply the given relative path to the given path,
	 * assuming standard Java folder separation (i.e. "/" separators);
	 * @param path the path to start from (usually a full file path)
	 * @param relativePath the relative path to apply
	 * (relative to the full file path above)
	 * @return the full file path that results from applying the relative path
	 */
	public static String applyRelativePath(String path, String relativePath) {
		int separatorIndex = path.lastIndexOf(FOLDER_SEPARATOR);
		if (separatorIndex != -1) {
			String newPath = path.substring(0, separatorIndex);
			if (!relativePath.startsWith(FOLDER_SEPARATOR)) {
				newPath += FOLDER_SEPARATOR;
			}
			return newPath + relativePath;
		}
		else {
			return relativePath;
		}
	}
	
	/**
	 * Extract the filename from the given path,
	 * e.g. "mypath/myfile.txt" -> "myfile.txt".
	 * @param path the file path (may be <code>null</code>)
	 * @return the extracted filename, or <code>null</code> if none
	 */
	public static String getFilename(String path) {
		if (path == null) {
			return null;
		}
		int separatorIndex = path.lastIndexOf(FOLDER_SEPARATOR);
		return (separatorIndex != -1 ? path.substring(separatorIndex + 1) : path);
	}
	
	/**
	 * Tokenize the given String into a String array via a StringTokenizer.
	 * Trims tokens and omits empty tokens.
	 * <p>The given delimiters string is supposed to consist of any number of
	 * delimiter characters. Each of those characters can be used to separate
	 * tokens. A delimiter is always a single character; for multi-character
	 * delimiters, consider using <code>delimitedListToStringArray</code>
	 * @param str the String to tokenize
	 * @param delimiters the delimiter characters, assembled as String
	 * (each of those characters is individually considered as delimiter).
	 * @return an array of the tokens
	 * @see java.util.StringTokenizer
	 * @see java.lang.String#trim()
	 * @see #delimitedListToStringArray
	 */
	public static String[] tokenizeToStringArray(String str, String delimiters) {
		return tokenizeToStringArray(str, delimiters, true, true);
	}

	/**
	 * Tokenize the given String into a String array via a StringTokenizer.
	 * <p>The given delimiters string is supposed to consist of any number of
	 * delimiter characters. Each of those characters can be used to separate
	 * tokens. A delimiter is always a single character; for multi-character
	 * delimiters, consider using <code>delimitedListToStringArray</code>
	 * @param str the String to tokenize
	 * @param delimiters the delimiter characters, assembled as String
	 * (each of those characters is individually considered as delimiter)
	 * @param trimTokens trim the tokens via String's <code>trim</code>
	 * @param ignoreEmptyTokens omit empty tokens from the result array
	 * (only applies to tokens that are empty after trimming; StringTokenizer
	 * will not consider subsequent delimiters as token in the first place).
	 * @return an array of the tokens (<code>null</code> if the input String
	 * was <code>null</code>)
	 * @see java.util.StringTokenizer
	 * @see java.lang.String#trim()
	 * @see #delimitedListToStringArray
	 */
	public static String[] tokenizeToStringArray(
			String str, String delimiters, boolean trimTokens, boolean ignoreEmptyTokens) {

		if (str == null) {
			return null;
		}
		StringTokenizer st = new StringTokenizer(str, delimiters);
		List tokens = new ArrayList();
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			if (trimTokens) {
				token = token.trim();
			}
			if (!ignoreEmptyTokens || token.length() > 0) {
				tokens.add(token);
			}
		}
		return toStringArray(tokens);
	}
	
	/**
	 * Resolve ${...} placeholders in the given text,
	 * replacing them with corresponding system property values.
	 * @param text the String to resolve
	 * @return the resolved String
	 * @see #PLACEHOLDER_PREFIX
	 * @see #PLACEHOLDER_SUFFIX
	 */
	public static String resolvePlaceholders(String text, Object[] argArr) {
		StringBuffer buf = new StringBuffer(text);
		int startIndex = buf.indexOf(PLACEHOLDER_PREFIX);
		int i = 0;
		while (startIndex != -1) {
			int endIndex = buf.indexOf(PLACEHOLDER_SUFFIX, startIndex + PLACEHOLDER_PREFIX.length());
			if (endIndex != -1) {
				String placeholder = buf.substring(startIndex + PLACEHOLDER_PREFIX.length(), endIndex);
				int nextIndex = endIndex + PLACEHOLDER_SUFFIX.length();
				try {
					String value = argArr[i++].toString();
					buf.replace(startIndex, endIndex + PLACEHOLDER_SUFFIX.length(), value);
					nextIndex = startIndex + value.length();
				}
				catch (Throwable ex) {
					System.err.println("Could not resolve placeholder '" + placeholder + "' in [" + text +
							"] as system property: " + ex);
				}
				startIndex = buf.indexOf(PLACEHOLDER_PREFIX, nextIndex);
			}
			else {
				startIndex = -1;
			}
		}
		log.debug("replace " + text + "value " + argArr.toString());
		return buf.toString();
	}
	
    /**
     * <p>Gets the substring before the first occurrence of a separator.
     * The separator is not returned.</p>
     *
     * <p>A <code>null</code> string input will return <code>null</code>.
     * An empty ("") string input will return the empty string.
     * A <code>null</code> separator will return the input string.</p>
     *
     * <pre>
     * StringUtils.substringBefore(null, *)      = null
     * StringUtils.substringBefore("", *)        = ""
     * StringUtils.substringBefore("abc", "a")   = ""
     * StringUtils.substringBefore("abcba", "b") = "a"
     * StringUtils.substringBefore("abc", "c")   = "ab"
     * StringUtils.substringBefore("abc", "d")   = "abc"
     * StringUtils.substringBefore("abc", "")    = ""
     * StringUtils.substringBefore("abc", null)  = "abc"
     * </pre>
     *
     * @param str  the String to get a substring from, may be null
     * @param separator  the String to search for, may be null
     * @return the substring before the first occurrence of the separator,
     *  <code>null</code> if null String input
     * @since 2.0
     */
    public static String substringBefore(String str, String separator) {
        if (isEmpty(str) || separator == null) {
            return str;
        }
        if (separator.length() == 0) {
            return EMPTY;
        }
        int pos = str.indexOf(separator);
        if (pos == -1) {
            return str;
        }
        return str.substring(0, pos);
    }
    
    /**
     * <p>Gets a substring from the specified String avoiding exceptions.</p>
     *
     * <p>A negative start position can be used to start <code>n</code>
     * characters from the end of the String.</p>
     *
     * <p>A <code>null</code> String will return <code>null</code>.
     * An empty ("") String will return "".</p>
     *
     * <pre>
     * StringUtils.substring(null, *)   = null
     * StringUtils.substring("", *)     = ""
     * StringUtils.substring("abc", 0)  = "abc"
     * StringUtils.substring("abc", 2)  = "c"
     * StringUtils.substring("abc", 4)  = ""
     * StringUtils.substring("abc", -2) = "bc"
     * StringUtils.substring("abc", -4) = "abc"
     * </pre>
     *
     * @param str  the String to get the substring from, may be null
     * @param start  the position to start from, negative means
     *  count back from the end of the String by this many characters
     * @return substring from start position, <code>null</code> if null String input
     */
    public static String substring(String str, int start) {
        if (str == null) {
            return null;
        }

        // handle negatives, which means last n characters
        if (start < 0) {
            start = str.length() + start; // remember start is negative
        }

        if (start < 0) {
            start = 0;
        }
        if (start > str.length()) {
            return EMPTY;
        }

        return str.substring(start);
    }
    
    /**
     * <p>Gets a substring from the specified String avoiding exceptions.</p>
     *
     * <p>A negative start position can be used to start/end <code>n</code>
     * characters from the end of the String.</p>
     *
     * <p>The returned substring starts with the character in the <code>start</code>
     * position and ends before the <code>end</code> position. All position counting is
     * zero-based -- i.e., to start at the beginning of the string use
     * <code>start = 0</code>. Negative start and end positions can be used to
     * specify offsets relative to the end of the String.</p>
     *
     * <p>If <code>start</code> is not strictly to the left of <code>end</code>, ""
     * is returned.</p>
     *
     * <pre>
     * StringUtils.substring(null, *, *)    = null
     * StringUtils.substring("", * ,  *)    = "";
     * StringUtils.substring("abc", 0, 2)   = "ab"
     * StringUtils.substring("abc", 2, 0)   = ""
     * StringUtils.substring("abc", 2, 4)   = "c"
     * StringUtils.substring("abc", 4, 6)   = ""
     * StringUtils.substring("abc", 2, 2)   = ""
     * StringUtils.substring("abc", -2, -1) = "b"
     * StringUtils.substring("abc", -4, 2)  = "ab"
     * </pre>
     *
     * @param str  the String to get the substring from, may be null
     * @param start  the position to start from, negative means
     *  count back from the end of the String by this many characters
     * @param end  the position to end at (exclusive), negative means
     *  count back from the end of the String by this many characters
     * @return substring from start position to end positon,
     *  <code>null</code> if null String input
     */
    public static String substring(String str, int start, int end) {
        if (str == null) {
            return null;
        }

        // handle negatives
        if (end < 0) {
            end = str.length() + end; // remember end is negative
        }
        if (start < 0) {
            start = str.length() + start; // remember start is negative
        }

        // check length next
        if (end > str.length()) {
            end = str.length();
        }

        // if start is greater than end, return ""
        if (start > end) {
            return EMPTY;
        }

        if (start < 0) {
            start = 0;
        }
        if (end < 0) {
            end = 0;
        }

        return str.substring(start, end);
    }

    /**
     * <p>Gets the substring after the first occurrence of a separator.
     * The separator is not returned.</p>
     *
     * <p>A <code>null</code> string input will return <code>null</code>.
     * An empty ("") string input will return the empty string.
     * A <code>null</code> separator will return the empty string if the
     * input string is not <code>null</code>.</p>
     *
     * <pre>
     * StringUtils.substringAfter(null, *)      = null
     * StringUtils.substringAfter("", *)        = ""
     * StringUtils.substringAfter(*, null)      = ""
     * StringUtils.substringAfter("abc", "a")   = "bc"
     * StringUtils.substringAfter("abcba", "b") = "cba"
     * StringUtils.substringAfter("abc", "c")   = ""
     * StringUtils.substringAfter("abc", "d")   = ""
     * StringUtils.substringAfter("abc", "")    = "abc"
     * </pre>
     *
     * @param str  the String to get a substring from, may be null
     * @param separator  the String to search for, may be null
     * @return the substring after the first occurrence of the separator,
     *  <code>null</code> if null String input
     * @since 2.0
     */
    public static String substringAfter(String str, String separator) {
        if (isEmpty(str)) {
            return str;
        }
        if (separator == null) {
            return EMPTY;
        }
        int pos = str.indexOf(separator);
        if (pos == -1) {
            return EMPTY;
        }
        return str.substring(pos + separator.length());
    }

	
	private static final char QUOTE_ENCODE[] = "&quot;".toCharArray();
	private static final char AMP_ENCODE[] = "&amp;".toCharArray();
	private static final char LT_ENCODE[] = "&lt;".toCharArray();
	private static final char GT_ENCODE[] = "&gt;".toCharArray();
	private static MessageDigest digest = null;
	private static final int fillchar = 61;
	private static final String cvt = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
	private static Random randGen = new Random();
	private static char numbersAndLetters[] = "0123456789abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	private static final char zeroArray[] = "0000000000000000".toCharArray();
	private static final String FOLDER_SEPARATOR = "/";
	private static final String WINDOWS_FOLDER_SEPARATOR = "\\";
	private static final String TOP_PATH = "..";
	private static final String CURRENT_PATH = ".";
	private static final char EXTENSION_SEPARATOR = '.';
	public static final String PLACEHOLDER_PREFIX = "{";
	public static final String PLACEHOLDER_SUFFIX = "}";
	
	public static void main(String[] args) throws Exception {
		System.out.println(getAfterColonValue("name:ganjp,id:12","name"));
	}
}
