package com.semantyca;

import static org.junit.Assert.assertNotNull;

import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wildfly.swarm.arquillian.DefaultDeployment;

import com.semantyca.entity.UiPage;

@RunWith(Arquillian.class)
@DefaultDeployment(type = DefaultDeployment.Type.JAR)
public class ITests {

    @ArquillianResource
    InitialContext context;

    @Inject
    private UiPage uiPage;

    @Test
    public void testInjection() {
        assertNotNull(CDI.current());
        assertNotNull("Bean injection has failed.", uiPage);
    }

  /*  @Test
    public void testDataSourceIsBound() throws Exception {
        DataSource ds = (DataSource) context.lookup("java:jboss/datasources/devDS");
        assertNotNull( ds );
    }*/


}
