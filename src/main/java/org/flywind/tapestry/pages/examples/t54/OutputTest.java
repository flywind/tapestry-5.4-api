package org.flywind.tapestry.pages.examples.t54;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.tapestry5.annotations.Property;

/**
 * <p>Tapestry5.4 API</p>
 * 
 * @author flywind(飞风)
 * @date 2016年6月2日
 * @网址：http://www.flywind.org
 * @QQ技术群：41138107(人数较多最好先加这个)或33106572
 * @since 1.0
 */
public class OutputTest {

	/**
	 * Get方式输出
	 * **/
	public String getSiteInfo(){
		return "Hello, Welcome to flywind.org";
	}
	public Date getMytime(){
		return new Date();
	}
	public Format getTodayFormat(){
		return new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
	}
	
	/**
	 * setupRender方式输出
	 * **/
	@Property
	private String msg,html;
	
	public void setupRender(){
		msg = "Show some thing";
		html = "<div id='me'>Outputraw<br/> <a style='color:green'>output</a> html elements</div>";
	}
}
