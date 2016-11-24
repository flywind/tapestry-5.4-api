package org.flywind.tapestry.pages.examples.t54;

import org.apache.tapestry5.Asset;
import org.apache.tapestry5.annotations.Path;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

/**
 * <p>Tapestry5.4 API</p>
 * 
 * @author flywind(飞风)
 * @date 2016年6月6日
 * @网址：http://www.flywind.org
 * @QQ技术群：41138107(人数较多最好先加这个)或33106572
 * @since 1.0
 */
public class SwitchStyleTest {

	private int styleNum;

    @Inject
    @Path("context:styles/cs1.css")
    private Asset stylesheet0;

    @Inject
    @Path("context:styles/cs2.css")
    private Asset stylesheet1;

    @Inject
    private JavaScriptSupport javaScriptSupport;

    void onActivate(int styleNum) {
        this.styleNum = styleNum;
    }

    int onPassivate() {
        return styleNum;
    }
    
    void setupRender() {
        javaScriptSupport.importStylesheet(getStylesheet());
    }

    void onChange() {
        styleNum = (styleNum + 1) % 2;
    }

    public Asset getStylesheet() {
        switch (styleNum) {
        case 0:
            return stylesheet0;
        case 1:
            return stylesheet1;
        default:
            return stylesheet0;
        }
    }
}
