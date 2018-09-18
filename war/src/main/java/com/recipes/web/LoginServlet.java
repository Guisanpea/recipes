package com.recipes.web;

import com.recipes.ejb.UserFacade;
import com.recipes.entities.User;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.util.password.BasicPasswordEncryptor;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    @EJB
    private UserFacade userFacade;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = userFacade.findByUsername(username);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(redirection(request, password, user));
        dispatcher.forward(request, response);
    }

    private String redirection(HttpServletRequest request, String password, User user) {
        if (isNull(user) || incorrectPassword(user, password)) {
            request.setAttribute("incorrectLogin", true);
            return "/login.jsp";
        }
        return "/recipeList";
    }

    private boolean incorrectPassword(User user, String password) {
        BasicPasswordEncryptor encryptor = new BasicPasswordEncryptor();

        return encryptor.checkPassword(user.getHashedPassword(), password);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
