package org.flywind.tapestry.pages.examples.t54;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;

/**
 * <p>Tapestry5.4 API</p>
 * 
 * @author flywind(飞风)
 * @date 2016年6月6日
 * @网址：http://www.flywind.org
 * @QQ技术群：41138107(人数较多最好先加这个)或33106572
 * @since 1.0
 */
public class FormValidateTest {

	@Property
	@Persist(PersistenceConstants.FLASH)
	@NotNull(message="Can not be empty ah, please re-enter!")
	@Size(min=2,max=10,message="Character number ranges from 2 to 10 characters!")
	private String name;
}
