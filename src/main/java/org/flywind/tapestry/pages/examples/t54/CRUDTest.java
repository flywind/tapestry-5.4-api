package org.flywind.tapestry.pages.examples.t54;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.validation.constraints.Size;

import org.apache.tapestry5.EventContext;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.annotations.ActivationRequestParameter;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.flywind.tapestry.business.example.ExampleService;
import org.flywind.tapestry.entities.example.Example;
import org.flywind.tapestry.utils.ExceptionUtil;
import org.flywind.widgets.core.dao.FPage;

/**
 * <p>Tapestry5.4 API</p>
 * 
 * @author flywind(飞风)
 * @date 2016年6月7日
 * @网址：http://www.flywind.org
 * @QQ技术群：41138107(人数较多最好先加这个)或33106572
 * @since 1.0
 */
@Import(stylesheet="context:styles/filtercrud.css")
public class CRUDTest {

	/*
	 * 访问模式
	 */
	private enum Mode {
        CREATE, REVIEW, UPDATE;
    }
	
	/*
	 * 编辑模式
	 */
	@Property
    private Mode editorMode;
	
	/*
	 * 编辑的id
	 */
	@Property
    private Long editorPersonId;
	
	/*
	 * 过滤的参数
	 */
	@Property
    @ActivationRequestParameter
    @Size(max = 10)
    private String partialName;
	
	@Property
	private List<Example> listPersons;
	
	@Property
    private Example listPerson;

    @Property
    private Example editorPerson;

    @Property
    @Persist(PersistenceConstants.FLASH)
    private String deleteMessage;
    
    @Inject
    private ExampleService personService;
    
    @InjectComponent
    private Form createForm;

    @InjectComponent
    private Form updateForm;

    @InjectComponent("userName")
    private TextField firstNameField;

    @Inject
    private Messages messages;
    
    public boolean isOne(){
    	if(listPerson.getId().equals(Long.parseLong("1")) || editorPerson.getId().equals(Long.parseLong("1"))){
    		return true;
    	}
    	return false;
    }
    
    void onActivate(EventContext ec) {

        if (ec.getCount() == 0) {
            editorMode = null;
            editorPersonId = null;
        }
        else if (ec.getCount() == 1) {
            editorMode = ec.get(Mode.class, 0);
            editorPersonId = null;
        }
        else {
            editorMode = ec.get(Mode.class, 0);
            editorPersonId = ec.get(Long.class, 1);
        }

    }
    
    Object[] onPassivate() {

        if (editorMode == null) {
            return null;
        }
        else if (editorMode == Mode.CREATE) {
            return new Object[] { editorMode };
        }
        else if (editorMode == Mode.REVIEW || editorMode == Mode.UPDATE) {
            return new Object[] { editorMode, editorPersonId };
        }
        else {
            throw new IllegalStateException(editorMode.toString());
        }

    }
    
    void setupRender() {
    	if(partialName == null || "".equals(partialName)){
    		listPersons = personService.getAllExamples();
    		
    	}else{
    		Example example = new Example();
    		example.setUserName(partialName);
    		FPage page = new FPage();
    		page.setPageNumber(1);
    		page.setPageSize(10);
    		listPersons = personService.getAllExampleByTree(example, page, "0755");
    	}

        if (editorMode == Mode.REVIEW) {
            if (editorPersonId == null) {
                editorPerson = null;
            }
            else {
                if (editorPerson == null) {
                    editorPerson = personService.getById(editorPersonId);
                }
            }
        }

    }
    
    void onToCreate() {
        editorMode = Mode.CREATE;
        editorPersonId = null;
    }

    void onPersonSelected(Long personId) {
        editorMode = Mode.REVIEW;
        editorPersonId = personId;
    }
    
 // /////////////////////////////////////////////////////////////////////
    // CREATE
    // /////////////////////////////////////////////////////////////////////

    void onCancelCreate() {
        editorMode = null;
        editorPersonId = null;
    }

    void onPrepareForRenderFromCreateForm() throws Exception {

        if (createForm.isValid()) {
            editorMode = Mode.CREATE;
            editorPerson = new Example();
        }
    }

    void onPrepareForSubmitFromCreateForm() throws Exception {
        editorMode = Mode.CREATE;
        editorPerson = new Example();
    }

    void onValidateFromCreateForm() {

        if (createForm.getHasErrors()) {
            return;
        }

        try {
            personService.save(editorPerson);
        }
        catch (Exception e) {
            createForm.recordError(ExceptionUtil.getRootCauseMessage(e));
        }
    }

    void onSuccessFromCreateForm() {
        editorMode = Mode.REVIEW;
        editorPersonId = editorPerson.getId();
    }
    
    
 // /////////////////////////////////////////////////////////////////////
    // REVIEW
    // /////////////////////////////////////////////////////////////////////

    void onToUpdate(Long personId) {
        editorMode = Mode.UPDATE;
        editorPersonId = personId;
    }

    void onDelete(Long personId) {
        editorMode = Mode.REVIEW;
        editorPersonId = personId;

        try {
        	Example e = new Example();
        	e.setId(personId);
        	personService.delete(e);

            editorMode = null;
            editorPersonId = null;
        }
        catch (Exception e) {
            deleteMessage = ExceptionUtil.getRootCauseMessage(e);
        }
    }

    // /////////////////////////////////////////////////////////////////////
    // UPDATE
    // /////////////////////////////////////////////////////////////////////

    void onCancelUpdate(Long personId) {
        editorMode = Mode.REVIEW;
        editorPersonId = personId;
    }

    void onPrepareForRenderFromUpdateForm() {
    	
        if (updateForm.isValid()) {
            editorMode = Mode.UPDATE;
            editorPerson = personService.getById(editorPersonId);
        }
    }

    void onPrepareForSubmitFromUpdateForm() {
        editorMode = Mode.UPDATE;

        editorPerson = personService.getById(editorPersonId);

        if (editorPerson == null) {
            updateForm.recordError("Person has been deleted by another process.");
            editorPerson = new Example();
        }
    }

    void onValidateFromUpdateForm() {

        if (updateForm.getHasErrors()) {
            return;
        }

        try {
        	personService.update(editorPerson);
        }
        catch (Exception e) {
            updateForm.recordError(ExceptionUtil.getRootCauseMessage(e));
        }
    }

    void onSuccessFromUpdateForm() {
        editorMode = Mode.REVIEW;
        editorPersonId = editorPerson.getId();
        partialName = null;
    }

    // /////////////////////////////////////////////////////////////////////
    // GETTERS ETC.
    // /////////////////////////////////////////////////////////////////////

    public String getLinkCSSClass() {
        if (listPerson != null && listPerson.getId().equals(editorPersonId)) {
            return "active";
        }
        else {
            return "";
        }
    }

    public boolean isModeCreate() {
        return editorMode == Mode.CREATE;
    }

    public boolean isModeReview() {
        return editorMode == Mode.REVIEW;
    }

    public boolean isModeUpdate() {
        return editorMode == Mode.UPDATE;
    }


    public String getDatePattern() {
        return "dd/MM/yyyy";
    }

    public Format getDateFormat() {
        return new SimpleDateFormat(getDatePattern());
    }
}
