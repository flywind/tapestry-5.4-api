package org.flywind.tapestry.pages.examples.t54;

import java.util.List;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.flywind.tapestry.business.example.ExampleService;
import org.flywind.tapestry.entities.example.Example;

/**
 * <p>Tapestry5.4 API</p>
 * 
 * @author flywind(飞风)
 * @date 2016年6月2日
 * @网址：http://www.flywind.org
 * @QQ技术群：41138107(人数较多最好先加这个)或33106572
 * @since 1.0
 */
public class LoopTest {

	@Property
	private Example person;
	
	@Property
	private List<Example> persons;
	
	@Inject
	private ExampleService personService;
	
	public void setupRender(){
		persons = personService.getAllExamples();
	}
}
