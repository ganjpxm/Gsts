<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/page/inc/taglib.jsp" %>
<html>
<head>
	<title>upload</title>
	<%@ include file="/page/inc/head.jsp" %>
	<script type="text/javascript" language="javascript" src="<c:url value='/resource/js/jquery/plugin/jquery.form.${webConfig["jssuffix"]}'/>"></script>
</head>
<body>
<form id="imageForm" name="imageForm" action="<c:url value='/servlet/common?action=upload'/>" method="post" enctype="multipart/form-data" >
	<div id="upload_form_container">
	  load local image:
	  <input id="image" name="image" type="file"/> &nbsp; &nbsp;
	  <button onclick="ajaxUploadPic()" style="width:50px;height:20px"><fmt:message key='button.submit'/></button>
	</div>
</form>
</body> 
</html> 

<script type="text/javascript"> 
function ajaxUploadPic() { 
  if (Jp.isEmpty($("#image").val())) { 
    alert("please select local image"); 
    return false;
  } else {
	if (Jp.isIE) {
	  $('#imageForm').ajaxSubmit({ 
		success: function(html, status) { 
		  parent.document.getElementById("src").value = "<c:url value='/servlet/common?action=image&imageName='/>" + html;
		  parent.document.getElementById("src").onchange();
		},
		error: function(xmlHttpRequest, textStatus, errorThrown) { 
		  alert(textStatus); 
		}
	  });
	} else {
		$('#imageForm').ajaxForm({ 
		//url : "<c:url value='/servlet/common?action=upload'/>"
		//beforeSubmit: function() { }, 
		//clearForm: true,
		//type : "post",
		complete: function(xmlHttpRequest, textStatus) {
		  if(textStatus=="success") {
			if (parent.document.getElementById("showimg") != null) {
				parent.document.getElementById("showimg").src = "<c:url value='/servlet/common?action=image&imageName='/>" + xmlHttpRequest.responseText;
				parent.document.getElementById("showimg").style.display="";        
				parent.document.getElementById("showimg").border=1;
				parent.document.getElementById("imagepath").value = "<c:url value='/servlet/common?action=image&imageName='/>" + xmlHttpRequest.responseText;
				alert(parent.document.getElementById("imagepath").value);
			} else {
				parent.document.getElementById("src").value = "<c:url value='/servlet/common?action=image&imageName='/>" + xmlHttpRequest.responseText;
			    parent.document.getElementById("src").onchange();	
			}
		  } else {
		    alert(textStatus); 
		  }
	    }
	  });
	}
  }
}
</script>
