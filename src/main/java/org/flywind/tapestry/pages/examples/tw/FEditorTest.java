package org.flywind.tapestry.pages.examples.tw;

import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;
@Import(stylesheet="context:assets/styles/demo.css")
public class FEditorTest {
	@Property
	private String content,content2,content3;

}
