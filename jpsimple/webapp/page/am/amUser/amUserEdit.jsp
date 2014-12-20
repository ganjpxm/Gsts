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
    <form id="amUserForm" action="<c:url value='/servlet/amUser?action=edit'/>" method="post"  OnSubmit="preHandle()">
      <input type="hidden" name="userId" value="<c:out value='${amUser.userId}'/>"/>
      <input type="hidden" name="roleIds" id="roleIds" />
      <input type="hidden" id="treeRoleId" name="treeRoleId" value="<c:out value='${treeRoleId}'/>"/>
      <table id="amUserTable" class="table">
  	    <caption><fmt:message key="amUser.form.tile"/></caption>
  	    <tbody class="formEdit">  
  	       <!--userId,userCd,userName,userAlias,userLevel,userScore,loginTimes,password,gender,qq,email,birthYear,birthMonth,birthDay,createDateTime,modifyTimestamp,dataState,-->
	      <tr>
		    <th><span style="color:red">*</span><fmt:message key="amUser.userCd"/>:</th>
			<td><input id="userCd" name="userCd" value="<c:out value='${amUser.userCd}'/>"/></td>
			<th><span style="color:red">*</span><fmt:message key="amUser.userName"/>:</th>
			<td><input id="userName" name="userName" value="<c:out value='${amUser.userCd}'/>"/></td>
	      </tr>
	      <tr>
		    <th><span style="color:red">*</span><fmt:message key="amUser.password"/>:</th>
			<td><input id="password" name="password" value="<c:out value='${amUser.password}'/>"/></td>
			<th><span style="color:red">*</span><fmt:message key="amUser.userAlias"/>:</th>
			<td><input id="userAlias" name="userAlias" value="<c:out value='${amUser.userAlias}'/>"/></td>
	      </tr>
	      <tr>
		    <th><span style="color:red">*</span><fmt:message key="amUser.userLevel"/>:</th>
			<td><input id="userLevel" name="userLevel" value="<c:out value='${amUser.userLevel}'/>"/></td>
			<th><span style="color:red">*</span><fmt:message key="amUser.userScore"/>:</th>
			<td><input id="userScore" name="userScore" value="<c:out value='${amUser.userScore}'/>"/>/></td>
	      </tr>
	      <tr>
		    <th><span style="color:red">*</span><fmt:message key="amUser.gender"/>:</th>
			<td><input id="gender" name="gender" value="<c:out value='${amUser.gender}'/>"/></td>
			<th><span style="color:red">*</span><fmt:message key="amUser.qq"/>:</th>
			<td><input id="qq" name="qq" value="<c:out value='${amUser.qq}'/>"/></td>
	      </tr>
	      <tr>
		    <th><span style="color:red">*</span><fmt:message key="amUser.email"/>:</th>
			<td><input id="email" name="email" value="<c:out value='${amUser.email}'/>"/></td>
			<th><span style="color:red">*</span><fmt:message key="amUser.birthDay"/>:</th>
			<td></td>
	      </tr>
	      <tr>
		    <th><span style="color:red">*</span><fmt:message key="amRole.roleName"/>:</th>
			<td colspan="5">
				<c:forEach items="${roleIdAndNameMap}" var="entry"> 
					<input type="checkbox" name="roleId" style="width:30" value="<c:out value='${entry.key}'/>" /><c:out value='${entry.value}' />
				</c:forEach> 
			</td>
	      </tr>
	    </tbody>  
	    <tfoot>
		  <tr>
		    <td colspan="4" >
		      <input style="width:50px" type="submit" value="<fmt:message key='button.submit'/>" />
			  <input style="width:50px" type="reset" value="<fmt:message key='button.reset'/>" />
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
Jp.selectCheckbox("roleId","<c:out value='${roleIds}'/>");
function preHandle() {
	$("#roleIds").val(Jp.getCheckValues("roleId"));
};
//-->
</script>
