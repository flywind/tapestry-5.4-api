package org.flywind.tapestry.pages.examples.t54;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.internal.services.StringValueEncoder;

/**
 * <p>Tapestry5.4 API</p>
 * 
 * @author flywind(飞风)
 * @date 2016年6月6日
 * @网址：http://www.flywind.org
 * @QQ技术群：41138107(人数较多最好先加这个)或33106572
 * @since 1.0
 */
public class CoreInputTest {
	
	@Property
	private StringValueEncoder stringEncoder = new StringValueEncoder();

	/* Checkbox */
	@Property
	@Persist
	private boolean checkboxValue;

	/* Checklist */
	@Property
	@Persist
	private List<String> checklistSelectedValues;

	@Property
	private final String[] STATIONERY = { "Pens", "Pencils", "Paper" };

	/* DateField */
	@Property
	@Persist
	private Date dateValue;

	// 返回时间格式.
	public String getDateFieldFormat() {
		return "dd/MM/yyyy";
	}

	/* Palette */
	@Property
	@Persist
	private List<String> paletteSelectedValues;

	@Property
	private final String[] PETS = { "Dog", "Cat", "Parrot", "Mouse" };

	/* Password */

	@Property
	@Persist
	private String passwordValue;

	/* RadioGroup and Radio */

	@Property
	@Persist
	private String radioSelectedValue;

	/* Select */

	@Property
	@Persist
	private String selectedValue;

	/* TextArea */

	@Property
	@Persist
	private String textAreaValue;

	/* TextField */

	@Property
	@Persist
	private String textValue;

	/* 页面初始化 */

	void setupRender() {
		if (dateValue == null) {
			dateValue = new Date();
		}
		if (paletteSelectedValues == null) {
			paletteSelectedValues = new ArrayList<String>();
		}
	}
}
