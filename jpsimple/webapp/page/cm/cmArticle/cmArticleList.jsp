<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/page/inc/taglib.jsp" %>
<html>
<head>
  <title><fmt:message key="cmArticle.list.tile"/></title>
  <%@ include file="/page/inc/head.jsp" %>
</head>
<body>
<%@ include file="/page/inc/header.jsp" %>
<div class="main">
  <div id="search" class="dp100 mt1">
  	<form id="cmArticleForm" action="<c:url value='/servlet/cmArticle?action=toList'/>" method="post" OnSubmit="addQueryFilterEl(this)">
  	<input type="hidden" name="queryFilters" value="<c:out value='${cmArticle.queryFilters}'/>"/>
    <table id="searchTable" class="table">
	  <caption><fmt:message key="lable.search.condition"/></caption>
	  <tbody class="formEdit">
	    <tr>
	      <!--articleId,articleCategoryIds,articleCd,articleTitle,articleContent,commentAuthorId,commentAuthorName,articleRecommendLevelId,displayNo,remark,auditFlag,operatorId,operatorName,createDateTime,modifyTimestamp,dataState,-->
	      <th><fmt:message key="cmArticle.articleTitle"/></th>
	  	  <td><input id="articleTitle" name="articleTitle" value="<c:out value='${articleTitle}'/>"/></td>
		  <th><fmt:message key="cmArticle.articleContent"/></th>
		  <td><input id="articleContent" name="articleContent" value="<c:out value='${articleContent}'/>"/></td>
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
    <table id="cmArticleTable" class="table">
      <caption><fmt:message key="cmArticle.list.tile"/></caption>
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
		  <!--articleId,articleCategoryIds,articleCd,articleTitle,articleContent,commentAuthorId,commentAuthorName,articleRecommendLevelId,displayNo,remark,auditFlag,operatorId,operatorName,createDateTime,modifyTimestamp,dataState,-->
		  <th style="width:20"><input id="checkHead" onclick="Jp.checkAll(this,'pk')" type="checkbox"/></th>
		  <th style="width:99%"><fmt:message key="cmArticle.articleTitle"/></th>
		</tr>
	  </thead>
	  <tbody class="list">
	    <c:forEach var="model" items="${page.result}">
		  <tr>
		    <td><input name="pk" type="checkbox" value="<c:out value='${model.articleId}'/>"/></td>
		    <td>
			  <a href="<c:url value='/servlet/cmArticle?action=toView&articleId=${model.articleId}'/>">
			    <c:out value="${model.articleTitle}"/>
			  </a>
		    </td>
          </tr>
	    </c:forEach>
	  </tbody>
	  <tfoot>
	    <tr><td colspan="3"><script type="text/javascript">var form = "cmArticleForm";</script>
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
		window.location.href="<c:url value='/servlet/cmArticle?action=toView&articleId='/>"+checkValues;	
	}
}
function add() {
	window.location.href="<c:url value='/servlet/cmArticle?action=toAdd&articleCategoryIds=${articleCategoryIds}'/>";
}
function edit() {
	var checkValues = Jp.getCheckValues("pk");
	if (Jp.isEdit(checkValues)) {
		window.location.href="<c:url value='/servlet/cmArticle?action=toEdit&articleId='/>"+checkValues;	
	}
}
function reset() {
	window.location.href="<c:url value='/servlet/cmArticle?action=toList'/>";
}

function batchDelete() {
	var checkValues = Jp.getCheckValues("pk");
	if (Jp.isEmpty(checkValues)) {
		alert(I18N.msg_no_sel_del_record);
	}
	var formEl = document.getElementById("cmArticleForm");
	addQueryFilterEl(formEl);
	formEl.appendChild(Jp.createHidden("pks", checkValues));
	formEl.appendChild(Jp.createHidden("pageNo", currentPage));
	formEl.appendChild(Jp.createHidden("pageSize", pageSize));
	formEl.action = "<c:url value='/servlet/cmArticle?action=delete'/>";
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


