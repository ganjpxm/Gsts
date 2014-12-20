<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/page/inc/taglib.jsp" %>
<html>
<head>
  <title><fmt:message key="amUser.form.tile"/></title>
  <%@ include file="/page/inc/head.jsp" %>
</head>
<body>
<%@ include file="/page/inc/header.jsp" %>
<div class="main">
  <div id="search" class="dp100 mt1">	
    <form id="amUserForm" action="<c:url value='/servlet/amUser?action=show'/>" method="post">
      <table id="amUserTable" class="table">
  	    <caption><fmt:message key="amUser.form.tile"/></caption>
  	    <tbody class="formView">
  	      <!--userId,userCd,userName,userAlias,userLevel,userScore,loginTimes,password,gender,qq,email,birthYear,birthMonth,birthDay,createDateTime,modifyTimestamp,dataState,-->
	      <tr>
		    <th><fmt:message key="amUser.userCd"/>:</th>
			<td><c:out value='${amUser.userCd}'/></td>
			<th><fmt:message key="amUser.userName"/>:</th>
			<td><c:out value='${amUser.userCd}'/></td>
	      </tr>
	      <tr>
		    <th><fmt:message key="amUser.password"/>:</th>
			<td><c:out value='${amUser.password}'/></td>
			<th><fmt:message key="amUser.userAlias"/>:</th>
			<td><c:out value='${amUser.userAlias}'/></td>
	      </tr>
	      <tr>
		    <th><fmt:message key="amUser.userLevel"/>:</th>
			<td><c:out value='${amUser.userLevel}'/></td>
			<th><fmt:message key="amUser.userScore"/>:</th>
			<td><c:out value='${amUser.userScore}'/></td>
	      </tr>
	      <tr>
		    <th><fmt:message key="amUser.gender"/>:</th>
			<td><c:out value='${amUser.gender}'/></td>
			<th><fmt:message key="amUser.qq"/>:</th>
			<td><c:out value='${amUser.qq}'/></td>
	      </tr>
	      <tr>
		    <th><fmt:message key="amUser.email"/>:</th>
			<td><c:out value='${amUser.email}'/></td>
			<th><fmt:message key="amUser.birthday"/>:</th>
			<td></td>
	      </tr>
	    </tbody>
	    <tfoot>
		  <tr>
		    <td colspan="6" >
			  <input style="width:50px" type="button" onclick="window.location = '<c:url value='/servlet/amUser?action=toList&treeRoleId=${treeRoleId}'/>'" 
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
