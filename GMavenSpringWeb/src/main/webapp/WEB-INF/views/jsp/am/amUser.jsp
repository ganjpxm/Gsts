<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="author" content="Gan Jianping">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
  <title>User</title>
  <link rel="shortcut icon" href="<c:url value='/resources/jp/image/favicon.png'/>"/>
  <link rel="stylesheet" href="<c:url value='/resources/jp/jp.css'/>">
  <link rel="stylesheet" href="<c:url value='/resources/jp/jp-blue-grey.css'/>">
</head>
<body>
<div id="users" class="jp-box">
  <div class="jp-title-bar">
    <div class="jp-title-bar-text">User</div>
    <div class="jp-title-bar-right-btns">
      <button type="button" class="jp-btn" onclick="add();">Add</button>
      <button id="edit-btn" type="button" class="jp-btn jp-hide" onclick="edit();">Edit</button>
      <button id="delete-btn" type="button" class="jp-btn jp-hide" onclick="del();">Delete</button>
  	  <button id="search-btn" type="button" class="jp-btn jp-hide" onclick="search();">Search</button>
  	</div>
  </div>
  <div class="jp-search-bar">
    <input type="text" id="search" name="search" class="jp-search-input">
  </div>
  <div style="overflow: auto;">
	<table class="jp-table">
	  <thead>
		<tr id="list-head">
		  <th><input id='check-all' name='check-all' type='checkbox' class='jp-check-box' value='all'/></th>
		  <c:forTokens items="${fieldNames}" delims="," var="name">
			<th><c:out value="${name}"/></th>
		  </c:forTokens>
		</tr>
	  </thead>
	  <tbody id="list-items"></tbody>
	</table>
  </div>
</div>
<div id="user" class="jp-box jp-hide">
  <div class="jp-title-bar" style="text-align:center;position:fixed;">
    <div id="user-title" style="padding-top:10px;font-size:18px;"></div>
    <button id="back_btn" type="button" class="jp-btn jp-btn-top-left" onclick="back();">Back</button>
    <button id="save_btn" type="button" class="jp-btn jp-btn-top-right" onclick="save();">Save</button>
  </div>
  <form id="user-form" method="POST">
	<div id="form-fields" class="jp-form-fields">
	  
	</div>
	<div class="jp-center">
	  <button type="button" class="jp-btn jp-btn-bottom" onclick="save();">Save</button>
	</div>
  </form>
</div>
<script type="text/javascript" src="<c:url value="/resources/jquery/jquery-1.11.1.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/jqueryform/jquery.form-3.51.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/json/json2.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/jp/jp.js" />"></script>
<script> 
var mFieldNames = "${fieldNames}".split(",");
var mSelUuids = "";
var mIsAdd = true;
var mRootUrl = "<c:url value='/'/>";
if (mRootUrl.indexOf(";")!=-1) {
  var mRootUrlArr = mRootUrl.split(";");
  mRootUrl = mRootUrlArr[0];
}
var mParamJson = {pageNo:"${pageNo}", pageSize:"${pageSize}"};

function loadUserList(paramJson) {
  $.getJSON("<c:url value='/am/userPage'/>", paramJson, function(page) {
	$("#list-items").html("");
	var data = page.result;
	$.each(data, function(index, map) {
	  var uuid = "";
	  var listTds = [];
	  listTds.push("<td><input name='uuid' type='checkbox' class='jp-check-box' value='" + map['userId'] + "'/></td>");
	  $.each(mFieldNames, function(index, fieldName) {
		var val = map[fieldName];
		if (jp.isEmpty(val)) {
			val = ""; 
		} else {
			if (fieldName.indexOf("Timestamp")!=-1 || fieldName.indexOf("DateTime")!=-1) {
				val = jp.formateFullDateTimeStr(val);
			}
			if (fieldName.indexOf("birthday")!=-1) {
				val = jp.formateDateStr(val);
			}
		}
		listTds.push("<td>" + val + "</td>");
	  });
	  listTds.unshift();
	  $("#list-items").append("<tr>" + listTds.join("") + "</tr>");
	});
	
	$("input[name='uuid']").change(function(){
		mSelUuids = jp.getCheckValues("uuid");
		if (jp.isEmpty(mSelUuids)) {
			$("#edit-btn").hide();
			$("#delete-btn").hide();
		} else {
			$("#edit-btn").show();
			$("#delete-btn").show();
		}
		if (mSelUuids.length > 40) {
			$("#edit-btn").hide();
		}
	});
	$("#check-all").change(function(){
		var value = jp.getCheckValues("check-all");
		$("#edit-btn").hide();
		if (jp.isEmpty(value)) {
			$("#delete-btn").hide();
		} else {
			$("#delete-btn").show();
		}
		jp.checkAll(this,'uuid');
		mSelUuids = jp.getCheckValues("uuid");
	});
	
  });
}

function add() {
  var formFields = [];
  $.each(mFieldNames, function(index, fieldName) {
	if (fieldName == "comment" || fieldName == "content" || fieldName == "remark" || fieldName == "description") {
		formFields.push("<div class='jp-lable'>" + fieldName + "</div>" + 
				"<div><textarea name='" + fieldName + "' class='jp-textare'/>");
	} else {
		formFields.push("<div class='jp-lable'>" + fieldName + "</div>" +
			"<div><input name='" + fieldName + "' class='jp-input'></div>");
	}
  });
  $("#form-fields").html(formFields.join(""));
  $("#users").hide();
  $("#user").show();
  $("#user-title").html("Add User");
  mIsAdd = true;
}
function edit() {
  if (!jp.isEmpty(mSelUuids) && mSelUuids.length==32) {
	  $.getJSON(mRootUrl + "am/user/" + mSelUuids, function(data) {
		  var formFields = [];
		  $.each(mFieldNames, function(index, fieldName) {
			var value = data[fieldName];  
			if (jp.isEmpty(value)) {
				value="";
			} else if (fieldName.indexOf("Timestamp")!=-1 || fieldName.indexOf("DateTime")!=-1) {
				value = jp.formateFullDateTimeStr(value);
			}
			if (fieldName.indexOf("birthday")!=-1) {
				val = jp.formateDateStr(val);
			}
			if (fieldName == "comment" || fieldName == "content" || fieldName == "remark" || fieldName == "description") {
				formFields.push("<div class='jp-lable'>" + fieldName + "</div>" + 
						"<div><textarea name='" + fieldName + "' class='jp-textare'>" + value + "</textarea></div>");
			} else {
				formFields.push("<div class='jp-lable'>" + fieldName + "</div>" + 
					"<div><input name='" + fieldName + "' value='" + value + "' class='jp-input'></div>");
			}
		  });
		  $("#form-fields").html(formFields.join(""));
	  });
	  $("#users").hide();
	  $("#user").show();
	  $("#user-title").html("Edit User");
  } else {
	  alert("Sorry for you must select only one first.");
  }
  mIsAdd = false;
}

function save() {
  var saveUrl = "<c:url value='/am/user'/>";
  if (mIsAdd == false) {
	  saveUrl = mRootUrl + "am/user/" + mSelUuids;
  }
  $("#user-form").ajaxSubmit({
    dataType:  'json',
    url: saveUrl,
	success: function(data) {
	  if (data.result=="success") {
		loadUserList(mParamJson);
		$("#users").show();
		$("#user").hide();  
	  } else {
		alert("Fail");
	  }
    }
  });
}

function del() {
  var urlStr = "<c:url value='/am/user/delete'/>";
  $.ajax({type:"POST", url:urlStr, data: {userIds:mSelUuids}, async:true, dataType:'json', 
	success : function(data) {
	  if (data.result=="success") {
		loadUserList(mParamJson);
		$("#users").show();
		$("#user").hide();  
	  } else {
		alert("Fail");
      }
	} 
  });
}

function back() {
  $("#users").show();
  $("#user").hide();
}

$(document).ready(function() {
  loadUserList(mParamJson);
  
  $('#search').focus( function() {
	 $("#search-btn").show();
  });
  $('#search').blur( function() {
	 if (jp.isEmpty($(this).val())) {
		 $("#search-btn").hide();
	 } else {
		 $("#search-btn").show();
	 }
  });
 
});

</script>
</body>
</html>