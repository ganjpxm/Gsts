package org.ganjp.core.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ganjp.am.service.impl.AmUserServiceImpl;
import org.ganjp.core.db.base.Page;

public class TestServlet extends BaseServlet {
	
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		if ("base".equals(action)) {
			request.setAttribute("message0", getMessage("test.message"));
			request.setAttribute("message1", getMessage("test.message.oneParam", "age"));
			request.setAttribute("message2", getMessage("test.message.twoParam", "age", "28"));
			pageUrlEntity.setJspUrlAndSuccessMsg("test/testBase",getMessage("welcome.message"));
		} else if ("json".equals(action)) {
			pageUrlEntity.setJsonData("{failure:true,msg:'id repeat'}");
		} else if ("tag".equals(action)) {
			request.setAttribute("currentYear", "2012");
			pageUrlEntity.setJspUrl("test/testTag");
		} else if ("jqgrid".equals(action)) {
			pageUrlEntity.setJspUrl("test/testJqgrid");
		} else if ("jqueryUi".equals(action)) {
			pageUrlEntity.setJspUrl("test/testJqueryUi");
		} else if ("menu".equals(action)) {
			pageUrlEntity.setJspUrl("test/testMenu");
		} else if ("jqueryUiForm".equals(action)) {
			pageUrlEntity.setJspUrl("test/testJqueryUiForm");
		} else if ("getJqgridJsonData".equalsIgnoreCase(action)) {
			String jqgridJsonData = "{\"page\":1,\"total\":1,\"records\":1,\"rows\":[{\"id\":\"1\",\"birthDate\":\"1990-10-01\"," +
					"\"name\":\"小红\",\"amount\":\"200.00\",\"note\":\"备注1\"}]}";
			pageUrlEntity.setJsonData(jqgridJsonData);
		} else if ("saveJqgridData".equalsIgnoreCase(action)) {
			
		} else if ("jqueryMobile".equals(action)) {
			Page page = new AmUserServiceImpl().getAllPage(1, 20, null);
			request.setAttribute("page", page);
			pageUrlEntity.setJspUrl("test/testJqueryMobile");
		} else if ("getJqueryMobileHtml".equals(action)) {
			String html1 = "<p class='intro'><strong>Welcome Gan Jianping.</strong> Browse the jQuery Mobile components and " +
					"learn how to make rich, accessible, touch-friendly websites and apps.</p>";
			String html2 = "<div data-role='controlgroup'><a id='yes' data-role='button' data-theme='d'>Yes</a>"+
						   "<a id='no' data-role='button' data-theme='e'>No</a></div>";
			pageUrlEntity.setStringData(html2);
		}
	}
}
