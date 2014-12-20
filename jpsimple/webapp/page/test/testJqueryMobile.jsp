<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/page/inc/taglib.jsp" %>
<html>
<head>
  <title>jquery mobile</title>
  <%@ include file="/page/inc/headjquerymobile.jsp" %>
</head>
<body>
<div id="mainPage" data-role="page" data-theme="f">
  <div data-role="header" data-position="fixed" data-theme="f">
    <h1>Page Header</h1>
    <a href="#" data-icon="gear" class="ui-btn-right">Options</a>
  </div>
  <div id="content" data-role="content" data-theme="d">
	  <ul id="list" data-role="listview">
	  	<c:forEach var="model" items="${page.result}">
	  	  <li id="li<c:out value='${model.userId}'/>">
	  		<a href="#"><c:out value="${model.userCd}"/></a>
	  	  </li>
	  	</c:forEach>
	  </ul>
	  <div id="dymatic"></div>
  </div>
</div>
</body>
</html>
<<script type="text/javascript">
<!--
$(document).ready(function(){
	$.get("<c:url value='/servlet/test?action=getJqueryMobileHtml'/>",function(data){
			$('#dymatic').append(data).trigger('create');
	},'html');
})
//-->
</script>
