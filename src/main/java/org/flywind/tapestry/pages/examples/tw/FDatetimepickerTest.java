package org.flywind.tapestry.pages.examples.tw;

import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.json.JSONObject;
@Import(stylesheet="context:assets/styles/demo.css")
public class FDatetimepickerTest {

	@Property
	private String name,name2,name3;
	
	public JSONObject getOptions(){
		JSONObject opts = new JSONObject();
		opts.put("timepicker", true);
		opts.put("datepicker", false);
		opts.put("format", "H:i");
		return opts;
	}
}
