<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/page/inc/taglib.jsp" %>
<html>
<head>
<title>welcome to youngsun</title>
</head>

<body>
  <iframe id="iframeContent" src="http://www.globalcloudservices.net/Pages/EducationalProduct.aspx" style="width:100%;height:650px;">Here is Floating Frame</iframe>
</body>
</html>
<script language="javascript">
<!--
$(document).ready(function(){
  var clientHeight = document.body.clientHeight;
  $("#iframeContent").height(clientHeight - 15);
});
//-->
</script>