package org.flywind.tapestry.pages.examples.t54;

/**
 * <p>Tapestry5.4 API</p>
 * 
 * @author flywind(飞风)
 * @date 2016年6月6日
 * @网址：http://www.flywind.org
 * @QQ技术群：41138107(人数较多最好先加这个)或33106572
 * @since 1.0
 */
public class MultipleFormsTest2 {
	
	private SearchType searchType;

	private String name;

	public enum SearchType {
		CUSTOMERS, SUPPLIERS;
	}

	public void set(SearchType searchType, String lastName) {
		this.searchType = searchType;
		this.name = lastName;
	}

	Object[] onPassivate() {
		return new Object[] { searchType, name };
	}

	void onActivate(SearchType searchType, String lastName) {
		this.searchType = searchType;
		this.name = lastName;
	}

	public String getYourSearch() {
		if (searchType == SearchType.CUSTOMERS) {
			return "You select Customers name: \"" + name + "\".";
		} else {
			return "You select  Suppliers name: \"" + name + "\".";
		}
	}
}
