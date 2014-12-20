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
  <div id="body" class="dp100 mt1">	
    <form id="bmMenuForm" action="<c:url value='/servlet/bmMenu?action=edit'/>" method="post">
      <input type="hidden" name="menuId" value="<c:out value='${bmMenu.menuId}'/>"/>
      <table id="bmMenuTable" class="table" width="100%">
  	    <caption><fmt:message key="bmMenu.form.tile"/></caption>
  	    <tbody class="formEdit">  
  	       <!--menuId,menuPid,menuCd,menuName,menuType,url,belongUserId,displayLevel,displayNo,endFlag,modifyTimestamp,createDateTime,dataState,-->
	      <tr>
		    <th><span style="color:red">*</span><fmt:message key="bmMenu.menuPid"/>:</th>
			<td><input id="menuPid" name="menuPid" value="<c:out value='${bmMenu.menuPid}'/>"/></td>
			<th><span style="color:red">*</span><fmt:message key="bmMenu.menuCd"/>:</th>
			<td><input id="menuCd" name="menuCd" value="<c:out value='${bmMenu.menuCd}'/>"/></td>
	      </tr>
	      <tr>
	      	<th><span style="color:red">*</span><fmt:message key="bmMenu.menuName"/>:</th>
			<td><input id="menuName" name="menuName" value="<c:out value='${bmMenu.menuName}'/>"/></td>
			<th><span style="color:red">*</span><fmt:message key="bmMenu.displayLevel"/>:</th>
			<td><input id="displayLevel" name="displayLevel" value="<c:out value='${bmMenu.displayLevel}'/>"/></td>
	      </tr>
	      <tr>
			<th><span style="color:red">*</span><fmt:message key="bmMenu.displayNo"/>:</th>
			<td><input id="displayNo" name="displayNo" value="<c:out value='${bmMenu.displayNo}'/>"/></td>
			<th><span style="color:red">*</span><fmt:message key="bmMenu.endFlag"/>:</th>
			<td><input id="url" name="endFlag" value="<c:out value='${bmMenu.endFlag}'/>"/></td>
	      </tr>
	      <tr>
			<th><span style="color:red">*</span><fmt:message key="bmMenu.url"/>:</th>
			<td colspan="3">
				encycolpedia:402838043652ab23013652ae1b4b0001,IT:402838043652ab23013652b113bc0002<br>
				StudyEnglish:402838043652ab23013652b2690c0003<br>
				/servlet/common?action=toArticle&menuId=&articleCategoryId=<c:out value='${bmMenu.menuId}'/><br>
				<textarea id="url" name="url" rows="4" cols="100"><c:out value='${bmMenu.url}'/></textarea>
			</td>
	      </tr> 
	    </tbody>  
	    <tfoot>
		  <tr>
		    <td colspan="4" >
		      <input style="width:50px" type="submit" value="<fmt:message key='button.submit'/>" />
			  <input style="width:50px" type="reset" value="<fmt:message key='button.reset'/>" />
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
