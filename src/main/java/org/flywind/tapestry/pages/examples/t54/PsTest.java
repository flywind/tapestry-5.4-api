package org.flywind.tapestry.pages.examples.t54;

import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;

/**
 * <p>Tapestry5.4 API</p>
 * 
 * @author flywind(飞风)
 * @date 2016年6月6日
 * @网址：http://www.flywind.org
 * @QQ技术群：41138107(人数较多最好先加这个)或33106572
 * @since 1.0
 */
public class PsTest {

	@Persist(PersistenceConstants.FLASH)
	@Property
	private String persistString;

	@Property
	private String userName;

	@Property
	private String password;

	@SessionState
	private String user;

	Object onSuccessFromLoginForm() {
		if ("flywind".equals(userName) && "123456".equals(password)) {
			user = userName;
			return PsTest2.class;
		}
		return null;
	}
}
