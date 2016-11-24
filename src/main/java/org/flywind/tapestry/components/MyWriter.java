package org.flywind.tapestry.components;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 * <p>Tapestry5.4 API</p>
 * 
 * @author flywind(飞风)
 * @date 2016年6月6日
 * @网址：http://www.flywind.org
 * @QQ技术群：41138107(人数较多最好先加这个)或33106572
 * @since 1.0
 */
public class MyWriter {

	@Inject
	private ComponentResources componentResources;

	public void setupRender(MarkupWriter writer){
		writer.element("div", "style", "color:red", "id", "divId");
		writer.element("i", "style", "color:green");
		writer.write("Green color");
		writer.end();
	}
	
	public void afterRender(MarkupWriter writer){
		componentResources.renderInformalParameters(writer);
		writer.end();
	}
}
