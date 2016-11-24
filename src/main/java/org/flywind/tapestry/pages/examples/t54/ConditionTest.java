package org.flywind.tapestry.pages.examples.t54;

import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 * <p>
 * Tapestry5.4 API
 * </p>
 * 
 * @author flywind(飞风)
 * @date 2016年6月3日
 * @网址：http://www.flywind.org
 * @QQ技术群：41138107(人数较多最好先加这个)或33106572
 * @since 1.0
 */
public class ConditionTest {

	@Property
	private String str = "Hello";
	
	@Persist
	@Property
	static final private int currentNum = 7;

	@Inject
	private Block case1, case2, case3, case4, case5, case6, case7;

	public boolean isField() {
		if (str.equalsIgnoreCase("hello")) {
			return true;
		}
		return false;
	}

	public Object getMyCase() {
		switch (currentNum) {
		case 1:
			return case1;
		case 2:
			return case2;
		case 3:
			return case3;
		case 4:
			return case4;
		case 5:
			return case5;
		case 6:
			return case6;
		case 7:
			return case7;

		default:
			return null;
		}
	}
}
