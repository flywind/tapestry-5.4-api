define(["jquery"],function($){
	var init;
	
	init = function(obj){
		var $textbox = $("#" + obj.id);

        var normalColor = $textbox.css("color");

        $textbox.on("focus submit", doClearHint);
        $textbox.on("blur change", doCheckHint);

        $textbox.blur();

        function doClearHint() {
            var $field = $(this);
            
            if ($field.val() == obj.text) {
                $field.val("");
            }

            $field.css("color", normalColor);
        }

        function doCheckHint() {
            var $field = $(this);

            if ($field.val() == "") {
                $field.val(obj.text);
                $field.css("color", obj.color);
            }
            else if ($field.val() == obj.text) {
                $field.css("color", obj.color);
            }
            else {
                $field.css("color", normalColor);
            }
        }
	};
	
	return {
		init:init
	};
});
