/*
 * $Id: Package2Path.java,v 1.1 2011/05/12 09:58:19 ganjp Exp $
 * 
 * Copyright 2011 the original author or authors.
 * This software is core of developing project and written by ganjianping. 
 */
package org.ganjp.extend.ant;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.ganjp.core.util.FileUtil;
import org.ganjp.core.util.StringUtil;

/**
 * <p>替换或新增文本内容</p>
 * 
 * @author ganjp
 * @since 1.0
 */
public class ReplaceTextTask extends Task {
	private String fileFullPath;
	private String srcDirs;

	public void execute() throws BuildException {
		String existStr = "";
		String oldExistRegexStr = "";
		String newExistStr = "";
		String oldNoExistRegexStr = "";
		String newNoExistStr = "";
		
		if (this.fileFullPath.endsWith("classpath")) {
			String startTag = "<!-- src-start -->";
			String endTag = "<!-- src-end -->";
			existStr = startTag;
			oldExistRegexStr = startTag + "(?s:.)*" + endTag;
			oldNoExistRegexStr="</classpath>";
			
			String[] srcDirArr = this.srcDirs.split(";");
			String line = "<classpathentry kind=\"src\" path=\"%s\" output=\"web/WEB-INF/classes\" />";
			String content = startTag;
			for (int i = 0; i < srcDirArr.length; i++) {
				String srcDir = srcDirArr[i];
				content += "\r\n    " + StringUtil.format(line, srcDir);
			}
			content = content + "\r\n    " + endTag;
			newExistStr = content;
			newNoExistStr = "    " + content + "\r\n</classpath>";
		}
		FileUtil.writeOrReplaceText(this.fileFullPath, existStr, oldExistRegexStr, newExistStr, oldNoExistRegexStr, newNoExistStr);
	}

	public void setFileFullPath(String fileFullPath) {
		this.fileFullPath = fileFullPath;
	}

	public void setSrcDirs(String srcDirs) {
		this.srcDirs = srcDirs;
	}
	
}
