package com.crud.servlet;

import com.crud.dao.abstraction.UserDao;
import com.crud.dao.impl.UserDaoImpl;
import com.crud.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/users")
public class UserPageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            UserDaoImpl userDao = new UserDaoImpl();
            List<User> users = userDao.getAll();
            req.setAttribute("users", users);
        }
        catch (ClassNotFoundException|SQLException |InstantiationException|IllegalAccessException ex) {
            ex.printStackTrace();
        }

        resp.setContentType("text/html");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/user.jsp");
        dispatcher.forward(req, resp);
    }
}
