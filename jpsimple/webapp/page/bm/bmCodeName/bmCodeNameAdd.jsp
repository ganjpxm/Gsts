<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/page/inc/taglib.jsp" %>
<html>
<head>
  <title><fmt:message key="bmCodeName.list.tile"/></title>
  <%@ include file="/page/inc/head.jsp" %>
</head>
<body>
<%@ include file="/page/inc/header.jsp" %>
<div class="main">
  <div id="content" class="dp100 mt1">	
    <form id="bmCodeNameForm" action="<c:url value='/servlet/bmCodeName?action=add'/>"  method="post">
      <table id="bmCodeNameTable" class="table">
  	    <caption><fmt:message key="bmCodeName.form.tile"/></caption>
  	    <tbody class="formEdit">
  	      <!--codeNameId,codeNamePid,code,name,displayNo,displayLevel,endFlag,createDateTime,modifyTimestamp,dataState,-->
	  	  <tr>
		    <th><span style="color:red">*</span><fmt:message key="bmCodeName.code"/>:</th>
			<td><input id="code" name="code"/></td>
			<th><span style="color:red">*</span><fmt:message key="bmCodeName.name"/>:</th>
			<td><input id="name" name="name"/></td>
			<th><span style="color:red">*</span><fmt:message key="bmCodeName.displayNo"/>:</th>
			<td><input id="displayNo" name="displayNo"/></td>
	      </tr>
	    </tbody>  
	    <tfoot>
		  <tr>
		    <td colspan="6" >
		      <input type="submit" value="<fmt:message key='button.submit'/>" />
			  <input type="reset" value="<fmt:message key='button.reset'/>" />
			  <input type="button" onclick="window.location = '<c:url value='/servlet/bmCodeName?action=toList'/>'" 
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
