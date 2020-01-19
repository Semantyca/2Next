package com.semantyca.srv.tests;

import static org.junit.Assert.assertNotNull;

import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.naming.InitialContext;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.semantyca.srv.endpoints.DefaultEndpoint;
import com.semantyca.transport.SeMessage;

@RunWith(Arquillian.class)
public class ITests {

    @ArquillianResource
    InitialContext context;

    @Inject
    private SeMessage msg;

    @Deployment(testable = true)
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(DefaultEndpoint.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void testInjection() {
        assertNotNull(CDI.current());
        assertNotNull("Bean injection has failed.", msg);
    }

  /*  @Test
    public void testDataSourceIsBound() throws Exception {
        DataSource ds = (DataSource) context.lookup("java:jboss/datasources/devDS");
        assertNotNull( ds );
    }*/


}
