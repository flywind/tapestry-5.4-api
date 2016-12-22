package org.flywind.tapestry.services;

import java.io.IOException;

import org.apache.tapestry5.*;
import org.apache.tapestry5.annotations.Path;
import org.apache.tapestry5.beanvalidator.BeanValidatorConfigurer;
import org.apache.tapestry5.beanvalidator.BeanValidatorSource;
import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.Resource;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Local;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.ioc.services.ApplicationDefaults;
import org.apache.tapestry5.ioc.services.SymbolProvider;
import org.apache.tapestry5.services.*;
import org.apache.tapestry5.services.javascript.JavaScriptModuleConfiguration;
import org.apache.tapestry5.services.javascript.ModuleManager;
import org.slf4j.Logger;

/**
 * This module is automatically included as part of the Tapestry IoC Registry, it's a good place to
 * configure and extend Tapestry, or to place your own service definitions.
 */
public class AppModule
{
	
	public static final String PLUGINS_PATH = "plugins.path";
	
    public static void bind(ServiceBinder binder)
    {
    	binder.bind(CountryNames.class);
    }

    public static void contributeFactoryDefaults(
        MappedConfiguration<String, Object> configuration)
    {
        // The values defined here (as factory default overrides) are themselves
        // overridden with application defaults by DevelopmentModule and QaModule.

        // The application version is primarily useful as it appears in
        // any exception reports (HTML or textual).
        configuration.override(SymbolConstants.APPLICATION_VERSION, "1.0");

        // This is something that should be removed when going to production, but is useful
        // in the early stages of development.
        configuration.override(SymbolConstants.PRODUCTION_MODE, false);
    }

    public static void contributeApplicationDefaults(
        MappedConfiguration<String, Object> configuration)
    {
        // Contributions to ApplicationDefaults will override any contributions to
        // FactoryDefaults (with the same key). Here we're restricting the supported
        // locales to just "en" (English). As you add localised message catalogs and other assets,
        // you can extend this list of locales (it's a comma separated series of locale names;
        // the first locale name is the default when there's no reasonable match).
        configuration.add(SymbolConstants.SUPPORTED_LOCALES, "en,zh_CN");

              // You should change the passphrase immediately; the HMAC passphrase is used to secure
        // the hidden field data stored in forms to encrypt and digitally sign client-side data.
        configuration.add(SymbolConstants.HMAC_PASSPHRASE, "change this immediately");
        //configuration.add(SymbolConstants.BOOTSTRAP_ROOT, "classpath:");
        
        configuration.add(SymbolConstants.COMBINE_SCRIPTS, true);
    	
    	configuration.add(SymbolConstants.COMPRESS_WHITESPACE, true);
        
    	configuration.add(SymbolConstants.GZIP_COMPRESSION_ENABLED, true);
    	
    	configuration.add(SymbolConstants.ASSET_URL_FULL_QUALIFIED, true);
    	
    	configuration.add(SymbolConstants.ENABLE_PAGELOADING_MASK, false);
    	
    	configuration.add(SymbolConstants.ASSET_PATH_PREFIX, "assets");
        
        configuration.add(SymbolConstants.COMPACT_JSON, "true");
        
        configuration.add(PLUGINS_PATH, "classpath:/META-INF/modules/plugins");
    }

	/**
	 * Use annotation or method naming convention: <code>contributeApplicationDefaults</code>
	 */
	@Contribute(SymbolProvider.class)
	@ApplicationDefaults
	public static void setupEnvironment(MappedConfiguration<String, Object> configuration)
	{
        // Support for jQuery is new in Tapestry 5.4 and will become the only supported
        // option in 5.5.
		//configuration.add(SymbolConstants.JAVASCRIPT_INFRASTRUCTURE_PROVIDER, "jquery");
		configuration.add(SymbolConstants.BOOTSTRAP_ROOT, "context:mybootstrap");
		//configuration.add(SymbolConstants.MINIFICATION_ENABLED, true);
	}
	
	/**
     * 贡献rest easy组件，webservice目录
     * @param configuration
     */
    public static void contributeResteasyPackageManager(Configuration<String> configuration)
    {
    	configuration.add("org.flywind.cms.rest");
    }
    
    /**
     * 贡献组件静态资源目录，组件开发时未贡献的目录在项目中无法调用
     * @param configuration
     */
    public static void contributeClasspathAssetAliasManager(
			MappedConfiguration<String, String> configuration) {
		//定义modules资源路径,如果是modules也可以不定义，默认会是modules这个文件夹
		configuration.add("modules-root", "META-INF/modules");
	}
    
    /**
     * requireJS shim---注册jQuery模块
     * @param configuration
     * @param fastclick 组件
     * @param placeholder 组件
     * @param slimscroll 组件
     * @param app 程序默认模块
     */
    @Contribute(ModuleManager.class)
	public static void setupComponentsShims(
			MappedConfiguration<String, Object> configuration,
			@Inject @Path("${plugins.path}/validate/jquery.validate.js") Resource jvalidate) {
    	
    	//后台module
		configuration.add("plugin/validate",new JavaScriptModuleConfiguration(jvalidate).dependsOn("jquery"));
	} 

	/**
     * decorateRequestExceptionHandler(覆盖T5异常页面)
     * TODO(重新定义T5异常页面，在产品模式下生效)
     * @Exception MyExceptionReport
     */
     public RequestExceptionHandler decorateRequestExceptionHandler(final Object delegate, final ResponseRenderer renderer,
         final ComponentSource componentSource, @Symbol(SymbolConstants.PRODUCTION_MODE) final boolean productionMode, Object service) {

         return new RequestExceptionHandler() {
             public void handleRequestException(final Throwable exception) throws IOException {
                 if (productionMode) {
                     String exceptionPage = "MyExceptionTest";
                     final ExceptionReporter errorPage = (ExceptionReporter) componentSource.getPage(exceptionPage);
                     errorPage.reportException(exception);
                     renderer.renderPageMarkupResponse(exceptionPage);
                 } else {
                     ((RequestExceptionHandler) delegate).handleRequestException(exception);
                 }
             }
         };

     }

    @Contribute(BeanValidatorSource.class)
    public static void provideBeanValidatorConfigurer(OrderedConfiguration<BeanValidatorConfigurer> configuration)
    {
       configuration.add("MyConfigurer", new BeanValidatorConfigurer()
       {
          public void configure(javax.validation.Configuration<?> configuration)
          {
             configuration.ignoreXmlConfiguration();
          }
       });
    }
	
    /**
     * This is a service definition, the service will be named "TimingFilter". The interface,
     * RequestFilter, is used within the RequestHandler service pipeline, which is built from the
     * RequestHandler service configuration. Tapestry IoC is responsible for passing in an
     * appropriate Logger instance. Requests for static resources are handled at a higher level, so
     * this filter will only be invoked for Tapestry related requests.
     *
     *
     * Service builder methods are useful when the implementation is inline as an inner class
     * (as here) or require some other kind of special initialization. In most cases,
     * use the static bind() method instead.
     *
     *
     * If this method was named "build", then the service id would be taken from the
     * service interface and would be "RequestFilter".  Since Tapestry already defines
     * a service named "RequestFilter" we use an explicit service id that we can reference
     * inside the contribution method.
     */
    public RequestFilter buildTimingFilter(final Logger log)
    {
        return new RequestFilter()
        {
            public boolean service(Request request, Response response, RequestHandler handler)
            throws IOException
            {
                long startTime = System.currentTimeMillis();

                try
                {
                    // The responsibility of a filter is to invoke the corresponding method
                    // in the handler. When you chain multiple filters together, each filter
                    // received a handler that is a bridge to the next filter.

                    return handler.service(request, response);
                } finally
                {
                    long elapsed = System.currentTimeMillis() - startTime;

                    log.info("Request time: {} ms", elapsed);
                }
            }
        };
    }

    /**
     * This is a contribution to the RequestHandler service configuration. This is how we extend
     * Tapestry using the timing filter. A common use for this kind of filter is transaction
     * management or security. The @Local annotation selects the desired service by type, but only
     * from the same module.  Without @Local, there would be an error due to the other service(s)
     * that implement RequestFilter (defined in other modules).
     */
    @Contribute(RequestHandler.class)
    public void addTimingFilter(OrderedConfiguration<RequestFilter> configuration,
     @Local
     RequestFilter filter)
    {
        // Each contribution to an ordered configuration has a name, When necessary, you may
        // set constraints to precisely control the invocation order of the contributed filter
        // within the pipeline.

        configuration.add("Timing", filter);
    }
}
