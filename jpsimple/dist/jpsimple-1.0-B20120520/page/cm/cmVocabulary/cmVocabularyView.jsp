<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/page/inc/taglib.jsp" %>
<html>
<head>
  <title><fmt:message key="cmVocabulary.form.tile"/></title>
  <%@ include file="/page/inc/head.jsp" %>
</head>
<body>
<%@ include file="/page/inc/header.jsp" %>
<div class="main">
  <div id="content" class="dp100 mt1">	
    <form id="cmVocabularyForm" action="<c:url value='/servlet/cmVocabulary?action=show'/>" method="post">
      <input type="hidden" name="vocabularyId" value="<c:out value='${cmVocabulary.vocabularyId}'/>"/>
      <table id="cmVocabularyTable" class="table">
  	    <caption><fmt:message key="cmVocabulary.form.tile"/></caption>
  	    <tbody class="formView">
  	      <!--vocabularyId,foreignLanguage,phonogram,chinese,propertyCatagoryIds,vocabularyCatagoryIds,levelId,recommendState,displayNo,description,operatorId,operatorName,createDateTime,modifyTimestamp,dataState,-->
  	      <tr>
		    <th><fmt:message key="cmVocabulary.foreignLanguage"/>:</th>
			<td><c:out value='${cmVocabulary.foreignLanguage}'/></td>
			<th><fmt:message key="cmVocabulary.phonogram"/>:</th>
			<td><c:out value='${cmVocabulary.phonogram}'/></td>
	      </tr>
	      <tr>
	        <th><fmt:message key="cmVocabulary.chinese"/>:</th>
			<td><c:out value='${cmVocabulary.chinese}'/></td>
			<th><fmt:message key="cmVocabulary.propertyCatagoryIds"/>:</th>
			<td><c:out value='${cmVocabulary.propertyCatagoryIds}'/></td>
	      </tr>
	      <tr>
	        <th><fmt:message key="cmVocabulary.vocabularyCatagoryIds"/>:</th>
			<td><c:out value='${cmVocabulary.vocabularyCatagoryIds}'/></td>
			<th><fmt:message key="cmVocabulary.levelId"/>:</th>
			<td><c:out value='${cmVocabulary.levelId}'/></td>
	      </tr>
	      <tr>
	        <th><fmt:message key="cmVocabulary.recommendState"/>:</th>
			<td><c:out value='${cmVocabulary.recommendState}'/></td>
			<th><fmt:message key="cmVocabulary.displayNo"/>:</th>
			<td><c:out value='${cmVocabulary.displayNo}'/></td>
	      </tr>
	      <tr>
	        <th><fmt:message key="cmVocabulary.description"/>:</th>
		    <td colspan="3">
			  <c:out value='${cmVocabulary.description}'/>
		    </td>
	      </tr>
	    </tbody>
	    <tfoot>
		  <tr>
		    <td colspan="4" >
			  <input style="width:50px" type="button" onclick="window.location = '<c:url value='/servlet/cmVocabulary?action=toList'/>'" 
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
