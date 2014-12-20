<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/page/inc/taglib.jsp" %>
<html>
<head>
  <title><fmt:message key="amUserRole.form.tile"/></title>
  <%@ include file="/page/inc/head.jsp" %>
</head>
<body>
<%@ include file="/page/inc/header.jsp" %>
<div class="main">
  <div id="search" class="dp100 mt1">	
    <form id="amUserRoleForm" action="<c:url value='/servlet/amUserRole?action=edit'/>" method="post">
      <input type="hidden" name="userRoleId" value="<c:out value='${amUserRole.userRoleId}'/>"/>
      <table id="amUserRoleTable" class="table">
  	    <caption><fmt:message key="amUserRole.form.tile"/></caption>
  	    <tbody class="formEdit">  
  	       <!--userRoleId,roleId,userId,createDateTime,modifyTimestamp,dataState,-->
	  	  <tr>
		    <th><span style="color:red">*</span><fmt:message key="amUserRole.fieldName1"/>:</th>
			<td><input id="fieldName1" name="fieldName1" value="<c:out value='${amUserRole.fieldName1}'/>"/></td>
			<th><span style="color:red">*</span><fmt:message key="amUserRole.fieldName2"/>:</th>
			<td><input id="fieldName2" name="fieldName2" value="<c:out value='${amUserRole.fieldName2}'/>"/></td>
			<th><span style="color:red">*</span><fmt:message key="amUserRole.fieldName3"/>:</th>
			<td><input id="fieldName3" name="fieldName3" value="<c:out value='${amUserRole.fieldName3}'/>"/></td>
	      </tr>
	    </tbody>  
	    <tfoot>
		  <tr>
		    <td colspan="6" >
		      <input style="width:50px" type="submit" value="<fmt:message key='button.submit'/>" />
			  <input style="width:50px" type="reset" value="<fmt:message key='button.reset'/>" />
			  <input style="width:50px" type="button" onclick="window.location = '<c:url value='/servlet/amUserRole?action=toList'/>'" 
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
