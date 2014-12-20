<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/page/inc/taglib.jsp" %>
<html>
<head>
  <title><fmt:message key="emProduct.list.tile"/></title>
  <%@ include file="/page/inc/head.jsp" %>
</head>
<body>
<%@ include file="/page/inc/header.jsp" %>
<div class="main">
  <div id="search" class="dp100 mt1">
  	<form id="emProductForm" action="<c:url value='/servlet/emProduct?action=toList'/>" method="post" OnSubmit="addQueryFilterEl(this)">
  	<input type="hidden" name="queryFilters" value="<c:out value='${emProduct.queryFilters}'/>"/>
    <table id="searchTable" class="table">
	  <caption><fmt:message key="lable.search.condition"/></caption>
	  <tbody class="formEdit">
	    <tr>
	      <!--productCd, productNameCn, productNameEn, price, imagepath,description,createDateTime,modifyTimestamp,dataState,-->
	      <th><fmt:message key="emProduct.productNameCn"/></th>
	  	  <td><input id="productNameCn" name="productNameCn" value="<c:out value='${productNameCn}'/>"/></td>
	  	  <th><fmt:message key="emProduct.productNameEn"/></th>
	  	  <td><input id="productNameEn" name="productNameEn" value="<c:out value='${productNameEn}'/>"/></td>
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
    <table id="emProductTable" class="table">
      <caption><fmt:message key="emProduct.list.tile"/></caption>
	  <thead>
	    <tr>
		  <td colspan="5">
			<button onclick="view();"><fmt:message key="button.view"/></button>
			<button onclick="add();"><fmt:message key="button.add"/></button>
			<button onclick="edit();"><fmt:message key="button.edit"/></button>
			<button onclick="batchDelete();"><fmt:message key="button.delete"/></button>
		  </td>
		</tr>
		<tr>
		  <!--productCd, productNameCn, productNameEn, price, imagepath,description,createDateTime,modifyTimestamp,dataState,-->
		  <th style="width:20"><input id="checkHead" onclick="Jp.checkAll(this,'pk')" type="checkbox"/></th>
		  <th style="width:33%"><fmt:message key="emProduct.productNameCn"/></th>
		  <th style="width:33%"><fmt:message key="emProduct.productNameEn"/></th>
		  <th style="width:33%"><fmt:message key="emProduct.price"/></th>
		</tr>
	  </thead>
	  <tbody class="list">
	    <c:forEach var="model" items="${page.result}">
		  <tr>
		    <td><input name="pk" type="checkbox" value="<c:out value='${model.productId}'/>"/></td>
		    <td>
			  <a href="<c:url value='/servlet/emProduct?action=toView&productId=${model.productId}'/>">
			    <c:out value="${model.productNameCn}"/>
			  </a>
		    </td>
		    <td><c:out value='${model.productNameEn}'/></td>
		    <td><c:out value='${model.price}'/></td>
          </tr>
	    </c:forEach>
	  </tbody>
	  <tfoot>
	    <tr><td colspan="4"><script type="text/javascript">var form = "emProductForm";</script>
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
		window.location.href="<c:url value='/servlet/emProduct?action=toView&productId='/>"+checkValues;	
	}
}
function add() {
	window.location.href="<c:url value='/servlet/emProduct?action=toAdd'/>";
}
function edit() {
	var checkValues = Jp.getCheckValues("pk");
	if (Jp.isEdit(checkValues)) {
		window.location.href="<c:url value='/servlet/emProduct?action=toEdit&productId='/>"+checkValues;	
	}
}
function reset() {
	window.location.href="<c:url value='/servlet/emProduct?action=toList'/>";
}

function batchDelete() {
	var checkValues = Jp.getCheckValues("pk");
	if (Jp.isEmpty(checkValues)) {
		alert(I18N.msg_no_sel_del_record);
	}
	var formEl = document.getElementById("emProductForm");
	addQueryFilterEl(formEl);
	formEl.appendChild(Jp.createHidden("pks", checkValues));
	formEl.appendChild(Jp.createHidden("pageNo", currentPage));
	formEl.appendChild(Jp.createHidden("pageSize", pageSize));
	formEl.action = "<c:url value='/servlet/emProduct?action=delete'/>";
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


