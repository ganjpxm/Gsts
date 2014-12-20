<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/page/inc/taglib.jsp" %>
<html>
<head>
  <title><fmt:message key="test.jqgrid.tile"/></title>
  <%@ include file="/page/inc/headjquery.jsp" %>
  <style>
	#tabs { height: 200px; } 
	#tabs .ui-tabs-panel { height: 140px; overflow: auto; } 
	.tabs-bottom { position: relative; } 
	.tabs-bottom .ui-tabs-panel { height: 140px; overflow: auto; } 
	.tabs-bottom .ui-tabs-nav { position: absolute !important; left: 0; bottom: 0; right:0; padding: 0 0.2em 0.2em 0; } 
	.tabs-bottom .ui-tabs-nav li { margin-top: -2px !important; margin-bottom: 1px !important; border-top: none; border-bottom-width: 1px; }
	.ui-tabs-selected { margin-top: -3px !important; }
	#button { padding: .5em 1em; text-decoration: none; }
	#effect { width: 240px;  padding: 1em;  font-size: 1.2em; border: 1px solid #000; background: #eee; color: #333; }
	.newClass { text-indent: 40px; letter-spacing: .4em; width: 410px; height: 100px; padding: 30px; margin: 10px; font-size: 1.6em; }
	.ui-button { margin-left: -1px; }
	.ui-button-icon-only .ui-button-text { padding: 0.35em; } 
	.ui-autocomplete-input { margin: 0; padding: 0.48em 0 0.47em 0.45em; }
	.ui-autocomplete { max-height: 50px; overflow-y: auto; overflow-x: hidden;  padding-right: 20px; /* add padding to account for vertical scrollbar */}
 	#log { cursor: move; }
  </style>
</head>
<body>
<%@ include file="/page/inc/header.jsp" %>
<div class="main">
	<div id="body" class="dp100 mt1">
		<label for="tags">Autocomplete Tags: </label><input id="tags" />
		<select id="combobox">
			<option value="">Select one...</option>
			<option value="ActionScript">ActionScript</option>
			<option value="AppleScript">AppleScript</option>
			<option value="Asp">Asp</option>
		</select>
		<div id="log" style="height: 50px; width: 300px; overflow: auto;" class="ui-widget-content dr"></div>
		Date: <input type="text" id="datepicker" size="30"/>&nbsp;&nbsp;<input type="text" id="alternate" size="30"/>&nbsp;&nbsp;
		Format options:
		<select id="format">
			<option value="mm/dd/yy">Default - mm/dd/yy</option>
			<option value="yy-mm-dd">ISO 8601 - yy-mm-dd</option>
			<option value="d M, y">Short - d M, y</option>
			<option value="d MM, y">Medium - d MM, y</option>
			<option value="DD, d MM, yy">Full - DD, d MM, yy</option>
			<option value="'day' d 'of' MM 'in the year' yy">With text - 'day' d 'of' MM 'in the year' yy</option>
		</select>&nbsp;&nbsp;
		<select id="anim">
			<option value="blind">Blind</option>
			<option value="bounce">Bounce</option>
			<option value="clip">Clip</option>
			<option value="drop">Drop</option>
			<option value="explode">Explode</option>
			<option value="fold">Fold</option>
			<option value="highlight">Highlight</option>
			<option value="puff">Puff</option>
			<option value="pulsate">Pulsate</option>
			<option value="scale">Scale</option>
			<option value="shake">Shake</option>
			<option value="size">Size</option>
			<option value="slide">Slide</option>
		</select><button id="effectHideBtn">Effect Hide</button>
		<label for="from">From</label>
		<input type="text" id="from" name="from"/>
		<label for="to">to</label>
		<input type="text" id="to" name="to"/>
	</div>
	<div id="innerdatepicker"></div>
	<div id="tabs"><!-- class="tabs-bottom" -->
		<ul>
			<li><a href="#tabs-1">Nunc tincidunt</a></li>
			<li><a href="<c:url value='/servlet/test?action=base'/>">Proin dolor</a></li>
			<li><a href="#tabs-3">Aenean lacinia</a></li>
		</ul>
		<div id="tabs-1">
			<p>Proin elit arcu, rutrum commodo, vehicula tempus, commodo a, risus. Curabitur nec arcu. lectus.</p>
		</div>
		<div id="tabs-3">
			<p>Mauris eleifend est et turpis. Duis id erat. </p>
			<p>Duis cursus. Maecenas ligula eros, blandit nec, pharetra at, semper at, magna. Nullam ac lacus.</p>
		</div>
   	</div>
   	<div id="accordionResizer" style="padding:10px; width:350px; height:220px;" class="ui-widget-content">
	   	<div id="accordion">
			<h3><a href="#">Section 1</a></h3>
			<div><p>Mauris mauris ante, blandit et, ultrices a, suscipit eget, quam. Integer ut neque.</p>
				 <ul><li>List item</li><li>List item</li><li>List item</li><li>List item</li><li>List item</li><li>List item</li></ul></div>
			<h3><a href="#">Section 2</a></h3>
			<div><p> Sed non urna. Donec et ante. Phasellus eu ligula. Vestibulum sit amet purus. </p></div>
			<h3><a href="#">Section 3</a></h3>
			<div><p>Nam enim risus, molestie et, porta ac, aliquam ac, risus. Quisque lobortis.Phasellus pellentesque purus in massa. </p></div>
			<h3><a href="#">Section 4</a></h3>
			<div><p>Cras dictum. Pellentesque habitant morbi tristique senectus et netuset malesuada fames ac turpis egestas. </p></div>
		</div>
		<span class="ui-icon ui-icon-grip-dotted-horizontal" style="margin:2px auto;"></span>
	</div>	
	<div id="effect" class="ui-corner-all">
		Etiam libero neque, luctus a, eleifend nec, semper at, lorem. Sed pede.
	</div>
   	<div class="button">
   		<button id="changeClassBtn">Change Class</button>
   		<button id="toggle">Toggle icons</button>
	   	<button id="dialogBtn">dialog button</button>&nbsp;&nbsp;
	   	<button id="dialogModalBtn">dialog-modal button</button>&nbsp;&nbsp;
	   	<button id="dialogMessageBtn">dialog message button</button>&nbsp;&nbsp;
	   	<button id="dialogConfirmBtn">dialog confirm button</button>&nbsp;&nbsp;
	   	<button id="dialogFormBtn">dialog form button</button>&nbsp;&nbsp;
	   	<input type="submit" value="A submit button"/>&nbsp;&nbsp;
	   	<a href="#">An anchor</a>
	   	<input type="checkbox" id="check" /><label for="check">checkbox Toggle</label>
	   	<button id="rerun">Run last action</button>
		<button id="select">Select an action</button>
	   	<div id="checkbox">
			<input type="checkbox" id="check1" /><label for="check1">checkbox B</label>
			<input type="checkbox" id="check2" /><label for="check2">checkbox I</label>
			<input type="checkbox" id="check3" /><label for="check3">checkbox U</label>
		</div>
	   	<div id="radio">
	   		<input type="radio" id="radio1" name="radio" /><label for="radio1">radio 1</label>
			<input type="radio" id="radio2" name="radio" checked="checked" /><label for="radio2">radio 2</label>
			<input type="radio" id="radio3" name="radio" /><label for="radio3">radio 3</label>	
	   	</div>
	   	
	   	<span id="toolbar" class="ui-widget-header ui-corner-all" style="padding: 10px 4px;">
			<button id="beginning">go to beginning</button>
			<button id="rewind">rewind</button>
			<button id="play">play</button>
			<button id="stop">stop</button>
			<button id="forward">fast forward</button>
			<button id="end">go to end</button>
			<input type="checkbox" id="shuffle" /><label for="shuffle">Shuffle</label>
			<span id="repeat">
				<input type="radio" id="repeat0" name="repeat" checked="checked" /><label for="repeat0">No Repeat</label>
				<input type="radio" id="repeat1" name="repeat" /><label for="repeat1">Once</label>
				<input type="radio" id="repeatall" name="repeat" /><label for="repeatall">All</label>
			</span>
		</span>
	</div>
   	<div id="dialog" title="Basic dialog">
		<p>This is the default dialog which is useful for displaying information. The dialog window can be moved, resized and closed with the 'x' icon.</p>
	</div>
	<div id="dialog-modal" title="Basic modal dialog">
		<p>Adding the modal overlay screen makes the dialog look more prominent because it dims out the page content.</p>
	</div>
	<div id="dialog-message" title="Download complete">
		<p>Your files have downloaded successfully into the My Downloads folder.</p>
		<p>Currently using <b>36% of your storage space</b>.</p>
	</div>
	<div id="dialog-confirm" title="Empty the recycle bin?">
		<p><span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>
		These items will be permanently deleted and cannot be recovered. Are you sure?</p>
	</div>
	<div id="products">
		<h1 class="ui-widget-header">Products</h1>	
		<div id="catalog">
			<h3><a href="#">T-Shirts</a></h3>
			<div>
				<ul>
					<li>Lolcat Shirt</li>
					<li>Cheezeburger Shirt</li>
					<li>Buckit Shirt</li>
				</ul>
			</div>
			<h3><a href="#">Bags</a></h3>
			<div>
				<ul>
					<li>Zebra Striped</li>
					<li>Black Leather</li>
					<li>Alligator Leather</li>
				</ul>
			</div>
			<h3><a href="#">Gadgets</a></h3>
			<div>
				<ul>
					<li>iPhone</li>
					<li>iPod</li>
					<li>iPad</li>
				</ul>
			</div>
		</div>
	</div>

	<div id="cart">
		<h1 class="ui-widget-header">Shopping Cart</h1>
		<div class="ui-widget-content">
			<ol>
				<li class="placeholder">Add your items here</li>
			</ol>
		</div>
	</div>
</div>
<%@ include file="/page/inc/footer.jsp" %>		
</body>
</html>
<script type="text/javascript">
<!--
$.fx.speeds._default = 1000;
$(function() {
	//---------------------------------------------auto complete--------------------------------------
	var availableTags = ["ActionScript","AppleScript","Asp","BASIC","C","C++","Clojure","COBOL","ColdFusion",
			"Erlang","Fortran","Groovy","Haskell","Java","JavaScript","Lisp","Perl","PHP","Python","Ruby","Scala","Scheme"];
	var data = [{ label: "anders", category: "" }, { label: "andreas", category: "" },{ label: "antal", category: "" },
				{ label: "annhhx10", category: "Products" }, { label: "annk K12", category: "Products" }, 
				{ label: "anders andersson", category: "People" },{ label: "andreas andersson", category: "People" }];
	function log( message ) {$( "<div/>" ).text( message ).prependTo( "#log" );$( "#log" ).scrollTop( 0 );}
	$( "#tags" ).autocomplete({source: availableTags, minLength: 2, 
		select: function( event, ui ) { log( ui.item ? "Selected: " + ui.item.value + " aka " + ui.item.id : "Nothing selected, input was " + this.value ); }});
	//---------------------------------------------change class--------------------------------------
	//$( "#changeClassBtn" ).click(function() { $( "#effect" ).addClass( "newClass", 1000, callback );return false; });
	//function callback() { setTimeout(function() { $( "#effect" ).removeClass( "newClass" );}, 1500 ); }
	$( "#changeClassBtn" ).toggle( 
		function() {$( "#effect" ).animate({backgroundColor: "#aa0000", color: "#fff", width: 500}, 1000 );}, 
		function() {$( "#effect" ).animate({backgroundColor: "#fff", color: "#000", width: 240}, 1000 );}
	);
	//------------------------------draggable-----------------------
	var count = 0;
	$( "#log" ).draggable({start: function() {log(count++);}, drag: function() {log(count++);}, stop: function() {log(count++);}, 
		axis: "x", containment: "#body", scroll: false, grid: [ 80,80 ], scroll: true, scrollSensitivity: 100, scrollSpeed: 100, 
		revert: false, cursor: "move",  handle: "p"});
	
	//---------------------------------------------datepicker--------------------------------------
	$( "#datepicker" ).datepicker({
		altField: "#alternate",
		altFormat: "DD, d MM, yy",
		
		showButtonPanel: true,
		
		changeMonth: true,
		changeYear: true,
		
		numberOfMonths: 2,
		
		showWeek: true,
		firstDay: 1,
		
		showOn: "button",
		buttonImage: "<c:url value='/resource/style/base/image/icon/calendar.gif'/>",
		buttonImageOnly: true,
		
		minDate: -20, 
		maxDate: "+1Y +1M +10D" 
	});
	$( "#innerdatepicker" ).datepicker();
	$( "#format" ).change(function() {
		$( "#datepicker" ).datepicker( "option", "dateFormat", $( this ).val() );
	});
	$( "#anim" ).change(function() {
		$( "#datepicker" ).datepicker( "option", "showAnim", $( this ).val() );
	});
	var dates = $( "#from, #to" ).datepicker({
		defaultDate: "+1w",
		changeMonth: true,
		numberOfMonths: 3,
		onSelect: function( selectedDate ) {
			var option = this.id == "from" ? "minDate" : "maxDate",
				instance = $( this ).data( "datepicker" ),
				date = $.datepicker.parseDate(instance.settings.dateFormat || $.datepicker._defaults.dateFormat, 
						selectedDate, instance.settings );
			dates.not( this ).datepicker( "option", option, date );
		}
	});
	//---------------------------------------------tabs--------------------------------------
	$("#tabs").tabs({
		event: "mouseover",
		collapsible: true
		//cookie: {expires: 1}// store cookie for a day, without, it would be a session cookie
	}).find( ".ui-tabs-nav" ).sortable({ axis: "x" });
	//$(".tabs-bottom .ui-tabs-nav, .tabs-bottom .ui-tabs-nav > *").removeClass("ui-corner-all ui-corner-top").addClass("ui-corner-bottom");
	//---------------------------------------------accordion--------------------------------------
	var icons = {header: "ui-icon-circle-arrow-e", headerSelected: "ui-icon-circle-arrow-s"};
	$( "#accordion" ).accordion({fillSpace: true, autoHeight: false, navigation: true, collapsible: true, icons: icons});//event: "mouseover"click hoverintent
	$( "#accordionResizer" ).resizable({minHeight: 140, resize: function() {$( "#accordion" ).accordion( "resize" );} });
	$( "#toggle" ).button().toggle(function() { $( "#accordion" ).accordion( "option", "icons", false );
	}, function() { $( "#accordion" ).accordion( "option", "icons", icons ); });	
	
	//---------------------------------------------button & Dialog--------------------------------------
	$( "input:submit, a, button", ".button" ).button();
	$( "a", ".button" ).click(function() { return false; });
	$( "#radio" ).buttonset();
	$( "#check" ).button();
	$( "#checkbox" ).buttonset();
	$( "#dialogBtn" ).button({icons: {primary: "icon-save",secondary:"icon-edit"},text: true});
	$( "#dialog" ).dialog({autoOpen: false,show: "blind",hide: "explode"});
	$( "#dialogBtn" ).click(function() {$( "#dialog" ).dialog( "open" );return false;});
	$( "#dialog-modal" ).dialog({autoOpen: false, height: 140, modal: true});
	$( "#dialogModalBtn" ).click(function() { $( "#dialog-modal" ).dialog( "open" );});
	$( "#dialog-message" ).dialog({autoOpen: false, modal: true,
		buttons: {Ok: function() {$( this ).dialog( "close" );}} });
	$( "#dialogMessageBtn" ).click(function() { $( "#dialog-message" ).dialog( "open" );});
	$( "#dialog-confirm" ).dialog({autoOpen: false, resizable: false, height:140, modal: true,
		buttons:{"Delete all items": function(){$( this ).dialog( "close" );}, Cancel: function() {$( this ).dialog( "close" );} } });
	$( "#dialogConfirmBtn" ).click(function() {$("#dialog-confirm").dialog("open");});
	
	$( "#beginning" ).button({text: false,icons: { primary: "ui-icon-seek-start"}});
	$( "#rewind" ).button({text: false,icons: {primary: "ui-icon-seek-prev"}});
	$( "#play" ).button({text: false,icons: {primary: "ui-icon-play"}})
	.click(function() { var options;
		if ( $( this ).text() === "play" ) { options = { label: "pause", icons: {primary: "ui-icon-pause"} };
		} else { options = { label: "play", icons: { primary: "ui-icon-play" } }; }
		$( this ).button( "option", options );
	});
	$( "#stop" ).button({text: false,icons: {primary: "ui-icon-stop"}})
	.click(function() { $( "#play" ).button( "option", {label: "play",icons: {primary: "ui-icon-play"}});
	});
	$( "#forward" ).button({ text: false, icons: { primary: "ui-icon-seek-next" } });
	$( "#end" ).button({ text: false,icons: {primary: "ui-icon-seek-end"} });
	$( "#shuffle" ).button();
	$( "#repeat" ).buttonset();
	
	$( "#rerun" ).button().click(function() {alert( "Running the last action" );})
	.next().button( {text: false, icons: { primary: "ui-icon-triangle-1-s" } })
		.click(function() {alert( "Could display a menu to select an action" );})
		.parent().buttonset();
	//-----------------------------------------run effect-----------------------------
	function runEffect() {
		var selectedEffect = $( "#anim" ).val();
		var options = {};
		if ( selectedEffect === "scale" ) {
			options = { percent: 0 };
		} else if ( selectedEffect === "size" ) {
			options = { to: { width: 200, height: 60 } };
		}
		$( "#innerdatepicker" ).hide( selectedEffect, options, 1000, callback );
	};
	function callback() {
		setTimeout(function() {
			$( "#innerdatepicker" ).removeAttr( "style" ).hide().fadeIn();
		}, 1000 );
	};
	// set effect from select menu value
	$( "#effectHideBtn" ).click(function() {
		runEffect();
		return false;
	});
	//-----------------------------------------accordion draggable------------------------
	$( "#catalog" ).accordion();
	$( "#catalog li" ).draggable({
		appendTo: "body",
		helper: "clone"
	});
	$( "#cart ol" ).droppable({
		activeClass: "ui-state-default",
		hoverClass: "ui-state-hover",
		accept: ":not(.ui-sortable-helper)",
		drop: function( event, ui ) {
			$( this ).find( ".placeholder" ).remove();
			$( "<li></li>" ).text( ui.draggable.text() ).appendTo( this );
		}
	}).sortable({
		items: "li:not(.placeholder)",
		sort: function() {
			// gets added unintentionally by droppable interacting with sortable using connectWithSortable fixes this, but doesn't allow you to customize active/hoverClass options
			$( this ).removeClass( "ui-state-default" );
		}
	});
	
	$.widget( "ui.combobox", {
		_create: function() {
			var self = this,
				select = this.element.hide(),
				selected = select.children( ":selected" ),
				value = selected.val() ? selected.text() : "";
			var input = this.input = $( "<input>" )
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
	
	$( "#combobox" ).combobox();
});
//-->
</script>


	
	