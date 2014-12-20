<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/page/inc/taglib.jsp" %>
<html>
<head>
  <title><fmt:message key="test.tile"/></title>
  <%@ include file="/page/inc/headjquery.jsp" %>
  <%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
  %>
</head>
<body>
<%@ include file="/page/inc/header.jsp" %>
<div class="main">
  <div id="header" class="dp100 title1">
    <div class="content">
    	<fmt:message key="test.tile"/>
    </div>
  </div>
  <div id="body" class="dp100 mt1">
  	<img alt="ganjp" src="<c:url value='/servlet/common?action=image'/>"/>
    <fmt:requestEncoding value="utf-8"/>
  	<span><b><fmt:message key="test.message.fmt"/></b></span><br/>
	  <c:out value="${message0}"></c:out><br/>
   	  <c:out value="${message1}"></c:out><br/>
   	<c:out value="${message2}"></c:out><br/>
   	<hr/>
   	<span><b><fmt:message key="test.message.fmt"/></b></span><br/>
      <fmt:message key="test.message.oneParam">
        <fmt:param value="ganjianping" />
      </fmt:message>
    <hr/>
    <span><b><fmt:message key="test.fmt.number.format"/></b></span><br/>
      1234567890 : <fmt:formatNumber value="1234567890" type="number"/><br/>
      1234567890-#,#00.0# : <fmt:formatNumber value="1234567890" type="number" pattern="#,#00.0#" /><br/>
      35000-currency ：<fmt:formatNumber value="35000" type="currency" /><br/>
      0.317-percent : <fmt:formatNumber value="0.317" type="percent" /><br/>
    <hr/>
   	<span><b><fmt:message key="test.fmt.date.format"/></b></span><br/>
      <jsp:useBean id="now" class="java.util.Date"></jsp:useBean>
      <fmt:formatDate  value="${now}" type="date" /><br/>
      <fmt:formatDate  value="${now}" type="both" dateStyle="long" timeStyle="long" /><br/>
      <fmt:formatDate  value="${now}" type="both" pattern="yyyy-MM-dd HH:mm:ss" /><br/>
      <fmt:setTimeZone value="GMT+07:00" />
      <fmt:formatDate value="${now}" type="time" timeStyle="full" />
    <hr/>
  </div>
</div>	
</body>
</html>
