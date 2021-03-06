package com.recipes.web;

import com.recipes.ejb.UserFacade;
import com.recipes.entities.User;
import org.jasypt.util.password.BasicPasswordEncryptor;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    private static final BasicPasswordEncryptor ENCRYPTOR = new BasicPasswordEncryptor();
    @EJB
    private UserFacade userFacade;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(redirection(request));
    }

    private String redirection(HttpServletRequest request) {
        return Optional.ofNullable(userFacade.findByUsername(request.getParameter("username")))
                .filter(user -> correctPassword(user, request.getParameter("password")))
                .map(user -> {
                    manageSession(request, user);
                    return "listRecipes";
                })
                .orElseGet(() -> {
                    request.setAttribute("incorrectLogin", true);
                    return "login.jsp";
                });
    }

    private void manageSession(HttpServletRequest request, User user) {
        HttpSession session = request.getSession(true);

        if (session.isNew())
            session.setAttribute("user", user);
    }

    private boolean correctPassword(User user, String password) {
        return ENCRYPTOR.checkPassword(password, user.getHashedPassword());
    }
}
