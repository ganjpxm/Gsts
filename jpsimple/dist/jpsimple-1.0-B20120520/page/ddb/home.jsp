<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/page/inc/taglib.jsp" %>
<html>
<head>
  <title>Reading Pen</title>
  <%@ include file="/page/inc/headjquery.jsp" %>
</head>
<body>
<%@ include file="/page/ddb/header.jsp" %>
<!-- *************************** 展示广告图片 *************************** -->
<div id="showpic">
	<div class="top">
		<div><img id="pic" class="bigimg"/></div>
		<div class="smallpic">
			<a href="javascript:changeImg(1);" id="num1" class="asmallpic" target="_self">
				<img src="<c:url value='/resource/style/${webConfig["csstheme"]}/image/ddb/num1.gif'/>"/>
			</a>
			<a href="javascript:changeImg(2);" id="num2" class="asmallpic" target="_self">
				<img src="<c:url value='/resource/style/${webConfig["csstheme"]}/image/ddb/num2.gif'/>"/>
			</a>
			<a href="javascript:changeImg(3);" id="num3" class="asmallpic" target="_self">
				<img src="<c:url value='/resource/style/${webConfig["csstheme"]}/image/ddb/num3.gif'/>"/>
			</a>
		</div>
	</div>
	<div id="bottom"></div>
</div>  
<!-- *************************** 自动滚动图片 *************************** -->
<div id="scrollpic">
	<marquee scrollamount="4" scrolldelay="3" valign="middle" behavior="scroll">
		<img border="0" src="<c:url value='/resource/style/${webConfig["csstheme"]}/image/ddb/product1.jpg'/>">
		<img border="0" src="<c:url value='/resource/style/${webConfig["csstheme"]}/image/ddb/book1.jpg'/>">
		<img border="0" src="<c:url value='/resource/style/${webConfig["csstheme"]}/image/ddb/book2.jpg'/>">
	</marquee> 
</div>
<%@ include file="/page/ddb/footer.jsp" %>
</body>
</html>
<script language="javascript">
<!--
//--------------------------图片滚动展示----------------------------
var img1 = new Image();
img1.src = "<c:url value='/resource/style/${webConfig["csstheme"]}/image/ddb/ad_1.jpg'/>";
var img2 = new Image();
img2.src = "<c:url value='/resource/style/${webConfig["csstheme"]}/image/ddb/ad_2.jpg'/>";
var img3 = new Image();
img3.src = "<c:url value='/resource/style/${webConfig["csstheme"]}/image/ddb/ad_3.jpg'/>";
var counts = 3;
var activeImgNum = 1;
var key = 0;
var timeout;
function changeImg(index) {
	if (index) {
		activeImgNum = index;
	  	window.clearInterval(timeout);
	}
	if (key == 0) {
   		key = 1;
  	} else if (document.all) {
   		document.getElementById("pic").filters[0].Apply();
   		document.getElementById("pic").filters[0].Play(duration = 2);
  	}
  	eval('document.getElementById("pic").src=img' + activeImgNum + '.src');
  	for ( var i = 1; i <= counts; i++) {
   		document.getElementById("num" + i).className = 'asmallpic';
  	}
  	document.getElementById("num" + activeImgNum).className = 'asmallpicsel';
  	activeImgNum++;
  	if (activeImgNum > counts) {
   		activeImgNum = 1;
  	}
  	timeout = setTimeout('changeImg()', 2000);//5秒滚动一次
}

changeImg(); 
//-->
</script>
