<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/page/inc/taglib.jsp" %>
<html>
<head>
  <title><fmt:message key="bmMenu.form.tile"/></title>
  <%@ include file="/page/inc/head.jsp" %>
</head>
<body>
<%@ include file="/page/inc/header.jsp" %>
<div class="main">
  <div id="search" class="dp100 mt1">	
    <form id="bmMenuForm" action="<c:url value='/servlet/bmMenu?action=show'/>" method="post">
      <input type="hidden" name="menuId" value="<c:out value='${bmMenu.menuId}'/>"/>
      <table id="bmMenuTable" class="table">
  	    <caption><fmt:message key="bmMenu.form.tile"/></caption>
  	    <tbody class="formView">
  	      <!--menuId,menuPid,menuCd,menuName,menuType,url,belongUserId,displayLevel,displayNo,endFlag,modifyTimestamp,createDateTime,dataState,-->
  	      <tr>
		    <th><fmt:message key="bmMenu.menuId"/>:</th>
		    <td><c:out value='${bmMenu.menuId}'/></td>
		    <th><fmt:message key="bmMenu.menuPid"/>:</th>
		    <td><c:out value='${bmMenu.menuPid}'/></td>
		    <th><fmt:message key="bmMenu.menuCd"/>:</th>
		    <td><c:out value='${bmMenu.menuCd}'/></td>
	      </tr>
	      <tr>
		    <th><fmt:message key="bmMenu.menuName"/>:</th>
		    <td><c:out value='${bmMenu.menuName}'/></td>
		    <th><fmt:message key="bmMenu.url"/>:</th>
		    <td><c:out value='${bmMenu.url}'/></td>
		    <th><fmt:message key="bmMenu.displayLevel"/>:</th>
		    <td><c:out value='${bmMenu.displayLevel}'/></td>
	      </tr>
	      <tr>
		    <th><fmt:message key="bmMenu.displayNo"/>:</th>
		    <td><c:out value='${bmMenu.displayNo}'/></td>
		    <th><fmt:message key="bmMenu.endFlag"/>:</th>
		    <td colspan="3"><c:out value='${bmMenu.endFlag}'/></td>
	      </tr>
	    </tbody>
	    <tfoot>
		  <tr>
		    <td colspan="6" >
			  <input style="width:50px" type="button" onclick="window.location = '<c:url value='/servlet/bmMenu?action=toList'/>'" 
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
