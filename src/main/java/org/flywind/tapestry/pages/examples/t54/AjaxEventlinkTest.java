package org.flywind.tapestry.pages.examples.t54;

import java.util.Date;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;
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
public class AjaxEventlinkTest {

	@Property
	@Persist
	private int count;

	@InjectComponent
	private Zone addZone;
	
	@Inject
	private ComponentResources componentResources;
	
	@Inject
	private Request request;
	
	@Inject
	private AjaxResponseRenderer ajaxResponseRenderer;
	
	@Inject
	private JavaScriptSupport javaScriptSupport;
	
	public void setupRender(){
		String ajaxUrl = componentResources.createEventLink("add").toAbsoluteURI();
		String timeUrl = componentResources.createEventLink("serviceTime").toAbsoluteURI();
		JSONObject spec = new JSONObject();
		spec.put("addUrl", ajaxUrl);
		spec.put("timeUrl", timeUrl);
		javaScriptSupport.require("init-ajaxeventlink").invoke("init").with(spec);
	}
	
	void onAdd(int count) {
		this.count += count;
		
		if(request.isXHR()){
			ajaxResponseRenderer.addRender(addZone);
		}
	}
	
	JSONObject onServiceTime() {
		Date d = new Date();
		String time = d.toString();
		JSONObject spec= new JSONObject();
		spec.put("time", time);
		return  spec;
	}
}
