define(["jquery","t5/core/dom","t5/core/zone"],function($,dom,zone){
	var init;
	
	init = function(spec){
		$('#addBtn').click(function(){
			var url = spec.addUrl + "/10";
			zone.deferredZoneUpdate('addZone',url);
		});
		
		$('#timeBtn').click(function(){
			var ajaxOpts = {
				success:function(data){
					if(data.text){
						var d = $.parseJSON(data.text);
						$('#nowTime').empty().html(d.time);
					}
				}
			};
			
			dom.ajaxRequest(spec.timeUrl,ajaxOpts);
		});
	};
	
	return {
		init:init
	}
})