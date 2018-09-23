/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recipes.ejb;

import com.recipes.entities.Category;

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
public class CategoryFacade extends AbstractFacade<Category> {

    @PersistenceContext(unitName = "ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoryFacade() {
        super(Category.class);
    }

    public Category findByName(String name) {
        Query query = em.createNamedQuery("Category.findByName", Category.class);
        query.setParameter("name", name);

        return (Category) getSingleResultOrNull(query);
    }
}
