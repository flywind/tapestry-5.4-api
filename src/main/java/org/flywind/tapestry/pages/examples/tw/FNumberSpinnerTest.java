package org.flywind.tapestry.pages.examples.tw;

import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;
@Import(stylesheet="context:assets/styles/demo.css")
public class FNumberSpinnerTest {

	@Property
	private int num = 10;
	
	@Property
	private int  mum2 = 20;
}
