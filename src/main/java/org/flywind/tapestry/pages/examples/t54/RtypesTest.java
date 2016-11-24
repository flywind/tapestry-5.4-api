package org.flywind.tapestry.pages.examples.t54;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.tapestry5.Link;
import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.StreamResponse;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.HttpError;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.apache.tapestry5.services.Response;

/**
 * <p>Tapestry5.4 API</p>
 * 
 * @author flywind(飞风)
 * @date 2016年6月6日
 * @网址：http://www.flywind.org
 * @QQ技术群：41138107(人数较多最好先加这个)或33106572
 * @since 1.0
 */
public class RtypesTest {

	@Property
    @Persist(PersistenceConstants.FLASH)
    private String message;

    void onReturnVoid() {
        message = "No return, that is to return the current page";
    }

    Object onReturnNull() {
        message = "Return this page";
        return null;
    }

    boolean onReturnFalse() {
        message = "Return this page";
        return false;
    }

    boolean onReturnTrue() {
        message = "Returns the current page, canceling event bubbling";
        return true;
    }

    Class<RtypesTest> onReturnClass() {
        return RtypesTest.class;
    }

    String onReturnString() {
        return "examples/show/ReturnTypesString";
    }

    @InjectPage
    private RtypesTest rtypesTest;

    Object onReturnPageInstance() {
    	//rtypesTest.set("Hello");//带值传递
        return rtypesTest;
    }

    @Inject
    private PageRenderLinkSource pageRenderLinkSource;

    Link onReturnLink() {
        String parameters = "Hello";
        Link link = pageRenderLinkSource.createPageRenderLinkWithContext(RtypesTest.class, parameters);
        return link;
    }

    StreamResponse onReturnStreamResponse() {
        return new StreamResponse() {
            InputStream inputStream;

            @Override
            public void prepareResponse(Response response) {
                ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                inputStream = classLoader.getResourceAsStream("TypeText.txt");

                try {
                    response.setHeader("Content-Length", "" + inputStream.available());
                }
                catch (IOException e) {
                    
                }
            }

            @Override
            public String getContentType() {
                return "text/plain";
            }

            @Override
            public InputStream getStream() throws IOException {
                return inputStream;
            }
        };
    }

    URL onReturnURL() throws MalformedURLException {
        return new URL("http://www.flywind.org");
    }

    HttpError onReturnHttpError() {
        return new HttpError(404, "404 not page.");
    }
}
