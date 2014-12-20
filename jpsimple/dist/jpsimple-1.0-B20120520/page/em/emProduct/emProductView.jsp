<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/page/inc/taglib.jsp" %>
<html>
<head>
  <title><fmt:message key="emProduct.form.tile"/></title>
  <%@ include file="/page/inc/head.jsp" %>
</head>
<body>
<%@ include file="/page/inc/header.jsp" %>
<div class="main">
  <div id="content" class="dp100 mt1">	
    <form id="emProductForm" action="<c:url value='/servlet/emProduct?action=show'/>" method="post">
      <input type="hidden" name="productId" value="<c:out value='${emProduct.productId}'/>"/>
      <table id="emProductTable" class="table">
  	    <caption><fmt:message key="emProduct.form.tile"/></caption>
  	    <tbody class="formView">
  	      <!--productId,productName,price,publishName,publishDate,imagepath,description,createDateTime,modifyTimestamp,dataState,-->
  	      <tr>
		    <th style="width:15%"><fmt:message key="emProduct.productName"/>:</th>
		    <td style="width:35%"><c:out value='${emProduct.productName}'/></td>
		    <th style="width:15%"><fmt:message key="emProduct.price"/>:</th>
		    <td style="width:35%"><c:out value='${emProduct.price}'/></td>
	      </tr>
	      <tr>
		    <th><fmt:message key="emProduct.publishName"/>:</th>
		    <td><c:out value='${emProduct.publishName}'/></td>
		    <th><fmt:message key="emProduct.publishDate"/>:</th>
		    <td><c:out value='${emProduct.publishDate}'/></td>
	      </tr>
	      <tr>
	        <th><span style="color:red">*</span>product picture:</th>
	      	<td colspan="3" >
	      	  <img name="showimg" id="showimg" src="<c:out value='${emProduct.imagepath}'/>" height="80" width="80"/>
	        </td>  
	      </tr>
	      <tr>
		    <th><fmt:message key="emProduct.description"/>:</th>
		    <td colspan="3">
			  <c:out value='${emProduct.description}' escapeXml="false"/>
		    </td>
	      </tr>
	    </tbody>
	    <tfoot>
		  <tr>
		    <td colspan="6" >
			  <input style="width:50px" type="button" onclick="window.location = '<c:url value='/servlet/emProduct?action=toList'/>'" 
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
