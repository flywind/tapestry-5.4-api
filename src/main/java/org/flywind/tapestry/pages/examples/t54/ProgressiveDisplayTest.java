package org.flywind.tapestry.pages.examples.t54;

import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 * <p>
 * Tapestry5.4 API
 * </p>
 * 
 * @author flywind(飞风)
 * @date 2016年6月7日
 * @网址：http://www.flywind.org
 * @QQ技术群：41138107(人数较多最好先加这个)或33106572
 * @since 1.0
 */
public class ProgressiveDisplayTest {
	
	@Property
	private int sleepTimeMillis;

	@Inject
	private Block resultSixBlock;

	public void onProgressiveDisplayFromShowOne() {
		sleep(1000);
	}

	public void onProgressiveDisplayFromShowTwo() {
		sleep(2000);
	}

	public void onProgressiveDisplayFromShowThree(int sleepTimeMillis) {
		this.sleepTimeMillis = sleepTimeMillis;
		sleep(sleepTimeMillis);
	}

	public void onProgressiveDisplayFromShowFour() {
		sleep(4000);
	}

	public void onProgressiveDisplayFromShowFive() {
		sleep(5000);
	}

	public Block onProgressiveDisplayFromShowSix() {
		sleep(6000);
		return resultSixBlock;
	}

	private void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {

		}
	}
	
}
