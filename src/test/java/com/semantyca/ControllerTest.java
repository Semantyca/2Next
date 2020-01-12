package com.semantyca;

import static org.junit.Assert.assertNotNull;

import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wildfly.swarm.arquillian.DefaultDeployment;

import com.semantyca.service.Greeter;

@RunWith(Arquillian.class)
@DefaultDeployment(type = DefaultDeployment.Type.JAR)
public class ControllerTest {
    @Inject
    private Greeter greeter;

    @Test
    public void testInjection() {
        assertNotNull(CDI.current());
        assertNotNull("Bean injection has failed.", greeter);
    }
}
