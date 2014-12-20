<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
  <title>AmUser</title>
  <meta name="_csrf" content="${_csrf.token}"/>
  <meta name="_csrf_header" content="${_csrf.headerName}"/>
  <style>
    #jp-native table {border-collapse: collapse;}
  	#jp-native td, #jp-native th {border-collapse: collapse; border: 1px solid gray; padding: 5px;}
  </style>
</head>
<body>
<div id="jp-native">
  <div id="btn-bar">
	<button onclick="showAddPanel()">Add</button>
  </div>
  <div id="form-panel" style="display:none;">
   
  </div>
  <table id="list">
	<thead>
	  <tr id="head">
	  </tr>
	</thead>
	<tbody id="body"></tbody>
  </table>
</div>
<script type="text/javascript" src="<c:url value="/resources/jquery/jquery-1.11.1.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/jqueryform/jquery.form-3.51.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/json2.js" />"></script>
<script>
function getAmUsers() {
  var paramJson = {};
  $.getJSON("<c:url value='/am/users'/>", paramJson, function(data) {
	var heads = [];
	var bodys = [];
	$.each(data, function(index, value) {
	  bodys.push("<tr>");
	  $.each(value, function(key, val) {
		if (index == 0) {
		  heads.push("<th>" + key + "</th>");
		}
		if (!val) val = ""; 
		bodys.push("<td>" + val + "</td>");
	  });
	  bodys.push("</tr>");
	});
	$("#head").html(heads.join(""));
	$("#body").html(bodys.join(""));
  });
}
$(document).ready(function() {
  getAmUsers();
});

</script>
</body>
</html>