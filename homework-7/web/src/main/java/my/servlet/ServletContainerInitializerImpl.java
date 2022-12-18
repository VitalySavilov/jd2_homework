package my.servlet;

import my.DataConfig;
import my.ServiceConfig;
import my.web.WebConfig;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.Set;

public class ServletContainerInitializerImpl implements ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        AnnotationConfigWebApplicationContext context =
                new AnnotationConfigWebApplicationContext();
        context.register(
                WebConfig.class,
                ServiceConfig.class,
                DataConfig.class);

        DispatcherServlet dispatcherServlet =
                new DispatcherServlet(context);

        final ServletRegistration.Dynamic servletRegistration =
                ctx.addServlet("dispatcherServlet", dispatcherServlet);
        servletRegistration.setLoadOnStartup(1);
        servletRegistration.addMapping("/");
    }
}
