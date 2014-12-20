<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/page/inc/taglib.jsp" %>
<html>
<head>
  <title><fmt:message key="test.tag.tile"/></title>
  <%@ include file="/page/inc/headjquery.jsp" %>
</head>
<body>
<%@ include file="/page/inc/header.jsp" %>
<div class="main">
  <div id="header" class="dp100 title1">
    <div class="content">
    	<fmt:message key="test.tag.tile"/>
    </div>
  </div>
  <div id="body" class="dp100 mt1">
  	<span><b><fmt:message key="test.tag.jp"/></b></span><br/>
   	  <jp:yearmonth name="year" businessYear="${currentYear}"></jp:yearmonth>
  </div>
</div>	
</body>
</html>
