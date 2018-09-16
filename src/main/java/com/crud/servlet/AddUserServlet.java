package com.crud.servlet;

import com.crud.dao.impl.UserDaoImpl;
import com.crud.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/register")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        User user = new User(name, login, password);

        try {
            UserDaoImpl userDao = new UserDaoImpl();
            userDao.save(user);

        }
        catch (ClassNotFoundException|SQLException|InstantiationException|IllegalAccessException ex) {
            ex.printStackTrace();
        }

        resp.sendRedirect("/users");
    }
}
