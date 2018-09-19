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

@WebServlet("/admin/edit")
public class EditUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String role = req.getParameter("role");

        UserService userService = new UserServiceImpl();
        userService.update(new User(id, name, login, password, role));

        resp.sendRedirect("/admin");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));

        UserService userService = new UserServiceImpl();
        User user = userService.get(id);

        req.setAttribute("update",1);
        req.setAttribute("user",user);

        req.getRequestDispatcher("/admin").forward(req, resp);
    }
}
