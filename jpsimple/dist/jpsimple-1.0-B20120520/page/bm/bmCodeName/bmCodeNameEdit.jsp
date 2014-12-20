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
    <form id="bmCodeNameForm" action="<c:url value='/servlet/bmCodeName?action=edit'/>" method="post">
      <input type="hidden" name="codeNameId" value="<c:out value='${bmCodeName.codeNameId}'/>"/>
      <table id="bmCodeNameTable" class="table">
  	    <caption><fmt:message key="bmCodeName.form.tile"/></caption>
  	    <tbody class="formEdit">  
  	       <!--codeNameId,codeNamePid,code,name,displayNo,displayLevel,endFlag,createDateTime,modifyTimestamp,dataState,-->
	  	  <tr>
		    <th><span style="color:red">*</span><fmt:message key="bmCodeName.code"/>:</th>
			<td><input id="code" name="code" value="<c:out value='${bmCodeName.code}'/>"/></td>
			<th><span style="color:red">*</span><fmt:message key="bmCodeName.name"/>:</th>
			<td><input id="name" name="name" value="<c:out value='${bmCodeName.name}'/>"/></td>
			<th><span style="color:red">*</span><fmt:message key="bmCodeName.displayNo"/>:</th>
			<td><input id="displayNo" name="displayNo" value="<c:out value='${bmCodeName.displayNo}'/>"/></td>
	      </tr>
	    </tbody>  
	    <tfoot>
		  <tr>
		    <td colspan="6" >
		      <input style="width:50px" type="submit" value="<fmt:message key='button.submit'/>" />
			  <input style="width:50px" type="reset" value="<fmt:message key='button.reset'/>" />
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
