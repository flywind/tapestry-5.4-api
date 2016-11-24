package org.flywind.tapestry.pages.examples.tw;

import java.util.List;

import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.flywind.tapestry.base.AppBase;
import org.flywind.tapestry.business.example.ExampleService;
import org.flywind.tapestry.entities.example.Example;

public class FTreegridTest extends AppBase {

	@Property
	private List<Example> examples;
	
	@Property
	@Persist
	private Example example;
	
	@Inject
	private ExampleService exampleService;
	

}
