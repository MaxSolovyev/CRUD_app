package com.crud.servlet;

import com.crud.model.User;
import com.crud.service.abstraction.UserService;
import com.crud.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        UserService userService = new UserServiceImpl();
        User user = userService.getByLogin(login);

        String destination = "/user";
        if (user!=null) {
            if (password.equals(user.getPassword())) {
                if ("admin".equals(user.getRole())) {
                    destination = "/admin";
                }
            } else {
                user = null;
            }
        }
        req.getSession().setAttribute("user", user);

        resp.sendRedirect(destination);
    }
}