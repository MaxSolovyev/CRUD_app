package com.crud.servlet;

import com.crud.dao.abstraction.UserDao;
import com.crud.dao.impl.UserDaoImpl;
import com.crud.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/edit")
public class EditUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        try {
            UserDao userDao = new UserDaoImpl();
            User user = new User(id, name, login, password);
            userDao.update(user);
        }
        catch (ClassNotFoundException|SQLException |InstantiationException|IllegalAccessException ex) {
            ex.printStackTrace();
        }


        resp.sendRedirect("/users");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));

        try {
            UserDao userDao = new UserDaoImpl();
            User user = userDao.get(id);

            req.setAttribute("update",1);
            req.setAttribute("user",user);
        }
        catch (ClassNotFoundException|SQLException |InstantiationException|IllegalAccessException ex) {
            ex.printStackTrace();
        }

        req.getRequestDispatcher("/users").forward(req, resp);

//        resp.sendRedirect("/users");
    }
}
