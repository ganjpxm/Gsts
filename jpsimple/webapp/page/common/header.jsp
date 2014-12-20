<div class="topBar">
  <div id="topBarLeft" class="left">&nbsp;
  	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
  	 <a id="mobile" href="http://192.168.0.187:8080/jp/page/test/mobile/index.html">mobile</a>
  </div>
  <div id="topBarMiddle" class="middle">&nbsp;</div>
  <div id="topBarRight" class="right">
	<c:choose>
	  <c:when test="${url == null}">
	  	<a id="signIn" href="#">sign in</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="signUp" href="#">sign up</a>
	  </c:when>
	  <c:otherwise>
	  	<a target='_blank' href="<c:out value='${url}'/>"><c:out value='${amUser.userCd}'/></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  	<a href="<c:url value='/servlet/common?action=logout'/>">logout</a>
	  </c:otherwise>
	</c:choose>
  </div>
</div>
<div id="sign-in-dialog" title="sign in">
	<form>
	  <fieldset class="fieldset">
		<label class="dialogLabel">Name</label>
		<input type="text" name="signUpUserCd" id="signInUserCd" class="block text ui-widget-content ui-corner-all" />
		<label class="dialogLabel">Password</label>
		<input type="password" name="signInPassword" id="signInPassword" value="" class="block text ui-widget-content ui-corner-all" />
	  </fieldset>
	</form>
	<p id="signInTips" class="validateTips"></p>
	 </div>
	 <div id="sign-up-dialog" title="sign up">
	<form>
	  <fieldset class="fieldset">
		<label class="dialogLabel">Name</label>
		<input type="text" name="signUpUserCd" id="signUpUserCd" class="text ui-widget-content ui-corner-all" />
		<label class="dialogLabel">Email</label>
		<input type="text" name="signUpEmail" id="signUpEmail" value="" class="text ui-widget-content ui-corner-all" />
		<label class="dialogLabel">Password</label>
		<input type="password" name="signUpPassword" id="signUpPassword" value="" class="text ui-widget-content ui-corner-all" />
	  </fieldset>
	</form>
	<p id="signUpTips" class="validateTips"></p>
</div>
<div class="main95">
  <div id="header" class="dp100 title3"></div>
  <div id="menubar">
  	<c:out value='${htmlMenu}' escapeXml="false"/>
  </div>
  
<script language="javascript">
<!--
stuHover = function () {
    var cssRule;
	var newSelector;
	for (var i = 0; i < document.styleSheets.length; i++)
	  for (var x = 0; x < document.styleSheets[i].rules.length ; x++) {
	    cssRule = document.styleSheets[i].rules[x];
		if (cssRule.selectorText.indexOf("LI:hover") != -1) {
		  newSelector = cssRule.selectorText.replace(/LI:hover/gi, "LI.iehover");
		  document.styleSheets[i].addRule(newSelector , cssRule.style.cssText);
		}
	  }
	  var getElm = document.getElementById("nav").getElementsByTagName("LI");
	  for (var i=0; i<getElm.length; i++) {
	    getElm[i].onmouseover=function() {
		this.className+=" iehover";
	  }
	  getElm[i].onmouseout=function() {
		  this.className=this.className.replace(new RegExp(" iehover\\b"), "");
	  }
	}
}
if (window.attachEvent) window.attachEvent("onload", stuHover);

//---------------------------------------sign up and sign in dialog
var clientWidth = document.body.clientWidth - 0;
var signInWinHeight = 300;
var signUpWinHeight = 350;
if (Jp.isIE) {
  $("#nav li a.top_link span").width((clientWidth-clientWidth*0.1)/8);
  signInWinHeight = 450;
  signUpWinHeight = 600;
} else {
  $("#nav li a.top_link span").width((clientWidth-clientWidth*0.1)/13.5);
}

$(document).ready(function(){
  var signInUserCd = $( "#signInUserCd" ), signInPassword = $( "#signInPassword" ),
	signUpUserCd = $( "#signUpUserCd" ), signUpEmail = $( "#signUpEmail" ), signUpPassword = $( "#signUpPassword" ),
	allFields_signIn = $( [] ).add( signInUserCd ).add( signInPassword ),
	allFields_signUp = $( [] ).add( signUpUserCd ).add( signUpEmail ).add( signUpPassword ),
	signInTips = $( "#signInTips" ),signUpTips = $( "#signUpTips" );
  $("#dialog:ui-dialog").dialog( "destroy" );
  function updateTips(tips, t ) {
    tips.text(t).addClass( "ui-state-highlight" );
    setTimeout(function() {tips.removeClass( "ui-state-highlight", 1500 );}, 500 );
  }
  function checkLength( o, n, min, max, tips ) {
    if ( o.val().length > max || o.val().length < min ) {
	  o.addClass( "ui-state-error" );
	  updateTips(tips, "Length of " + n + " must be between " + min + " and " + max + "." );
	  return false;
    } else {
	  return true;
    }
  }
  function checkRegexp( o, regexp, n, tips ) {
    if ( !( regexp.test( o.val() ) ) ) {
	  o.addClass( "ui-state-error" );
	  updateTips(tips, n );
	  return false;
    } else {
	  return true;
    }
  }
  $( "#sign-in-dialog" ).dialog({
    autoOpen: false,
    height: signInWinHeight,
    width: 350,
    modal: true,
    buttons: {
	  "sign in": function() {
	    var bValid = true;
	    allFields_signIn.removeClass( "ui-state-error" );
	    bValid = bValid && checkLength( signInUserCd, "Name", 3, 16, signInTips);
	    bValid = bValid && checkLength( signInPassword, "Password", 1, 16, signInTips);
	    bValid = bValid && checkRegexp( signInUserCd, /^[a-z]([0-9a-z_])+$/i, "Username may consist of a-z, 0-9, underscores, begin with a letter.", signInTips );
	    bValid = bValid && checkRegexp( signInPassword, /^([0-9a-zA-Z])+$/, "Password field only allow : a-z 0-9", signInTips);
	    if (bValid) {
	      $.ajax({
	    	url: "<c:url value='/servlet/amUser?action=signIn'/>",
	    	data: {"userCd":signInUserCd.val(), "password":signInPassword.val()},
	    	type: "POST",
	    	dataType: "json", //json,xml,html,script,jsonp
	    	success: function(json, textStatus) {
	    	  if (json.result == "fail") {
				alert(json.msg);		    			
	    	  } else {
	    		$("#topBarRight").html("<a target='_blank' href='" + json.url + "'>" + json.userCd + "</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" +
	    		"<a href='<c:url value='/servlet/common?action=logout'/>'>logout</a>");
	    		$("#sign-in-dialog").dialog( "close" );
	    	  }
	    	},
	    	error: function(xmlHttpRequest, textStatus, errorThrown) { 
	    	  alert("login error : "+ textStatus); 
	    	}
	      });
	    }
	  },
	  Cancel: function() { $( this ).dialog( "close" ); }
    },
    close: function() {
	  allFields_signIn.val( "" ).removeClass( "ui-state-error" );
	  signInTips.text("");
    }
  });
  
  $( "#sign-up-dialog" ).dialog({
    autoOpen: false,
    height: signUpWinHeight,
    width: 350,
    modal: true,
    buttons: {
	  "sign up": function() {
	    var bValid = true;
	    allFields_signUp.removeClass( "ui-state-error" );

	    bValid = bValid && checkLength( signUpUserCd, "Name", 3, 16, signUpTips);
	    bValid = bValid && checkLength( signUpEmail, "Email", 6, 80, signUpTips );
        bValid = bValid && checkLength( signUpPassword, "Password", 1, 16, signUpTips );

	    bValid = bValid && checkRegexp( signUpUserCd, /^[a-z]([0-9a-z_])+$/i, "Username may consist of a-z, 0-9, underscores, begin with a letter.", signUpTips );
	    bValid = bValid && checkRegexp( signUpEmail, /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i, "eg. ui@jquery.com", signUpTips );
	    bValid = bValid && checkRegexp( signUpPassword, /^([0-9a-zA-Z])+$/, "Password field only allow : a-z 0-9", signUpTips );

	    if ( bValid ) {
	      $.ajax({
		    url: "<c:url value='/servlet/amUser?action=signUp'/>",
		    data: {"userCd":signUpUserCd.val(), "password":signUpPassword.val(), "email":signUpEmail.val()},
		    type: "POST",
		    dataType: "json", //json,xml,html,script,jsonp
		    success: function(json, textStatus) {
		      if (json.result == "fail") {
				alert(json.msg);		    			
		      } else {
		    	window.location.href="<c:url value='/servlet/common?action=toHome'/>";	  
		      }
		    },
		    error: function(xmlHttpRequest, textStatus, errorThrown) { 
		      alert("login error : "+ textStatus); 
		    }
	      });
	    }
	  },
	  Cancel: function() { $( this ).dialog( "close" ); }
    },
    close: function() {
	  allFields_signUp.val( "" ).removeClass( "ui-state-error" );
	  signUpTips.text("");
    }
  });
  
  $("#signIn").click(function() {
    $("#sign-in-dialog").dialog("open");
  });
  $("#signUp").click(function() {
	$("#sign-up-dialog").dialog("open");
  });
});
//-->
</script>
