<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/page/inc/taglib.jsp" %>
<html>
<head>
  <title><fmt:message key="amUserDetail.list.tile"/></title>
  <%@ include file="/page/inc/head.jsp" %>
</head>
<body>
<%@ include file="/page/inc/header.jsp" %>
<div class="main">
  <div id="search" class="dp100 mt1">	
    <form id="amUserDetailForm" action="<c:url value='/servlet/amUserDetail?action=add'/>"  method="post">
      <table id="amUserDetailTable" class="table">
  	    <caption><fmt:message key="amUserDetail.form.tile"/></caption>
  	    <tbody class="formEdit">
  	      <!--userDetailId,userId,mobilePhone,homePhone,countryId,provinceId,cityId,birthPlace,livePlace,annualIncome,jobCategoryPid,jobCategoryId,jobPositionId,companyName,shoolName,educationId,interestIds,passwordTipCustom,passwordTipId,passwordTipAnswer,signature,attach,attachName,remark,createDateTime,modifyTimestamp,dataState,-->
	  	  <tr>
		    <th><span style="color:red">*</span><fmt:message key="amUserDetail.fieldName1"/>:</th>
			<td><input id="fieldName1" name="fieldName1"/></td>
			<th><span style="color:red">*</span><fmt:message key="amUserDetail.fieldName2"/>:</th>
			<td><input id="fieldName2" name="fieldName2"/></td>
			<th><span style="color:red">*</span><fmt:message key="amUserDetail.fieldName3"/>:</th>
			<td><input id="fieldName3" name="fieldName3"/></td>
	      </tr>
	    </tbody>  
	    <tfoot>
		  <tr>
		    <td colspan="6" >
		      <input type="submit" value="<fmt:message key='button.submit'/>" />
			  <input type="reset" value="<fmt:message key='button.reset'/>" />
			  <input type="button" onclick="window.location = '<c:url value='/servlet/amUserDetail?action=toList'/>'" 
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
