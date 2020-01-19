package com.semantyca.srv.persistence;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class GroupServiceJpaFactory {
    private EntityManager _entityManager;

    public EntityManager getEntityManager() {

        if (_entityManager != null) return _entityManager;

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("inmem");
        _entityManager = factory.createEntityManager();
        return _entityManager;
    }

}
