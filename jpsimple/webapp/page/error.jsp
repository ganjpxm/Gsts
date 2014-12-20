<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" isErrorPage="true" %>
<%@ include file="/page/inc/taglib.jsp" %>
<html>
<head>
  <title><fmt:message key="errorPage.title"/></title>
</head>
<body>
<h1><fmt:message key="errorPage.message"/></h1>
<div id="errorMsg" style="display:none;">
</div>
<br/>
<input type="button" class="btn" onclick="showErrorMsg();" value="<fmt:message key='button.view.error'/>"/>
<input type="button" class="btn" onclick="history.back();" value="<fmt:message key='button.back'/>"/>
</body>
</html>
<script>
function showErrorMsg() {
    document.getElementById('errorMsg').style.display = "block";
    document.getElementById('errorMsg').style.width = "500px";
    document.getElementById('errorMsg').style.height = "500px";
    document.getElementById('errorMsg').innerHTML = '<c:out value="${error}" escapeXml="false"/>';
    <c:remove var="error"/>
}
</script>
