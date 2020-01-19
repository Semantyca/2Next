package com.semantyca;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.jaxrs.JAXRSArchive;

import com.semantyca.App;

public class Main {

    public static void main(String... args) throws Exception {

        Swarm swarm = new Swarm();
        JAXRSArchive deployment = ShrinkWrap.create(JAXRSArchive.class);
        deployment.addPackage("com.semantyca");
        deployment.addPackage("com.semantyca.entity");
        deployment.addPackage("com.semantyca.transport");
        deployment.addPackage("com.semantyca.traits");
        deployment.addPackage("com.semantyca.srv.endpoints");
        deployment.addPackage("com.semantyca.srv.persistence");
        deployment.addClass(App.class);
        deployment.addAsResource(EmptyAsset.INSTANCE, "META-INF/beans.xml");
        deployment.addAsResource(EmptyAsset.INSTANCE, "META-INF/persistence.xml");
        deployment.addAsResource(EmptyAsset.INSTANCE, "WEB-INF/classes/META-INF/persistence.xml");
        deployment.addAsResource(EmptyAsset.INSTANCE, "project-defaults.yaml");
        deployment.addAsResource(EmptyAsset.INSTANCE, "modules/com/h2/main/modules.xml");
        deployment.addAllDependencies();
        swarm.start().deploy(deployment);

    }

}
