package org.flywind.tapestry.pages.examples.t54;

import org.apache.tapestry5.PersistenceConstants;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.alerts.Duration;
import org.apache.tapestry5.alerts.Severity;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 * <p>Tapestry5.4 API</p>
 * 
 * @author flywind(飞风)
 * @date 2016年6月6日
 * @网址：http://www.flywind.org
 * @QQ技术群：41138107(人数较多最好先加这个)或33106572
 * @since 1.0
 */
public class AlertsTest {

	@Property
    @Persist(PersistenceConstants.FLASH)
    private Duration duration;
    
    @Property
    @Persist(PersistenceConstants.FLASH)
    private Severity severity;
    
    @Property
    @Persist(PersistenceConstants.FLASH)
    private int quantity;
    
    @Property
    @Persist(PersistenceConstants.FLASH)
    private Boolean showDismissAll;

    @Inject
    private AlertManager alertManager;

    void setupRender() {
        if (duration == null) {
            duration = Duration.SINGLE;
        }
        if (severity == null) {
            severity = Severity.INFO;
        }
        if (quantity == 0) {
            quantity = 1;
        }
        if (showDismissAll == null) {
            showDismissAll = false;
        }
    }

    void onValidateFromForm() {
        for (int i = 0; i < quantity; i++) {
            alertManager.alert(duration, severity, "Here is a " + duration + ", " + severity + " alert.");
        }
    }
}
