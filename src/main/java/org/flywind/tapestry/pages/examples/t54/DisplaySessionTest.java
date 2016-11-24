package org.flywind.tapestry.pages.examples.t54;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.Session;

/**
 * <p>Tapestry5.4 API</p>
 * 
 * @author flywind(飞风)
 * @date 2016年6月6日
 * @网址：http://www.flywind.org
 * @QQ技术群：41138107(人数较多最好先加这个)或33106572
 * @since 1.0
 */
public class DisplaySessionTest {

	@Property
	private String attributeName;

	@Inject
	private Request request;

	public boolean getHasSession() {
		return request.getSession(false) != null;
	}

	public Session getSession() {
		return request.getSession(false);
	}

	public Object getAttributeValue() {
		return getSession().getAttribute(attributeName);
	}
}
