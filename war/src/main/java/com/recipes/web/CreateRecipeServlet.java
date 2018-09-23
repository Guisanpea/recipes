package com.recipes.web;

import com.recipes.ejb.RecipeFacade;
import com.recipes.ejb.UserFacade;
import com.recipes.entities.Recipe;
import com.recipes.entities.User;
import com.recipes.web.support.RecipeSupport;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Recipe recipe = recipeSupport.getRecipe(request);

        recipeFacade.create(recipe);

        updateLastRecipieAdded(recipe);

        getServletContext().getRequestDispatcher("/listRecipes").forward(request, response);
    }

    private void updateLastRecipieAdded(Recipe recipe) {
        User user = recipe.getUserId();

        user.setLastRecipeAdded(new Date());

        userFacade.edit(user);
    }
}
