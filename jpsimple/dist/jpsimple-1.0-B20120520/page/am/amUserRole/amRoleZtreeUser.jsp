<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/page/inc/taglib.jsp" %>
<html>
<head>
  <title><fmt:message key="amUserRole.tree.edit"/></title>
  <%@ include file="/page/inc/headjquery.jsp" %>
</head>
<body>
<%@ include file="/page/inc/header.jsp" %>
<div class="main">
  <div id="body" class="dp100">
	  <div id="left" class="dl frameborder">
	    <div id="treetitle" class="barTitle"><fmt:message key="bmMenu.tree.edit"/></div>
		<div class="buttonBar">
		  <button class="barButton" type="submit" class="icon-save"  onclick="saveNodes()">
		    <fmt:message key="button.save"/>
		  </button> 
		</div>
		<ul id="tree" class="tree"></ul>
	  </div>
	  <div id="right" class="dl">
	     <iframe id="iframeContent" src="<c:url value='/page/content.jsp'/>" style="width:100%;">Here is Floating Frame</iframe>
	  </div>
  </div>
  <div id="menu" style="position:absolute; display:none; background: #f9f9f9;">
    <table>
	  <tr><td><a href="javascript:addNodes(false);"><fmt:message key="amRole.add"/></a></td></tr>
	  <tr><td><a href="javascript:editAmRole();"><fmt:message key="amRole.edit"/></a></td></tr>
	  <tr><td><a href="javascript:delNode();"><fmt:message key="amRole.delete"/></a></td></tr>
	</table>
  </div>
</div>
</body>
</html>
<script language="javascript">
<!--
var zTreeObj;          
var modifyNodeArr = [];
var newNodeArr = [];
var delNodeIds = "";
var newOrgIds = ""; 
var treeConfig = {
  isSimpleData : true,
  treeNodeKey : "id",
  treeNodeParentKey : "pid",
  checkable: false,
  editable: true,
  edit_renameBtn: true,
  edit_removeBtn: true,
  dragCopy: true,
  dragMove: true,
  keepParent: true,
  keepLeaf: true,
  async: true,
  asyncUrl: "<c:url value='/servlet/amRole?action=getZtreeJsonNodes'/>",
  root: {isRoot: true,nodes: [{id:'01', pid:'0', cd:'0', name:'Role', isParent:true}]},
  nameCol: "name",
  callback: {
    beforeClick: function(treeId, treeNode){ },
	click: onClick,
	confirmRename: onConfirmRename,
	remove: onRemoveNode,
	rightClick: onRightClickTree
  }
};
function addNodes(isParent) {
  var selectedNode = zTreeObj.getSelectedNode();
  if (selectedNode) {
    $.ajax({
	  url: "<c:url value='/servlet/common?action=getUuid'/>",
	  type: "POST",
	  dataType: "text", //json,xml,html,script,jsonp
	  success: function(uuid, textStatus) {
		if (Jp.isEmpty(uuid)) {
		  alert(I18N.uuid_get_fail);
		} else {
		  newOrgIds += uuid + ",";
		  var newNode = {id:uuid, cd:selectedNode.cd, name:"new node"};
		  newNodeArr.push(Jp.clone(newNode));
		  zTreeObj.addNodes(selectedNode, [newNode]);
		}  
	  },
	  error: function(xmlHttpRequest, textStatus, errorThrown) { 
			  alert(I18N.uuid_get_fail + textStatu); 
	  }
    });
    //$.get("<c:url value='/servlet/common?action=getUuid'/>", function(uuid){});
  } else {
	alert(I18N.msg_no_sel_node);
  }
}
function editNodeName() {
  var selectedNode = zTreeObj.getSelectedNode();
  if (selectedNode) {
	zTreeObj.inputNodeName(selectedNode);
  } else {
	alert(I18N.msg_no_sel_edit_node);			
  }
}
function delNode() {
  var selectedNode = zTreeObj.getSelectedNode();
  if (selectedNode) {
	zTreeObj.removeNode(selectedNode);
	onRemoveNode(null, selectedNode.id, selectedNode);
  } else {
	alert(I18N.msg_no_sel_del_node);			
  }
}
function saveNodes() {
  $.ajax({
    url: "<c:url value='/servlet/amRole?action=saveZtree'/>",
    data: {"newRecordArr":Jp.toStr(newNodeArr), "modifyRecordArr":Jp.toStr(modifyNodeArr), "delNodeIds":delNodeIds},
    type: "POST",
    dataType: "text", //json,xml,html,script,jsonp
	success: function(result, textStatus) {
	  newNodeArr = [];
	  modifyNodeArr = [];
	  delNodeArr = [];	
	  alert(I18N.msg_save_sucess);
	},
	error: function(xmlHttpRequest, textStatus, errorThrown) { 
	  alert(I18N.msg_save_fail + " : "+ textStatus); 
	}
  });
}
//确定修改名称的事件（回调函数）
function onConfirmRename(treeId, treeNode, newName){
  if (newOrgIds.indexOf(treeNode.id)!=-1) {
	Jp.each(newNodeArr, function(){
	  if (treeNode.id==this.id) {
	    this.name = newName;
	    return;
	  }
    });
  } else {
	for(var index in modifyNodeArr){
	  if(treeNode.id==modifyNodeArr[index].id ) {
	    modifyNodeArr[index].name = newName;
		modifyNodeArr[index].cd = treeNode.cd;
		return;
	  }
	}
	var editNode = {id:treeNode.id, cd:treeNode.cd, name:newName};
	modifyNodeArr.push(Jp.clone(editNode));
  }
}
//删除节点事件
function onRemoveNode(event, treeId, treeNode) {
  for (var index in newNodeArr) {
    if (treeNode.id==newNodeArr[index].id) {
      //alert("remove new:" + newNodeArr[index].id);
	  newNodeArr.splice(index,1);
	  return;
	}
  };
  for (var index in modifyNodeArr) {
    if (treeNode.id==modifyNodeArr[index].id) {
	  //alert("remove modify:"+newNodeArr[index].id);
	  modifyNodeArr.splice(index,1);
	  break;
	}
  }; 
  delNodeIds += treeNode.id + ",";
}
//右击事件（回调函数）
function onRightClickTree(event, treeId, treeNode) {
  if (!treeNode) {
    showMenu("root", event.clientX, event.clientY);
  } else if (treeNode && !treeNode.noR) {
    if (treeNode.newrole && event.target.tagName != "a" && $(event.target).parents("a").length == 0) {
	  showMenu("root", event.clientX, event.clientY);
	} else {
	  showMenu("node", event.clientX, event.clientY);
	}
  }
}
function showMenu(type, x, y) {
  $("#menu").show();
  $("#menu").css({"top":y+"px", "left":x+"px", "display":"block"});
}
function hideMenu() {
  $("#menu").hide();
}

function onClick(event, treeName, treeNode) {
  if (treeNode.id=="01") {
	document.getElementById("iframeContent").src = "<c:url value='/servlet/amUser?action=toList'/>";
  } else {
	document.getElementById("iframeContent").src = "<c:url value='/servlet/amUser?action=toList&treeRoleId='/>" + treeNode.id;
  }
}

function editAmRole() {
  var selectedNode = zTreeObj.getSelectedNode();
  if (selectedNode.id!="01") {
	  document.getElementById("iframeContent").src = "<c:url value='/servlet/amRole?action=toEdit&roleId='/>" + selectedNode.id;	  
  }
}
$(document).ready(function(){
  var clientHeight = document.body.clientHeight;
  var clientWidth = document.body.clientWidth;
  $("#left").width(200);
  $("#left").height(clientHeight - 15);
  $("iframe").height(clientHeight - 10);
  if (Jp.isIE) {
    $("#right").width(clientWidth - 240);
  } else {
    $("#right").width(clientWidth - 235);
  }
  zTreeObj = $("#tree").zTree(treeConfig);
	
  $("#menu").mouseout(function(){
    var e,s;
	//兼容火狐的contains方法
	if(typeof(HTMLElement)!="undefined"){   
	  HTMLElement.prototype.contains = function(obj) {   
	  while(obj!=null && typeof(obj.tagName)!="undefind") {
	    if(obj==this) return  true;    
	      obj=obj.parentNode;
	    }    
	    return false;   
	  };   
    };
    e = arguments.callee.caller.arguments[0] || window.event;//事件
    s = e.toElement || e.relatedTarget;  //事件关联元素
    if(!this.contains(s)) hideMenu();//判断是否子元素，是则不隐藏
  });
});

//-->
</script>