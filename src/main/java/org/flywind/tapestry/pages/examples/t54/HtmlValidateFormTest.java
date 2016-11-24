package org.flywind.tapestry.pages.examples.t54;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

/**
 * <p>Tapestry5.4 API</p>
 * 
 * @author flywind(飞风)
 * @date 2016年6月3日
 * @网址：http://www.flywind.org
 * @QQ技术群：41138107(人数较多最好先加这个)或33106572
 * @since 1.0
 */
public class HtmlValidateFormTest {
	
	@Inject
	private ComponentResources componentResources;
	
	@Inject
	private JavaScriptSupport javaScriptSupport;
	
	@Property
	private String saveUrl;
	
	@Inject
	private Request request;
	
	public void setupRender(){
		saveUrl = componentResources.createEventLink("saveForm").toAbsoluteURI();
		javaScriptSupport.require("init-htmlvalidateform").invoke("init").with(saveUrl);;
	}
	
	//可以理解为控制层的方法
	public JSONObject onSaveForm(){
		String name = request.getParameter("name");
		JSONObject spec = new JSONObject();
		try {
			spec.put("name", "Hello "+name);
			spec.put("result", "Success!!!");
			return spec;
		} catch (Exception e) {
			spec.put("result", "Failed!!!");
			return spec;
		}
		
	}
}
