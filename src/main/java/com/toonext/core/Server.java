package com.toonext.core;

import com.toonext.EnvConst;
import com.toonext.core.resources.LanguageResource;
import com.toonext.core.task.DatabaseInitializer;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.postgres.PostgresPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;


public class Server extends Application<ServerConfiguration> {

    public static void main(String[] args) throws Exception {
        new Server().run(args);
    }

    @Override
    public String getName() {
        return EnvConst.FRAMEWORK_NAME;
    }

    @Override
    public void initialize(Bootstrap<ServerConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/assets/", "/", "index.html"));
    }

    @Override
    public void run(ServerConfiguration config, Environment environment) {
        environment.jersey().setUrlPattern("/api/*");
        DataSourceFactory sourceFactory = config.getDataSourceFactory();
        Jdbi jdbi = Jdbi.create(sourceFactory.getUrl(),sourceFactory.getUser(),sourceFactory.getPassword());
        jdbi.installPlugin(new PostgresPlugin());
        jdbi.installPlugin(new SqlObjectPlugin());
        environment.jersey().register(new LanguageResource(jdbi));
        environment.admin().addTask(new DatabaseInitializer(jdbi));
    }



}
