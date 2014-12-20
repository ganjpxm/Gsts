<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/page/inc/taglib.jsp" %>
<html>
<head>
  <title><fmt:message key="cmArticle.list.tile"/></title>
  <%@ include file="/page/inc/head.jsp" %>
  <script type="text/javascript" language="javascript" src="<c:url value='/resource/js/tiny_mce/jquery.tinymce.${webConfig["jssuffix"]}'/>"></script>
</head>
<body>
<%@ include file="/page/inc/header.jsp" %>
<div class="main">
  <div id="search" class="dp100 mt1">	
    <form id="cmArticleForm" action="<c:url value='/servlet/cmArticle?action=add'/>"  method="post">
      <table id="cmArticleTable" class="table">
  	    <caption><fmt:message key="cmArticle.form.tile"/></caption>
  	    <tbody class="formEdit">
  	      <!--articleId,articleCategoryIds,articleCd,articleTitle,articleContent,commentAuthorId,commentAuthorName,articleRecommendLevelId,
  	          displayNo,remark,auditFlag,operatorId,operatorName,createDateTime,modifyTimestamp,dataState,-->
	  	  <tr>
		    <th><span style="color:red">*</span><fmt:message key="cmArticle.articleTitle"/>:</th>
			<td colspan="3"><input id="articleTitle" name="articleTitle" style="width:400px"/></td>
	      </tr>
	      <tr>
	      	<th><span style="color:red">*</span><fmt:message key="cmArticle.articleCd"/>:</th>
		  	<td><input id="articleCd" name="articleCd"/></td>
		  	<th><span style="color:red">*</span><fmt:message key="cmArticle.displayNo"/>:</th>
		  	<td><input id="displayNo" name="displayNo"/></td>
		  </tr>
		  <tr>
		    <th><fmt:message key="cmArticle.articleCategoryIds"/>:</th>
		    <td colspan="3">
			  <input id="articleCategoryIds" name="articleCategoryIds" value="<c:out value='${articleCategoryIds}'/>" />
		    </td>
	      </tr>
	      <tr>
		    <th><fmt:message key="cmArticle.articleContent"/>:</th>
		    <td colspan="3">
			  <textarea id="articleContent" name="articleContent" class="tinymce" rows="23" cols="65"></textarea>
		    </td>
	      </tr>
	    </tbody>  
	    <tfoot>
		  <tr>
		    <td colspan="4" >
		      <input type="submit" value="<fmt:message key='button.submit'/>" />
			  <input type="reset" value="<fmt:message key='button.reset'/>" />
			  <input type="button" onclick="window.location = '<c:url value='/servlet/cmArticle?action=toList'/>'" 
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
$().ready(function() {
	$("#articleTitle").width(document.body.clientWidth-200);
	$("#articleCategoryIds").width(document.body.clientWidth-200);
	$('textarea.tinymce').tinymce({
		// Location of TinyMCE script
		script_url : "<c:url value='/resource/js/tiny_mce/tiny_mce.${webConfig["jssuffix"]}'/>",
		// General options
		theme : "advanced",
		plugins : "autolink,lists,style,layer,table,save,advhr,advimage,advlink,emotions,inlinepopups,insertdatetime,preview,media,searchreplace,print,contextmenu,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras,template,advlist",
		// Theme options
		theme_advanced_buttons1 : "save,newdocument,|,bold,italic,underline,strikethrough,forecolor,backcolor,sub,sup,charmap,|,justifyleft,justifycenter,justifyright,justifyfull,|cut,copy,paste,pastetext,|,search,replace,|,bullist,numlist,|,outdent,indent",
		theme_advanced_buttons2 : "undo,redo,|,link,unlink,anchor,cleanup,|,hr,removeformat,visualaid,|,insertdate,inserttime,|,emotions,image,media,advhr,|,formatselect,fontselect,fontsizeselect",
		theme_advanced_buttons3 : "tablecontrols,|,print,|,ltr,rtl,|,insertlayer,moveforward,movebackward,absolute,|,del,ins,pagebreak,template,|,fullscreen,code,preview",
		theme_advanced_toolbar_location : "top",
		theme_advanced_toolbar_align : "left",
		theme_advanced_statusbar_location : "bottom",
		theme_advanced_resizing : true,
		relative_urls : false
	});
});
//-->
</script>
