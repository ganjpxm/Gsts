<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/page/inc/taglib.jsp" %>
<html>
<head>
  <title><fmt:message key="cmArticle.list.tile"/></title>
  <%@ include file="/page/inc/head.jsp" %>
</head>
<body>
  <div id="content" class="dp100 ml2 bookBg" style="text-align:left">
  	<form id="cmArticleForm" action="<c:url value='/servlet/cmArticle?action=toSimpleList&articleCategoryIds=${articleCategoryIds}'/>" method="post" >
    <table id="cmArticleTable" class="simpleTable">
	  <tbody class="list">
	  	<tr>
		  <td style="height:40px;">&nbsp;</td>
        </tr>
	    <c:forEach var="model" items="${page.result}">
		  <tr>
		    <td style="height:40px;font-weight:bolder;font-size:14px;font-color:black; padding-left:60px;">
			  <a href="<c:url value='/servlet/cmArticle?action=toSimpleView&articleId=${model.articleId}&articleCategoryIds=${articleCategoryIds}'/>">
			    <c:out value="${model.articleTitle}"/>
			  </a>
		    </td>
          </tr>
	    </c:forEach>
	  </tbody>
	  <tfoot>
	    <tr><td id="page" style="padding-left:60px"><script type="text/javascript">var form = "cmArticleForm";</script>
	    <%@ include file="/page/inc/page.jsp" %></td></tr>
	  </tfoot>
	</table>
	</form>
  </div>
</body>
</html>
<script type="text/javascript">
<!--
var clientWidth = parent.document.body.clientWidth;
if ($("#content").height()<600) {
	$("#content").height(620);
}

if (totalCount<10) {
	$("#page").hide();
}
function addQueryFilterEl(formEl) {
	
}
//-->
</script>


