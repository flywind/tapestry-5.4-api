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
public class EditPersonTest {

	@Property
	//@PageActivationContext
	private Long id;
	
	@Property
    private Example person;
	
	@InjectComponent("personForm")
    private BeanEditForm form;

	
	void onActivate(Long id) {
        this.id = id;
    }

    Long onPassivate() {
        return id;
    }
    
    @Inject
    private ExampleService personService;
    
    
    void onPrepareForRender() throws Exception {

        if (form.isValid()) {
            person = findPerson(id);

            if (person == null) {
                throw new Exception("Person " + id + " Does not exist.");
            }
        }

    }
    
    void onPrepareForSubmit() {
        person = findPerson(id);

        if (person == null) {
            form.recordError("Person Does not exist.");
            person = new Example();
        }
    }

    void onValidateFromPersonForm() {

       /* if (!person.getFirstName().equals("flywind")) {
            form.recordError("First Name 必须是flywind.");
        }*/

        if (form.getHasErrors()) {
            return;
        }

        try {
        	personService.update(person);
        }
        catch (Exception e) {
            form.recordError("Failed to update.");
        }
    }

    Object onSuccess() {
        return GridTest.class;
    }
    
    private Example findPerson(Long id) {
        return personService.getById(id);
    }
}
