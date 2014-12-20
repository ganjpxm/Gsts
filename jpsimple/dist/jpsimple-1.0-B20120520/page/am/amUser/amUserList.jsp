<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/page/inc/taglib.jsp" %>
<html>
<head>
  <title><fmt:message key="amUser.list.tile"/></title>
  <%@ include file="/page/inc/head.jsp" %>
</head>
<body>
<%@ include file="/page/inc/header.jsp" %>
<div class="main">
  <div id="search" class="dp100 mt1">
  	<form id="amUserForm" action="<c:url value='/servlet/amUser?action=toList'/>" method="post" OnSubmit="addQueryFilterEl(this)">
  	<input type="hidden" name="queryFilters" value="<c:out value='${amUser.queryFilters}'/>"/>
    <table id="searchTable" class="table">
	  <caption><fmt:message key="lable.search.condition"/></caption>
	  <tbody class="formEdit">
	    <tr>
	      <!--userId,userCd,userName,userAlias,userLevel,userScore,loginTimes,password,gender,qq,email,birthYear,birthMonth,birthDay,createDateTime,modifyTimestamp,dataState,-->
	      <th><fmt:message key="amUser.userCd"/></th>
	  	  <td><input id="userCd" name="userCd" value="<c:out value='${userCd}'/>"/></td>
		  <th><fmt:message key="amUser.userName"/></th>
		  <td><input id="userName" name="userName" value="<c:out value='${userName}'/>"/></td>
	    </tr>
	  </tbody>
	  <tfoot>
        <tr>
          <td colspan="4">
            <button type="submit"><fmt:message key="button.search"/></button>
            <button type="reset" onclick="reset()"><fmt:message key="button.reset"/></button>
          </td>
        </tr>
      </tfoot>
	</table>
	</form>
  </div>
  <div id="content" class="dp100 mt1">
    <table id="amUserTable" class="table">
      <caption><fmt:message key="amUser.list.tile"/></caption>
	  <thead>
	    <tr>
		  <td colspan="6">
			<button onclick="view();"><fmt:message key="button.view"/></button>
			<button onclick="add();"><fmt:message key="button.add"/></button>
			<button onclick="edit();"><fmt:message key="button.edit"/></button>
			<button onclick="batchDelete();"><fmt:message key="button.delete"/></button>
		  </td>
		</tr>
		<tr>
		  <!--userId,userCd,userName,userAlias,userLevel,userScore,loginTimes,password,gender,qq,email,birthYear,birthMonth,birthDay,createDateTime,modifyTimestamp,dataState,-->
		  
		  <th style="width:20"><input id="checkHead" onclick="Jp.checkAll(this,'pk')" type="checkbox"/></th>
		  <th style="width:20%"><fmt:message key="amUser.userCd"/></th>
		  <th style="width:20%"><fmt:message key="amUser.userName"/></th>
		  <th style="width:20%"><fmt:message key="amUser.password"/></th>
		  <th style="width:20%"><fmt:message key="amUser.userLevel"/></th>
		  <th style="width:20%"><fmt:message key="amUser.userScore"/></th>
		</tr>
	  </thead>
	  <tbody class="list">
	    <c:forEach var="model" items="${page.result}">
		  <tr>
		    <td><input name="pk" type="checkbox" value="<c:out value='${model.userId}'/>"/></td>
		    <td>
			  <a href="<c:url value='/servlet/amUser?action=toView&userId=${model.userId}'/>">
			    <c:out value="${model.userCd}"/>
			  </a>
		    </td>
		    <td><c:out value='${model.userName}'/></td>
		    <td><c:out value='${model.password}'/></td>
		    <td><c:out value='${model.userLevel}'/></td>
		    <td><c:out value='${model.userScore}'/></td>
          </tr>
	    </c:forEach>
	  </tbody>
	  <tfoot>
	    <tr><td colspan="6"><script type="text/javascript">var form = "amUserForm";</script>
	    <%@ include file="/page/inc/page.jsp" %></td></tr>
	  </tfoot>
	</table>
  </div>
</div>
<%@ include file="/page/inc/footer.jsp" %>
</body>
</html>
<script type="text/javascript">
<!--
function view() {
	var checkValues = Jp.getCheckValues("pk");
	if (Jp.isEdit(checkValues)) {
		window.location.href="<c:url value='/servlet/amUser?action=toView&userId='/>"+checkValues + "&treeRoleId=" + "<c:out value='${treeRoleId}'/>";	
	}
}
function add() {
	window.location.href="<c:url value='/servlet/amUser?action=toAdd&treeRoleId=${treeRoleId}'/>";
}
function edit() {
	var checkValues = Jp.getCheckValues("pk");
	if (Jp.isEdit(checkValues)) {
		window.location.href="<c:url value='/servlet/amUser?action=toEdit&userId='/>"+checkValues + "&treeRoleId=" + "<c:out value='${treeRoleId}'/>";	
	}
}
function reset() {
	window.location.href="<c:url value='/servlet/amUser?action=toAdd&treeRoleId=${treeRoleId}'/>";
}

function batchDelete() {
	var checkValues = Jp.getCheckValues("pk");
	if (Jp.isEmpty(checkValues)) {
		alert(I18N.msg_no_sel_del_record);
	}
	var formEl = document.getElementById("amUserForm");
	addQueryFilterEl(formEl);
	formEl.appendChild(Jp.createHidden("pks", checkValues));
	formEl.appendChild(Jp.createHidden("pageNo", currentPage));
	formEl.appendChild(Jp.createHidden("pageSize", pageSize));
	formEl.action = "<c:url value='/servlet/amUser?action=delete&treeRoleId='/>" + "<c:out value='${treeRoleId}'/>";;
	formEl.submit();
}

function addQueryFilterEl(form) {
	var searchStr = '';
    for (var i = 0; i < form.elements.length; i++) {
        var element = form.elements[i];
        if (element.type == 'text') {
        	//MatchType:EQ, LIKE, LT, GT, LE, GE; PropertyType:S, I, L, N(Double), D(Date), B(Boolean), K(BigDecimal), T(Timestamp);
        	searchStr += 'LIKES_' + element.name + ':' + element.value + ',';
        }
    }
    searchStr = searchStr.substr(0, searchStr.length - 1);
    form.elements["queryFilters"].value=searchStr;
};

//-->
</script>


