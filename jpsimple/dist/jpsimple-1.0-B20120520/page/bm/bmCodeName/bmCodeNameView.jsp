<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/page/inc/taglib.jsp" %>
<html>
<head>
  <title><fmt:message key="bmCodeName.form.tile"/></title>
  <%@ include file="/page/inc/head.jsp" %>
</head>
<body>
<%@ include file="/page/inc/header.jsp" %>
<div class="main">
  <div id="search" class="dp100 mt1">	
    <form id="bmCodeNameForm" action="<c:url value='/servlet/bmCodeName?action=show'/>" method="post">
      <input type="hidden" name="codeNameId" value="<c:out value='${bmCodeName.codeNameId}'/>"/>
      <table id="bmCodeNameTable" class="table">
  	    <caption><fmt:message key="bmCodeName.form.tile"/></caption>
  	    <tbody class="formView">
  	      <!--codeNameId,codeNamePid,code,name,displayNo,displayLevel,endFlag,createDateTime,modifyTimestamp,dataState,-->
  	      <tr>
		    <th><fmt:message key="bmCodeName.code"/>:</th>
		    <td><c:out value='${bmCodeName.code}'/></td>
		    <th><fmt:message key="bmCodeName.name"/>:</th>
		    <td><c:out value='${bmCodeName.name}'/></td>
		    <th><fmt:message key="bmCodeName.displayNo"/>:</th>
		    <td><c:out value='${bmCodeName.displayNo}'/></td>
	      </tr>
	    </tbody>
	    <tfoot>
		  <tr>
		    <td colspan="6" >
			  <input style="width:50px" type="button" onclick="window.location = '<c:url value='/servlet/bmCodeName?action=toList'/>'" 
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
