package com.recipes.web;

import com.recipes.ejb.UserFacade;
import com.recipes.entities.User;
import org.apache.commons.lang3.StringUtils;
import org.jasypt.util.password.BasicPasswordEncryptor;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.util.Objects.nonNull;

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {

    @EJB
    private UserFacade userFacade;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String redirect;
        if (nonNull(userFacade.findByUsername(username))) {
            request.setAttribute("incorrectRegister", true);
            redirect = "/register.jsp";
        } else {
            createUser(request);
            redirect = "/login.jsp";
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(redirect);
        dispatcher.forward(request, response);
    }

    private void createUser(HttpServletRequest request) {
        User userToAdd = new User();
        setPassword(request, userToAdd);
        setBirthDate(request, userToAdd);
        userToAdd.setFullName(request.getParameter("fullname"));
        userFacade.create(userToAdd);
    }

    private void setBirthDate(HttpServletRequest request, User userToAdd) {
        Date birthDate = null;
        try {
            String birthString = request.getParameter("birthDate");
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            birthDate = format.parse(birthString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        userToAdd.setBirthdate(birthDate);
    }

    private void setPassword(HttpServletRequest request, User userToAdd) {
        String password = request.getParameter("password");
        BasicPasswordEncryptor encryptors = new BasicPasswordEncryptor();
        String hashedPassword = encryptors.encryptPassword(password);
        userToAdd.setHashedPassword(hashedPassword);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
          ServletException, IOException {

    }
}
