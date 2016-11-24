package org.flywind.tapestry.pages.examples.t54;

import java.util.Date;

import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;

/**
 * <p>
 * Tapestry5.4 API
 * </p>
 * 
 * @author flywind(飞风)
 * @date 2016年6月6日
 * @网址：http://www.flywind.org
 * @QQ技术群：41138107(人数较多最好先加这个)或33106572
 * @since 1.0
 */
public class AjaxEATest {

	@Property
	@Persist
	private int count;

	@InjectComponent
	private Zone addZone, timeZone;

	@Inject
	private Request request;

	Object onAdd(int count) {
		this.count += count;
		return addZone.getBody();
	}

	Object onActionFromServiceTime() {
		return request.isXHR() ? timeZone.getBody() : null;
	}

	public Date getToday() {
		return new Date();
	}

	void onClear() {
		count = 0;
	}
}
