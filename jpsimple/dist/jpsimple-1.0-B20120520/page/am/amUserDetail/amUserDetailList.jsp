<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/page/inc/taglib.jsp" %>
<html>
<head>
  <title><fmt:message key="amUserDetail.list.tile"/></title>
  <%@ include file="/page/inc/head.jsp" %>
</head>
<body>
<%@ include file="/page/inc/header.jsp" %>
<div class="main">
  <div id="search" class="dp100 mt1">
  	<form id="amUserDetailForm" action="<c:url value='/servlet/amUserDetail?action=toList'/>" method="post" OnSubmit="addQueryFilterEl(this)">
  	<input type="hidden" name="queryFilters" value="<c:out value='${amUserDetail.queryFilters}'/>"/>
    <table id="searchTable" class="table">
	  <caption><fmt:message key="lable.search.condition"/></caption>
	  <tbody class="formEdit">
	    <tr>
	      <!--userDetailId,userId,mobilePhone,homePhone,countryId,provinceId,cityId,birthPlace,livePlace,annualIncome,jobCategoryPid,jobCategoryId,jobPositionId,companyName,shoolName,educationId,interestIds,passwordTipCustom,passwordTipId,passwordTipAnswer,signature,attach,attachName,remark,createDateTime,modifyTimestamp,dataState,-->
	      <th><fmt:message key="amUserDetail.fieldName1"/></th>
	  	  <td><input id="fieldName1" name="fieldName1" value="<c:out value='${fieldName1}'/>"/></td>
		  <th><fmt:message key="amUserDetail.fieldName2"/></th>
		  <td><input id="fieldName2" name="fieldName2" value="<c:out value='${fieldName2}'/>"/></td>
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
    <table id="amUserDetailTable" class="table">
      <caption><fmt:message key="amUserDetail.list.tile"/></caption>
	  <thead>
	    <tr>
		  <td colspan="3">
			<button onclick="view();"><fmt:message key="button.view"/></button>
			<button onclick="add();"><fmt:message key="button.add"/></button>
			<button onclick="edit();"><fmt:message key="button.edit"/></button>
			<button onclick="batchDelete();"><fmt:message key="button.delete"/></button>
		  </td>
		</tr>
		<tr>
		  <!--userDetailId,userId,mobilePhone,homePhone,countryId,provinceId,cityId,birthPlace,livePlace,annualIncome,jobCategoryPid,jobCategoryId,jobPositionId,companyName,shoolName,educationId,interestIds,passwordTipCustom,passwordTipId,passwordTipAnswer,signature,attach,attachName,remark,createDateTime,modifyTimestamp,dataState,-->
		  
		  <th style="width:20"><input id="checkHead" onclick="Jp.checkAll(this,'pk')" type="checkbox"/></th>
		  <th style="width:50%"><fmt:message key="amUserDetail.fieldName1"/></th>
		  <th style="width:49%"><fmt:message key="amUserDetail.fieldName2"/></th>
		</tr>
	  </thead>
	  <tbody class="list">
	    <c:forEach var="model" items="${page.result}">
		  <tr>
		    <td><input name="pk" type="checkbox" value="<c:out value='${model.userDetailId}'/>"/></td>
		    <td>
			  <a href="<c:url value='/servlet/amUserDetail?action=toView&userDetailId=${model.userDetailId}'/>">
			    <c:out value="${model.fieldName1}"/>
			  </a>
		    </td>
		    <td><c:out value='${model.fieldName2}'/></td>
          </tr>
	    </c:forEach>
	  </tbody>
	  <tfoot>
	    <tr><td colspan="3"><script type="text/javascript">var form = "amUserDetailForm";</script>
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
		window.location.href="<c:url value='/servlet/amUserDetail?action=toView&userDetailId='/>"+checkValues;	
	}
}
function add() {
	window.location.href="<c:url value='/servlet/amUserDetail?action=toAdd'/>";
}
function edit() {
	var checkValues = Jp.getCheckValues("pk");
	if (Jp.isEdit(checkValues)) {
		window.location.href="<c:url value='/servlet/amUserDetail?action=toEdit&userDetailId='/>"+checkValues;	
	}
}
function reset() {
	window.location.href="<c:url value='/servlet/amUserDetail?action=toList'/>";
}

function batchDelete() {
	var checkValues = Jp.getCheckValues("pk");
	if (Jp.isEmpty(checkValues)) {
		alert(I18N.msg_no_sel_del_record);
	}
	var formEl = document.getElementById("amUserDetailForm");
	addQueryFilterEl(formEl);
	formEl.appendChild(Jp.createHidden("pks", checkValues));
	formEl.appendChild(Jp.createHidden("pageNo", currentPage));
	formEl.appendChild(Jp.createHidden("pageSize", pageSize));
	formEl.action = "<c:url value='/servlet/amUserDetail?action=delete'/>";
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


