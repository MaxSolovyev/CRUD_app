package com.crud.servlet;

import com.crud.dao.abstraction.UserDao;
import com.crud.dao.impl.UserDaoJDBCImpl;
import com.crud.model.User;
import com.crud.service.abstraction.UserService;
import com.crud.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/delete")
public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));

        UserService userService = new UserServiceImpl();
        userService.delete(new User(id));

        resp.sendRedirect("/admin");
    }
}
