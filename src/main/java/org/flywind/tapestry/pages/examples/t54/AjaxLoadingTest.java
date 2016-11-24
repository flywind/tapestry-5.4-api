package org.flywind.tapestry.pages.examples.t54;

import org.apache.tapestry5.Asset;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Path;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;
import org.apache.tapestry5.services.ajax.JavaScriptCallback;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;
import org.apache.tapestry5.services.javascript.StylesheetLink;
import org.apache.tapestry5.services.javascript.StylesheetOptions;

/**
 * <p>Tapestry5.4 API</p>
 * 
 * @author flywind(飞风)
 * @date 2016年6月7日
 * @网址：http://www.flywind.org
 * @QQ技术群：41138107(人数较多最好先加这个)或33106572
 * @since 1.0
 */
@Import(stylesheet="context:styles/zone-overlay.css")
public class AjaxLoadingTest {

	static final private String[] hello = { "Hello ", "everyone", " nice to meet you" };

	@Property
	private String[] helloCalls;
	
	@Property
	private String helloCall;

	@InjectComponent
	private Zone mezone;

	@Inject
	private Request request;

	@Inject
	private AjaxResponseRenderer ajaxResponseRenderer;

	@Inject
	private JavaScriptSupport javaScriptSupport;
	
	@Inject
	@Path("context:styles/zone-overlay-ie.css")
	private Asset iepath;
	
	void afterRender(){
		
		javaScriptSupport.importStylesheet(new StylesheetLink(iepath, new StylesheetOptions().withCondition("IE")));
		javaScriptSupport.require("init-zoneoverlay");
		
	}

	void onCallzone() {
		
		sleep(3000);
		helloCalls = hello;
		if (request.isXHR()) {
			ajaxResponseRenderer.addRender(mezone).addCallback(new JavaScriptCallback() {
				public void run(JavaScriptSupport javaScriptSupport) {
					javaScriptSupport.addScript("alert('Ajax Eventlink finish');");
				}
			});
		}
		
	}
	
	void onSuccess(){
		
		helloCalls = hello;
		sleep(1000);
		if (request.isXHR()) {
			ajaxResponseRenderer.addRender(mezone).addCallback(new JavaScriptCallback() {
				public void run(JavaScriptSupport javaScriptSupport) {
					javaScriptSupport.addScript("alert('Ajax Form finish');");
				}
			});
		}
		
	}

	private void sleep(long millis) {
		
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {

		}
		
	}
}
