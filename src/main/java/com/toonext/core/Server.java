package com.toonext.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.toonext.core.cli.Console;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;



public class Server extends Application<MainConfiguration> {

    public static Object compilationTime;

    private static boolean isDevMode = true;


    public static void main(String[] args) throws Exception {
        new Server().run(args);
        //if (EnvConst.CLI.equalsIgnoreCase("on") || keepCLI) {
            Thread thread = new Thread(new Console());
            thread.setPriority(Thread.MIN_PRIORITY);
            thread.start();
        /*} else {
            Server.logger.warning("CLI is disabled");
        }*/

    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<MainConfiguration> bootstrap) {
        ObjectMapper om = bootstrap.getObjectMapper();
        om.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        om.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        om.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        om.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        bootstrap.addBundle(new AssetsBundle("/assets/", "/", "index.html"));
    }

    @Override
    public void run(MainConfiguration configuration,
                    Environment environment) {
        environment.jersey().setUrlPattern("/*");

    }

}
