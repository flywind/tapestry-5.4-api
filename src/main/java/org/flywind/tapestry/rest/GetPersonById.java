package org.flywind.tapestry.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.flywind.tapestry.business.example.ExampleService;
import org.flywind.tapestry.entities.example.Example;

@Path("/personbject")
public class GetPersonById {
	
	@Inject
	private ExampleService personService;

	@GET
	@Path("{id}")
	//@Produces({"application/xml", "application/json"})//优先返回XML
	@Produces({"application/json"})//返回JSONObject
	public Example getPersonById(@PathParam("id") Long id)
	{
		return (personService.getById(id));
	}
	
}
