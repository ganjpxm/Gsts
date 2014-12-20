<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/page/inc/taglib.jsp" %>
<html>
<head>
  <title><fmt:message key="test.jqgrid.tile"/></title>
  <%@ include file="/page/inc/headjquery.jsp" %>
</head>
<body>
<%@ include file="/page/inc/header.jsp" %>
<ul id="nav">
  <li class="top"><a href="#nogo1" class="top_link"><span>Home</span></a></li>
  <li class="top"><a href="#nogo2" id="products" class="top_link"><span class="down">Products</span></a>
	<ul class="sub">
	  <li><a href="#nogo3" class="fly">Cameras</a>
		<ul>
	      <li><a href="#nogo4">Nikon</a></li>
		  <li><a href="#nogo5">Minolta</a></li>
		</ul>
	  </li>
	  <li class="mid"><a href="#nogo7" class="fly">Lenses</a>
		<ul>
		  <li><a href="#nogo8">Wide Angle</a></li>
		  <li><a href="#nogo11" class="fly">Zoom</a>
			<ul>
			  <li><a href="#nogo12">35 to 125mm</a></li>
			  <li><a href="#nogo13">50 to 250mm</a></li>
			</ul>
		  </li>
		  <li><a href="#nogo15">Mirror</a></li>
		  <li><a href="#nogo16" class="fly">Non standard</a>
			<ul>
			  <li><a href="#nogo17">Bayonet mount</a></li>
			  <li><a href="#nogo18">Screw mount</a></li>
			</ul>
		  </li>
		</ul>
	  </li>
	  <li><a href="#nogo19">Flash Guns</a></li>
	</ul>
  </li>
  <li class="top"><a href="#nogo57" id="privacy" class="top_link"><span>Privacy Policy</span></a></li>
</ul>
<%@ include file="/page/inc/footer.jsp" %>		
</body>
</html>
<script type="text/javascript">
<!--
stuHover = function () {
    var cssRule;
	var newSelector;
	for (var i = 0; i < document.styleSheets.length; i++)
	  for (var x = 0; x < document.styleSheets[i].rules.length ; x++) {
	    cssRule = document.styleSheets[i].rules[x];
		if (cssRule.selectorText.indexOf("LI:hover") != -1) {
		  newSelector = cssRule.selectorText.replace(/LI:hover/gi, "LI.iehover");
		  document.styleSheets[i].addRule(newSelector , cssRule.style.cssText);
		}
	  }
	  var getElm = document.getElementById("nav").getElementsByTagName("LI");
	  for (var i=0; i<getElm.length; i++) {
	    getElm[i].onmouseover=function() {
		this.className+=" iehover";
	  }
	  getElm[i].onmouseout=function() {
		  this.className=this.className.replace(new RegExp(" iehover\\b"), "");
	  }
	}
}
if (window.attachEvent) window.attachEvent("onload", stuHover);
//-->
</script>


	
	