package org.flywind.tapestry.mixins;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.ClientElement;
import org.apache.tapestry5.annotations.InjectContainer;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

/**
 * <p>Tapestry5.4 API</p>
 * 
 * @author flywind(飞风)
 * @date 2016年6月6日
 * @网址：http://www.flywind.org
 * @QQ技术群：41138107(人数较多最好先加这个)或33106572
 * @since 1.0
 */
public class TextMessage {

	@Inject
	private JavaScriptSupport javaScriptSupport;
	
	@Parameter(required = true, defaultPrefix = BindingConstants.LITERAL)
	private String text;
	
	@Parameter(required = true, defaultPrefix = BindingConstants.LITERAL)
	private String color;
	
	@InjectContainer
	private ClientElement clientElement;
	
	public void afterRender(){
		
		//json object传递
		JSONObject obj = new JSONObject();
		obj.put("id", clientElement.getClientId());
		obj.put("text", text);
		obj.put("color", color);
		javaScriptSupport.require("init-textmessage").invoke("init").with(obj);
		
	}
}
