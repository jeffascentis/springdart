import hello.WebSocketConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

/**
 * Created with IntelliJ IDEA.
 * User: cmathias
 * Date: 1/8/14
 * Time: 1:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class WebApplicationInitty implements WebApplicationInitializer {

    public void onStartup(ServletContext container) {

        //Load Annotation Based Configs
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();

        // Manage the lifecycle of the root application context
        container.addListener(new ContextLoaderListener(rootContext));
        rootContext.scan("hello");
        rootContext.register(WebSocketConfig.class);

        /**
         *

         <filter>
         <filter-name>CorsFilter</filter-name>
         <filter-class>org.apache.catalina.filters.CorsFilter</filter-class>
         </filter>
         <filter-mapping>
         <filter-name>CorsFilter</filter-name>
         <url-pattern>/*</url-pattern>
         </filter-mapping>

         */

        security.SimpleCORSFilter filter = new security.SimpleCORSFilter();

        container.addFilter(
                "SimpleCORSFilter",
                filter);

        /**
         * "The container will execute the filters in the order in
         which they are defined."
         */
//        container.addFilter(
//                "springSecurityFilterChain",
//                new DelegatingFilterProxy("springSecurityFilterChain"))
//                .addMappingForUrlPatterns(null, false, "/*");


        // Create the dispatcher servlet's Spring application context
//        AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
//        dispatcherContext.scan("hello");
//        dispatcherContext.register(WebSocketConfig.class);

        // Register and map the dispatcher servlet
        ServletRegistration.Dynamic dispatcher =
                container.addServlet("dispatcher", new DispatcherServlet(rootContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
//        dispatcher.setInitParameter("spring.profiles.active", "container");

    }
}