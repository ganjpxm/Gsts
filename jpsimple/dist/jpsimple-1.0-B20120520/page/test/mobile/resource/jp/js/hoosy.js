(function($) {
	$(function() {
		var SafariWindowHeightFix = 34; // XXX:
		function fixed(elm) {
			if (elm.data("iscroll-plugin")) {
				return;
			}					
			var $wrapper = elm.find('[data-iscroll="scroller"]');
			if($wrapper.length){
				$wrapper.css({
					"z-index": 1,
					top:"40px",
					bottom:"40px" ,
					overflow:"hidden",
					position:"absolute",
					width:"100%"
				});
			}
			var scroller = elm.find('[data-iscroll="scroller"]').get(0);
			if (scroller) {
				var iscroll = new iScroll(scroller, {
					useTransform: false,
					onBeforeScrollStart: function (e) {
						var target = e.target;
						while (target.nodeType != 1) target = target.parentNode;
						if (target.tagName != 'SELECT' && target.tagName != 'INPUT' && target.tagName != 'TEXTAREA')
							e.preventDefault();
					}
				});
				elm.data("iscroll-plugin", iscroll);
			}
		}
		$('[data-role="page"][data-iscroll="enable"]').live("pageshow", function() {
			fixed($(this));
		});
		if ($.mobile.activePage&&$.mobile.activePage.data("iscroll") == "enable") {
			fixed($.mobile.activePage);
		}
	}
);
})(jQuery);