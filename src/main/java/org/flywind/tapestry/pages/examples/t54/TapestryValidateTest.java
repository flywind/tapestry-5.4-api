package org.flywind.tapestry.pages.examples.t54;

import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.Validate;

/**
 * <p>Tapestry5.4 API</p>
 * 
 * @author flywind(飞风)
 * @date 2016年6月6日
 * @网址：http://www.flywind.org
 * @QQ技术群：41138107(人数较多最好先加这个)或33106572
 * @since 1.0
 */
public class TapestryValidateTest {

	@Property
    @Persist(PersistenceConstants.FLASH)
	@Validate("required,maxlength=5,regexp")
    private String firstName;

    @Property
    @Persist(PersistenceConstants.FLASH)
    private String lastName;
}
