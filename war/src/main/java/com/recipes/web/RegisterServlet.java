package com.recipes.web;

import com.recipes.ejb.UserFacade;
import com.recipes.entities.User;
import lombok.SneakyThrows;
import org.jasypt.util.password.BasicPasswordEncryptor;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {

    private static final BasicPasswordEncryptor ENCRYPTORS = new BasicPasswordEncryptor();
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    @EJB
    private UserFacade userFacade;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(redirect(request, username));
        dispatcher.forward(request, response);
    }

    private String redirect(HttpServletRequest request, String username) {
        return Optional.ofNullable(userFacade.findByUsername(username))
                .map(user -> {
                    request.setAttribute("incorrectRegister", true);
                    return "/register.jsp";
                }).orElseGet(() -> {
                    createUser(request);
                    return "/login.jsp";
                });
    }

    private void createUser(HttpServletRequest request) {
        userFacade.create(User.builder()
                .hashedPassword(ENCRYPTORS.encryptPassword(request.getParameter("password")))
                .birthdate(getBirthDate(request))
                .fullName(request.getParameter("fullName"))
                .build());
    }

    @SneakyThrows(ParseException.class)
    private Date getBirthDate(HttpServletRequest request) {
        return DATE_FORMAT.parse(request.getParameter("birthDate"));
    }

}
