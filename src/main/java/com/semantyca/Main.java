package com.semantyca;

import java.util.Base64;

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
        System.out.println(new String(Base64.getDecoder().decode("ICAgICAgICAgICAgICBfX19fXyBfX19fX19fXyAgX19fX19fICAgIF8gICBfX19fX19fX19fICBfX19fX19fX19fXyAKICAgICAgICAgICAgIC8gX19fLy8gX19fXy8gIHwvICAvICAgfCAgLyB8IC8gL18gIF9fL1wgXC8gLyBfX19fLyAgIHwKICAgICAgICAgICAgIFxfXyBcLyBfXy8gLyAvfF8vIC8gL3wgfCAvICB8LyAvIC8gLyAgICBcICAvIC8gICAvIC98IHwKICAgICAgICAgICAgX19fLyAvIC9fX18vIC8gIC8gLyBfX18gfC8gL3wgIC8gLyAvICAgICAvIC8gL19fXy8gX19fIHwKICAgICAgICAgICAvX19fXy9fX19fXy9fLyAgL18vXy8gIHxfL18vIHxfLyAvXy8gICAgIC9fL1xfX19fL18vICB8X3wKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgX19fICAgIF9fX18gIF9fX18KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAvICAgfCAgLyBfXyBcLyAgXy8KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIC8gL3wgfCAvIC9fLyAvLyAvICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgLyBfX18gfC8gX19fXy8vIC8gICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAvXy8gIHxfL18vICAgL19fXy8gICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAK")));
    }

}
