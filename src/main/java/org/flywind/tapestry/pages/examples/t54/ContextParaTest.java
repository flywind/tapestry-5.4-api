package org.flywind.tapestry.pages.examples.t54;

import java.util.HashMap;
import java.util.Map;

import org.apache.tapestry5.annotations.Property;

/**
 * <p>Tapestry5.4 API</p>
 * 
 * @author flywind(飞风)
 * @date 2016年6月2日
 * @网址：http://www.flywind.org
 * @QQ技术群：41138107(人数较多最好先加这个)或33106572
 * @since 1.0
 */
public class ContextParaTest {

	@Property
	private String name;
	
	@Property
	private int age;
	
	void setupRender(){
		name = "flywind";
		age = 29;
	}
	
	public Map<String,Object> getParams(){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", name);
		params.put("age", age);
		return params;
	}
}
