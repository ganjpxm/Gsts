<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/page/inc/taglib.jsp" %>
<html>
<head>
	<title>{#markettoimages_dlg.title}</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script type="text/javascript" src="<c:url value='/resource/js/tiny_mce/tiny_mce_popup.${webConfig["jssuffix"]}'/>"></script>
	<script type="text/javascript" src="<c:url value='/resource/js/tiny_mce/plugins/markettoimages/js/dialog.${webConfig["jssuffix"]}'/>"></script>
	
	<link href="css/dialog.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div id="upload_in_progress" class="upload_infobar"><img src="img/spinner.gif" width="16" height="16" /> {#markettoimages_dlg.upload_in_progress}</div>
<div id="upload_infobar" class="upload_infobar"></div>
<div id="upload_form_container">
  <form id="upl" name="upl" action="<c:url value='/servlet/common?action=upload'/>" method="post" 
     enctype="multipart/form-data" onsubmit="MarkettoImagesDialog.inProgress();" height="50px">
	<p>{#markettoimages_dlg.select_an_image}:</p>
	<p><input id="uploader" name="userfile" type="file" class="text" onChange="document.upl.submit(); MarkettoImagesDialog.inProgress();" />
	 <input type="submit" value="{#markettoimages_dlg.upload}" /></p>
  </form>
</div>
<iframe id="upload_target" name="upload_target" src="<c:url value='/servlet/common?action=getUuid'/>"></iframe>
</body>
</html>
