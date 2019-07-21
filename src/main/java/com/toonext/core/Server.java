package com.toonext.core;

import com.toonext.EnvConst;
import com.toonext.core.cli.Console;
import com.toonext.core.resources.LanguageResource;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;


public class Server extends Application<ServerConfiguration> {


    public static void main(String[] args) throws Exception {
        new Server().run(args);
        Thread thread = new Thread(new Console());
        thread.setPriority(Thread.MIN_PRIORITY);
        thread.start();
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

        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, config.getDataSourceFactory(), "postgresql");
        environment.jersey().register(new LanguageResource(jdbi));

       // final LanguageDAO dao = database.onDemand(LanguageDAO.class);


    }

}
