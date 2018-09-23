package com.recipes.web;

import com.recipes.ejb.RecipeFacade;
import com.recipes.entities.Recipe;
import com.recipes.entities.User;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "RecipesServlet", value = "/listRecipes")
public class RecipesServlet extends HttpServlet {

    @EJB
    private RecipeFacade recipeFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");

        List<Recipe> recipeList = recipeFacade.findByUserAndMax(user.getId(), 10);
        request.setAttribute("recipeList", recipeList);

        getServletContext().getRequestDispatcher("/recipes.jsp").forward(request, response);
    }
}
