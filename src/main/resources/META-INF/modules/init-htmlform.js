define(["jquery","t5/core/dom","t5/core/ajax"],function($,dom,ajax){
	var init,getFormData;
	
	init = function(url){
		$('#myBtn').click(function(){
			var name = $('#name').val().trim();
			if(name == ""){
				alert("Please input user name!");
				return;
			};
			
			data = getFormData('meform');
			var ajaxOpts = {
				data: data,
				dataType: 'json',
				success: function(data){
					if(data.statusText == "OK"){
						var d = $.parseJSON(data.text);
						$('#muName').empty().html(d.name);
						console.log(d.result);
					}
				}
			};
			
			dom.ajaxRequest(url,ajaxOpts);
		});
	};
	
	//系列化表单数据
	getFormData = function(formId){
    	var o = {};
		var a = $('#'+formId).serializeArray();
		$.each(a, function() {
			if (o[this.name] !== undefined) {
				if (!o[this.name].push) {
					o[this.name] = [ o[this.name] ];
				}
				o[this.name].push(this.value || '');
			} else {
				o[this.name] = this.value || '';
			}
		});
		return o;
    };

	return {
		init:init
	}
})