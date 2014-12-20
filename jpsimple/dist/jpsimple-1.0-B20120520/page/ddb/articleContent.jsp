<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/page/inc/taglib.jsp" %>
<html>
<head>
  <title><fmt:message key="cmArticle.form.tile"/></title>
  <%@ include file="/page/inc/head.jsp" %>
</head>
<body>
<%@ include file="/page/inc/header.jsp" %>
<div class="main80">
      <table id="cmArticleTable">
  	    <tbody>
	      <tr>
		    <td>
			  <c:out value='${cmArticle.articleContent}' escapeXml="false"/>
		    </td>
	      </tr>
	    </tbody>
	    <tfoot>
		  <tr>
		    <td style="text-align:right;">
			   <a href= '<c:url value='/servlet/cmArticle?action=toArticleTitle&articleCategoryIds=${articleCategoryIds}'/>'">
			     back
			   </a>
		    </td>
		  </tr>
	    </tfoot>
  	  </table>
</div>
<%@ include file="/page/inc/header.jsp" %>
</body>
</html>
<script type="text/javascript">
<!--

//-->
</script>
