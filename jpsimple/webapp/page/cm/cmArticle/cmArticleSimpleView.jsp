<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/page/inc/taglib.jsp" %>
<html>
<head>
  <title><fmt:message key="cmArticle.form.tile"/></title>
  <%@ include file="/page/inc/head.jsp" %>
</head>
<body>
<%@ include file="/page/inc/header.jsp" %>
<div class="main">
  <div id="search" class="dp100 mt1">	
      <input type="hidden" name="articleId" value="<c:out value='${cmArticle.articleId}'/>"/>
      <table id="cmArticleTable">
  	    <tbody>
  	      <!--articleId,articleCategoryIds,articleCd,articleTitle,articleContent,commentAuthorId,commentAuthorName,articleRecommendLevelId,displayNo,remark,auditFlag,operatorId,operatorName,createDateTime,modifyTimestamp,dataState,-->
	      <tr>
		    <td>
			  <c:out value='${cmArticle.articleContent}' escapeXml="false"/>
		    </td>
	      </tr>
	    </tbody>
	    <tfoot>
		  <tr>
		    <td style="text-align:right;">
			   <a href= '<c:url value='/servlet/cmArticle?action=toSimpleList&articleCategoryIds=${articleCategoryIds}'/>'">
			    <fmt:message key='button.back'/>
			   </a>
		    </td>
		  </tr>
	    </tfoot>
  	  </table>
  </div>
</div>
<%@ include file="/page/inc/header.jsp" %>
</body>
</html>
<script type="text/javascript">
<!--

//-->
</script>
