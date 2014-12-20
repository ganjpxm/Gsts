<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/page/inc/taglib.jsp" %>
<html>
<head>
  <title><fmt:message key="bmConfig.list.tile"/></title>
  <%@ include file="/page/inc/head.jsp" %>
  <script type="text/javascript" language="javascript" src="<c:url value='/resource/js/tiny_mce/jquery.tinymce.${webConfig["jssuffix"]}'/>"></script>
</head>
<body>
<%@ include file="/page/inc/header.jsp" %>
<div class="main">
  <div id="content" class="dp100 mt1">	
    <form id="bmConfigForm" action="<c:url value='/servlet/bmConfig?action=add'/>"  method="post">
      <table id="bmConfigTable" class="table">
  	    <caption><fmt:message key="bmConfig.form.tile"/></caption>
  	    <tbody class="formEdit">
	  	  <tr>
		    <th><span style="color:red">*</span><fmt:message key="bmConfig.configKey"/>:</th>
			<td><input id="configKey" name="configKey"/></td>
			<th><span style="color:red">*</span><fmt:message key="bmConfig.configKeyDisplayName"/>:</th>
			<td><input id="configKeyDisplayName" name="configKeyDisplayName"/></td>
			<th><span style="color:red">*</span><fmt:message key="bmConfig.configValue"/>:</th>
			<td><input id="configValue" name="configValue"/></td>
	      </tr>
	      <tr>
		    <th><fmt:message key="bmConfig.description"/>:</th>
		    <td colspan="5">
			  <textarea id="description" name="description" class="tinymce" rows="20" cols="100"></textarea>
		    </td>
	      </tr>
	    </tbody>  
	    <tfoot>
		  <tr>
		    <td colspan="6" >
		      <input type="submit" value="<fmt:message key='button.submit'/>" />
			  <input type="reset" value="<fmt:message key='button.reset'/>" />
			  <input type="button" onclick="window.location = '<c:url value='/servlet/bmConfig?action=toList'/>'" 
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
/**
tinyMCE_GZ.init({
    plugins : 'style,layer,table,save,advhr,advimage,advlink,emotions,iespell,insertdatetime,preview,media,searchreplace,print,contextmenu',
    themes : 'simple,advanced',
    languages : 'en',
    disk_cache : true,
    debug : false,
    compress : false
});
*/
$().ready(function() {
	$('textarea.tinymce').tinymce({
		// Location of TinyMCE script
		script_url : "<c:url value='/resource/js/tiny_mce/tiny_mce.${webConfig["jssuffix"]}'/>",

		// General options
		theme : "advanced",
		plugins : "autolink,lists,pagebreak,style,layer,table,save,advhr,advimage,advlink,emotions,iespell,inlinepopups,insertdatetime,preview,media,searchreplace,print,contextmenu,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras,template,advlist",

		// Theme options
		theme_advanced_buttons1 : "save,newdocument,|,bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,styleselect,formatselect,fontselect,fontsizeselect",
		theme_advanced_buttons2 : "cut,copy,paste,pastetext,pasteword,|,search,replace,|,bullist,numlist,|,outdent,indent,blockquote,|,undo,redo,|,link,unlink,anchor,image,cleanup,help,code,|,insertdate,inserttime,preview,|,forecolor,backcolor",
		theme_advanced_buttons3 : "tablecontrols,|,hr,removeformat,visualaid,|,sub,sup,|,charmap,emotions,iespell,media,advhr,|,print,|,ltr,rtl,|,fullscreen",
		theme_advanced_buttons4 : "insertlayer,moveforward,movebackward,absolute,|,styleprops,|,cite,abbr,acronym,del,ins,attribs,|,visualchars,nonbreaking,template,pagebreak",
		theme_advanced_toolbar_location : "top",
		theme_advanced_toolbar_align : "left",
		theme_advanced_statusbar_location : "bottom",
		theme_advanced_resizing : true,

		// Example content CSS (should be your site CSS)
		content_css : "css/content.css",

		// Drop lists for link/image/media/template dialogs
		template_external_list_url : "lists/template_list.js",
		external_link_list_url : "lists/link_list.js",
		external_image_list_url : "lists/image_list.js",
		media_external_list_url : "lists/media_list.js",

		relative_urls : false,
		// Replace values for the template plugin
		template_replace_values : {
			username : "Some User",
			staffid : "991234"
		}
	});
});
	
//-->
</script>
