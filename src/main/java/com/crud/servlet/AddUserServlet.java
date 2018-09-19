package com.crud.servlet;

import com.crud.model.User;
import com.crud.service.abstraction.UserService;
import com.crud.service.impl.UserServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/register")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String role = req.getParameter("role");

        UserService userService = new UserServiceImpl();
        userService.save(new User(name, login, password, role));

        resp.sendRedirect("/admin");
    }
}
