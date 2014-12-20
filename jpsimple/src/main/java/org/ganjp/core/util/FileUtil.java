/*
 * $Id: FileUtil.java,v 1.1 2011/05/12 09:58:19 ganjp Exp $
 * 
 * Copyright 2011 the original author or authors.
 * This software is core of developing project and written by ganjianping. 
 */
package org.ganjp.core.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Properties;

import org.ganjp.core.exception.JpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>文件操作工具类</p>
 *
 * @author ganjp
 * @since 1.0
 */
public class FileUtil {
	private static Logger log = LoggerFactory.getLogger(FileUtil.class);
	
	public static String fileSeparator =  "/";
	
	/**
	 * <p>创建文件,不能创建目录</p>
	 * 
	 * @param fileFullPath
	 * @throws RuntimeException
	 */
	public static File createFile(String fileFullPath) {
        try {
            File file = new File(fileFullPath);
            if (!file.exists()) {
            	String tmpFile = fileFullPath.replace('/',File.separatorChar);
            	String dir = fileFullPath.substring(0,tmpFile.lastIndexOf(File.separatorChar));
            	File dirFile = new File(dir);
            	if(!dirFile.exists()){
            		dirFile.mkdirs();
            	}
            	file.createNewFile();
            }
            return file;
        } catch (Exception e) {
        	log.error("创建文件失败：" + e.getMessage());
            throw new RuntimeException(e.toString());
        }    
    }

	/**
	 * <p>创建目录。 如果目录已经存在，该目录不创建;如果目录是个文件或目录创建出错，那么就抛出一个异常。</p>
	 * 
	 * <pre>
	 * Example:
	 * File file = new File(&quot;D:/Workspace&quot;);
	 * FileUtil.forceMkdir(file);
	 * </pre>
	 * 
	 * @param directory 要创建的目录
	 * @throws IOException 如果不能创建，抛出的异常
	 */
	public static void createDirectory(File directory) throws IOException {
		if (directory.exists()) {
			if (directory.isFile()) {
				String message = "File " + directory + " exists and is "
						+ "not a directory. Unable to create directory.";
				throw new IOException(message);
			}
		} else {
			if (false == directory.mkdirs()) {
				String message = "Unable to create directory " + directory;
				throw new IOException(message);
			}
		}
	}
	
	/**
	 * <p>删除指定路径的内容，路径可以是文件或者目录</p>
	 * <pre>
	 *  Example:删除D:/tmp 文件夹以及下面的所有内容：
	 *  File dirFile = new File(&quot;D:/tmp&quot;);
	 *  FileUtil.delete(dirFile);s
	 * </pre>
	 * 
	 * @param file 路径
	 */
	public static void delete(File file) {
		if (file==null) {
			return;
		}
		try {
			if (file.isFile()) {
				file.delete();
				return;
			}
			File[] subs = file.listFiles();
			if (subs.length == 0) {
				file.delete();
			} else {
				for (int i = 0; i < subs.length; i++) {
					delete(subs[i]);
				}
				file.delete();
			}
		} catch (Exception ex) {
			log.error("删除目录或文件：" + file.getAbsolutePath() + "失败：" + ex.getMessage());
		}
	}

	/**
	 * <p>清空指定的目录，不会删除目录，只会清空 </p>
	 * 
	 * @param dirFile directory to clean
	 * @throws IOException in case cleaning is unsuccessful
	 */
	public static void cleanDirectory(File dirFile) {
		if (!dirFile.exists()) {
			String message = dirFile + " does not exist";
			throw new IllegalArgumentException(message);
		}
		if (!dirFile.isDirectory()) {
			String message = dirFile + " is not a directory";
			throw new IllegalArgumentException(message);
		}
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			delete(file);
		}
	}

	/**
	 * <p>复制目录（文件夹）下的所有内容到指定的目录。</p>
	 * <p>1、如果目标目录不存在，会按照源目录创建。 </p>
	 * <p>2、如果目标目录已经存在，会覆盖目标目录下的同名文件。 </p>
	 * <pre>
	 *  Example:复制D:/ganjp文件夹的内容到D:/tmp
	 *  File srcDirFile = new File(&quot;D:/ganjp&quot;);
	 *  File tgtDirFile = new File(&quot;D:/tmp&quot;);
	 *  FileUtil.rmdirs(srcDirFile, tgtDirFile);
	 * </pre>
	 * 
	 * @param srcDirFile 源目录
	 * @param tgtDirFile 目标目录
	 */
	public static void xcopy(File srcDirFile, File tgtDirFile) {
		if (srcDirFile==null) {
			return;
		}
		try {
			if (!srcDirFile.exists() || srcDirFile.getCanonicalPath().equals(tgtDirFile.getCanonicalPath()))
				return;
		} catch (IOException ex) {
			log.error("判断源目录文件时出错：" + ex.getMessage());
		}
		File[] chs = srcDirFile.listFiles();
		for (int i = 0; i < chs.length; i++) {
			if (chs[i].isFile()) {
				File tgtFile = new File(tgtDirFile, chs[i].getName());
				copy(chs[i], tgtFile);
			} else {
				File subTgtDirFile = new File(tgtDirFile, chs[i].getName());
				subTgtDirFile.mkdirs();
				xcopy(chs[i], subTgtDirFile);
			}
		}
	}

	/**
	 * <p>移动目录,将源目录移动到指定的目录下，移动完毕后，将原目录删除。 <p>
	 * 
	 * @param srcDirFile 要移动的目录
	 * @param tgtDirFile 目标目录
	 */
	public static void move(File srcDirFile, File tgtDirFile) {
		if (srcDirFile==null) {
			return;
		}
		try {
			if (!srcDirFile.exists() || srcDirFile.getCanonicalPath().equals(tgtDirFile.getCanonicalPath()))
				return;
		} catch (IOException ex) {
			log.error("判断源目录文件时出错：" + ex.getMessage());
		}
		copy(srcDirFile, tgtDirFile);
		srcDirFile.delete();
	}

	/**
	 * <p>复制文件</p>
	 * 
	 * @param srcFile
	 * @param tgtFile
	 */
	public static void copy(File srcFile, File tgtFile) {
		try {
			if (!srcFile.exists() || srcFile.getCanonicalPath().equals(tgtFile.getCanonicalPath()))
				return;
			FileInputStream fins = new FileInputStream(srcFile);
			copy(fins, tgtFile);
			tgtFile.setLastModified(srcFile.lastModified());
		} catch (Exception e) {
		}
	}

	/**
	 * <p>复制文件</p>
	 * 
	 * @param srcInputStream
	 * @param tgtInputStream
	 */
	public static void copy(InputStream srcInputStream, File tgtInputStream) {
		try {
			if (srcInputStream == null)
				return;
			tgtInputStream.getParentFile().mkdirs();
			FileOutputStream fos = new FileOutputStream(tgtInputStream);
			byte[] buf = new byte[1024];
			int readLen;
			while ((readLen = srcInputStream.read(buf, 0, buf.length)) > 0) {
				fos.write(buf, 0, readLen);
			}
			fos.flush();
			fos.close();
			srcInputStream.close();
		} catch (Exception ex) {
			log.error("拷贝文件流时出错：" + ex.getMessage());
		}
	}

	/**
	 * <p>返回指定目录的大小</p>
	 * <p>
	 * Recursively count size of a directory (sum of the length of all files).
	 * <p>
	 * 
	 * @param dirFile directory to inspect
	 * @return size of directory in bytes.
	 */
	public static long sizeOfDirectory(File dirFile) {
		if (dirFile==null || !dirFile.exists()) {
			throw new IllegalArgumentException(dirFile + " does not exist");
		}
		if (!dirFile.isDirectory()) {
			String message = dirFile + " is not a directory";
			throw new IllegalArgumentException(message);
		}
		long size = 0;
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			if (file.isDirectory()) {
				size += sizeOfDirectory(file);
			} else {
				size += file.length();
			}
		}
		return size;
	}

	/**
	 * <p>对比两个文件的创建时间，如果file是新创建的返回true</p>
	 * 	 
	 * @param file
	 * @param refFile 
	 * @return true if the <code>File</code> exists and has been modified more recently
	 *         than the reference <code>File</code>.
	 */
	public static boolean isFileNewer(File file, File refFile) {
		if (refFile == null) {
			throw new IllegalArgumentException("No specified reference file");
		}
		if (!refFile.exists()) {
			throw new IllegalArgumentException("The reference file '" + file + "' doesn't exist");
		}

		return isFileNewer(file, refFile.lastModified());
	}

	/**
	 * <p>与给定的时间（<code>Date</code>）相比，文件是否是新创建的。如果是返回true。</p>
	 * 
	 * @param file the <code>File</code> of which the modification date must be compared
	 * @param date the date reference
	 * @return true if the <code>File</code> exists and has been modified after the
	 *         given <code>Date</code>.
	 */
	public static boolean isFileNewer(File file, Date date) {
		if (date == null) {
			throw new IllegalArgumentException("No specified date");
		}
		return isFileNewer(file, date.getTime());
	}

	/**
	 * <p>与给定的时间（毫秒数）相比，文件是否是新创建的。如果是返回true。</p>
	 * 
	 * @param file the <code>File</code> of which the modification date must be
	 *            compared.
	 * @param timeMillis the time reference measured in milliseconds since the epoch
	 *            (00:00:00 GMT, January 1, 1970)
	 * @return true if the <code>File</code> exists and has been modified after the
	 *         given time reference.
	 */
	public static boolean isFileNewer(File file, long timeMillis) {
		if (file == null) {
			throw new IllegalArgumentException("No specified file");
		}
		if (!file.exists()) {
			return false;
		}

		return file.lastModified() > timeMillis;
	}

	/**
	 * <p>将<code>URL</code>转换为<code>File</code></p>
	 * 
	 * @param fileUrl File URL.
	 * @return The equivalent <code>File</code> object, or <code>null</code> if the
	 *         URL's protocol is not <code>file</code>
	 */
	public static File toFile(URL fileUrl) {
		if (fileUrl.getProtocol().equals("file") == false) {
			return null;
		} else {
			String filename = fileUrl.getFile().replace('/', File.separatorChar);
			return new File(filename);
		}
	}

	/**
	 * <p>将<code>File</code>数组转换为<code>URL</code>数组</p>
	 * 
	 * @param fileArr the array of files
	 * @return the array of URLs
	 * @throws IOException if an error occurs
	 */
	public static URL[] toURLs(File[] fileArr) throws IOException {
		URL[] urlArr = new URL[fileArr.length];
		for (int i = 0; i < urlArr.length; i++) {
			urlArr[i] = fileArr[i].toURL();
		}
		return urlArr;
	}

	/**
	 * <p>把文本text用utf-8编码写到filePath文件里</p>
	 * 
	 * @param filePath
	 * @param text
	 * @throws RuntimeException
	 */
	public static void toFile(String filePath, String text) throws RuntimeException {
		toFile(filePath,text,null);
	}
	
	/**
	 * <p>把文本text用encode编码写到fileName文件里，并创建filePath文件</p>
	 * 
	 * @param filePath
	 * @param text
	 * @param encode
	 * @throws RuntimeException
	 */
	public static void toFile(String filePath, String text, String encode) throws RuntimeException {
	    OutputStreamWriter filewriter = null;
        try {
        	createFile(filePath);
            if (StringUtil.isNotBlank(encode)) {
            	filewriter = new OutputStreamWriter(new FileOutputStream(filePath), encode);
            } else {
            	filewriter = new OutputStreamWriter(new FileOutputStream(filePath), "utf-8");
            }
            filewriter.write(text);
            filewriter.flush();
        } catch (Exception e) {
            throw new RuntimeException(e.toString());
        } finally {
            if (filewriter != null) {
                try {
                    filewriter.close();
                } catch (Exception e) {
                	throw new RuntimeException(e.toString());
                }
            }
        }
	}
	
	/**
	 * <p>读取文件内容,并把内容以字符串形式返回</p>
	 * 
	 * @param fileFullPath 文件路径
	 * @param encode   编码
	 * @return
	 */
	public static String readFile(String fileFullPath)  {
		return readFile(fileFullPath, null);
	}
	
	/**
	 * <p>读取文件内容,并把内容以字符串形式返回</p>
	 * 
	 * @param fileFullPath 文件完整路径
	 * @param encode   编码
	 * @return
	 */
	public static String readFile(String fileFullPath, String encode) {
		InputStreamReader fileReader = null;
		try {
			if (StringUtil.isNotBlank(encode)) {
				fileReader = new InputStreamReader(new FileInputStream(fileFullPath), encode);
            } else {
            	fileReader = new InputStreamReader(new FileInputStream(fileFullPath), "utf-8");
            }
			BufferedReader reader = new BufferedReader(fileReader);
			StringBuffer strBuff = new StringBuffer();
			String str = reader.readLine();
			while (str != null) {
				if(strBuff.length() >0){
					strBuff.append("\r\n" + str);
				}else {
					strBuff.append(str);
				}
				str = reader.readLine();
			}
			return strBuff.toString();
		} catch (Exception ex) {
			log.error(ex.getMessage());
			throw new RuntimeException(ex.getMessage());
		} finally {
            if (fileReader != null) {
                try {
                	fileReader.close();
                } catch (Exception e) {
                	throw new RuntimeException(e.toString());
                }
            }
        }
	}
	
	/**
	 * <p>如果文件filePath里存在着existStr字符串，那么用regexExist规则替换成replaceStrExist字符串，
	 *    否则使用regexNoExist字符串替换成replaceStrNoExit</p>
	 * 
	 * @param fileFullPath         文件完整路径
	 * @param existStr            存在的字符串
	 * @param oldExistRegexStr    存在字符串时就得表达式字符串
	 * @param newExistStr         存在字符串时新的字符串 
	 * @param oldNoExistRegexStr  不存在字符串时就得表达式字符串
	 * @param newNoExistStr       不存在字符串时新的字符串
	 * 
	 */
	public static void writeOrReplaceText(String fileFullPath, String existStr, String oldExistRegexStr, String newExistStr, 
			String oldNoExistRegexStr, String newNoExistStr) {
		String fileStr = readFile(fileFullPath);
		if (fileStr.indexOf(existStr)==-1) {
			fileStr = fileStr.replaceAll(oldNoExistRegexStr, newNoExistStr);
		} else {
			fileStr = fileStr.replaceAll(oldExistRegexStr, newExistStr);
		}
		toFile(fileFullPath, fileStr);
	}
	
	/**
	 * 通过文件的相对于类的路径获取InputStream资源
	 * 
	 * @param classPath
	 * @return InputStream
	 */
	public static InputStream getResourceAsStream(String classPath) {
		String stripped = classPath.startsWith("/") ? classPath.substring(1) : classPath;
		InputStream stream = null;
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		if (classLoader!=null) {
			stream = classLoader.getResourceAsStream( stripped );
		}
		if ( stream == null ) {
			stream = FileUtil.class.getResourceAsStream( classPath );
		}
		if ( stream == null ) {
			stream = FileUtil.class.getClassLoader().getResourceAsStream( stripped );
		}
		if ( stream == null ) {
			throw new JpException( classPath + " not found" );
		}
		return stream;
	}
	
	/** 
	 * Try to locate a local URL representing the incoming path.  
	 * This method <b>only</b> attempts to locate this URL as a 
	 * java system resource.
	 *
	 * @param path The path representing the config location.
	 * @return An appropriate URL or null.
	 */
	public static final URL findAsResource(final String path) {
		URL url = null;

		// First, try to locate this resource through the current context classloader.
		ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
		if (contextClassLoader!=null) {
			url = contextClassLoader.getResource(path);
		}
		if (url != null)
			return url;

		// Next, try to locate this resource through this class's classloader
		url = FileUtil.class.getClassLoader().getResource(path);
		if (url != null)
			return url;

		// Next, try to locate this resource through the system classloader
		url = ClassLoader.getSystemClassLoader().getResource(path);

		// Anywhere else we should look?
		return url;
	}
	
	/** Try to locate a local URL representing the incoming path.  The first attempt
	 * assumes that the incoming path is an actual URL string (file://, etc).  If this
	 * does not work, then the next attempts try to locate this UURL as a java system
	 * resource.
	 *
	 * @param path The path representing the config location.
	 * @return An appropriate URL or null.
	 */
	public static final URL getURL(final String path) {
		try {
			return new URL(path);
		}
		catch(MalformedURLException e) {
			return findAsResource(path);
		}
	}
	
	/** Open an InputStream to the URL represented by the incoming path.  First makes a call
	 * to {@link #getURL(java.lang.String)} in order to find an appropriate URL.
	 * {@link java.net.URL#openStream()} is then called to obtain the stream.
	 *
	 * @param path The path representing the config location.
	 * @return An input stream to the requested config resource.
	 * @throws JpException Unable to open stream to that resource.
	 */
	public static final InputStream getInputStream(final String path) throws JpException {
		final URL url = getURL(path);

		if (url == null) {
			String msg = "Unable to locate config file: " + path;
			log.error( msg );
			throw new JpException(msg);
		}

		try {
			return url.openStream();
        }
		catch(IOException e) {
	        throw new JpException("Unable to open config file: " + path, e);
        }
	}
	
	/** Loads a properties instance based on the data at the incoming config location.
	 *
	 * @param path The path representing the config location.
	 * @return The loaded properties instance.
	 * @throws JpException Unable to load properties from that resource.
	 */
	public static final Properties getProperties(String path) throws JpException {
		try {
			Properties properties = new Properties();
			properties.load( getInputStream(path) );
			return properties;
		}
		catch(IOException e) {
			throw new JpException("Unable to load properties from specified config file: " + path, e);
		}
	}
	
	public static void main(String[] args) {
		try {
			//delete(new File("D:/tmp"));
			//cleanDirectory(new File("D:/tmp"));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
		}
	}
}
