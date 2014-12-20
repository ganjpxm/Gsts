<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/page/inc/taglib.jsp" %>
<html>
<head>
  <title><fmt:message key="cmVocabulary.list.tile"/></title>
  <%@ include file="/page/inc/headjquery.jsp" %>
  <style type="text/css">
  	li {list-style: none outside none;}
  </style>
</head>
<body>
  <div id="navigation" class="dp100 mt1 ml2" style="color: #333333;height:20px; font-family: Arial,simsun; font-size:12px; font-weight:bolder;">
  	<div class="dl">
  		Navigation : <c:out value="${navHtml}" escapeXml="false"></c:out>
  	</div>
  	<div id="search" class="dr" style="padding-bottom:5px;padding-right:30px">
	  	<form id="cmVocabularyForm" action="<c:url value='/servlet/cmVocabulary?action=toThumbnail'/>" method="post" OnSubmit="addQueryFilterEl(this)">	
	  	  <input type="hidden" name="navMenuIdsWithComma" value="<c:out value='${navMenuIdsWithComma}'/>"/>
	  	  <input type="hidden" name="selectVocabularyCatagoryId" />
	  	  <input type="hidden" name="vocabularyCatagoryIds" value="<c:out value='${vocabularyCatagoryIds}'/>"/>
	  	  <select id="menuIdNameComb" >  
	  	    <c:forEach var="bmMenu" items="${bmMenus}">
				<option value="<c:out value="${bmMenu.menuId}"/>"
				<c:if test="${bmMenu.menuId == selectVocabularyCatagoryId}">selected="selected"</c:if>
				><c:out value="${bmMenu.menuName}"/></option>
	 		</c:forEach>
	 	  </select>
	 	</form>
 	</div>
  	<div id="hidden" class="dr" style="padding-bottom:5px;padding-right:5px">
	  <input type="checkbox" id="cn" /><label for="cn">chinese</label>
	  <input type="checkbox" id="en" /><label for="en">english</label>
	  <input type="checkbox" id="phonogram" /><label for="phonogram">phonogram</label>
  	</div> 
  </div>
  <div id="content" class="dp100 mt1">
  	<c:choose>
 		<c:when test="${noShowPic==true}">
 			<div class="theme_list">
		      <c:forEach var="model" items="${page.result}">
		      	<div class="content">
		      	  <span class="theme_txt_en_l"><c:out value="${model.foreignLanguage}"/></span>&nbsp;&nbsp;
		      	  <span class="theme_txt_phonogram_l"><c:out value="${model.phonogram}"/></span>
		      	  <img width="16" height="16" title="listen" src="<c:url value='/resource/style/base/image/icon/btn_listen_en.gif'/>"  style="cursor:pointer;">&nbsp;&nbsp;&nbsp;&nbsp;
				  <span class="theme_txt_cn_l"><c:out value="${model.chinese}"/></span>
				   <div class="dr">
					<a id="detail" href="javascript:toDetail('<c:out value='${model.description}'/>')">detail</a></span>
				  </div>
				</div>
			  </c:forEach>
			</ul>
 		</c:when>
	 	<c:otherwise>
	 		<ul class="theme_list">
		      <c:forEach var="model" items="${page.result}">
				<li class="theme_list_box">
				  <div class="theme_photo_l">
					<img width="120" height="80" title="" alt="" src="<c:url value='/servlet/common?action=image&imageName=${model.foreignLanguage}.jpg'/>" +>
				  </div>
				  <ul class="theme_data_l">
					<li class="theme_txt_cn_l">	
						<span style="text-align:top"><c:out value="${model.chinese}"/></span>
						<img width="16" height="16" title="listen" src="<c:url value='/resource/style/base/image/icon/btn_listen.gif'/>"  style="cursor:pointer;">
					</li>
					<li class="theme_txt_en_l">
						<c:out value="${model.foreignLanguage}"/>&nbsp;&nbsp;<span class="theme_txt_phonogram_l"><c:out value="${model.phonogram}"/></span>
						<img width="16" height="16" title="listen" src="<c:url value='/resource/style/base/image/icon/btn_listen_en.gif'/>"  style="cursor:pointer;">
					</li>
					<li><a id="detail" href="javascript:toDetail('<c:out value='${model.description}'/>')">detail</a></li>
				  </ul>
				</li>
			  </c:forEach>
			</ul>
		</c:otherwise>
	</c:choose>
  </div>
  <div id="vocabulary-detail-dialog" title="vocabulary detail information"></div>
  <div id="page">
    <script type="text/javascript">var form = "cmVocabularyForm";</script>
	<%@ include file="/page/inc/simplePage.jsp" %>
  </div>
</body>
</html>
<script type="text/javascript">
<!--
//$(".theme_txt_en_l").hide();
$(".theme_list_box").width(document.body.clientWidth/2-30);
$(".theme_list").width(document.body.clientWidth);
$(".theme_list .content").width(document.body.clientWidth-30);

function addQueryFilterEl(form) {
    form.elements["selectVocabularyCatagoryId"].value=$("#menuIdNameComb").val();
};
$(function() {
  $( "#hidden" ).buttonset();
  $("#hidden :checkbox").click(function() {
	  //$( ".theme_txt_en_l" ).animate({visibility:hidden;}, 1000 );
	  if($(this).attr("id")=="cn") {
		  $(".theme_txt_cn_l").slideToggle(10);
	  } else if($(this).attr("id")=="en") {
		  $(".theme_txt_en_l").slideToggle(10);
	  } else if($(this).attr("id")=="phonogram") {
		  $(".theme_txt_phonogram_l").slideToggle(10);
	  }
  });
  if (totalCount<10) {
	$("#page").hide();
  }
  $.widget( "ui.combobox", {
		_create: function() {
			var self = this,
				select = this.element.hide(),
				selected = select.children( ":selected" ),
				value = selected.val() ? selected.text() : "";
			var input = this.input = $( "<input style='height:30px;'>" )
				.insertAfter( select )
				.val( value )
				.autocomplete({
					delay: 0,
					minLength: 0,
					source: function( request, response ) {
						var matcher = new RegExp( $.ui.autocomplete.escapeRegex(request.term), "i" );
						response( select.children( "option" ).map(function() {
							var text = $( this ).text();
							if ( this.value && ( !request.term || matcher.test(text) ) )
								return {
									label: text.replace(
										new RegExp(
											"(?![^&;]+;)(?!<[^<>]*)(" +
											$.ui.autocomplete.escapeRegex(request.term) +
											")(?![^<>]*>)(?![^&;]+;)", "gi"
										), "<strong>$1</strong>" ),
									value: text,
									option: this
								};
						}) );
					},
					select: function( event, ui ) {
						ui.item.option.selected = true;
						self._trigger( "selected", event, {
							item: ui.item.option
						});
						window.location.href="<c:url value='/servlet/cmVocabulary?action=toThumbnail&vocabularyCatagoryIds" +
								"=${vocabularyCatagoryIds}&navMenuIdsWithComma=${navMenuIdsWithComma}" + 
								"&selectVocabularyCatagoryId='/>"+$("#menuIdNameComb").val();	
					},
					change: function( event, ui ) {
						if ( !ui.item ) {
							var matcher = new RegExp( "^" + $.ui.autocomplete.escapeRegex( $(this).val() ) + "$", "i" ),
								valid = false;
							select.children( "option" ).each(function() {
								if ( $( this ).text().match( matcher ) ) {
									this.selected = valid = true;
									return false;
								}
							});
							if ( !valid ) {
								// remove invalid value, as it didn't match anything
								$( this ).val( "" );
								select.val( "" );
								input.data( "autocomplete" ).term = "";
								return false;
							}
						}
					}
				})
				.addClass( "ui-widget ui-widget-content ui-corner-left" );

			input.data( "autocomplete" )._renderItem = function( ul, item ) {
				return $( "<li></li>" )
					.data( "item.autocomplete", item )
					.append( "<a>" + item.label + "</a>" )
					.appendTo( ul );
			};

			this.button = $( "<button type='button'>&nbsp;</button>" )
				.attr( "tabIndex", -1 )
				.attr( "title", "Show All Items" )
				.insertAfter( input )
				.button({
					icons: {
						primary: "ui-icon-triangle-1-s"
					},
					text: false
				})
				.removeClass( "ui-corner-all" )
				.addClass( "ui-corner-right ui-button-icon" )
				.click(function() {
					// close if already visible
					if ( input.autocomplete( "widget" ).is( ":visible" ) ) {
						input.autocomplete( "close" );
						return;
					}

					// work around a bug (likely same cause as #5265)
					$( this ).blur();

					// pass empty string as value to search for, displaying all results
					input.autocomplete( "search", "" );
					input.focus();
				});
		},

		destroy: function() {
			this.input.remove();
			this.button.remove();
			this.element.show();
			$.Widget.prototype.destroy.call( this );
		}
	});
	
	$("#menuIdNameComb").combobox();
	
	$( "#vocabulary-detail-dialog" ).dialog({autoOpen: false, height: 500, width:400, modal: false});
	//$( "#detail" ).click(function() { $( "#vocabulary-detail-dialog" ).dialog( "open" );});
});
function toDetail(value) {
	$( "#vocabulary-detail-dialog" ).html(value);
	$( "#vocabulary-detail-dialog" ).dialog( "open" );
}
//-->
</script>


