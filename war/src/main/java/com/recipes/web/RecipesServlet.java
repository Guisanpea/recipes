package com.recipes.web;

import com.recipes.ejb.RecipeFacade;
import com.recipes.entities.Recipe;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RecipesServlet", value = "/listRecipes")
public class RecipesServlet extends HttpServlet {

    @EJB
    private RecipeFacade recipeFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("recipes", recipeFacade.findByUserAndMax(Integer.valueOf(request.getParameter("idUser")), 10));

        getServletContext().getRequestDispatcher("/recipes.jsp").forward(request, response);
    }
}
