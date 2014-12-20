package org.ganjp.core.web.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ganjp.am.model.AmUser;
import org.ganjp.bm.service.impl.BmMenuServiceImpl;
import org.ganjp.core.Configuration;
import org.ganjp.core.Constants;
import org.ganjp.core.db.id.UUIDHexGenerator;

public class CommonServlet extends BaseServlet {
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		if ("toGlobleManage".equalsIgnoreCase(action)) {
			pageUrlEntity.setJspUrl("common/manage");
		} else if ("toHome".equalsIgnoreCase(action)) {
			HttpSession session = request.getSession();
			Object obj = session.getAttribute("amUser");
			Map menuAndContentMap = null;
			if (session.getAttribute("htmlMenu")==null) {
				menuAndContentMap = new BmMenuServiceImpl().getHtmlMenuAndContentMap(Constants.WEBSITE_MENU_ROOT_ID,super.getBasePath(request));
				session.setAttribute("htmlMenu", menuAndContentMap.get("htmlMenu"));
			}
			if (session.getAttribute("htmlContent")==null) {
				if (menuAndContentMap==null) {
					menuAndContentMap = new BmMenuServiceImpl().getHtmlMenuAndContentMap(Constants.WEBSITE_MENU_ROOT_ID,super.getBasePath(request));
				}
				request.setAttribute("htmlContent", menuAndContentMap.get("htmlContent"));
			}
			if (obj!=null) {
				AmUser amUser = (AmUser)obj;
				String url = getUrl(amUser.getRoleIds(), request);//different role has a different url
				request.getSession().setAttribute("url", url);
			}
			pageUrlEntity.setJspUrl("common/home");
		}  else if ("toDdb".equalsIgnoreCase(action)) {
			HttpSession session = request.getSession();
			if (session.getAttribute("htmlMenu")==null) {
				//String htmlMenu = new BmMenuServiceImpl().getOneLevelMenuHtml(Constants.WEBSITE_MENU_ROOT_ID,super.getBasePath(request));
				//session.setAttribute("htmlMenu", htmlMenu);
			}
			pageUrlEntity.setJspUrl("ddb/home");
		} else if ("testDb".equalsIgnoreCase(action)) {
			pageUrlEntity.setJspUrl("ddb/db");
		} else if ("toArticle".equalsIgnoreCase(action)) {
			HttpSession session = request.getSession();
			if (session.getAttribute("htmlMenu")==null) {
				Map menuAndContentMap = new BmMenuServiceImpl().getHtmlMenuAndContentMap(Constants.WEBSITE_MENU_ROOT_ID,super.getBasePath(request));
				session.setAttribute("htmlMenu", menuAndContentMap.get("htmlMenu"));
			}
//			if (session.getAttribute("accordionHtml")==null) {
				request.setAttribute("accordionHtml", new BmMenuServiceImpl().getAccordionHtml(request.getParameter("menuId"),super.getBasePath(request)));
//			}
			request.setAttribute("articleCategoryId",request.getParameter("articleCategoryId"));
			pageUrlEntity.setJspUrl("common/article");
		} else if ("logout".equalsIgnoreCase(action)) {
			request.getSession().invalidate();
			pageUrlEntity.setServletUrl("/servlet/common?action=toHome");
		} else if ("getUuid".equalsIgnoreCase(action)) {
			pageUrlEntity.setStringData(UUIDHexGenerator.getUuid());
		}  else if ("toUpload".equalsIgnoreCase(action)) {
			pageUrlEntity.setJspUrl("common/upload");
		} else if ("upload".equalsIgnoreCase(action)) {
			try {
				String filePaths = saveFileAndGetFilePaths(request, response, getSavePath(request));
				pageUrlEntity.setStringData(filePaths.substring(filePaths.lastIndexOf(File.separatorChar)+1,filePaths.length()-1));
			} catch(IOException ioEx) {
				log.error(ioEx.getMessage());
			}
		} else if ("image".equalsIgnoreCase(action)) {
			String imageName = request.getParameter("imageName");
			String imageFullPath = getSavePath(request) + File.separatorChar + imageName;
			if (!new File(imageFullPath).exists()) {
				imageFullPath = getSavePath(request) + File.separatorChar + "disappearance.jpg";
			}
			pageUrlEntity.setImagePath(imageFullPath);
		} 
		
	}

	protected String saveFileAndGetFilePaths(HttpServletRequest request, HttpServletResponse response, String savePath) throws IOException {
		FileOutputStream fout = null;
		HashMap paraMap = new HashMap();
		try {
			ServletInputStream in = request.getInputStream();
			byte b[] = new byte[512], lastByteArray[] = new byte[512];
			String separator;
			int hasRead = 0, offset = 0;
			if ((hasRead = in.readLine(b, 0, b.length)) != -1) {// 判断有没有上传文件
				separator = new String(b, 0, hasRead, "ascii");
				finish: while (true) {
					hasRead = in.readLine(b, 0, b.length);
					String filename = null, formname = "";
					String[] strs = new String(b, 0, hasRead, "UTF8").split(";");
					for (int i = 0; i < strs.length; i++) {
						String tmp = strs[i];
						if (tmp.trim().toLowerCase().startsWith("filename=")) {
							if (tmp.indexOf(File.separatorChar)!=-1) {
								int t = tmp.lastIndexOf(File.separatorChar)+1;
								filename = tmp.substring(t, tmp.lastIndexOf('"'));
							} else {
								filename = tmp.substring(tmp.indexOf('"') + 1, tmp.lastIndexOf('"'));
							}
						} else if (tmp.trim().toLowerCase().startsWith("name=")) {
							formname = tmp.substring(tmp.indexOf('"') + 1,
									tmp.lastIndexOf('"'));
						}
					}
					if (filename == null) {// 当传来的一部分不是文件时
						String param = "";
						in.readLine(b, 0, b.length);// 忽略空分割行
						while (true) {
							if ((hasRead = in.readLine(b, 0, b.length)) == -1) {
								response.sendError(400);
								return "error";
							} else if (separator.equals(new String(b, 0, hasRead, "ascii"))) {
								HashSet hs = paraMap.containsKey(formname) ? (HashSet) paraMap.get(formname) : new HashSet();
								hs.add(param.substring(0, param.length() - 2));// 去掉作为分隔符的"\r\n"
								paraMap.put(formname, hs);
								continue finish;
							} else if (new String(b, 0, hasRead, "ascii").startsWith((separator.substring(0, separator.length() - 2) + "--"))) {
								HashSet hs = paraMap.containsKey(formname) ? (HashSet) paraMap
										.get(formname) : new HashSet();
								hs.add(param.substring(0, param.length() - 2));// 去掉作为分隔符的"\r\n"
								paraMap.put(formname, hs);
								break finish;
							} else {
								param += new String(b, 0, hasRead, "utf8");
							}
						}
					}// 处理非文件上传结束

					// 以下处理上传文件
					in.readLine(b, 0, b.length);
					in.readLine(b, 0, b.length);// 忽略Content-Type:
												// text/plain这一行和空分割行
					File f = new File(savePath + File.separatorChar + filename);
					fout = new FileOutputStream(f);
					while (true) {
						if ((hasRead = in.readLine(b, 0, b.length)) == -1) {
							response.sendError(400);
							return "error";
						} else if (separator.equals(new String(b, 0, hasRead, "ascii"))) {
							fout.write(lastByteArray, 0, offset - 2);// offset-2==>文件尾，去除request中作为分割用的"\r\n"
							HashSet hs = paraMap.containsKey(formname) ? (HashSet) paraMap.get(formname) : new HashSet();
							hs.add(f);
							paraMap.put(formname, hs);// 将文件的路径存入ParameterMap
							offset = 0;
							fout.close();
							break;
						} else if (new String(b, 0, hasRead, "ascii").startsWith((separator.substring(0, separator.length() - 2) + "--"))) {
							fout.write(lastByteArray, 0, offset - 2);// offset-2==>文件尾，去除request中作为分割符用的"\r\n"
							HashSet hs = paraMap.containsKey(formname) ? (HashSet) paraMap.get(formname) : new HashSet();
							hs.add(f);
							paraMap.put(formname, hs);// 将文件的路径存入ParameterMap
							fout.close();
							break finish;
						} else {
							fout.write(lastByteArray, 0, offset);
							byte tmpB[] = lastByteArray;
							lastByteArray = b;
							b = tmpB;
							offset = hasRead;
						}
					}
					// 处理上传文件部分结束
				}
			}
		} catch (NullPointerException npe) {
			response.sendError(400);
		} catch (ArrayIndexOutOfBoundsException aio) {
			response.sendError(400);
		} finally {
			if (fout != null) {
				fout.close();
			}
		}
		Collection colection = paraMap.values();
		String filePaths = "";
        for (Iterator iterator = colection.iterator(); iterator.hasNext();) {
			HashSet hs = (HashSet) iterator.next();
			for (Iterator iterator2 = hs.iterator(); iterator2.hasNext();) {
				Object o = (Object) iterator2.next();
				filePaths +=  o + ",";
				//if (o instanceof File)
			}
		}
        return filePaths;
	}
	
	public String getSavePath(HttpServletRequest request) {
		String savePath = Configuration.getString("save.path");
		if ("yes".equalsIgnoreCase(Configuration.getString("upload.file.to.webserver"))) {
			savePath = request.getRealPath("/") + "resource" + File.separatorChar + "picture";
		}
		return savePath;
	}
	
}
