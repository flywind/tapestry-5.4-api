package org.flywind.tapestry.pages.examples.t54;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.ValidationException;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;

/**
 * <p>Tapestry5.4 API</p>
 * 
 * @author flywind(飞风)
 * @date 2016年6月6日
 * @网址：http://www.flywind.org
 * @QQ技术群：41138107(人数较多最好先加这个)或33106572
 * @since 1.0
 */
public class ValidateEventTest {

	@Property
    @Persist(PersistenceConstants.FLASH)
    @Size(max = 10)
    private String firstName;

    @Property
    @Persist(PersistenceConstants.FLASH)
    @Min(1)
    @Max(100)
    private Integer luckyNumber;

    @InjectComponent("inputs")
    private Form form;

    void onValidateFromFirstName(String value) throws ValidationException {

        if (value != null) {
            if (!value.matches("[A-Za-z]+")) {
                throw new ValidationException("First Name must also be in English");
            }
        }

    }

    void onValidateFromLuckyNumber(Integer value) throws ValidationException {

        if (value != null) {
            if (value.equals(88)) {
                throw new ValidationException("Do not allow input 88");
            }
        }

    }

    void onValidateFromInputs() {

        if (form.getHasErrors()) {
            return;
        }

        if (firstName == null && luckyNumber == null) {
            form.recordError("first name or lucky number can not be empty.");
        }

    }
}
