package org.flywind.tapestry.pages.examples.t54.ws;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.tapestry5.StreamResponse;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Response;
import org.flywind.tapestry.business.example.ExampleService;
import org.flywind.tapestry.entities.example.Example;
import org.flywind.tapestry.entities.example.Example1;

/**
 * <p>Tapestry5.4 API</p>
 * 
 * @author flywind(飞风)
 * @date 2016年6月6日
 * @网址：http://www.flywind.org
 * @QQ技术群：41138107(人数较多最好先加这个)或33106572
 * @since 1.0
 */
public class PersonFind {

	@Inject
    private ExampleService personFinderService;

    Object onActivate(final Long personId) {

        return new StreamResponse() {
            private InputStream inputStream;

            @Override
            public void prepareResponse(Response response) {
            	Example1 person01 = findPerson(personId);

                if (person01 == null) {
                    try {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Resource not found");
                    }
                    catch (IOException e) {
                        throw new RuntimeException("Failed to redirect?", e);
                    }
                }

                else {
                    try {
                        JAXBContext context = JAXBContext.newInstance(person01.getClass());
                        Marshaller marshaller = context.createMarshaller();
                        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                        marshaller.marshal(person01, outputStream);

                        inputStream = new ByteArrayInputStream(outputStream.toByteArray());
                        
                        response.setHeader("Content-Length", "" + outputStream.size());
                    }
                    catch (JAXBException e) {
                        throw new IllegalStateException(e);
                    }
                }

            }

            @Override
            public String getContentType() {
                return "text/xml";
            }

            @Override
            public InputStream getStream() throws IOException {
                return inputStream;
            }
        };

    }

    public Example1 findPerson(Long personId) {
    	Example1 response = null;

    	Example person = personFinderService.getById(personId);

        if (person != null) {
            response = new Example1(person.getId(), person.getCompanyName(), person.getUserName(), person.getCreateTime());
        }

        return response;
    }
}
