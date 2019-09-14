package com.toonext;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.semantyca.admin.resources.HomePageResource;
import com.semantyca.admin.resources.LanguageResource;
import com.semantyca.admin.resources.SessionResource;
import com.semantyca.admin.resources.UserResource;
import com.toonext.core.health.TemplateHealthCheck;
import com.toonext.core.jdbi.IUserDAO;
import com.toonext.core.task.DatabaseInitializer;
import com.toonext.core.task.PopulateTestUsers;
import com.toonext.domain.user.IUser;
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
    private  Jdbi jdbi;
    private RestHighLevelClient elasticClient;

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

        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
        om.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.ANY);

        bootstrap.addBundle(new AssetsBundle("/assets/", "/", "index.html"));
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
        restEnv.register(new SessionResource(jdbi, validator));
        restEnv.register(new UserResource(jdbi));
        restEnv.register(new LanguageResource(jdbi));

        environment.admin().addTask(new DatabaseInitializer(jdbi));
        environment.admin().addTask(new PopulateTestUsers(jdbi));

        IUserDAO userDAO = jdbi.onDemand(IUserDAO.class);

        restEnv.register(new AuthDynamicFeature(
                new CustomAuthFilter.Builder<IUser>(jdbi)
                        .setAuthenticator(new CustomAuthenticator(userDAO))
                        .setAuthorizer(new CustomAuthorizer())
                        .buildAuthFilter()));


        restEnv.register(RolesAllowedDynamicFeature.class);
        restEnv.register(new AuthValueFactoryProvider.Binder<>(IUser.class));

        restEnv.register(new HomePageResource());
   //     environment.jersey().register(new JsonProcessingExceptionMapper(true));
     //   environment.jersey().register(new DefaultExceptionMapper());
        restEnv.register(new JsonInformativeExceptionMapper());
        Cors.insecure(environment);

    }

    public Jdbi getJdbi() {
        return jdbi;
    }

    public RestHighLevelClient getFTEngineClient() {
        return elasticClient;
    }

    public static  boolean isDevMode() {
        return isDevMode;
    }
}
