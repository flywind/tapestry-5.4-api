define(["t5/core/dom","t5/core/events","t5/core/zone"],function(dom,events,zoneManager){
	return function(){
		dom.onDocument(events.zone.refresh,function(){
			var $zone = this.$;
			this.prepend("<div class='zone-loading-overlay'/>");
            var $overlay = $zone.find("div:first");
            $overlay.css({
                width : $zone.width() + "px",
                height : $zone.height() + "px"
            });
		});
	};
});