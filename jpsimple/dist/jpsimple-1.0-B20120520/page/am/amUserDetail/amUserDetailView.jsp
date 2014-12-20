<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/page/inc/taglib.jsp" %>
<html>
<head>
  <title><fmt:message key="amUserDetail.form.tile"/></title>
  <%@ include file="/page/inc/head.jsp" %>
</head>
<body>
<%@ include file="/page/inc/header.jsp" %>
<div class="main">
  <div id="search" class="dp100 mt1">	
    <form id="amUserDetailForm" action="<c:url value='/servlet/amUserDetail?action=show'/>" method="post">
      <input type="hidden" name="userDetailId" value="<c:out value='${amUserDetail.userDetailId}'/>"/>
      <table id="amUserDetailTable" class="table">
  	    <caption><fmt:message key="amUserDetail.form.tile"/></caption>
  	    <tbody class="formView">
  	      <!--userDetailId,userId,mobilePhone,homePhone,countryId,provinceId,cityId,birthPlace,livePlace,annualIncome,jobCategoryPid,jobCategoryId,jobPositionId,companyName,shoolName,educationId,interestIds,passwordTipCustom,passwordTipId,passwordTipAnswer,signature,attach,attachName,remark,createDateTime,modifyTimestamp,dataState,-->
  	      <tr>
		    <th><fmt:message key="amUserDetail.fieldName1"/>:</th>
		    <td><c:out value='${amUserDetail.fieldName1}'/></td>
		    <th><fmt:message key="amUserDetail.fieldName2"/>:</th>
		    <td><c:out value='${amUserDetail.fieldName2}'/></td>
		    <th><fmt:message key="amUserDetail.fieldName3"/>:</th>
		    <td><c:out value='${amUserDetail.fieldName3}'/></td>
	      </tr>
	    </tbody>
	    <tfoot>
		  <tr>
		    <td colspan="6" >
			  <input style="width:50px" type="button" onclick="window.location = '<c:url value='/servlet/amUserDetail?action=toList'/>'" 
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
