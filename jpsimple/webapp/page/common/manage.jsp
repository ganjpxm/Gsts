<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/page/inc/taglib.jsp" %>
<html>
<head>
  <title><fmt:message key="global.manage.title"/></title>
  <%@ include file="/page/inc/headjquery.jsp" %>
  <%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    request.setAttribute("basePath", basePath);
  %>
</head>
<body>
<%@ include file="/page/inc/header.jsp" %>
<div class="topBar">
  <div id="topBarLeft" class="left">&nbsp;</div>
  <div id="topBarMiddle" class="middle">&nbsp;</div>
  <div id="topBarRight" class="right">
	<a id="home" href="<c:url value='/servlet/common?action=toIndex'/>">Home</a>
  </div>
</div>
<div class="main">
  <div id="header" class="dp100 title1">
    <div class="content"><fmt:message key="global.manage.title"/></div>
  </div>
  <div id="body" class="dp100">
    <div id="left" class="dl mt1 frameborder">
	  <ul id="tree" class="tree"></ul>
	</div>
	<div id="right" class="dl">
	  <iframe id="iframeContent" src="<c:url value='/page/content.jsp'/>" style="width:100%;">Here is Floating Frame</iframe>
	</div>
  </div>
</div>
</body>
</html>
<script language="javascript">
<!--
var zTreeObj;          //树对象
//------------------------------------异步加载数据------------------------------------
var treeConfig = {
  isSimpleData: true, //为true时,treeNodeKey和treeNodeParentKey都要设置,动态加载适合设置为true，如果是一次性加载需要嵌套json，那么就设置为false
  treeNodeKey: "id",
  treeNodeParentKey: "pid",
  async: true,
  asyncUrl: "<c:url value='/servlet/bmMenu?action=getZtreeJsonNodes'/>",//获取节点数据的URL地址
  asyncParam: ["id"],
  asyncParamOther: ["isAllLoad",false], //其它参数 ( key, value 键值对格式)
  root: {isRoot: true,nodes: [{id:'00000000000000000000000000000001', pid:'0', cd:'0', name:'根', isParent:true}]},
  nameCol: "name",
  callback: {click: onClick}
};
function onClick(event, treeName, treeNode) {
  if (treeNode.isParent) {
    return;
  } else {
    document.getElementById("iframeContent").src = "<c:out value='${basePath}'/>" + treeNode.url;
  }
}

$(document).ready(function(){
  $("#left").width(200);
  $("#left").height(document.body.clientHeight - 85);
  $("#right").width(document.body.clientWidth - 245);
  $("#right").height(document.body.clientHeight - 70);
  $("iframe").height(document.body.clientHeight - 70);
  zTreeObj = $("#tree").zTree(treeConfig);
});

//-->
</script>