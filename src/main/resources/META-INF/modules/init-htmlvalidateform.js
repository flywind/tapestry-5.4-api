define(["jquery","t5/core/dom","t5/core/ajax","plugin/validate"],function($,dom,ajax,jvalidate){
	var init,getFormData;
	
	init = function(url){
		$("#meform").validate({
            rules: {
            	name: {
                    required: true,
                    minlength: 2
                },
                password: {
					required: true,
					minlength: 5
				},
				confirm_password: {
					required: true,
					minlength: 5,
					equalTo: "#password"
				},
				email: {
					required: true,
					email: true
				},
				topic: {
					required: "#newsletter:checked",
					minlength: 2
				},
				agree: "required"
            },
            messages: {
				name: {
					required: "Please enter a name",
					minlength: "Your username must consist of at least 2 characters"
				},
				password: {
					required: "Please provide a password",
					minlength: "Your password must be at least 5 characters long"
				},
				confirm_password: {
					required: "Please provide a password",
					minlength: "Your password must be at least 5 characters long",
					equalTo: "Please enter the same password as above"
				},
				email: "Please enter a valid email address",
				agree: "Please accept our policy"
			},
            submitHandler: function(form) {
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
    			
                return false;
            }
        });
		
		/*$('#myBtn').click(function(){
			var name = $('#name').val().trim();
			if(name == ""){
				alert("姓名不能为空!");
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
		});*/
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