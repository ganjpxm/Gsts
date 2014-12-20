<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/page/inc/taglib.jsp" %>
<html>
<head>
  <title><fmt:message key="emProduct.form.tile"/></title>
  <%@ include file="/page/inc/head.jsp" %>
  <script type="text/javascript" language="javascript" src="<c:url value='/resource/js/tiny_mce/jquery.tinymce.${webConfig["jssuffix"]}'/>"></script>
</head>
<body>
<%@ include file="/page/inc/header.jsp" %>
<div class="main">
  <div id="content" class="dp100 mt1">	
    <form id="emProductForm" action="<c:url value='/servlet/emProduct?action=edit'/>" method="post">
      <input type="hidden" name="productId" value="<c:out value='${emProduct.productId}'/>"/>
      <table id="emProductTable" class="table">
  	    <caption><fmt:message key="emProduct.form.tile"/></caption>
  	    <tbody class="formEdit">  
  	       <!--productId,productName,price,publishName,publishDate,imagepath,description,createDateTime,modifyTimestamp,dataState,-->
	  	  <tr>
		    <th><span style="color:red">*</span><fmt:message key="emProduct.productName"/>:</th>
			<td><input id="productName" name="productName" value="<c:out value='${emProduct.productName}'/>"/></td>
			<th><span style="color:red">*</span><fmt:message key="emProduct.price"/>:</th>
			<td><input id="price" name="price" value="<c:out value='${emProduct.price}'/>"/></td>
	      </tr>
	      <tr>
	        <th><span style="color:red">*</span><fmt:message key="emProduct.publishName"/>:</th>
			<td><input id="publishName" name="publishName" value="<c:out value='${emProduct.publishName}'/>"/></td>
			<th><span style="color:red">*</span><fmt:message key="emProduct.publishDate"/>:</th>
			<td><input id="publishName" name="publishDate" value="<c:out value='${emProduct.publishDate}'/>"/></td>
	      </tr>
	      <tr>
	        <th><span style="color:red">*</span>product picture:</th>
	      	<td colspan="3" >
	      	  <iframe id="upload_target" name="upload_target" src="<c:url value='/servlet/common?action=toUpload'/>" style="width:480px;height:30px;"></iframe>
	      	  <img name="showimg" id="showimg" src="<c:out value='${emProduct.imagepath}'/>" height="80" width="80"/>
	      	  <input type="hidden" id="imagepath" name="imagepath" value="<c:out value='${emProduct.imagepath}'/>" />
	        </td>  
	      </tr>
	      <tr>
		    <th><fmt:message key="emProduct.description"/>:</th>
		    <td colspan="3">
			  <textarea id="description" name="description" class="tinymce" rows="23" cols="100"><c:out value='${emProduct.description}'/></textarea>
		    </td>
	      </tr>
	    </tbody>  
	    <tfoot>
		  <tr>
		    <td colspan="4" >
		      <input style="width:50px" type="submit" value="<fmt:message key='button.submit'/>" />
			  <input style="width:50px" type="reset" value="<fmt:message key='button.reset'/>" />
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
function viewmypic(imgfile) {  
  var mypic = document.getElementById("showimg");	
  if (imgfile.value){        
    mypic.src=imgfile.value;        
    mypic.style.display="";        
    mypic.border=1;        
  }
}
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
