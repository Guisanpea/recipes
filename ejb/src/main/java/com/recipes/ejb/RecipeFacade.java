/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recipes.ejb;

import com.recipes.entities.Recipe;
import com.recipes.entities.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

import static com.recipes.ejb.support.JpaResultHelper.getSingleResultOrNull;

/**
 * @author archie
 */
@Stateless
public class RecipeFacade extends AbstractFacade<Recipe> {

    @EJB
    private UserFacade userFacade;

    @PersistenceContext(unitName = "ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RecipeFacade() {
        super(Recipe.class);
    }

    public Recipe findByname(String name) {
        TypedQuery query = em.createNamedQuery("Recipe.findByName", Recipe.class);
        query.setParameter("name", name);

        return (Recipe) getSingleResultOrNull(query);
    }

    public List<Recipe> findByUserAndMax(Integer idUser, int max) {
        TypedQuery<Recipe> query = em.createNamedQuery("Recipe.findByUser", Recipe.class);
        query.setParameter("user", userFacade.find(idUser));
        query.setMaxResults(max);

        return query.getResultList();
    }
}
