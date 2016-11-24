package org.flywind.tapestry.pages.examples.t54;

import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.flywind.tapestry.pages.examples.t54.MultipleFormsTest2.SearchType;

/**
 * <p>Tapestry5.4 API</p>
 * 
 * @author flywind(飞风)
 * @date 2016年6月6日
 * @网址：http://www.flywind.org
 * @QQ技术群：41138107(人数较多最好先加这个)或33106572
 * @since 1.0
 */
public class MultipleFormsTest {

	@Property
	private String customerName;

	@Property
	private String supplierName;

	@InjectPage
	private MultipleFormsTest2 page2;

	void onPrepareFromSearchCustomers() {
		// Initialize the Customers form
	}

	void onPrepareFromSearchSuppliers() {
		// Initialize the Suppliers form
	}

	Object onSuccessFromSearchCustomers() {
		page2.set(SearchType.CUSTOMERS, customerName);
		return page2;
	}

	Object onSuccessFromSearchSuppliers() {
		page2.set(SearchType.SUPPLIERS, supplierName);
		return page2;
	}
}
