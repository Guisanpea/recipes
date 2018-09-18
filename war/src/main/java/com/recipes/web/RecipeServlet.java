package com.recipes.web;

import com.recipes.ejb.RecipeFacade;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RecipeServlet", value = "/recipe")
public class RecipeServlet extends HttpServlet {

    @EJB
    private RecipeFacade recipeFacade;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        // NOP
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("recipe", recipeFacade.find(request.getParameter("id")));

        getServletContext().getRequestDispatcher("/recipe.jsp").forward(request, response);
    }
}
