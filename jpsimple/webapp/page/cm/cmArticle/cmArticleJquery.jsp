<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/page/inc/taglib.jsp" %>
<html>
<head>
  <title><fmt:message key="cmArticleManager.title"/></title>
  <%@ include file="/page/inc/headjquery.jsp" %>
</head>
<body>
<%@ include file="/page/inc/header.jsp" %>
<div class="main">
	<!-- 标题 -->
	<div id="header" class="dp100 title1">
		<div class="content"><fmt:message key="cmArticleManager.title"/></div>
	</div>
	<div id="body" class="dp100 mt1">
		<table id="grid" cellpadding="0" cellspacing="0"></table>
		<div id="pageToolBar"></div>
	</div>
</div>
<%@ include file="/page/inc/footer.jsp" %>
</body>
</html>
<script type="text/javascript">
<!--
jQuery(document).ready(function(){  

});
//-->
</script>


