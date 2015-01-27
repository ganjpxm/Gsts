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
  <title>AmUser</title>
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
		<tr id="list-head"></tr>
	  </thead>
	  <tbody id="list-items"></tbody>
	</table>
  </div>
</div>
<div id="user" class="jp-box jp-hide">
  <div class="jp-title-bar" style="text-align:center;position:fixed;">
    <div id="user-title" style="padding-top:10px;font-size:18px;"></div>
    <button id="back_btn" type="button" class="jp-btn" style="float:left;margin-top:-30px;margin-left:6px;" onclick="back();">Back</button>
    <button id="save_btn" type="button" class="jp-btn" style="float:right;margin-top:-30px;margin-right:6px;" onclick="save();">Save</button>
  </div>
  <form id="user-form" method="POST">
	<div id="form-fields" style="padding:5px 16px;margin-top:48px;">
	  
	</div>
	<div class="jp-center">
	  <button type="button" class="jp-btn" style="height:40px;margin:16px;" onclick="save();">Save</button>
	</div>
  </form>
</div>
<script type="text/javascript" src="<c:url value="/resources/jquery/jquery-1.11.1.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/jqueryform/jquery.form-3.51.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/json/json2.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/jp/jp.js" />"></script>
<script>
var rootUrl = "<c:url value='/'/>"; 
var fieldNames = [];
var listThs = [];
var isFirst = true;
var selUuids = "";
var isAdd = true;
if (rootUrl.indexOf(";")!=-1) {
  var rootUrlArr = rootUrl.split(";");
  rootUrl = rootUrlArr[0];
}

function getAmUsers() {
  var paramJson = {};  
  $.getJSON("<c:url value='/am/users'/>", paramJson, function(data) {
	$("#list-items").html("");
	$.each(data, function(index, value) {
	  var uuid = "";
	  var listTds = [];
	  $.each(value, function(key, val) {
		if (isFirst && index==0) {
		  listThs.push("<th>" + key + "</th>");
		  fieldNames.push(key);
		}
		if (jp.isEmpty(val)) {
			val = ""; 
		}
		listTds.push("<td>" + val + "</td>");
		if (key=="userId") {
		  uuid = val;
		}
	  });
	  if (isFirst && index==0) {
		  listThs.unshift("<th><input type='checkbox' style='width:18px;height:18px;vertical-align:middle;' name='uuid' value='all'/></th>");
	  }
	  isFirst = false;
	  listTds.unshift("<td><input type='checkbox' style='width:18px;height:18px;vertical-align:middle;' name='uuid' value='" + uuid + "'/></td>");
	  $("#list-items").append("<tr>" + listTds.join("") + "</tr>");
	});
	$("#list-head").html(listThs.join(""));
	
	$("input[name='uuid']").change(function(){
		selUuids = jp.getCheckValues("uuid");
		if (jp.isEmpty(selUuids)) {
			$("#edit-btn").hide();
			$("#delete-btn").hide();
		} else {
			$("#edit-btn").show();
			$("#delete-btn").show();
		}
		if (selUuids.length > 40) {
			$("#edit-btn").hide();
		}
	});
  });
}

function add() {
  var formFields = [];
  $.each(fieldNames, function(index, value) {
	if (value == "comment" || value == "content" || value == "remark" || value == "description") {
		formFields.push("<div style='padding:5px 5px 5px 0px;font-weight:700'>" + value + "</div><div><textarea name='" 
				+ value + "' style='width:100%;height:32px;padding-left:5px;margin-left:-5px;border:1px solid #CFD8DC;'/>");
	} else {
		formFields.push("<div style='padding:5px 5px 5px 0px;font-weight:700'>" + value + "</div><div><input name='" + value 
				+ "' style='width:100%;height:32px;padding-left:5px;margin-left:-5px;'>");
	}
  });
  $("#form-fields").html(formFields.join(""));
  $("#users").hide();
  $("#user").show();
  $("#user-title").html("Add User");
  isAdd = ture;
}
function edit() {
  if (!jp.isEmpty(selUuids) && selUuids.length==32) {
	  $.getJSON(rootUrl + "am/user/" + selUuids, function(data) {
		  var formFields = [];
		  $.each(data, function(key, value) {
			if (jp.isEmpty(value)) {
				value="";
			}
			if (key == "comment" || key == "content" || key == "remark" || key == "description") {
				formFields.push("<div style='padding:5px 5px 5px 0px;font-weight:700'>" + key + "</div><div><textarea name='" 
						+ key + "' style='width:100%;height:32px;padding-left:5px;margin-left:-5px;border:1px solid #CFD8DC;'>" + value + "</textarea>");
			} else {
				formFields.push("<div style='padding:5px 5px 5px 0px;font-weight:700'>" + key + "</div><div><input name='" + key 
						+ "' value='" + value + "' style='width:100%;height:32px;padding-left:5px;margin-left:-5px;'>");
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
  isAdd = false;
}

function save() {
  var saveUrl = "<c:url value='/am/user'/>";
  if (isAdd == false) {
	  saveUrl = rootUrl + "am/user/" + selUuids;
  }
  $("#user-form").ajaxSubmit({ //beforeSubmit:showRequest,url,type,dataType,clearForm,resetForm,timeout
    dataType:  'json',
    url: saveUrl,
	success: function(data) {
	  if (data.result=="success") {
		getAmUsers();
		$("#users").show();
		$("#user").hide();  
	  } else {
		alert("Fail");
	  }
    }
  });
}

function del() {
  $.ajax({
	type: "POST",
	url: "<c:url value='/am/user/delete'/>",
	contentType: 'application/json',
	data: {userIds:selUuids},
	success: function() {
	  if (data.result=="success") {
		getAmUsers();
		$("#users").show();
		$("#user").hide();
		$("#delete-btn").hide();
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
  getAmUsers();
  
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