<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" isErrorPage="true" %>
<%@ include file="/page/inc/taglib.jsp" %>
<html>
<head>
<title><fmt:message key="403.title"/></title>
</head>
<body>
<h1><fmt:message key="403.message"/></h1>
<br/>
<input type="button" class="btn" onclick="history.back();" value="<fmt:message key="button.back"/>"/>
</body>
</html>