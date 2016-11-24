package org.flywind.tapestry.pages.examples.t54;

import java.util.List;

import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.corelib.components.Grid;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;
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
public class GridTest {

	@Property
	private Example person;
	
	@Property
	private List<Example> persons;
	
	@InjectComponent("megrid")
    private Grid grid;
	
	@Property
    private BeanModel<Example> myModel;
	
	@Inject
    private BeanModelSource beanModelSource;
	
	@Inject
    private Messages messages;
	
	@Inject
	private ExampleService personService;
	
	public void setupRender(){
		persons = personService.getAllExamples();
		//默认排序
		/*if (grid.getSortModel().getSortConstraints().isEmpty()) {
            grid.getSortModel().updateSort("startDate");
        }*/
		
		myModel = beanModelSource.createDisplayModel(Example.class, messages);
        myModel.add("action", null);
        myModel.include("id", "userName", "createTime");
        myModel.get("userName").sortable(false);
        myModel.get("userName").label("Your user name");
	}
	
	public void onDel(Long id){
		Example e = new Example();
		e.setId(id);
		personService.delete(e);
	}
	
	public boolean isNumone(){
		if(person.getId().equals(Long.parseLong("1"))){
			return true;
		}
		return false;
	}
}
