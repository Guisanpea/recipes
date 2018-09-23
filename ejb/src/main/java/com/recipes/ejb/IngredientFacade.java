/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recipes.ejb;

import com.recipes.entities.Ingredient;

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
public class IngredientFacade extends AbstractFacade<Ingredient> {

    @PersistenceContext(unitName = "ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IngredientFacade() {
        super(Ingredient.class);
    }

    public Ingredient findByName(String name) {
        Query query = em.createNamedQuery("Ingredient.findByName", Ingredient.class);
        query.setParameter("name", name);

        return (Ingredient) getSingleResultOrNull(query);
    }
}
