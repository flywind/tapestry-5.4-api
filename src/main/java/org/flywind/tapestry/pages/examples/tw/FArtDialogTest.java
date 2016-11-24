package org.flywind.tapestry.pages.examples.tw;

import org.apache.tapestry5.annotations.Import;
import org.flywind.tapestry.base.AppBase;

@Import(stylesheet="context:assets/styles/demo.css")
public class FArtDialogTest extends AppBase {

	public void afterRender(){
		javaScriptSupport.require("init-fartdialogtest");
	}
}
