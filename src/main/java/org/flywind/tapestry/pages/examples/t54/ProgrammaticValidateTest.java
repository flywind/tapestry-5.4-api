package org.flywind.tapestry.pages.examples.t54;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.TextField;

/**
 * <p>Tapestry5.4 API</p>
 * 
 * @author flywind(飞风)
 * @date 2016年6月6日
 * @网址：http://www.flywind.org
 * @QQ技术群：41138107(人数较多最好先加这个)或33106572
 * @since 1.0
 */
public class ProgrammaticValidateTest {

	@Property
    @Persist(PersistenceConstants.FLASH)
    @NotNull
    @Size(max = 10)
    private String firstName;

    @Property
    @Persist(PersistenceConstants.FLASH)
    @NotNull
    @Size(max = 10)
    private String lastName;

    @InjectComponent("inputs")
    private Form form;

    @InjectComponent("firstName")
    private TextField firstNameField;

    @InjectComponent("lastName")
    private TextField lastNameField;

    void onValidateFromInputs() {

        if (firstName != null) {
            if (!firstName.matches("[A-Za-z]+")) {
                form.recordError(firstNameField, "First Name must be in English");
            }
        }

        if (lastName != null) {
            if (!lastName.matches("[A-Za-z]+")) {
                form.recordError(lastNameField, "Last Name must be in English");
            }
        }
    }
}
