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

@WebServlet("/delete")
public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));


        try {
            UserDao userDao = new UserDaoImpl();
            userDao.delete(new User(id));
        }
        catch (ClassNotFoundException|SQLException |InstantiationException|IllegalAccessException ex) {
            ex.printStackTrace();
        }

        resp.sendRedirect("/users");
    }
}
