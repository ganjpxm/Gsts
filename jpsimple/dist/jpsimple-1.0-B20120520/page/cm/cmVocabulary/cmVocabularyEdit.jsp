<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/page/inc/taglib.jsp" %>
<html>
<head>
  <title><fmt:message key="cmVocabulary.form.tile"/></title>
  <%@ include file="/page/inc/head.jsp" %>
  <script type="text/javascript" language="javascript" src="<c:url value='/resource/js/tiny_mce/jquery.tinymce.${webConfig["jssuffix"]}'/>"></script>
</head>
<body>
<%@ include file="/page/inc/header.jsp" %>
<div class="main">
  <div id="content" class="dp100 mt1">	
    <form id="cmVocabularyForm" action="<c:url value='/servlet/cmVocabulary?action=edit'/>" method="post">
      <input type="hidden" name="vocabularyId" value="<c:out value='${cmVocabulary.vocabularyId}'/>"/>
      <table id="cmVocabularyTable" class="table">
  	    <caption><fmt:message key="cmVocabulary.form.tile"/></caption>
  	    <tbody class="formEdit">  
  	       <!--vocabularyId,foreignLanguage,phonogram,chinese,propertyCatagoryIds,vocabularyCatagoryIds,levelId,recommendState,displayNo,description,operatorId,operatorName,createDateTime,modifyTimestamp,dataState,-->
	      <tr>
		    <th><span style="color:red">*</span><fmt:message key="cmVocabulary.foreignLanguage"/>:</th>
			<td><input id="foreignLanguage" name="foreignLanguage" value="<c:out value='${cmVocabulary.foreignLanguage}'/>"/></td>
			<th><span style="color:red">*</span><fmt:message key="cmVocabulary.phonogram"/>:</th>
			<td><input id="phonogram" name="phonogram" value="<c:out value='${cmVocabulary.phonogram}'/>"/></td>
	      </tr>
	      <tr>
	        <th><span style="color:red">*</span><fmt:message key="cmVocabulary.chinese"/>:</th>
			<td><input id="chinese" name="chinese" value="<c:out value='${cmVocabulary.chinese}'/>"/></td>
			<th><span style="color:red">*</span><fmt:message key="cmVocabulary.propertyCatagoryIds"/>:</th>
			<td><input id="propertyCatagoryIds" name="propertyCatagoryIds" value="<c:out value='${cmVocabulary.propertyCatagoryIds}'/>"/></td>
	      </tr>
	      <tr>
	        <th><span style="color:red">*</span><fmt:message key="cmVocabulary.vocabularyCatagoryIds"/>:</th>
			<td><input id="vocabularyCatagoryIds" name="vocabularyCatagoryIds" value="<c:out value='${cmVocabulary.vocabularyCatagoryIds}'/>"/></td>
			<th><span style="color:red">*</span><fmt:message key="cmVocabulary.levelId"/>:</th>
			<td><input id="levelId" name="levelId" value="<c:out value='${cmVocabulary.levelId}'/>"/></td>
	      </tr>
	      <tr>
	        <th><span style="color:red">*</span><fmt:message key="cmVocabulary.recommendState"/>:</th>
			<td><input id="recommendState" name="recommendState" value="<c:out value='${cmVocabulary.recommendState}'/>"/></td>
			<th><span style="color:red">*</span><fmt:message key="cmVocabulary.displayNo"/>:</th>
			<td><input id="displayNo" name="displayNo" value="<c:out value='${cmVocabulary.displayNo}'/>"/></td>
	      </tr>
	      <tr>
	        <th><span style="color:red">*</span><fmt:message key="cmVocabulary.description"/>:</th>
		    <td colspan="3">
			  <textarea id="description" name="description" class="tinymce" rows="20" cols="80"><c:out value='${cmVocabulary.description}'/></textarea>
		    </td>
	      </tr>
	    </tbody>  
	    <tfoot>
		  <tr>
		    <td colspan="4" >
		      <input style="width:50px" type="submit" value="<fmt:message key='button.submit'/>" />
			  <input style="width:50px" type="reset" value="<fmt:message key='button.reset'/>" />
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
$('textarea.tinymce').tinymce({
	script_url : "<c:url value='/resource/js/tiny_mce/tiny_mce.${webConfig["jssuffix"]}'/>",
	theme : "simple",
});
//-->
</script>
