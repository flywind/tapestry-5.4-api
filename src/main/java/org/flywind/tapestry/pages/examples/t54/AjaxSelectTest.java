package org.flywind.tapestry.pages.examples.t54;

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
 * @date 2016年6月7日
 * @网址：http://www.flywind.org
 * @QQ技术群：41138107(人数较多最好先加这个)或33106572
 * @since 1.0
 */
public class AjaxSelectTest {

	static final private String[] ALL_CITY = new String[] { "梅州市", "河源市" };
	
	static final private String[] M_COUNTY = new String[] { "五华县", "梅县"};
	static final private String[] H_COUNTY = new String[] { "紫金县", "龙川县"};
	
	static final private String[] WH_TOWN = new String[] { "华阳镇", "水寨镇", "安流镇"};
	static final private String[] MX_TOWN = new String[] { "程江镇","石扇镇","城东镇","白渡镇"};
	static final private String[] ZJ_TOWN = new String[] { "苏区镇","瓦溪镇","好义镇","中坝镇"};
	static final private String[] LC_TOWN = new String[] { "老隆镇","四都镇","黄石镇","细坳镇"};
	
	static final private String[] NO_MODELS = new String[] {};

    @Property
    private String[] citys;

    @Property
    private String city;

    @Property
    private String[] countys;

    @Property
    private String county;

    @Property
    private String[] towns;

    @Property
    private String town;

    @InjectComponent
    private Zone cityZone;

    @InjectComponent
    private Zone countyZone;

    @InjectComponent
    private Zone townsZone;

    @Inject
    private Request request;

    @Inject
    private AjaxResponseRenderer ajaxResponseRenderer;

    void setupRender() {
    	
    	if(citys == null){
	        citys = ALL_CITY;
	        countys = NO_MODELS;
	        towns = NO_MODELS;
    	}
    	
    }

    void onValueChangedFromCity(String chosenCity) {

    	county = null;
    	countys = NO_MODELS;
    	
    	town = null;
    	towns = NO_MODELS;

        if(chosenCity != null){
        	if(chosenCity.equals("梅州市")){
        		countys = M_COUNTY;
        	}else if(chosenCity.equals("河源市")){
        		countys = H_COUNTY;
        	}else{
        		countys = NO_MODELS;
        	}
        }
        
        if (request.isXHR()) {
            ajaxResponseRenderer.addRender(countyZone).addRender(townsZone);
        }
        
    }

    void onValueChangedFromCounty(String chosenCounty) {
    	
    	town = null;
    	towns = NO_MODELS;
    	
    	if(chosenCounty != null){
    		if(chosenCounty.equals("五华县")){
    			towns = WH_TOWN;
    		}else if(chosenCounty.equals("梅县")){
    			towns = MX_TOWN;
    		}else if(chosenCounty.equals("紫金县")){
    			towns = ZJ_TOWN;
    		}else if(chosenCounty.equals("龙川县")){
    			towns = LC_TOWN;
    		}
    	}
    	
        if (request.isXHR()) {
            ajaxResponseRenderer.addRender(townsZone);
        }
    }
	
}
