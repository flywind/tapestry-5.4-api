package org.flywind.tapestry.pages.examples.t54;

import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;

/**
 * <p>Tapestry5.4 API</p>
 * 
 * @author flywind(飞风)
 * @date 2016年6月7日
 * @网址：http://www.flywind.org
 * @QQ技术群：41138107(人数较多最好先加这个)或33106572
 * @since 1.0
 */
public class AjaxFormTest {

	@InjectComponent
	private Zone formZone;
	
	@Property
	@Persist(PersistenceConstants.FLASH)
	private String name;
	
	@Property
	@Persist(PersistenceConstants.FLASH)
	private int age;
	
	@Inject
	private AjaxResponseRenderer ajaxResponseRenderer;
	
	@Inject
	private Request request;
	
	@InjectComponent
	private Form meForm;
	
	public void onPrepareForRender(){
		age = 1;
	}
	
	public void onPrepareForSubmit(){
		//提交事件触发时初始化数据，如：new Person
	}
	
	public void onValidateFromMeForm(){
		if(meForm.getHasErrors()){
			return;
		}
		//处理控制层逻辑如：添加、编辑、删除
	}
	
	void onFailureFromMeForm() {
        if (request.isXHR()) {
            ajaxResponseRenderer.addRender(formZone);
        }
    }
	
	public void onSuccessFromMeForm(){
		if(request.isXHR()){
			ajaxResponseRenderer.addRender(formZone);
		}
	}
	
}
