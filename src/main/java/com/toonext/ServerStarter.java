package com.toonext;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.toonext.api.IUser;
import com.toonext.core.health.TemplateHealthCheck;
import com.toonext.core.dao.IUserDAO;
import com.toonext.exception.JsonInformativeExceptionMapper;
import com.toonext.localization.validation.NBViolationExceptionMapper;
import com.toonext.localization.validation.ValidationMessageInterpolator;
import com.toonext.security.CustomAuthFilter;
import com.toonext.security.CustomAuthenticator;
import com.toonext.security.CustomAuthorizer;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.postgres.PostgresPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Locale;

public abstract class ServerStarter<C extends PrimaryConfiguration> extends Application<C> {
    private static boolean isDevMode = true;
    public Jdbi jdbi;
    public RestHighLevelClient elasticClient;
    public Validator validator;

    @Override
    public String getName() {
        return EnvConst.FRAMEWORK_NAME;
    }

    public void initialize(Bootstrap<C> bootstrap) {
        ObjectMapper om = bootstrap.getObjectMapper();
        om.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        om.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        om.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        om.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        om.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        om.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.NONE);
        om.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.PUBLIC_ONLY);
        om.setVisibility(PropertyAccessor.SETTER, JsonAutoDetect.Visibility.PUBLIC_ONLY);



        bootstrap.addBundle(new AssetsBundle("/assets/", "/", "index.html"));

        /*bootstrap.addBundle(new SwaggerBundle<PrimaryConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(PrimaryConfiguration configuration) {
                return configuration.getSwagger();
            }
        });*/
    }

    public void run(C config, Environment environment) {
        final TemplateHealthCheck healthCheck = new TemplateHealthCheck("health check of " + getName());
        environment.healthChecks().register("template", healthCheck);

        Validator validator = Validation.byDefaultProvider()
                .configure()
                .messageInterpolator(
                        new ValidationMessageInterpolator(Locale.getDefault())
                )
                .buildValidatorFactory()
                .getValidator();
        environment.setValidator(validator);

        JerseyEnvironment restEnv = environment.jersey();
        restEnv.setUrlPattern("/api/*");
        DataSourceFactory sourceFactory = config.getDataSourceFactory();
        jdbi = Jdbi.create(sourceFactory.getUrl(),sourceFactory.getUser(),sourceFactory.getPassword());
        jdbi.installPlugin(new PostgresPlugin());
        jdbi.installPlugin(new SqlObjectPlugin());
        elasticClient = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(config.getElastic().getHost(), config.getElastic().getPort(), "http")));


        restEnv.register(new NBViolationExceptionMapper());

        IUserDAO userDAO = jdbi.onDemand(IUserDAO.class);

        restEnv.register(new AuthDynamicFeature(
                new CustomAuthFilter.Builder<IUser>(jdbi)
                        .setAuthenticator(new CustomAuthenticator(userDAO))
                        .setAuthorizer(new CustomAuthorizer())
                        .buildAuthFilter()));


        restEnv.register(RolesAllowedDynamicFeature.class);
        restEnv.register(new AuthValueFactoryProvider.Binder<>(IUser.class));


   //     environment.jersey().register(new JsonProcessingExceptionMapper(true));
     //   environment.jersey().register(new DefaultExceptionMapper());
        restEnv.register(new JsonInformativeExceptionMapper());
        Cors.insecure(environment);

    }


    public static  boolean isDevMode() {
        return isDevMode;
    }
}
