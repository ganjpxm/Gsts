<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/page/inc/taglib.jsp" %>
<html>
<head>
  <title><fmt:message key="bmConfig.form.tile"/></title>
  <%@ include file="/page/inc/head.jsp" %>
</head>
<body>
<%@ include file="/page/inc/header.jsp" %>
<div class="main">
  <div id="search" class="dp100 mt1">	
    <form id="bmConfigForm" action="<c:url value='/servlet/bmConfig?action=show'/>" method="post">
      <input type="hidden" name="configId" value="<c:out value='${bmConfig.configId}'/>"/>
      <table id="bmConfigTable" class="table">
  	    <caption><fmt:message key="bmConfig.form.tile"/></caption>
  	    <tbody class="formView">
  	      <tr>
		    <th><fmt:message key="bmConfig.configKey"/>:</th>
		    <td><c:out value='${bmConfig.configKey}'/></td>
		    <th><fmt:message key="bmConfig.configKeyDisplayName"/>:</th>
		    <td><c:out value='${bmConfig.configKeyDisplayName}'/></td>
		    <th><fmt:message key="bmConfig.configValue"/>:</th>
		    <td><c:out value='${bmConfig.configValue}'/></td>
	      </tr>
	      <tr>
		    <th><fmt:message key="bmConfig.description"/>:</th>
		    <td colspan="5">
			  <c:out value='${bmConfig.description}'/>
		    </td>
	      </tr>
	    </tbody>
	    <tfoot>
		  <tr>
		    <td colspan="6" >
			  <input style="width:50px" type="button" onclick="window.location = '<c:url value='/servlet/bmConfig?action=toList'/>'" 
			  	value="<fmt:message key='button.back'/>" />
		    </td>
		  </tr>
	    </tfoot>
  	  </table>
    </form>
  </div>
</div>
<%@ include file="/page/inc/header.jsp" %>
</body>
</html>
<script type="text/javascript">
<!--

//-->
</script>
