<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/page/inc/taglib.jsp" %>
<html>
<head>
  <title><fmt:message key="amRole.list.tile"/></title>
  <%@ include file="/page/inc/head.jsp" %>
</head>
<body>
<%@ include file="/page/inc/header.jsp" %>
<div class="main">
  <div id="search" class="dp100 mt1">	
    <form id="amRoleForm" action="<c:url value='/servlet/amRole?action=add'/>"  method="post">
      <table id="amRoleTable" class="table">
  	    <caption><fmt:message key="amRole.form.tile"/></caption>
  	    <tbody class="formEdit">
  	      <!--roleId,roleCd,roleName,description,createDateTime,modifyTimestamp,dataState,-->
	  	  <tr>
		    <th><span style="color:red">*</span><fmt:message key="amRole.roleCd"/>:</th>
			<td><input id="roleCd" name="roleCd"/></td>
			<th><span style="color:red">*</span><fmt:message key="amRole.roleName"/>:</th>
			<td><input id="roleName" name="roleName"/></td>
	      </tr>
	      <tr>
		    <th><fmt:message key="amRole.description"/>:</th>
		    <td colspan="3">
			  <textarea id="description" name="description" rows="25" cols="100"></textarea>
		    </td>
	      </tr>
	    </tbody>  
	    <tfoot>
		  <tr>
		    <td colspan="4" >
		      <input type="submit" value="<fmt:message key='button.submit'/>" />
			  <input type="reset" value="<fmt:message key='button.reset'/>" />
			  <input type="button" onclick="window.location = '<c:url value='/servlet/amRole?action=toList'/>'" 
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
