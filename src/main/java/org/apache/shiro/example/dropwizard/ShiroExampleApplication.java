package org.apache.shiro.example.dropwizard;

import com.codahale.metrics.health.HealthCheck;
import io.dropwizard.Application;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.dropwizard.jetty.setup.ServletEnvironment;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.apache.shiro.example.dropwizard.dao.DefaultStormtrooperDao;
import org.apache.shiro.example.dropwizard.dao.DefaultTieCraftDao;
import org.apache.shiro.example.dropwizard.dao.StormtrooperDao;
import org.apache.shiro.example.dropwizard.dao.TieCraftDao;
import org.apache.shiro.web.env.EnvironmentLoaderListener;
import org.apache.shiro.web.jaxrs.ShiroFeature;
import org.apache.shiro.web.servlet.ShiroFilter;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class ShiroExampleApplication extends Application<ShiroExampleConfiguration> {

    public static void main(final String[] args) throws Exception {
        new ShiroExampleApplication().run(args);
    }

    @Override
    public String getName() {
        return "shiro-example";
    }

    @Override
    public void initialize(Bootstrap<ShiroExampleConfiguration> bootstrap) {
        // look up config yaml on the classpath
        bootstrap.setConfigurationSourceProvider(new ResourceConfigurationSourceProvider());
    }

    @Override
    public void run(final ShiroExampleConfiguration configuration,  final Environment environment) {
        // example health check
        environment.healthChecks().register("example", new HealthCheck() {
            @Override
            protected HealthCheck.Result check() {
                // Everything is in memory, so we are always healthy ;)
                return Result.healthy();
            }
        });

        configureShiro(environment);
        configureJersey(environment.jersey());
    }

    private void configureShiro(final Environment environment) {

        // One line to enable Shiro
        environment.jersey().register(ShiroFeature.class); // JAX-RS Feature

        // Dropwizard does not load servlet fragments, so we must configure the servlet filter
        environment.servlets().addServletListeners(new EnvironmentLoaderListener());
        environment.servlets().addFilter("ShiroFilter", ShiroFilter.class)
                .addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    }

    private void configureJersey(JerseyEnvironment jersey) {

        // Load any resource in the resources package
        jersey.packages(getClass().getPackage().getName() + ".resources");

        // use @Inject to bind the DAOs
        jersey.register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(new DefaultStormtrooperDao()).to(StormtrooperDao.class);
                bind(new DefaultTieCraftDao()).to(TieCraftDao.class);
            }
        });
    }
}