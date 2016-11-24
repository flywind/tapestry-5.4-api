package org.flywind.tapestry.services;

import java.util.Collections;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>Tapestry5.4 API</p>
 * 
 * @author flywind(飞风)
 * @date 2016年6月7日
 * @网址：http://www.flywind.org
 * @QQ技术群：41138107(人数较多最好先加这个)或33106572
 * @since 1.0
 */
public class CountryNames {
    
    private Set<String> countryNames = new TreeSet<String>();

    public CountryNames() {
        Locale[] availableLocales = Locale.getAvailableLocales();

        for (Locale locale : availableLocales) {
            if (StringUtils.isNoneEmpty(locale.getDisplayCountry())) {
                countryNames.add(locale.getDisplayCountry().toUpperCase());
            }
        }
    }

    public Set<String> getSet() {
        return Collections.unmodifiableSet(countryNames);
    }
    
}