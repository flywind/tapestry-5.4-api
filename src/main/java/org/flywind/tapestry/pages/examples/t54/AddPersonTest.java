package org.flywind.tapestry.pages.examples.t54;

import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.flywind.tapestry.business.example.ExampleService;
import org.flywind.tapestry.entities.example.Example;
/**
 * <p>Tapestry5.4 API</p>
 * 
 * @author flywind(飞风)
 * @date 2016年6月2日
 * @网址：http://www.flywind.org
 * @QQ技术群：41138107(人数较多最好先加这个)或33106572
 * @since 1.0
 */
public class AddPersonTest {

	@Property
    private Example person;
	
	@InjectComponent("personform")
    private BeanEditForm form;
	
	@Inject
	private ExampleService personService;
	
	void onPrepareForRender() throws Exception {
        if (form.isValid()) {
            person = new Example();
        }
    }
	
	void onPrepareForSubmit() throws Exception {
        person = new Example();
    } 
    
    void onValidateFromPersonform() {

        if (form.getHasErrors()) {
            return;
        }

        try {
        	person.setCustomerCode("0755");
        	personService.saveOrUpdate(person);
        }
        catch (Exception e) {
            form.recordError("Failed to create.");
        }
    }

    Object onSuccess() {
        return GridTest.class;
    }
    
    Object onCanceled() {
        return GridTest.class;
    }
}
