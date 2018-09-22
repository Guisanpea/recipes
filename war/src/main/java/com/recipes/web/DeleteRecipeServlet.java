package com.recipes.web;

import com.recipes.ejb.RecipeFacade;
import com.recipes.entities.Recipe;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteRecipeServlet", value = "/deleteRecipe")
public class DeleteRecipeServlet extends HttpServlet {

    @EJB
    private RecipeFacade recipeFacade;

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        recipeFacade.remove(Recipe.builder().id(Integer.valueOf(req.getParameter("id"))).build());

        getServletContext().getRequestDispatcher("/listRecipes").forward(req, resp);
    }
}