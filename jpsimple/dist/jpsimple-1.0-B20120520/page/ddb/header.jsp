<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<div class="topBar">
  <div id="topBarLeft" class="left">&nbsp;</div>
  <div id="topBarMiddle" class="middle">&nbsp;</div>
  <div id="topBarRight" class="right">
	<a onClick="javascript:addFavorite('http://www.google.com/','google')" href="#">add to bookmark</a>
  </div>
</div>
<div id="example2" class="main80">
	<c:out value='${htmlMenu}' escapeXml="false"/>
</div>
<script language="javascript">
<!--
function addFavorite(sURL, sTitle) {
    try { 
        window.external.addFavorite(sURL, sTitle); 
    } catch (e) { 
        try { 
            window.sidebar.addPanel(sTitle, sURL, ""); 
        } catch (e) { 
        	 alert('Unfortunately, your browser does not support this action,'
                     + ' please bookmark this page manually by pressing Ctrl + D on PC or Cmd + D on Mac.'); 
        } 
    } 
}
$().ready(function() {
	$('.kwicks').kwicks({
		max : 220,
		spacing : 5
	});
});
//-->
</script>