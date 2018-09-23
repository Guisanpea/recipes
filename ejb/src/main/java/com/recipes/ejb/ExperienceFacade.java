/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recipes.ejb;

import com.recipes.entities.Experience;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import static com.recipes.ejb.support.JpaResultHelper.getSingleResultOrNull;

/**
 *
 * @author archie
 */
@Stateless
public class ExperienceFacade extends AbstractFacade<Experience> {

    @PersistenceContext(unitName = "ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ExperienceFacade() {
        super(Experience.class);
    }

    public Experience findByName(String name) {
        Query query = em.createNamedQuery("Experience.findByName", Experience.class);
        query.setParameter("name", name);

        return (Experience) getSingleResultOrNull(query);
    }
}
