<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/page/inc/taglib.jsp" %>
<html>
<head>
  <title><fmt:message key="bmConfig.form.tile"/></title>
  <%@ include file="/page/inc/head.jsp" %>
  <script type="text/javascript" language="javascript" src="<c:url value='/resource/js/tiny_mce/jquery.tinymce.${webConfig["jssuffix"]}'/>"></script>
</head>
<body>
<%@ include file="/page/inc/header.jsp" %>
<div class="main">
  <div id="search" class="dp100 mt1">	
    <form id="bmConfigForm" action="<c:url value='/servlet/bmConfig?action=edit'/>" method="post">
      <input type="hidden" name="configId" value="<c:out value='${bmConfig.configId}'/>"/>
      <table id="bmConfigTable" class="table">
  	    <caption><fmt:message key="bmConfig.form.tile"/></caption>
  	    <tbody class="formEdit">  
  	      <tr>
		    <th><span style="color:red">*</span><fmt:message key="bmConfig.configKey"/>:</th>
		    <td><input id="configKey" name="configKey" value="<c:out value='${bmConfig.configKey}'/>"/></td>
		    <th><span style="color:red">*</span><fmt:message key="bmConfig.configKeyDisplayName"/>:</th>
		    <td><input id="configKeyDisplayName" name="configKeyDisplayName" value="<c:out value='${bmConfig.configKeyDisplayName}'/>"/></td>
		    <th><span style="color:red">*</span><fmt:message key="bmConfig.configValue"/>:</th>
		    <td><input id="configValue" name="configValue" value="<c:out value='${bmConfig.configValue}'/>"/></td>
	      </tr>
	      <tr>
		    <th><fmt:message key="bmConfig.description"/>:</th>
		    <td colspan="5">
			  <textarea id="description" name="description" class="tinymce" rows="5" cols="100"><c:out value='${bmConfig.description}'/></textarea>
		    </td>
	      </tr>
	    </tbody>  
	    <tfoot>
		  <tr>
		    <td colspan="6" >
		      <input style="width:50px" type="submit" value="<fmt:message key='button.submit'/>" />
			  <input style="width:50px" type="reset" value="<fmt:message key='button.reset'/>" />
			  <input style="width:50px" type="button" onclick="window.location = '<c:url value='/servlet/bmConfig?action=toList'/>'" 
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
