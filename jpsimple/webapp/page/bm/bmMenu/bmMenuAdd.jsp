<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/page/inc/taglib.jsp" %>
<html>
<head>
  <title><fmt:message key="bmMenu.list.tile"/></title>
  <%@ include file="/page/inc/head.jsp" %>
</head>
<body>
<%@ include file="/page/inc/header.jsp" %>
<div class="main">
  <div id="search" class="dp100 mt1">	
    <form id="bmMenuForm" action="<c:url value='/servlet/bmMenu?action=add'/>"  method="post">
      <table id="bmMenuTable" class="table">
  	    <caption><fmt:message key="bmMenu.form.tile"/></caption>
  	    <tbody class="formEdit">
  	      <!--menuId,menuPid,menuCd,menuName,menuType,url,belongUserId,displayLevel,displayNo,endFlag,modifyTimestamp,createDateTime,dataState,-->
	  	  <tr>
		    <th><span style="color:red">*</span><fmt:message key="bmMenu.menuPid"/>:</th>
			<td><input id="menuPid" name="menuPid"/></td>
			<th><span style="color:red">*</span><fmt:message key="bmMenu.menuCd"/>:</th>
			<td><input id="menuCd" name="menuCd"/></td>
			<th><span style="color:red">*</span><fmt:message key="bmMenu.menuName"/>:</th>
			<td><input id="menuName" name="menuName"/></td>
	      </tr>
	      <tr>
		    <th><span style="color:red">*</span><fmt:message key="bmMenu.url"/>:</th>
			<td><input id="url" name="url"/></td>
			<th><span style="color:red">*</span><fmt:message key="bmMenu.displayLevel"/>:</th>
			<td><input id="displayLevel" name="displayLevel"/></td>
			<th><span style="color:red">*</span><fmt:message key="bmMenu.displayNo"/>:</th>
			<td><input id="displayNo" name="displayNo"/></td>
	      </tr>
	      <tr>
		    <th><span style="color:red">*</span><fmt:message key="bmMenu.endFlag"/>:</th>
			<td colspan="5"><input id="url" name="endFlag"/></td>
	      </tr>
	    </tbody>  
	    <tfoot>
		  <tr>
		    <td colspan="6" >
		      <input type="submit" value="<fmt:message key='button.submit'/>" />
			  <input type="reset" value="<fmt:message key='button.reset'/>" />
			  <input type="button" onclick="window.location = '<c:url value='/servlet/bmMenu?action=toList'/>'" 
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
