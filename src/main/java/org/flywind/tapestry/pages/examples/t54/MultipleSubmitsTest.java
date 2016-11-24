package org.flywind.tapestry.pages.examples.t54;

import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.flywind.tapestry.pages.examples.t54.MultipleSubmitsTest2.SearchType;

/**
 * <p>Tapestry5.4 API</p>
 * 
 * @author flywind(飞风)
 * @date 2016年6月6日
 * @网址：http://www.flywind.org
 * @QQ技术群：41138107(人数较多最好先加这个)或33106572
 * @since 1.0
 */
public class MultipleSubmitsTest {

	@Property
	private String name;

	@InjectPage
	private MultipleSubmitsTest2 page2;

	private SearchType searchType;

	void onActivate() {
		searchType = SearchType.CUSTOMERS;
	}

	void onSelectedFromSuppliers() {
		searchType = SearchType.SUPPLIERS;
	}

	Object onSuccess() {
		page2.set(searchType, name);
		return page2;
	}
}
