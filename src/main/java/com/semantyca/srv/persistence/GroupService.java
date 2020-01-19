package com.semantyca.srv.persistence;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.semantyca.entity.SemantycaGroup;

@ApplicationScoped
public class GroupService {

    @PersistenceContext(unitName = "inmem")
    private EntityManager em;

    public List<SemantycaGroup> getAll() {
       // EntityManagerFactory inmem = Persistence.createEntityManagerFactory("inmem");
        return em.createNamedQuery("SemantycaGroup.findAll", SemantycaGroup.class).getResultList();
}

}
