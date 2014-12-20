<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/page/inc/taglib.jsp" %>
<html>
<head>
  <title><fmt:message key="bmMenu.tree.edit"/></title>
  <%@ include file="/page/inc/headjquery.jsp" %>
  <%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    request.setAttribute("basePath", basePath);
  %>
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
	  <tr><td><a href="javascript:addNodes(true);"><fmt:message key="tree.sub.node.add"/></a></td></tr>
	  <tr><td><a href="javascript:addNodes(false);"><fmt:message key="tree.end.node.add"/></a></td></tr>
	  <tr><td><a href="javascript:editNodeName();"><fmt:message key="tree.node.edit"/></a></td></tr>
	  <tr><td><a href="javascript:delNode();"><fmt:message key="tree.node.delete"/></a></td></tr>
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
  asyncUrl: "<c:url value='/servlet/bmMenu?action=getZtreeJsonNodes'/>",
  asyncParam: ["id"],
  asyncParamOther: ["isAllLoad",false],
  root: {isRoot: true,nodes: [{id:'00000000000000000000000000000001', pid:'0', cd:'0', name:'根', isParent:true}]},
  nameCol: "name",
  callback: {
    beforeClick: function(treeId, treeNode){ },
	click: onClick,
	confirmRename: onConfirmRename,
	beforeDrop: onBeforeDrop,
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
		  var newNode = {id:uuid, pid:selectedNode.id, cd:selectedNode.cd, name:"new node", order:1, level:selectedNode.level+1};
	      if (isParent) {
	    	  newNode.isParent = true;
	      } else {
	    	  newNode.isParent = false;
	      }
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
    url: "<c:url value='/servlet/bmMenu?action=saveZtree'/>",
    data: {"newRecordArr":Jp.toStr(newNodeArr), "modifyRecordArr":Jp.toStr(modifyNodeArr), "delNodeIds":delNodeIds},
    type: "POST",
    dataType: "text", //json,xml,html,script,jsonp
	/**
	 complete: function(xmlHttpRequest, textStatus) {
	   if(textStatus=="success") {
	     alert("success:"+xmlHttpRequest.responseText);
	     newNodeArr = [];
	     modifyNodeArr = [];
	     delNodeArr = [];
	   }
	 },
	*/
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
//拖拽后的事件（回调函数）
function onBeforeDrop(treeId, treeNode, targetNode, moveType) {
  var pid = treeNode.pid, cd = treeNode.cd, order = treeNode.order, level = treeNode.level;
  if ("inner" == moveType) {
	pid = targetNode.id;
	cd = targetNode.cd + "01";
	order = 1;
	level = targetNode.level + 1;
  } else if ("before" == moveType) {
	order = targetNode.order - 1;
	level = targetNode.level;
	pid = targetNode.pid;
	if (cd.length != targetNode.cd.length) {
	  cd = targetNode.cd;
	}
  } else if ("after" == moveType) {
	order = targetNode.order + 1;
	level = targetNode.level;
	pid = targetNode.pid;
	if (cd.length != targetNode.cd.length) {
	  cd = targetNode.cd;
	}
  }
  if (newOrgIds.indexOf(treeNode.id)!=-1) {
	Jp.each(newNodeArr, function(){
	  if (treeNode.id==this.id) {
		this.pid = pid;
		this.cd = cd;
		this.order = order;
		this.level = level;
		return;
	  }
    });
  } else {
	for(var index in modifyNodeArr) {
	  if (treeNode.id==modifyNodeArr[index].id) {
	    modifyNodeArr[index].name = treeNode.name;
	    modifyNodeArr[index].pid = pid;
	    modifyNodeArr[index].cd = cd;
	    modifyNodeArr[index].order = order;
	    modifyNodeArr[index].level = level;
	    return;
	  }
    }
    modifyNodeArr.push({id:treeNode.id, pid:pid, cd:cd, name:treeNode.name, order:order, level:level});
  }
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
		modifyNodeArr[index].pid = treeNode.pid;
		modifyNodeArr[index].cd = treeNode.cd;
		modifyNodeArr[index].order = treeNode.order;
		modifyNodeArr[index].level = treeNode.level;
		return;
	  }
	}
	var editNode = {id:treeNode.id, pid:treeNode.pid, cd:treeNode.cd, name:newName, order:treeNode.order, level:treeNode.level};
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
  document.getElementById("iframeContent").src = "<c:url value='/servlet/bmMenu?action=toEdit&menuId='/>" + treeNode.id;
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