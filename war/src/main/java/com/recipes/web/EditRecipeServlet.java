package com.recipes.web;

import com.recipes.ejb.RecipeFacade;
import com.recipes.web.support.RecipeSupport;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EditRecipeServlet", value = "/editRecipe")
public class EditRecipeServlet extends HttpServlet {

    @EJB
    private RecipeFacade recipeFacade;
    @Inject
    private RecipeSupport recipeSupport;

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        recipeFacade.edit(recipeSupport.getRecipe(request));

        getServletContext().getRequestDispatcher("/listRecipes");
    }
}
