package org.flywind.tapestry.pages.examples.t54;

import java.math.BigDecimal;

import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.ajax.AjaxResponseRenderer;

/**
 * <p>Tapestry5.4 API</p>
 * 
 * @author flywind(飞风)
 * @date 2016年6月2日
 * @网址：http://www.flywind.org
 * @QQ技术群：41138107(人数较多最好先加这个)或33106572
 * @since 1.0
 */
public class ContextTypeTest {

	@Property
    private int anInt,anInt2;
    
    @Property
    private Long aLong,aLong2;
    
    @Property
    private String aString,aString2,aString3;
    
    @Property
    private double aDouble,aDouble2;
    
    @Property
    private BigDecimal aBigDecimal,aBigDecimal2;
    
    @Property
    private boolean aBoolean,aBoolean2;
    
    @InjectComponent
    private Zone myZone;
    
    @Inject
    private Request request;
    
    @Inject
    private AjaxResponseRenderer ajaxResponseRenderer;
    
    public void setupRender()  {
        anInt = 2;
        aLong = new Long(5);
        aString = "7";
        aDouble = 7.829;
        aBigDecimal = new BigDecimal("1.2");
        aBoolean = true;
    }
    
    //下面方法是pagelink context的传递
    /*void onActivate(int intParam, Long longParam, String stringParam, double doubleParam, BigDecimal bigDecimalParam,
            boolean booleanParam, String mode2Param) {
        this.anInt2 = intParam;
        this.aLong2 = longParam;
        this.aString2 = stringParam;
        this.aDouble2 = doubleParam;
        this.aBigDecimal = bigDecimalParam;
        this.aBoolean2 = booleanParam;
        this.aString3 = mode2Param;
    }*/
    
    void onShowMessage(int intParam, Long longParam, String stringParam, double doubleParam, BigDecimal bigDecimalParam,
            boolean booleanParam, String mode2Param) {
        this.anInt2 = intParam;
        this.aLong2 = longParam;
        this.aString2 = stringParam;
        this.aDouble2 = doubleParam;
        this.aBigDecimal2 = bigDecimalParam;
        this.aBoolean2 = booleanParam;
        this.aString3 = mode2Param;
        if (request.isXHR()) {
            ajaxResponseRenderer.addRender(myZone);
        }
    }
    
   
}
