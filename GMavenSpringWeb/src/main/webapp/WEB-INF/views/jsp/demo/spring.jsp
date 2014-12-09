<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Gan Jianping Spring Web Demo</title>
	<!--
		Used for including CSRF token in JSON requests
		Also see bottom of this file for adding CSRF token to JQuery AJAX requests
	-->
	<meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
</head>
<body>
<h1 style="text-align:center;">Spring Web Demo</h1>
<div style="max-width:800px;margin:5px auto;">
  <h2>Simple</h2>
  <a href="<c:url value='/spring/simple/hello'/>" target="_blank">@RequestMapping("/simple/hello")</a><br/><br/>
  <h2>Request Mapping</h2>
  <a href="<c:url value='/spring/mapping/path/ganjp'/>" target="_blank">@RequestMapping(value="/mapping/path/*", method=RequestMethod.GET)</a><br/><br/>
  <a href="<c:url value='/spring/mapping/parameter'/>" target="_blank">@RequestMapping(value="/mapping/parameter", method=RequestMethod.GET, params!="foo")</a><br/><br/>
  <a id="byHeader" href="<c:url value='/spring/mapping/header'/>" target="_blank">@RequestMapping(value="/mapping/header", method=RequestMethod.GET, headers="FooHeader")</a><br/><br/>
  <form id="byConsumes" class="readJsonForm" action="<c:url value='/spring/mapping/consumes'/>" method="post">
	<input id="byConsumesSubmit" type="submit" value="@RequestMapping(value='/mapping/consumes', method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)" />
  </form><br/><br/>
  <a id="byProducesAcceptJson" class="writeJsonLink" href="<c:url value="/spring/mapping/produces" />">RequestMapping(value="/mapping/produces", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)</a><br/><br/>
  <a id="byProducesAcceptXml" class="writeXmlLink" href="<c:url value="/spring/mapping/produces" />">@RequestMapping(value="/mapping/produces", method=RequestMethod.GET, produces=MediaType.APPLICATION_XML_VALUE)</a><br/><br/>
  <a id="byProducesJsonExt" class="writeJsonLink" href="<c:url value="/spring/mapping/produces.json" />">By produces via ".json"</a><br/><br/>
  <a id="byProducesXmlExt" class="writeXmlLink" href="<c:url value="/spring/mapping/produces.xml" />">By produces via ".xml"</a><br/><br/>
  <h2>Request Data</h2>
  <a id="param" target="_blank" href="<c:url value='/spring/data/param?userName=ganjp'/>">@RequestMapping(value="/data/param", method=RequestMethod.GET), withParam(@RequestParam String userName)</a><br/><br/>
  <a id="group" target="_blank" href="<c:url value='/spring/data/group?firstName=Jian&lastName=Ping'/>">@RequestMapping(value="/data/group", method=RequestMethod.GET), withParamGroup(User user)</a><br/><br/>
  <a id="var" target="_blank" href="<c:url value='/spring/data/path/ganjp'/>">@RequestMapping(value="/data/path/{var}", method=RequestMethod.GET), withPathVariable(@PathVariable String var)</a><br/><br/>
  <a id="matrixVar" target="_blank" href="<c:url value="/spring/data/user;userName=ganjp;userName=xiaogan/simple" />">@RequestMapping(value="/{path}/simple", method=RequestMethod.GET), withMatrixVariable(@PathVariable String path, @MatrixVariable String foo) </a><br/><br/>
  <a id="matrixVarMultiple" target="_blank" href="<c:url value="/spring/data/matrixvars;foo=bar1/multiple;foo=bar2" />">Matrix variables (multiple)</a><br/><br/>
  <a id="header" target="_blank" href="<c:url value='/spring/data/header'/>">@RequestMapping(value="/data/header", method=RequestMethod.GET); withHeader(@RequestHeader String Accept)</a><br/><br/>
  <form id="requestBody" class="textForm" action="<c:url value="/spring/data/body" />" method="post">
	<input id="requestBodySubmit" type="submit" value="Request Body : @RequestMapping(value='/data/body', method=RequestMethod.POST); withBody(@RequestBody String body)" />
  </form><br/><br/>
  <form id="requestBodyAndHeaders" class="textForm" action="<c:url value="/spring/data/entity" />" method="post">
	<input id="requestBodyAndHeadersSubmit" type="submit" value="Request Body and Headers" />
  </form><br/><br/>
  <a id="request" target="_blank" href="<c:url value="/spring/data/standard/request" />">Request arguments : standardRequestArgs(HttpServletRequest request, Principal user, Locale locale)</a><br/><br/>
  <form id="requestReader" class="textForm" action="<c:url value="/spring/data/standard/request/reader" />" method="post">
	<input id="requestReaderSubmit" type="submit" value="Request Reader : requestReader(Reader requestBodyReader)" />
  </form><br/><br/>
  <form id="requestIs" class="textForm" action="<c:url value="/spring/data/standard/request/is" />" method="post">
	<input id="requestIsSubmit" type="submit" value="Request InputStream : requestReader(InputStream requestBodyIs)" />
  </form><br/><br/>
  <a id="response" target="_blank" href="<c:url value="/spring/data/standard/response" />">Response arguments : response(HttpServletResponse response)</a><br/><br/>
  <a id="writer" target="_blank" href="<c:url value="/spring/data/standard/response/writer" />">Response Writer : availableStandardResponseArguments(Writer responseWriter)</a><br/><br/>
  <a id="os" target="_blank" href="<c:url value="/spring/data/standard/response/os" />">Response OutputStream : availableStandardResponseArguments(OutputStream os)</a>	<br/><br/>
  <a id="session" target="_blank" href="<c:url value="/spring/data/standard/session" />">Session</a><br/><br/>
  <h2>Response Writing</h2>
  <a id="responseCharsetAccept" class="utf8TextLink" href="<c:url value="/spring/response/charset/accept" />">@ResponseBody (UTF-8 charset requested)</a><br/><br/>
  <a id="responseCharsetProduce" target="_blank" href="<c:url value="/spring/response/charset/produce" />">@ResponseBody (UTF-8 charset produced)</a><br/><br/>
  <h2>Message Converters</h2>
  <a id="writeForm" target="_blank" href="<c:url value="/spring/messageconverters/form" />">Write Form Data</a><br/><br/>
  <form id="readXml" class="readXmlForm" action="<c:url value="/spring/messageconverters/xml" />" method="post">
	<input id="readXmlSubmit" type="submit" value="Read XML" />		
  </form><br/><br/>
  <a id="writeXmlAccept" class="writeXmlLink" href="<c:url value="/spring/messageconverters/xml" />">Write XML via Accept=application/xml</a><br/><br/>
  <a id="writeXmlExt" class="writeXmlLink" href="<c:url value="/spring/messageconverters/xml.xml" />">Write XML via ".xml"</a><br/><br/>
  <form id="readJson" class="readJsonForm" action="<c:url value="/spring/messageconverters/json" />" method="post">
	<input id="readJsonSubmit" type="submit" value="Read JSON" />	
  </form><br/><br/>
  <form id="readJsonInvalid" class="readJsonForm invalid" action="<c:url value="/spring/messageconverters/json" />" method="post">
	<input id="readInvalidJsonSubmit" type="submit" value="Read invalid JSON (400 response code)" />	
  </form><br/><br/>
  <a id="writeJsonAccept" class="writeJsonLink" href="<c:url value="/spring/messageconverters/json" />">Write JSON via Accept=application/json</a><br/><br/>
  <a id="writeJsonExt" class="writeJsonLink" href="<c:url value="/spring/messageconverters/json.json" />">Write JSON via ".json"</a><br/><br/>
  <form id="readAtom" action="<c:url value="/spring/messageconverters/atom" />" method="post">
	<input id="readAtomSubmit" type="submit" value="Read Atom" />		
  </form><br/><br/>
  <a id="writeAtom" href="<c:url value="/spring/messageconverters/atom" />">Write Atom</a><br/><br/>
  <form id="readRss" action="<c:url value="/messageconverters/rss" />" method="post">
	<input id="readRssSubmit" type="submit" value="Read Rss" />	
  </form><br/><br/>
  <a id="writeRss" href="<c:url value="/spring/messageconverters/rss" />">Write Rss</a><br/><br/>
  <h2>Java Bean Property Binding</h2>
  <a id="primitiveProp" target="_blank" href="<c:url value="/spring/convert/bean?primitive=3" />">Primitive</a><br/><br/>
  <a id="dateProp" target="_blank" href="<c:url value="/spring/convert/bean?date=2010-07-04" />">Date</a><br/><br/>
  <a id="maskedProp" target="_blank" href="<c:url value="/spring/convert/bean?masked=(205) 333-3333" />">Masked</a><br/><br/>
  <a id="listProp" target="_blank" href="<c:url value="/spring/convert/bean?list[0]=1&list[1]=2&list[2]=3" />">List Elements</a><br/><br/>
  <a id="formattedListProp" target="_blank" href="<c:url value="/spring/convert/bean?formattedList[0]=2010-07-04&formattedList[1]=2011-07-04" />">@Formatted List Elements</a><br/><br/>
  <a id="mapProp" target="_blank" href="<c:url value="/spring/convert/bean?map[0]=apple&map[1]=pear" />">Map Elements</a><br/><br/>
  <a id="nestedProp" target="_blank" href="<c:url value="/spring/convert/bean?nested.foo=bar&nested.list[0].foo=baz&nested.map[key].list[0].foo=bip" />">Nested</a><br/><br/>
  <h2>Type Conversion</h2>
  <a id="primitive" target="_blank" href="<c:url value="/spring/convert/primitive?value=3" />">Primitive</a><br/><br/>
  <a id="date" target="_blank" href="<c:url value="/spring/convert/date/2010-07-04" />">Date</a><br/><br/>
  <a id="collection" target="_blank" href="<c:url value="/spring/convert/collection?values=1&values=2&values=3&values=4&values=5" />">Collection 1 (multi-value parameter)</a><br/><br/>
  <a id="collection2" target="_blank" href="<c:url value="/spring/convert/collection?values=1,2,3,4,5" />">Collection 2 (single comma-delimited parameter value)</a><br/><br/>
  <a id="formattedCollection" target="_blank" href="<c:url value="/spring/convert/formattedCollection?values=2010-07-04,2011-07-04" />">@Formatted Collection</a><br/><br/>
  <a id="valueObject" target="_blank" href="<c:url value="/spring/convert/value?value=123456789" />">Custom Value Object</a><br/><br/>
  <a id="customConverter" target="_blank" href="<c:url value="/spring/convert/custom?value=123-45-6789" />">Custom Converter</a><br/><br/>
  <h2>Validation</h2>
  <a id="validateNoErrors" target="_blank" href="<c:url value="/spring/validate?age=3&birthday=2029-07-04" />">Validate, no errors</a><br/><br/>
  <h2>File Upload</h2>
  <div id="fileuploadContent">
  <form id="fileuploadForm" action="<c:url value='/spring/fileupload?${_csrf.parameterName}=${_csrf.token}' />" method="POST" enctype="multipart/form-data" class="cleanform">
	<div class="header">
	  <c:if test="${not empty message}">
		<div id="message" class="success">${message}</div>	  		
	  </c:if>
	</div>
	<label for="file">File</label>
	<input id="file" type="file" name="file" />
	<p><button type="submit">Upload</button></p>		
  </form>
  </div>
  <h2>Exception Handling</h2>
  <a id="exception" target="_blank" href="<c:url value="/spring/exception" />">@ExceptionHandler in Controller</a>
  <h2>Redirecting</h2>
  <a target="_blank" href="<c:url value="/spring/redirect/uriTemplate" />">URI Template String</a>
  <h2>Async Requests</h2>
  <a id="callableResponseBodyLink" class="textLink" href="<c:url value="/spring/async/callable/response-body" />">GET /async/callable/response-body</a><br/><br/>
  <a id="callableViewLink" class="textLink" href="<c:url value="/spring/async/callable/view" />">GET /async/callable/view</a><br/><br/>
  <a id="callableExceptionLink" class="textLink" href="<c:url value="/spring/async/callable/exception" />">GET /async/callable/exception</a><br/><br/>
  <a id="callableUnhandledExceptionLink" class="textLink" 
  	href="<c:url value="/spring/async/callable/exception?handled=false" />"> GET /async/callable/exception?handled=false</a>(500 Error expected)<br/><br/>
  <a id="callableCustomTimeoutLink" class="textLink"
				href="<c:url value="/spring/async/callable/custom-timeout-handling" />">GET /async/callable/custom-timeout-handling</a><br/><br/>
</div>
<script type="text/javascript" src="<c:url value="/resources/jquery/jquery-1.11.1.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/jqueryform/jquery.form-3.51.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/json/json2.js" />"></script>
<script>
MvcUtil = {};
MvcUtil.showSuccessResponse = function (text, element) {
	MvcUtil.showResponse("success", text, element);
};
MvcUtil.showErrorResponse = function showErrorResponse(text, element) {
	MvcUtil.showResponse("error", text, element);
};
MvcUtil.showResponse = function(type, text, element) {
	var responseElementId = element.attr("id") + "Response";
	var responseElement = $("#" + responseElementId);
	if (responseElement.length == 0) {
		responseElement = $('<br/><span id="' + responseElementId + '" class="' + type + '" style="display:none">' + text + '</span>').insertAfter(element);
	} else {
		responseElement.replaceWith('<br/><span id="' + responseElementId + '" class="' + type + '" style="display:none">' + text + '</span>');
		responseElement = $("#" + responseElementId);
	}
	responseElement.fadeIn("slow");
};
MvcUtil.xmlencode = function(xml) {
	//for IE 
	var text;
	if (window.ActiveXObject) {
	    text = xml.xml;
	}
	// for Mozilla, Firefox, Opera, etc.
	else {
	   text = (new XMLSerializer()).serializeToString(xml);
	}			
	return text.replace(/\&/g,'&'+'amp;').replace(/</g,'&'+'lt;')
        .replace(/>/g,'&'+'gt;').replace(/\'/g,'&'+'apos;').replace(/\"/g,'&'+'quot;');
};
</script>
<script type="text/javascript">
$(document).ready(function() {
	$("#byHeader").click(function(){
		var link = $(this);
		$.ajax({ url: this.href, dataType: "text", 
			beforeSend: function(req) { 
				req.setRequestHeader("FooHeader", "foo"); 
			}, success: function(result) { 
				alert("Success : " + result);
			}, error: function(xhr) { 
				alert("Error : " + xhr.responseText);
			}
		});
		return false;
	});
	
	$("form.readJsonForm").submit(function() {
		var form = $(this);
		var button = form.children(":first");
		var data = form.hasClass("invalid") ?
				"{ \"firstName\": \"Jian\" }" : 
				"{ \"firstName\": \"Jian\", \"lastName\": \"Ping\" }";
		$.ajax({ type: "POST", url: form.attr("action"), data: data, contentType: "application/json", dataType: "text", 
			success: function(text) { 
				alert(text); 
			}, 
			error: function(xhr) {
				alert(xhr.responseText); 
			}
		});
		return false;
	});
	
	$("a.writeJsonLink").click(function() {
		var link = $(this);
		$.ajax({ url: this.href,
			beforeSend: function(req) {
				if (!this.url.match(/\.json$/)) {
					req.setRequestHeader("Accept", "application/json");
				}
			},
			success: function(json) {
				alert(JSON.stringify(json));
			},
			error: function(xhr) {
				alert(xhr.responseText);
			}});
		return false;
	});
	
	$("a.writeXmlLink").click(function() {
		var link = $(this);
		$.ajax({ url: link.attr("href"),
			beforeSend: function(req) { 
				if (!this.url.match(/\.xml$/)) {
					req.setRequestHeader("Accept", "application/xml");
				}
			},
			success: function(xml) {
				MvcUtil.showSuccessResponse(MvcUtil.xmlencode(xml), link);
			},
			error: function(xhr) { 
				alert(xhr.responseText);
			}
		});
		return false;
	});
	
	$("a.textLink").click(function(){
		var link = $(this);
		$.ajax({ url: link.attr("href"), dataType: "text", 
			success: function(text) { 
				MvcUtil.showSuccessResponse(text, link); 
			}, 
			error: function(xhr) { 
				MvcUtil.showErrorResponse(xhr.responseText, link); 
			}
		});
		return false;
	});
	
	$("form.textForm").submit(function(event) {
		var form = $(this);
		var button = form.children(":first");
		$.ajax({ type: "POST", url: form.attr("action"), data: "Gan Jianping", contentType: "text/plain", dataType: "text", 
			success: function(text) { 
				MvcUtil.showSuccessResponse(text, button); 
			}, error: function(xhr) { 
				MvcUtil.showErrorResponse(xhr.responseText, button); 
			}
		});
		return false;
	});
	
	$("a.utf8TextLink").click(function(){
		var link = $(this);
		$.ajax({ url: link.attr("href"), dataType: "text", 
			beforeSend: function(req) { 
				req.setRequestHeader("Accept", "text/plain;charset=UTF-8"); 
			}, 
			success: function(text) { 
				MvcUtil.showSuccessResponse(text, link); 
			}, 
			error: function(xhr) { 
				MvcUtil.showErrorResponse(xhr.responseText, link); 
			}
		});
		return false;
	});
	
	$("form.readXmlForm").submit(function() {
		var form = $(this);
		var button = form.children(":first");
		$.ajax({ type: "POST", url: form.attr("action"), 
			data: "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><user><firstName>Xiao</firstName><lastName>Gan</lastName></user>", 
			contentType: "application/xml", dataType: "text", 
			success: function(text) { 
				MvcUtil.showSuccessResponse(text, button); 
			}, 
			error: function(xhr) { 
				MvcUtil.showErrorResponse(xhr.responseText, button); 
			}
		});
		return false;
	});
	
	$("#readAtom").submit(function() {
		var form = $(this);
		var button = form.children(":first");
		$.ajax({ type: "POST", url: form.attr("action"), 
			data: '<?xml version="1.0" encoding="UTF-8"?> <feed xmlns="http://www.w3.org/2005/Atom"><title>My Atom feed</title></feed>', 
			contentType: "application/atom+xml", 
			dataType: "text", 
			success: function(text) { 
				MvcUtil.showSuccessResponse(text, button); 
			}, error: function(xhr) { 
				MvcUtil.showErrorResponse(xhr.responseText, button); 
			}
		});
		return false;
	});
	
	$("#writeAtom").click(function() {
		var link = $(this);
		$.ajax({ url: link.attr("href"),
			beforeSend: function(req) { 
				req.setRequestHeader("Accept", "application/atom+xml");
			},
			success: function(feed) {
				MvcUtil.showSuccessResponse(MvcUtil.xmlencode(feed), link);
			},
			error: function(xhr) { 
				MvcUtil.showErrorResponse(xhr.responseText, link);
			}
		});
		return false;
	});
	
	$("#readRss").submit(function() {
		var form = $(this);
		var button = form.children(":first");
		$.ajax({ type: "POST", url: form.attr("action"), data: '<?xml version="1.0" encoding="UTF-8"?> <rss version="2.0"><channel><title>My RSS feed</title></channel></rss>', contentType: "application/rss+xml", dataType: "text", success: function(text) { MvcUtil.showSuccessResponse(text, button); }, error: function(xhr) { MvcUtil.showErrorResponse(xhr.responseText, button); }});
		return false;
	});

	$("#writeRss").click(function() {
		var link = $(this);	
		$.ajax({ url: link.attr("href"),
			beforeSend: function(req) { 
				req.setRequestHeader("Accept", "application/rss+xml");
			},
			success: function(feed) {
				MvcUtil.showSuccessResponse(MvcUtil.xmlencode(feed), link);
			},
			error: function(xhr) { 
				MvcUtil.showErrorResponse(xhr.responseText, link);
			}
		});
		return false;
	});
	
	$("#fileuploadForm").ajaxForm({ 
		success: function(result) {
			alert(result);
		}
	});
	
	// Include CSRF token as header in JQuery AJAX requests
	// See http://docs.spring.io/spring-security/site/docs/3.2.x/reference/htmlsingle/#csrf-include-csrf-token-ajax
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});
	
});
</script>
</body>
</html>