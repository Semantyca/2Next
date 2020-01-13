package com.semantyca;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.jaxrs.JAXRSArchive;

import com.semantyca.controller.WelcomePageController;

public class Main {

    public static void main(String... args) throws Exception {
        Swarm swarm = new Swarm();
        JAXRSArchive deployment = ShrinkWrap.create(JAXRSArchive.class);
        deployment.addClass(com.semantyca.entity.UiPage.class);
        deployment.addClass(com.semantyca.entity.Payload.class);
        deployment.addClass(com.semantyca.entity.UserSession.class);
        deployment.addClass(com.semantyca.entity.UserSettings.class);
        deployment.addClass(WelcomePageController.class);
        deployment.addClass(App.class);
        deployment.addAsResource(EmptyAsset.INSTANCE, "META-INF/beans.xml");
        deployment.addAllDependencies();
        swarm.start().deploy(deployment);
    }

}
