package com.recipes.web;

import com.recipes.ejb.RecipeFacade;
import com.recipes.ejb.UserFacade;
import com.recipes.entities.Recipe;
import com.recipes.entities.User;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@WebServlet(name = "CreateRecipeServlet", value = "/createRecipe")
public class CreateRecipeServlet extends HttpServlet {

    @EJB
    private RecipeFacade recipeFacade;

    @EJB
    private UserFacade userFacade;

    @Inject
    private RecipeSupport recipeSupport;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        Recipe recipe = recipeSupport.getRecipe(request);

        recipeFacade.create(recipe);

        updateLastRecipieAdded(recipe);
    }

    private void updateLastRecipieAdded(Recipe recipe) {
        User user = recipe.getUser();

        user.setLastRecipeAdded(new Date());

        userFacade.edit(user);
    }
}
