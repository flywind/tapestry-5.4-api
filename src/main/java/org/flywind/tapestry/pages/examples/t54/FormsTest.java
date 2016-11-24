package org.flywind.tapestry.pages.examples.t54;

import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.TextField;

/**
 * <p>Tapestry5.4 API</p>
 * 
 * @author flywind(飞风)
 * @date 2016年6月3日
 * @网址：http://www.flywind.org
 * @QQ技术群：41138107(人数较多最好先加这个)或33106572
 * @since 1.0
 */
public class FormsTest {

	@Property
	@Validate("required,minlength=10,regexp")
	@Persist(PersistenceConstants.FLASH)
	private String name;
	
	@InjectComponent("meform")
	private Form meform;
	
	@InjectComponent("name")
	private TextField nameField;
	
	void onValidateFromMeform(){
		if (name != null && name.equals("flywind")) {
			meform.recordError(nameField, "The user can not be flywind.");
        }
	}
}
