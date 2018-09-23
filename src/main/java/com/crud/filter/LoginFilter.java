package com.crud.filter;

import com.crud.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");

        boolean isLoginRequest = request.getRequestURI().equals("/login");
        boolean isUserPageRequest = request.getRequestURI().equals("/user");

        if (isLoginRequest) {
            filterChain.doFilter(request, response);
        } else if (user != null) {
            if ("admin".equals(user.getRole())) {
                if (isUserPageRequest) {
                    response.sendRedirect("/admin");
                } else {
                    filterChain.doFilter(request, response);
                }
            } else if ("user".equals(user.getRole())) {
                if (isUserPageRequest) {
                    filterChain.doFilter(request, response);
                } else {
                    response.sendRedirect("/user");
                }
            }
        } else {
            response.sendRedirect("/login");
        }
    }

    @Override
    public void destroy() {

    }
}