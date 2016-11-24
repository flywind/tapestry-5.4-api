package org.flywind.tapestry.pages.examples.t54;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.RequestParameter;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;

/**
 * <p>Tapestry5.4 API</p>
 * 
 * @author flywind(飞风)
 * @date 2016年6月7日
 * @网址：http://www.flywind.org
 * @QQ技术群：41138107(人数较多最好先加这个)或33106572
 * @since 1.0
 */
public class ZoneUpdatermixinupdateTest {
	
	@Property
    @Persist
    private String firstName;

    @Property
    @Persist
    private String lastName;

    @InjectComponent
    private Zone nameZone;

    @Inject
    private AjaxResponseRenderer ajaxResponseRenderer;

    @Inject
    private Request request;

    @Inject
    private ComponentResources componentResources;

    void setupRender() {
        if (firstName == null && lastName == null) {
            firstName = "Hello";
            lastName = "Kitty";
        }
    }

    void onFirstNameChanged(@RequestParameter(value = "param", allowBlank = true) String firstName) {
        if (firstName == null) {
            firstName = "";
        }

        this.firstName = firstName;

        if (request.isXHR()) {
            ajaxResponseRenderer.addRender(nameZone);
        }
    }

    void onLastNameChanged(@RequestParameter(value = "param", allowBlank = true) String lastName) {
        if (lastName == null) {
            lastName = "";
        }

        this.lastName = lastName;

        if (request.isXHR()) {
            ajaxResponseRenderer.addRender(nameZone);
        }
    }

    public String getName() {
        return firstName + " " + lastName;
    }

}
