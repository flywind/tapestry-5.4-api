package org.flywind.tapestry.pages;


import org.apache.tapestry5.Block;
import org.apache.tapestry5.EventContext;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Log;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.services.HttpError;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;
import org.slf4j.Logger;

import java.util.Date;

/**
 * Start page of application tapestry541.
 */
public class Index
{
  @Inject
  private Logger logger;

  @Inject
  private AjaxResponseRenderer ajaxResponseRenderer;

  @Property
  @Inject
  @Symbol(SymbolConstants.TAPESTRY_VERSION)
  private String tapestryVersion;

  @InjectPage
  private About about;

  @Inject
  private Block block;


  // Handle call with an unwanted context
  Object onActivate(EventContext eventContext)
  {
    return eventContext.getCount() > 0 ?
        new HttpError(404, "Resource not found") :
        null;
  }


  Object onActionFromLearnMore()
  {
    about.setLearn("LearnMore");

    return about;
  }

  @Log
  void onComplete()
  {
    logger.info("Complete call on Index page");
  }

  @Log
  void onAjax()
  {
    logger.info("Ajax call on Index page");

    ajaxResponseRenderer.addRender("middlezone", block);
  }

  public void onDelete(EventContext ec){
	  int num = ec.getCount();
	  int a = 0;
	  String b = "",c = "";
	  if(num > 0){
		 a = (int) ec.get(Integer.class, 0);
	  }
	  
	  if(num > 1){
		 b = ec.get(String.class, 1);
	  }
	  
	  if(num > 2){
		  c = ec.get(String.class, 2);
	  }
	  
	  System.out.println(a);
  }

  public Date getCurrentTime()
  {
    return new Date();
  }
}
