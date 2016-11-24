package org.flywind.tapestry.pages.examples.t54;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.flywind.tapestry.services.CountryNames;

/**
 * <p>Tapestry5.4 API</p>
 * 
 * @author flywind(飞风)
 * @date 2016年6月7日
 * @网址：http://www.flywind.org
 * @QQ技术群：41138107(人数较多最好先加这个)或33106572
 * @since 1.0
 */
public class AutocompleteTest {

	@Property
    private String countryName;

    @Inject
    private CountryNames countryNames;

    List<String> onProvideCompletionsFromCountryName(String partial) {
        List<String> matches = new ArrayList<String>();
        partial = partial.toUpperCase();

        for (String countryName : countryNames.getSet()) {
            if (countryName.contains(partial)) {
                matches.add(countryName);
            }
        }

        return matches;
    }
}
