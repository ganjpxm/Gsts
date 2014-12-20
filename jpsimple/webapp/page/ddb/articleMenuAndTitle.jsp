<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/page/inc/taglib.jsp" %>
<html>
<head>
  <title>Reading Pen</title>
  <%@ include file="/page/inc/headjquery.jsp" %>
</head>
<body>
<%@ include file="/page/ddb/header.jsp" %>
<div id="center" class="main">
 	<div id="left" class="dp20 mt1 ">
	   	<div id="accordion">
			<c:out value='${accordionHtml}' escapeXml="false"/>
		</div>
	</div>
	<div id="right" class="dp80 mt1">
	  <iframe id="iframeContent" name="iframeContent" src="<c:url value='/servlet/cmArticle?action=toArticleTitle&articleCategoryIds=${articleCategoryId}'/>"  width="100%" scrolling=no
	  	 onLoad="iFrameHeight()" frameborder="0" >Here is Floating Frame</iframe>
	</div>
</div>
<%@ include file="/page/ddb/footer.jsp" %>
</body>
</html>
<script language="javascript">
<!--
function iFrameHeight() { 
  var ifm= document.getElementById("iframeContent"); 
  var subWeb = document.frames ? document.frames["iframeContent"].document : ifm.contentDocument; 
  if(ifm != null && subWeb != null) { 
	ifm.height = subWeb.body.scrollHeight; 
  } 
} 
$(document).ready(function(){
	var clientWidth = document.body.clientWidth - 0;
	$(".articleTitlesFrame").width((clientWidth-clientWidth*0.16)/2);
	var icons = {header: "ui-icon-circle-arrow-e", headerSelected: "ui-icon-circle-arrow-s"};
	$( "#accordion" ).accordion({
		fillSpace: true, 
		autoHeight: true, 
		navigation: true, 
		collapsible: true, 
		icons: icons
	});//event: "mouseover"click hoverintent
});
//-->
</script>