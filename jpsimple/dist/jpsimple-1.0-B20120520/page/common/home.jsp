<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/page/inc/taglib.jsp" %>
<html>
<head>
  <title><fmt:message key="index.title"/></title>
  <%@ include file="/page/inc/headjquery.jsp" %>
</head>
<body>
<%@ include file="/page/common/header.jsp" %>
  <div id="content" class="dp100">
  	<c:out value='${htmlContent}' escapeXml="false"/>
  </div>
<%@ include file="/page/common/footer.jsp" %>
</body>
</html>
<script language="javascript">
<!--
$(document).ready(function(){
  $(".articleTitlesFrame").width((clientWidth-clientWidth*0.16)/2);
  $(".articleTitlesFrame").height(200);
  $(".articleTitlesFrame").draggable({start: function() {}, drag: function() {}, stop: function() {}, 
	scroll: false, grid: [ 420,300 ], scroll: true, scrollSensitivity: 100, scrollSpeed: 100, cursor: "move"});
});
//-->
</script>
